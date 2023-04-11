package admin.Utils;

import admin.Utils.Exception.UtilsCreateException;
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

    private JwtUtil() throws UtilsCreateException {
        throw new UtilsCreateException("No EduAdmin.Utils.JwtUtil instances for you!");
    }

    //默认有效期
    private static int JWT_TTL_HOUR   = 1;
    private static int JWT_TTL_MINUTE = 30;
    private static int JWT_TTL_SECOND = 0;
    //默认签发者
    private static final String JWT_ISSUER = "mouton";
    public static final SignatureAlgorithm HS256 = SignatureAlgorithm.HS256;

    public static Claims parseJWT(String jwt){
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
    public static Boolean verifyJWT(String jwt){
        try {
            parseJWT(jwt);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String createJWT(Map<String,Object> claimsMap) {
        JwtBuilder builder = getJwtBuilder("subject", null, getUUID(), claimsMap);
        return builder.compact();
    }
    public static String createJWT(String userId) {
        HashMap<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("userId",userId);
        JwtBuilder builder = getJwtBuilder("subject", null, getUUID(), claimsMap);
        return builder.compact();
    }
    public static String createJWT(String subject, Map<String,Object> claimsMap) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID(), claimsMap);
        return builder.compact();
    }
    public static String createJWT(String subject, Long ttlMillis, Map<String,Object> claimsMap) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID(), claimsMap);
        return builder.compact();
    }
    public static String createJWT(String uuid, String subject, Long ttlMillis, Map<String,Object> claimsMap) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis,  uuid, claimsMap);
        return builder.compact();
    }


    private static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    private static SecretKey generalKey() {
        //根据issuer构造一个密钥
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_ISSUER);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }
    private static Long defaultTime(){
        return ((JWT_TTL_HOUR* 60L + JWT_TTL_MINUTE)*60L + JWT_TTL_SECOND) *1000L;
    }
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid, Map<String,Object> claims) {
        ttlMillis = ttlMillis == null?defaultTime():ttlMillis;
        Date now = new Date(System.currentTimeMillis());//当前时间
        Date exp = new Date(now.getTime()+ttlMillis);//生效时间+生存时间

        return Jwts.builder()
                .setHeaderParam("type","JWT")
                .setHeaderParam("uuid",uuid)
                .setSubject(subject)        // 主题
                .setClaims(claims)          //有效载荷
                .setIssuer(JWT_ISSUER)      // 签发者
                .setIssuedAt(now)           // 签发时间
                .setExpiration(exp)         // 过期时间
                .signWith(HS256, generalKey()); //使用HS256对称加密算法签名


    }

    public static void main(String[] args) {
        HashMap<String, Object> m = new HashMap<>();
        String token = createJWT(m);
        Claims claims = parseJWT(token);
        System.out.println(token);
        System.out.println(claims);
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.getExpiration());
    }

}
