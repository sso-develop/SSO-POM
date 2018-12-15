package com.lambert.common.uitl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: lambert
 * @Date: 2018/12/15 14:14
 * @Description:
 */
public class JwtTokenUtil {


    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";

    public static String createToken(){

        Map<String, Object> map = new HashMap<>();

        map.put("alg", "HS256");
        map.put("typ", "JWT");

        String token = JWT.create()
                .withHeader(map)//header
                .withClaim("name", "zwz")//payload
                .withClaim("age", "18")
                .sign(Algorithm.HMAC256(SECRET));//加密

        return token;
    }

    public static void verifyToken(String token,String key){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(key))
                .build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> claims = jwt.getClaims();
        System.out.println(claims.get("name").asString());
    }

}
