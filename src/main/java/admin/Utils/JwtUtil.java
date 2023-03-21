package admin.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
 * JWT工具类
 * createJWT()  构建Token
 * parseJWT()   解析Token
 *
 */
public class JwtUtil {

    //默认有效期
    public static final int JWT_TTL_HOUR   = 0;
    public static final int JWT_TTL_MINUTE = 30;
    public static final int JWT_TTL_SECOND = 0;
    //默认签发者
    public static final String JWT_ISSUER = "mouton";
    public static final SignatureAlgorithm HS256 = SignatureAlgorithm.HS256;

    public static Claims parseJWT(String jwt){
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
        return builder.compact();
    }
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());
        return builder.compact();
    }
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);
        return builder.compact();
    }


    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    public static SecretKey generalKey() {
        //根据issuer构造一个密钥
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_ISSUER);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }
    private static Long defaultTime(){
        return ((JWT_TTL_HOUR*60 + JWT_TTL_MINUTE)*60 + JWT_TTL_SECOND) *1000L;
    }
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        ttlMillis = ttlMillis == null?defaultTime():ttlMillis;
        Date now = new Date(System.currentTimeMillis());
        Date exp = new Date(now.getTime()+ttlMillis);


        return Jwts.builder()
                .setHeaderParam("type","JWT")
                .setHeaderParam("uuid",uuid)
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer(JWT_ISSUER)     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(HS256, generalKey()) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(exp);
    }
}
