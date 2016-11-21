package com.example.monitor.utils;

import java.io.*;

/**
 * Created by chenjinlong on 16/9/20.
 */
public class FileUtils {

    public static boolean createFile(String fileName) {

        File file = new File(fileName);
        try {
            if (file.createNewFile()) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void writeFile(String filePath, String content) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filePath, true)));
            out.write(content + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
//    public static void main(String args[]) {
//
////        FileUtils.createFile("d:/test.log");
////        FileUtils.writeFile("d:/test.log", "test1");
////        FileUtils.writeFile("d:/test.log", "test2");
//
//        String filePath = "D:" + File.separatorChar + "record" + File.separatorChar + LocalDate.now()
//                + File.separatorChar + "192.168.0.1".replace(".", "_")  + ".log";
//
//        String str = "192.168.0.1".replace(".", "_");
//        FileUtils.createFile(filePath);
//        System.out.println(filePath);
//    }
}
