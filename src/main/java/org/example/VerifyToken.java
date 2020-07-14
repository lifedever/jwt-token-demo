package org.example;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class VerifyToken {
    public DecodedJWT verify(String token, String appId) {
        try {
            Algorithm algorithm = Algorithm.RSA256(KeyUtil.getPublicKey(), KeyUtil.getPrivateKey());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(appId)
                    .build();
            return verifier.verify(token);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            System.out.println("token验证失败！");
        }
        return null;
    }
}
