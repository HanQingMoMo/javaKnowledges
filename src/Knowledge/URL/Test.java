package Knowledge.URL;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hw 2017.1.5
 * URL类和URLConection类使用示例，读取某网页并将其写入文件
 */
public class Test {

  public static void main(String[] args) {

    try {
      URL baseUrl = new URL("http://blog.csdn.net");
      URL url = new URL(baseUrl, "witsmakemen/article/details/6989010");

      Map<String, Object> map = new HashMap<>();

      map.put("defaultPort", url.getDefaultPort());
      map.put("fileName",  url.getFile());
      map.put("host", url.getHost());
      map.put("path", url.getPath());
      map.put("port", url.getPort());
      map.put("protocol", url.getProtocol());
      map.put("ref" ,url.getRef());
      map.put("user", url.getUserInfo());
      map.put("urlString" ,url.toString());

      map.forEach((key, value) -> {
        System.out.println(key + " : " + value);
      });


      URLConnection con = url.openConnection();
      InputStream is = con.getInputStream();
      File file = new File("/Users/hw/Documents/GitHub/javaKnowledges/conf/URL/url.html");

      if(file.exists()) {
        file.delete();
        file = new File("/Users/hw/Documents/GitHub/javaKnowledges/conf/URL/url.html");
      }

      OutputStream os = new FileOutputStream(file);

      copyFile(is, os);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //拷贝输入流到输出流
  public static void copyFile(InputStream is, OutputStream os) throws IOException {
    int tmp = 0;
    byte[] bytes = new byte[1024];

    while((tmp = is.read(bytes)) != -1) {
      os.write(bytes, 0, tmp);
    }
    os.flush();

    is.close();
    os.close();
  }

}
