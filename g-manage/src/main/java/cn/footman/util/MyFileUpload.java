package cn.footman.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author footman77
 * @create 2018-12-05 10:55
 */
public class MyFileUpload {

    public static List<String> upload_image(MultipartFile[] files){

        ArrayList<String> lists = new ArrayList<>();

        for(MultipartFile file : files){
            if(!file.isEmpty()){
                String s = UUID.randomUUID().toString().replace("-", "");
                String extension = FilenameUtils.getExtension(file.getOriginalFilename());
                String name = s + "." + extension;

                String windows_path = MyUploadPath.getUploadPath("myUpload.properties", "windows_path");
                String fullName = windows_path + name;
                try {
                    file.transferTo(new File(fullName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                lists.add(name);
            }


        }
        return lists;

    }
}
