package com.pibigstar.parsevip.common.utils;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.util.Random;


/**
 * @author pibigstar
 * @create 2018-11-30 13:30
 * @desc pdf工具类
 **/
public class PDFUtil {
    private static final float IAMGE_HEIGHT = 110f; // 水印图片的的高度
    private static final float IAMGE_WIDTH = 110f; // 水印图片的的宽度

    /**
     * @Description: 添加Pdf水印
     * @param pdfPath pdf地址
     * @param logoPath 水印图片地址
     * @param outPath 输出pdf地址
     */
    public static void addPdfMark(String pdfPath,String logoPath, String outPath) throws Exception {

        PdfReader reader = new PdfReader(pdfPath, "PDF".getBytes());
        // 如果是web项目，直接下载应该放到response的流里面
        // PdfStamper stamp = new PdfStamper(reader,response.getOutputStream());
        // 添加水印之后的pdf文件
        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(outPath));
        int pageSize = reader.getNumberOfPages();

        float pageHeight = reader.getPageSize(1).getHeight();
        float pageWidth = reader.getPageSize(1).getWidth();
        try {
            // 每两行显示两个 左边一个，右边一个
            int lineNum = (int) (pageHeight / IAMGE_HEIGHT); // 行数
            int middleY = (int) pageWidth / 2;
            for (int i = 1; i <= pageSize; i++) {
                for (int j = 0, k = 0; j < lineNum; j = j + 4, k++) {
                    Random random = new Random();
                    // 放入水印
                    Image img = Image.getInstance(logoPath);
                    img.scaleAbsolute(IAMGE_WIDTH, IAMGE_HEIGHT * 184 / 455);
                    // 将水印显示到最底层
                    img.setAlignment(Image.UNDERLYING);
                    int trueY;
                    while (true) {
                        trueY = random.nextInt(middleY);
                        if (trueY > IAMGE_WIDTH / 2 && trueY < (middleY - IAMGE_WIDTH)) {
                            break;
                        }
                    }
                    // 水印的位置
                    img.setAbsolutePosition(trueY, j * IAMGE_HEIGHT + (float) random.nextInt((int) IAMGE_HEIGHT) - (k % 2) * 10);
                    // 旋转 角度
                    img.setRotationDegrees(random.nextInt(360));
                    PdfContentByte under = stamp.getUnderContent(i);

                    PdfGState gs = new PdfGState();
                    // 设置透明度为0.5
                    gs.setFillOpacity(0.5f);
                    under.setGState(gs);
                    under.addImage(img);
                    while (true) {
                        trueY = random.nextInt(middleY) + middleY;
                        if (trueY > middleY + IAMGE_WIDTH / 2 && trueY < (2 * middleY - IAMGE_WIDTH)) {
                            break;
                        }
                    }
                    // 水印的位置
                    img.setAbsolutePosition(trueY, j * IAMGE_HEIGHT + (float) random.nextInt((int) IAMGE_HEIGHT) - (k % 2) * 10);
                    // 旋转角度
                    img.setRotationDegrees(random.nextInt(360));
                    under.addImage(img);
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            stamp.close();
            reader.close();
        }

    }
}
