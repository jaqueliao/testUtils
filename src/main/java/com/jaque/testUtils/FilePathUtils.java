package com.jaque.testUtils;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FilePathUtils {
    public static File getRandomFile(String pathStr,String ext){
        File path = new File(pathStr);
        if(!path.isDirectory()){
            throw new ValueException("参数错误,"+pathStr+"非文件夹");
        }
        List<File> targetFileList = new ArrayList<>();
        for (File file:path.listFiles()){
            if(file.getName().toLowerCase().endsWith(ext.toLowerCase())){
                targetFileList.add(file);
            }
        }
        if(targetFileList.size()==0){
            throw new ValueException("参数错误,"+pathStr+"非文件夹");
        }
        return targetFileList.get(new Random().nextInt(targetFileList.size()));
    }

    public static void main(String[] args) {
        getRandomFile(PropertyPraser.getProperty("coverImagePath"),"jpg");
    }
}
