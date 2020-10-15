package com.fun.client.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.joda.time.DateTime;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 生成token以及校验token相关方法
 * 当前类基于 JJWT 0.11.2 版本
 *
 * @author mrdjun
 */
public class JwtUtils {
    private final static String JWT_PAYLOAD_AUTHORITIES = "FUN_AUTHORITIES";
    private final static String JWT_PAYLOAD_PERMISSIONS = "FUN_PERMISSIONS";
    // 过期时间默认一天 = 24 * 60（单位分钟）
    private final static int DEFAULT_JWT_EXPIRE = 1440;
    // 记住我时间默认七天 = 7 * 24 * 60（单位分钟）
    private final static int JWT_REMEMBER_EXPIRE = 10080; 

    /**
     * 私钥加密 Token
     *
     * @param roles      角色
     * @param loginName  登录账号
     * @param perms      权限
     * @param privateKey 私钥
     * @return JWT
     */
    public static String createToken(Set<String> perms, Set<String> roles, String loginName,
                                     PrivateKey privateKey, Boolean isRemember) {
        HashMap<String, Object> h = new HashMap<>(2);
        h.put(JWT_PAYLOAD_PERMISSIONS, String.join(",", perms));
        h.put(JWT_PAYLOAD_AUTHORITIES, String.join(",", roles));
        Date default_expire = DateTime.now().plusMinutes(DEFAULT_JWT_EXPIRE).toDate();
        Date remember_expire = DateTime.now().plusMinutes(JWT_REMEMBER_EXPIRE).toDate();
        return Jwts.builder()
                .setClaims(h)
                .setSubject(loginName)
                .setId(createJTI())
                .setIssuedAt(DateTime.now().toDate())
                .setExpiration(isRemember ? remember_expire : default_expire)
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    /**
     * 私钥加密token
     *
     * @param roles      角色
     * @param loginName  登录账号
     * @param perms      权限
     * @param privateKey 私钥
     * @return JWT
     */
    public static String createTokenExpireInSeconds(List<String> perms, List<String> roles, String loginName, PrivateKey privateKey) {
        HashMap<String, String> h = new HashMap<>(2);
        h.put(JWT_PAYLOAD_PERMISSIONS, String.join(",", perms));
        h.put(JWT_PAYLOAD_AUTHORITIES, String.join(",", roles));
        return Jwts.builder()
                .setClaims(h)
                .setSubject(loginName)
                .setId(createJTI())
                .setIssuedAt(DateTime.now().toDate())
                .setExpiration(DateTime.now().plusSeconds(DEFAULT_JWT_EXPIRE * 60).toDate())
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    /**
     * 公钥解析 token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥
     * @return Jws<Claims>
     */
    private static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(token);
    }

    private static String createJTI() {
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    /**
     * 获取 Token 中的账号
     *
     * @param token     token
     * @param publicKey publicKey
     * @return loginName
     */
    public static String getLoginName(String token, PublicKey publicKey)
            throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        return parserToken(token, publicKey).getBody().getSubject();
    }

    /**
     * 获取 Token 中的用户角色
     * 注：不能保证 token 的及时性，当用户角色修改时，应当重新登录
     *
     * @param token     token
     * @param publicKey publicKey
     * @return List<SimpleGrantedAuthority>
     */
    public static List<SimpleGrantedAuthority> getRoles(String token, PublicKey publicKey) {
        String roles = parserToken(token, publicKey).getBody().get(JWT_PAYLOAD_AUTHORITIES).toString();
        return Arrays.stream(roles.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    /**
     * 获取 Token 中的用户菜单
     *
     * @param token     token
     * @param publicKey publicKey
     * @return List<String>
     */
    public static List<String> getPerms(String token, PublicKey publicKey) {
        String perms = parserToken(token, publicKey).getBody().get(JWT_PAYLOAD_PERMISSIONS).toString();
        return Arrays.stream(perms.split(",")).collect(Collectors.toList());
    }

}