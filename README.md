说明
----

# JWT Token生成RSA密钥对
- 生成私钥
 ```bash
openssl genrsa -out private.pem 1024
```
- 生成公钥
```bash
openssl rsa -in private.pem -outform PEM -pubout -out public.pem
```
- 将私钥转码
```bash
openssl pkcs8 -topk8 -inform PEM -in private.pem -outform PEM -nocrypt -out private_key_pkcs8.pem
```

# Token 验证传输规则
crm端生成jwt token，携带 userId 和 loginName 两个参数，满意吧验证token，取出 userId 和 loginName，完成验证。
> 满意吧对于crm用户信息的获取，只需要userId和loginName两个字段即可。鉴于jwt token有可携带参数的特点，故采取此方案，既保证安全性，由实现简单的敏感信息传输。

# 接口说明
## 浏览器嵌入方式。CRM将满意吧嵌入，实现一键用户登录功能。
在满意吧地址后追加 token 和 appId 参数：如 https://www.manyibar.com?token=xxxxxx&appId=xxxx。满意吧监测到token参数后，会解析token，完成用户一键登录功能。

> 链接后追加两个参数：
> - token: 由crm生成jwt标准的token，由满意吧截取并验证。
> - appId: 由满意吧提供给crm，由crm回传用于token验证。

## api调用方式
在请求的headers中，追加 Token 和 AppId 参数。如：
```shell script
curl "http://www.manyibar.com/api/xxxxx" \
     -H 'Token: xxxxxx' \
     -H 'AppId: xxxxxx'
```
```shell script
curl -X "POST" "http://www.manyibar.com/api/xxxxx" \
     -H 'Token: xxxxxx' \
     -H 'AppId: xxxxxx' \
     -H 'Content-Type: application/x-www-form-urlencoded; charset=utf-8' \
     --data-urlencode "aaa=1" \
     --data-urlencode "bbb=2"
```