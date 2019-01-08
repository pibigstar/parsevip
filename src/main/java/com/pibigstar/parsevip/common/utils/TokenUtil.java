package com.pibigstar.parsevip.common.utils;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pibigstar
 * @create 2018-12-13 15:32
 * @desc token工具类
 **/
public class TokenUtil {

    private static final String SECRET = "rgsnsm#ldyh*ws%l&hdpmnmw@xyhndes";//私密key
    private static final Long TTL_EXPIRATION = 1000L * 60 * 30; //过期时间30分钟
    private static final String ISSUER = "pibigstar";//发行人

    /**
     * 加密信息，生成token
     */
    public static String creatToken(Map<String,Object> params) {
        SignatureAlgorithm signature = SignatureAlgorithm.HS256;

        byte[] secretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key secretKey = new SecretKeySpec(secretBytes, signature.getJcaName());
        Long expiration = System.currentTimeMillis() + TTL_EXPIRATION;

        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(expiration))
                .setIssuer(ISSUER)
                .setClaims(params)
                .signWith(signature,secretKey);

        return builder.compact();
    }

    /**
     * 解析token
     */
    public static Map<String, Object> parseToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                    .parseClaimsJws(token).getBody();
        } catch (SignatureException | MalformedJwtException e){
            System.out.println("token解析失败");
        } catch (ExpiredJwtException e) {
            System.out.println("token已过期");
        }
        return claims;
    }

    public static void main(String[] args){
        Map<String,Object> map = new HashMap<>();
        map.put("pibigstar","测试");
        String token = creatToken(map);
        System.out.println("token:" + token);
        Map<String, Object> result = parseToken(token);
        System.out.println(result.get("pibigstar"));
    }

}
