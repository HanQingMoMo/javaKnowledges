package Knowledge.Annotation;

/**
 * @author hw
 * 描述数据库中的member表
 */

@DBTable(name = "MEMBER")
public class Member {

  @SQLString(name = "ID", value = 10, constraint = @Constraints(primaryKey = true, unique = true))
  private String id;

  @SQLString(name = "NAME", value = 20)
  private String name;

  @SQLInteger(name = "AGE")
  private int age;

  @SQLString(name = "DESCRIPTION", value = 100, constraint = @Constraints(allowNull = true))
  private String description;

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public String getDescription() {
    return description;
  }

  public String getId() {
    return id;
  }
}
