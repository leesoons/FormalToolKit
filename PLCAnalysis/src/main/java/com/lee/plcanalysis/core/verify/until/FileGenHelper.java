package com.lee.plcanalysis.core.verify.until;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileGenHelper {
    public static String generateFile(String filePath, String text, String fileName){
        File myPath = new File(filePath);
        if ( !myPath.exists()){//若此目录不存在，则创建之
            myPath.mkdir();
            System.out.println("创建文件夹路径为："+ filePath);
        }
        try {
            //如果文件名的文件不存在，先创建再读写;存在的话直接追加写,关键字false表示不追加，从头开始写
            FileWriter fw = new FileWriter(filePath + "\\" + fileName,false);// filePath + "\\" + filename,true
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return filePath + "\\" + fileName;
    }

    public static void addTextToFile(String text, String fileName){
        try {
            //如果文件名的文件不存在，先创建再读写;存在的话直接追加写,关键字false表示不追加，从头开始写
            FileWriter fw = new FileWriter(fileName,true);// filePath + "\\" + filename,true
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
