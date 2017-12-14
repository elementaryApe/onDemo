package com.udemo.utils.file;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Desc: 文件上传
 * User: hansh
 * Date: 2017/12/14
 * Time: 15:24
 */
public class FileUtil {

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
