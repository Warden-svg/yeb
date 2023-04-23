package com.xxxx.server.config.security;

import com.sun.javafx.collections.MappingChange;
import io.jsonwebtoken.*;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//​ 标准的jwt令牌(token)分为三部分，分别是Header、payload、signature；payload是负荷

/**JWT工具类
 *
 */
@Component
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME = "sub";    //JWT的用户名,放在荷载头里,作为map集合中的key
    private static final String CLAIM_KEY_CREATED = "created"; //JWT的创建时间,放在荷载头里,作为map集合中的key
    @Value("${jwt.secret}")  //通过@Value去yaml拿到JWT的秘钥
    private String secret;
    @Value("${jwt.expiration}")//通过@Value去yaml拿到JWT的秘钥失效时间
    private Long expiration;


    /**
     * 根据用户信息生成JWT Token中的荷载
     *  下面的集合claims就是payload,有效荷载,简称荷载
     * @return
     */
    public String generateToken(UserDetails userDetails) {//UserDetails是security框架中的类,用户信息可以从这里面拿
        Map<String, Object> claims = new HashMap<>();    //Map是集合数据,其中的数据为key:value形式
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());//Map集合的put(key,value)方法往里加数据
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);     //claims是集合数据类型,因此,会自动调用下边的重载方法(该方法需要的参数才是集合类型)
    }

    /**
     * 根据上边生成的荷载生成整个JWT Token
     * @param claims
     * @return
     */
    private String generateToken(Map<String ,Object> claims){   //函数明与上边一样,java的方法重载
        return Jwts.builder()             //生成JWT对象
                .setClaims(claims)         //荷载放入
                .setExpiration(generateExpirationDate())       //放入失效时间
                .signWith(SignatureAlgorithm.HS512,secret)  //放入签名,SignatureAlgorithm是签名算法,secret是秘钥
                .compact();    //构建JWT对象
    }


    /**    从Token中获取用户名(第一步解密token获得载荷,第二步从在河中获取用户名)
     *
     */
    public String getUserNameFromToken(String token){
        String username;
        try { //Claims是JWT提供的用于生成荷载的类
            Claims claims = getClaimsFromToken(token);//getClaimsFromToken(token)//解密token 并且从token中获取荷载应
            username= claims.getSubject();//getSubject()可拿到荷载中的用户名
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return username;
    }

    /**
     *,解密token 从token中获取荷载应
     * @param token
     * @return
     */
    public Claims getClaimsFromToken(String token){
        Claims claims = null;

        try {
            claims=Jwts.parser()      //解析器
                    .setSigningKey(secret)   //放入密匙
                    .parseClaimsJws(token)//解析token
                    .getBody();             //得到荷载claims
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }


    /**
     * 判断token是否有效:1 是否过期,,2  用户信息是否对应
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token,UserDetails userDetails){
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername())&&!isTokenExpired(token);
    }
//判断是否过期
    private boolean isTokenExpired(String token) {
        Date expireDate = getExpiredDateFromToken(token);
        return expireDate.before(new Date());
    }
/**
 * 获取过期时间
 */

    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();//从荷载中取出失效时间
    }

    //失效时间方法
     private Date generateExpirationDate(){
         return new Date(System.currentTimeMillis() + expiration * 1000);
     }

//判断能否被刷新,过期了才能刷新
    public boolean CanRefresh(String token) {
        return !isTokenExpired(token);
    }
    /**
     *刷新token
     */
    public String refreshToken(String  token){
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }
}


