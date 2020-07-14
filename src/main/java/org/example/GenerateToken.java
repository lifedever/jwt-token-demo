package org.example;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class GenerateToken {

    public String createToken(String appId, String userId, String loginName) throws Exception {
        Algorithm algorithm = Algorithm.RSA256(KeyUtil.getPublicKey(), KeyUtil.getPrivateKey());
        return JWT.create()
                .withSubject(loginName)
                .withClaim("userId", userId)
                .withIssuer(appId)
                .sign(algorithm);
    }
}
