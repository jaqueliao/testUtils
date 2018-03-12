package com.jaque.testUtils;

import javax.imageio.*;
import java.awt.image.*;
import java.awt.*;//Color
import java.io.*;
public class PhotoDigest{
    public static void main(String[] args)throws Exception{
        float percent = compare(getData(new File("D:\\a.png")),getData(new File("D:\\b.png")));
        System.out.println("两张图片的相似度为："+percent+"%");
    }
    public static int[] getData(File file)throws Exception{
    	int i = 50;
        BufferedImage img = ImageIO.read(file);
        BufferedImage slt = new BufferedImage(i,i,BufferedImage.TYPE_INT_RGB);
        slt.getGraphics().drawImage(img,0,0,i,i,null);
        //ImageIO.write(slt,"jpeg",new File("slt.jpg"));
        int[] data = new int[256];
        for(int x = 0;x<slt.getWidth();x++){
            for(int y = 0;y<slt.getHeight();y++){
                int rgb = slt.getRGB(x,y);
                Color myColor = new Color(rgb);
                int r = myColor.getRed();
                int g = myColor.getGreen();
                int b = myColor.getBlue();
                data[(r+g+b)/3]++;
            }
        }
        //data 就是所谓图形学当中的直方图的概念
        return data;
    }
    private static float compare(int[] s,int[] t){
        float result = 0F;
        for(int i = 0;i<256;i++){
            int abs = Math.abs(s[i]-t[i]);
            int max = Math.max(s[i],t[i]);
            result += (1-((float)abs/(max==0?1:max)));
        }
        return (result/256)*100;
    }
    public static float getSimRate(File img1,File img2) throws Exception {
    	return compare(getData(img1),getData(img2));
    }
}