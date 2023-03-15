package com.xuan.base.config.uniqlo;


/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000122606445";

	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCvvTWBdkxb8cxO8+jhOFBGZsQ0lYEVZizsDW3jsjh6YIQTIha+57xVQXIwkwklHBk6WOZs+gSKtxsZsN4XnEgmCSaHmYoSEnuqTb8UQx4ra9a7ZzVfKCa0snK7N/49DZ788+IE7f9zTMyI0pah+0CVkENr2BYePBakuCrpunnSb1AWj+ly2HRtMorlETbAG+SRJc/7e8oGPawD456IiLqPYbMInaTTzh1p5F1jZXF6Z5hLDuLBRgmRTBqi51hpwVSTNvmubj3zrmVBvyu9QtIUSzVyAI7zQuThCwOhqSvxPlnC60yCGISJfzZ/PN3gl8WhyvGy1UVYLXGOH6JaIjnvAgMBAAECggEALQPHN9EogAiwr6yUg1QcY9wg9+MvvF5+tTw/YKqfAuHEEu6RavcK67+m00BmgEXxRFvPMizciveaDnuad+vFI1EGuIcgsb5gROBDSJpkmPS3fStTpXswZuqqrsAJ9HOPvPVveJnJ7CvI3GcsxHMKOQVJ787a44W6uxidjV8Bo2AIMk1z7famph5zy22Dl9gg4fSQ6Y5YQCpXexM/7dBnIpDyE0vxghT8zuwg2M3hRyudc7FD8GDmRzPgWn7BA3mKouTotXGXYp149BEldrSK+zTNY4u5T868DUYnDnRgCXEPLiYDz4nc9Fu+sf4DVqy5xPrTaAIWjn5tQL7Kor3y8QKBgQDzN1i2NwdS0CW5Cu21Fw+1azUEYHfsrImQzaKpM8GXylc9Rza5cHesgD8aDKrSmX/jl7lGrmiFITzTfb0WUU6ZS1JekD8HSiFPGoDLJmlYO6CzN8MAKSZ0A6QYtyMr2nC58ur0FnfbMPQ+slH87Oe6d7+OLX22drGaGnzSQbqW5wKBgQC4+eiFuq/sRdmBUblCmLgdnAtRIbqPtJV5gswNdJ1WLXSmhVznPVucybp+kgZVyPbioObPPhYCGGai1k5khroZcTomDeiAmxyUUpzjWBiZnZ8rQIWOAO8LSQCOKC/7wspCgq5wVs0bgNKI8A7FnRUg3oxloL0RbSCXEGOUvA7LuQKBgQDYQT3/xq4cVDc2cSErA3ZUOR7K5Xu8VS18M1uOVxargJ5RPiDKgdXm66Ev6FN7LkEofJ+g2TLWLNeItSSL03+vjemPa3KfaKp1S3t5Jo5EhcA9mjcozW4DeblOwIflVjUlIXkHz3KC0uRZWvTLj1n70r3gDVLaCm+gNi0GRqtBwQKBgGvv2BEAyNsUP0FI0NPdjcIpZiknh1eHxpkrC6rmW1EIIUNyGq4MjcLFq/9jcjLom7fOsCBKWElMt8dZSlE6lKl3+rht8TfqSxj5w/adV218Hn78nXIpdVOee6Z4KNe96Iw6+VMwM2tTFoc1nUkOyl9tvXsn+muKxDOiKpmFyxRxAoGAXGIzZb85XL0MYZ5w06asyJtkpIHQH3ipDpb7umLrW5AQHr+YmpeGGhLa5RJmmyqz8fWd4MO62octS0EWVdKfXTbio+69QzpSzXLF0+ru5mhD9yqPSXyz2EAmZHQOsfIZJQ1wOdGZsyQMHifPhhdOs5Wek7+cg/TJ1q9ntvEp+kU=";
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	public static String alipay_public_key ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqapfb9kZHY5mgHvBqH0Oc1uQDVQsdTE3Mwy6nRP2xT69KiDv5syEskyd0fGcZkHEGGn62CwfFnZu7UDUi/8hzYHqBo6qECpTpB3OWTRVovVNL4YX0zjDHOLiEEHqF9e1++UqBj4sod0hnQ8U3FP1mmkkKGjVOUKPs+0lxRvRXOm+8N/E1CtIO6YgGN6Y8qfyDddj1NqITJRkXhrUkK+MTiDE7/2UEqK7QIq52C4IMKNmbdUjW/7ZgMwl1bIM8yXuQPGMTt3Jy3XUCTrMnJMgaA94SKOU9lR6RDmGKkfg0gAWQ3iM4z/TvF+qCTMDRI99gxrKMW3D22Weu8P2aXOSQQIDAQAB";
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "\t\n" + "\t\n" + "http://16eb8396.r5.cpolar.top/uniqlo/pay/alipay_notify";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "\t\n" + "\t\n" + "http://16eb8396.r5.cpolar.top/user/usercenter";


	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	//public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
}