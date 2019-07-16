package com.jaque.tools;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.jaque.Element;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class QRCodeTools {
    /**
     * 解析二维码解析,此方法是解析Base64格式二维码图片
     * @param baseStr base64字符串,data:image/png;base64开头的
     * @return 二维码识别的文本
     */
    public static String deEncodeByBase64(String baseStr) {
        String content = null;
        BufferedImage image;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = null;
        try {
            int i = baseStr.indexOf("data:image/png;base64,");
            baseStr = baseStr.substring(i + "data:image/png;base64,".length());//去掉base64图片的data:image/png;base64,部分才能转换为byte[]

            b = decoder.decodeBuffer(baseStr);//baseStr转byte[]
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(b);//byte[] 转BufferedImage
            image = ImageIO.read(byteArrayInputStream);
            content = getQRString(content, image);
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 解析二维码,此方法解析一个路径的二维码图片
     * @param path 图片路径
     * @return 二维码识别的文本
     */
    public static String deEncodeByPath(String path) {
        String content = null;
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
            content = getQRString(content, image);
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 从Url解析二维码
     * @param url 图片网址
     * @return 二维码识别的文本
     */
    public static String deEncodeByUrl(String url) {
        String content = null;
        BufferedImage image;
        try {
            image = ImageIO.read(new URL(url));
            content = getQRString(content, image);
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 从WebElement中解析二维码
     *
     * @param webElement Selenium的WebElement元素
     * @return 二维码识别的文本
     */
    public static String deEncodeByWebElement(WebElement webElement) {
        WrapsDriver wrapsDriver = (WrapsDriver) webElement;
        File screen = ((TakesScreenshot) wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);
        String content = null;
        BufferedImage image;
        try {
            image = ImageIO.read(screen);
            content = getQRString(content, image);
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 直接网页截图识别二维码
     *
     * @param webDriver WebDriver
     * @return 二维码识别的文本
     */
    public static String deEncodeByWebDriver(WebDriver webDriver) {
        File screen = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        String content = null;
        BufferedImage image;
        try {
            image = ImageIO.read(screen);
            content = getQRString(content, image);
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 从Element中解析二维码
     *
     * @param element 自己封装的Element元素
     * @return 二维码识别的文本
     */
    public static String deEncodeByElement(Element element) {
        return deEncodeByWebElement(element.getElement());
    }

    private static String getQRString(String content, BufferedImage image) throws NotFoundException {
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
        Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        Result result = new MultiFormatReader().decode(binaryBitmap, hints);//解码
        //System.out.println("图片中内容：  ");
        //System.out.println("content： " + result.getText());
        content = result.getText();
        return content;
    }

    public static void main(String[] args) {
        System.out.println(deEncodeByUrl("https://dianbo.zycourse.com/gp_defult_code.jpg"));
        System.out.println(deEncodeByUrl("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQFq7zwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyQ2NWU0ZmT1hjSGoxN19fUnh0YzEAAgR-RSxdAwSAOgkA"));
    }
}