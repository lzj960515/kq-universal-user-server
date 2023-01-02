package com.grpn.demo.config.security;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.kqinfo.universal.common.exception.BusinessException;
import com.kqinfo.universal.common.response.BaseResultCode;
import com.grpn.demo.domain.CurrentUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.crypto.RsaProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.UUID;

/**
 * @author Zijian Liao
 * @since 1.0.0
 */
@Slf4j
public final class JwtUtil {

    private JwtUtil() {
    }

    public static final String TOKEN = "token";
    public static final String USER = "user";
    private static PrivateKey privateKey;
    private static PublicKey publicKey;

    public static  String JWT_KEY_File_Path = "./key/keys";

    public static void loadKey(){
        if (privateKey != null && publicKey != null) {
            return;
        }
        KeyPair keyPair = (KeyPair)readObjectFromFile(JWT_KEY_File_Path);
        if(keyPair == null){
            keyPair = RsaProvider.generateKeyPair();
            writeObjectToFile(keyPair, JWT_KEY_File_Path);
        }
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();
    }


    /**
     * 从文件中读取对象
     */
    public static Object readObjectFromFile(String file) {
        try(FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);) {
            return ois.readObject();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * 将对象保存在文件里
     *
     */
    public static void writeObjectToFile(Object obj, String file){
        FileUtil.touch(new File(file));
        try(FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(obj);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
    }

    public static String generateJwt(CurrentUser currentUser, int expireTime) {
        return Jwts.builder()
                .setId(UUID.randomUUID().toString().replace("-", ""))
                .setSubject(currentUser.getUsername())
                .setClaims(BeanUtil.beanToMap(currentUser))
                .setIssuedAt(new Date())
                .setExpiration(DateUtil.offsetMinute(new Date(), expireTime))
                .signWith(privateKey)
                .compact();
    }

    public static CurrentUser validToken(String token) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(publicKey).build();
        try {
            Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            CurrentUser currentUser = new CurrentUser();

            currentUser.setUserId(claims.get("userId", Long.class))
                    .setUsername(claims.get("username", String.class))
                    .setRoleName(claims.get("roleName", String.class));
            return currentUser;
        } catch (Exception e) {
            throw new BusinessException(BaseResultCode.TOKEN_EXPIRATION);
        }
    }


}
