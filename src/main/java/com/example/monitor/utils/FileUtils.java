package com.example.monitor.utils;

import java.io.File;
import java.io.IOException;

/**
 * Created by chenjinlong on 16/9/20.
 */
public class FileUtils {

    public static boolean createFile(String fileName) {

        File file = new File(fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("创建单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("创建单个文件" + fileName + "失败！");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("创建单个文件" + fileName + "失败！" + e.getMessage());
            return false;
        }
    }
}
