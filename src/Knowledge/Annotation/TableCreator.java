package Knowledge.Annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hw
 * 注解处理器
 * 根据注解来生成创建表的SQL语句
 */

public class TableCreator {

  /**
   * 为clazz生成创建表的sql语句
   *
   * @param clazz 需要被生成sql语句的表
   * @return 创建表的sql语句
   */
  public static String createTableSQL(Class<?> clazz) {
    DBTable dbTable = clazz.getAnnotation(DBTable.class);

    if (dbTable == null) {
      System.out.println("No DBTable annotations in class " + clazz.getName());
      return null;
    }

    String tableName = dbTable.name();
    if (tableName.length() == 0) {
      tableName = clazz.getName().toUpperCase();
    }
    //该链表用于存储每一列的定义语句
    List<String> columnDefs = new ArrayList();

    Field[] fields = clazz.getDeclaredFields();

    for (Field field : fields) {
      //获得字段上的注解
      Annotation[] anns = field.getDeclaredAnnotations();
      if (anns.length == 0) {
        System.out.println(field.getName() + " is not a column.");
      } else {
        //判断注解类型，并从中提取信息
        if (anns[0] instanceof SQLString) {
          SQLString ss = (SQLString) anns[0];
          String columnName = ss.name();

          if (columnName.length() == 0) {
            columnName = field.getName().toUpperCase();
          }

          //构造sql语句
          String columnDef = columnName + " VARCHAR(" + ss.value() + ")" + getConstraints(ss.constraint());
          columnDefs.add(columnDef);
        } else if (anns[0] instanceof SQLInteger) {
          SQLInteger si = (SQLInteger) anns[0];
          String columnName = si.name();

          if (columnName.length() == 0) {
            columnName = field.getName().toUpperCase();
          }

          //构造sql语句
          String columnDef = columnName + " INT" + getConstraints(si.constraint());
          columnDefs.add(columnDef);
        }
      }
    }

    //数据库表的构造语句
    StringBuilder sql = new StringBuilder();
    sql.append("CREATE TABLE " + tableName + "(");

    for (String columnDef : columnDefs) {
      sql.append("\n     " + columnDef + ",");
    }

    //删除最后的多余的逗号
    sql = sql.deleteCharAt(sql.length() - 1);
    sql.append("\n);");

    return sql.toString();
  }

  /**
   * 获取约束
   *
   * @param con 需要被解析的约束注解
   * @return 返回约束
   */
  private static String getConstraints(Constraints con) {
    StringBuilder sb = new StringBuilder();
    if (con.primaryKey()) {
      sb.append(" PRIMARY KEY");
    }
    if (!con.allowNull()) {
      sb.append(" NOT NULL");
    }
    if (con.unique()) {
      sb.append(" UNIQUE");
    }

    return sb.toString();
  }

  public static void main(String... args) {
    String[] tables = {"Knowledge.Annotation.Member", "java.lang.String"};

    try {
      for (String table : tables) {
        Class<?> clazz = Class.forName(table);
        String sql = createTableSQL(clazz);

        if (sql != null) {
          System.out.println("Table Creation SQL for " + table + " is :\n\n" + sql + "\n");
        }
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
