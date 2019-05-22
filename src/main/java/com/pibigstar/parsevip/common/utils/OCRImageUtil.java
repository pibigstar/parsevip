//package com.pibigstar.parsevip.common.utils;
//
//import net.sourceforge.tess4j.Tesseract;
//import net.sourceforge.tess4j.TesseractException;
//
//import java.io.File;
//
///**
// * OCR图片识别，识别图片中的文字
// * @author pibigstar
// */
//public class OCRImageUtil {
//    public static void main(String[] args) throws TesseractException {
//       System.out.println(getTextByImage("D://OCR/img/test.png"));
//    }
//
//    public static String getTextByImage(String imgPath) throws TesseractException {
//        File imageFile = new File(imgPath);
//        if (!imageFile.exists()){
//            throw new RuntimeException("图片不存在");
//        }
//
//        Tesseract tesseract = new Tesseract();
//        // 设置训练库的位置,https://github.com/tesseract-ocr/tessdata
//        tesseract.setDatapath("D://OCR/tessdata");
//        // 设置识别语言为中文
//        tesseract.setLanguage("chi_sim");
//
//        String result = tesseract.doOCR(imageFile);
//        return result;
//    }
//}
