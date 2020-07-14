package org.example;

import com.auth0.jwt.interfaces.DecodedJWT;

public class Test {
    public static void main(String[] args) {
        try {
            // 车店长 生成Token
            String appId = "chedianzhang";
            String userId = "zhangsan";
            String loginName = "18800000001";
            String token = new GenerateToken().createToken(appId, userId, loginName);
            System.out.println(token);
            // result: eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxODgwMDAwMDAwMSIsImFwcElkIjoiY2hlZGlhbnpoYW5nIiwiaXNzIjoiY2hlZGlhbnpoYW5nIiwidXNlcklkIjoiemhhbmdzYW4ifQ.El5ht2mgceY950ucD5FLXI1BA6iI_GEYMRy1fhdheRTZN5l6TyejDyjkwgo-fwriK29tArOqqLHpV5YGV11VO_dUuojovyy1fI-0IXE48PkYf3dCjVtB9XJvsq1MbxrTVzkquQEVYViBK48HB8FOwKQMieS6PhtCwgAOHgXZ2zU


            // 满意吧 验证Token
            VerifyToken verifyToken = new VerifyToken();
            DecodedJWT jwt = verifyToken.verify(token, appId);

            System.out.println("token验证成功！");
            System.out.println("当前请求账号为：" + jwt.getSubject());
            System.out.println("账号Id为：" + jwt.getClaim("userId").asString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
