package com.pibigstar.parsevip.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author pibigstar
 * @desc 海报生成工具
 **/
public class PosterUtil {
    private static final String BACKGROUND_IMG = "images/bg.jpg"; // 背景图片
    private static final String result_img = "D:/tmp/images/result.jpg"; // 最终输出图片
    private static final String QQ = "741047261";
    private static final String avatar_img = "http://q1.qlogo.cn/g?b=qq&nk="+QQ+"&s=100";//QQ获取头像接口
    private static final String signature = "魔前一叩三千年，回首凡尘不做仙。"; // 个性签名

    public static void main(String[] args){
        drawPoster();
    }

    /**
     * @Author:pibigstar
     * @Description: 画海报
     */
    public static void drawPoster(){
        try {
            long startTime = System.currentTimeMillis();
            // 1. 创建画布
            BufferedImage backgroundImg = ImageIO.read(getInputStream(BACKGROUND_IMG));
            BufferedImage canvas = new BufferedImage(backgroundImg.getWidth(),backgroundImg.getHeight(),BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) canvas.getGraphics();
            // 设置抗锯齿
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

            // 2. 将头像设置为圆角
            BufferedImage avatar = ImageIO.read(new URL(avatar_img));
            BufferedImage newAvatar = new BufferedImage(avatar.getWidth(), avatar.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, avatar.getWidth(), avatar.getHeight());
            Graphics2D g2 = newAvatar.createGraphics();
            newAvatar = g2.getDeviceConfiguration().createCompatibleImage(avatar.getWidth(), avatar.getHeight(), Transparency.TRANSLUCENT);
            g2 = newAvatar.createGraphics();
            g2.setComposite(AlphaComposite.Clear);
            g2.fill(new Rectangle(newAvatar.getWidth(), newAvatar.getHeight()));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1.0f));
            g2.setClip(shape);
            // 使用 setRenderingHint 设置抗锯齿
            g2 = newAvatar.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fillRoundRect(0, 0,avatar.getWidth(), avatar.getHeight(), 150, 150);
            g2.setComposite(AlphaComposite.SrcIn);
            g2.drawImage(avatar, 0, 0, avatar.getWidth(), avatar.getHeight(), null);
            g2.dispose();

            // 3. 将背景图和头像结合
            // 画背景
            g.drawImage(backgroundImg.getScaledInstance(backgroundImg.getWidth(), backgroundImg.getHeight(), Image.SCALE_DEFAULT), 0, 0, null);
            // 背景上画头像
            g.drawImage(newAvatar.getScaledInstance(100, 100, Image.SCALE_DEFAULT), 42, 35, null);

            // 4. 写字（昵称）
            g.setColor(new Color(33, 33, 33, 128));
            g.setFont(getFont(2, 32.0f));
            g.drawString(getNickName(QQ), 200, 75);

            // 5. 画个性签名
            g.setColor(new Color(33, 33, 33, 128));
            g.setFont(getFont(1, 40.0f));
            // 画竖着的文字
            int x = 240; // 起始位置
            int length = signature.length();//字符总长度
            int size = length/2; //一竖列的长度
            int j = 0; // 读取到那个字符了
            for (int i=0; i<2; i++) {//两竖列
                int y = 300;
                for (;j < size; j++) {
                    String c = String.valueOf(signature.charAt(j));
                    y += 40;
                    g.drawString(c,x,y);
                }
                size = length;
                x += 70;//竖列之间的距离
            }
            g.dispose();
            File resultImg = new File(result_img);
            ImageIO.write(canvas, "png", resultImg);

            System.out.println("生成成功！");
            System.out.println("耗时: " + (System.currentTimeMillis()-startTime)/1000.0 + "s");
            System.out.println("生成文件路径: " + resultImg.getAbsolutePath());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @Description: 获取QQ昵称，无意间发现的QQ音乐的一个漏洞
     */
    private static String getNickName(String qq){
        String api = "https://c.y.qq.com/rsc/fcgi-bin/fcg_get_profile_homepage.fcg?cid=205360838&ct=20&userid="+qq+"&reqfrom=1";
        String nick = "";
        JSONObject creatorObject = null;
        try {
            Document document = Jsoup.connect(api).ignoreContentType(true).timeout(10000).get();
            String body = document.body().text();
            JSONObject jsonObject = JSONObject.parseObject(body);
            JSONObject dataObject = (JSONObject) jsonObject.get("data");
            creatorObject = (JSONObject) dataObject.get("creator");
            nick = creatorObject.get("nick").toString();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return nick;
    }

    /**
     * 根据字体类型获取字体
     * @param type 字体类型
     * @param size 字体大小
     */
    private static Font getFont(int type, float size) {
        // 字体路径
        String path;
        switch (type) {
            case 1:
                path = "ttf/JianTi.ttf";
                break;
            case 2:
                path = "ttf/PingFang.ttf";
                break;
            default:
                path = "ttf/JianTi.ttf";
        }
        InputStream inputStream = null;
        try {
            inputStream = getInputStream(path);
            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            font = font.deriveFont(size);
            return font;
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取resources下的文件输入流
     */
    private static InputStream getInputStream(String fileName) {
        return PosterUtil.class.getClassLoader().getResourceAsStream(fileName);
    }
}
