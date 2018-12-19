package cn.footman.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author footman77
 * @create 2018-12-05 11:27
 */
public class MyUploadPath {
    public static String getUploadPath(String propertiesPath,String propertiesKey){
        Properties properties = new Properties();
        InputStream fileInputStream = MyUploadPath.class.getClassLoader().getResourceAsStream(propertiesPath);
        try {

//            FileInputStream fileInputStream = new FileInputStream(propertiesPath);
            properties.load(fileInputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }

        String path = properties.getProperty(propertiesKey);
        return path;

    }

}
