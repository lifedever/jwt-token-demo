package org.example;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class KeyUtil {
    public static RSAPublicKey getPublicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        // 读取公钥
        PemReader publicKeyReader = new PemReader(new InputStreamReader(new FileInputStream(getFile("public.pem")), Charset.forName("utf-8")));
        PemObject pemObject = publicKeyReader.readPemObject();
        publicKeyReader.close();
        X509EncodedKeySpec privKeySpec = new X509EncodedKeySpec(pemObject.getContent());
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) factory.generatePublic(privKeySpec);
    }

    public static RSAPrivateKey getPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        // 读取私钥
        PemReader privateKeyReader = new PemReader(new InputStreamReader(new FileInputStream(getFile("private_key_pkcs8.pem")), Charset.forName("utf-8")));
        PemObject pemObject = privateKeyReader.readPemObject();
        privateKeyReader.close();
        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(pemObject.getContent());
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) factory.generatePrivate(privKeySpec);
    }

    private static String getFile(String fileName) {
        ClassLoader classLoader = KeyUtil.class.getClassLoader();
        URL url = classLoader.getResource(fileName);
        assert url != null;
        return url.getFile();
    }

}
