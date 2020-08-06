package SQ;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

import net.sf.json.JSONObject;




public class RSATool {
	//公钥
    public static final String PUBLICKEY = 
    		"MIIBTzANBgkqhkiG9w0BAQEFAAOCATwAMIIBNwKCAS4A2sg0lqSGvPLoL1rWnbrH7sY30SCH9eZZZZo2UodWBXndWvqzNJU/84ScxxkfkxVOGjgPyILZybqXfixPXcL8YKH8Jk7l0s76f2pA7swJ7dQFiHCZFNi98r6PFWQe4jDjIuKsbjUxBd+BSwgnzw2jJVv+0U5gAsyATbbEGVm2wJfWAVFqEMu64+22mAWzhFHTqgnut9XugmL3apabE7mQH9Hsmp/eA7MV8nimwUr6wbwcEqgmCo3luEp385QQs3pn/M8J3lu3AQhyzZYB0UcnrYK0GQ/aYpin2nAXwQp8wmqK+71e6uCu++roZtla9xWW1tYjDbScPuNZts+ow/o+Ea0lEwl8dADAvDQ+FbAdIeEUp5dttZn5Ubjp0IF6rNaed8pB7Tm3hsYAecASCQIDAQAB";
    public static final String PRIVATEKEY = "";
    //传递的信息 
    public static final String ENCODEMSG="UKppbGM64/BYQEcmGbUjmQuRrbV6wWYGUDI6//WrpFlTdu49P+gaLn8mX7a1+ILAKqzzbs/wzgy0cu0ZJlrExM+M7VCCR0qbU0x5ZRKsviQs9xuZcdlCWxX1T15JFHV/ojtzm8JFonq1FtEdBfbz/obOextYW5yirxGrEe0tPDL/FPvQrtRF6Qm4GfD+N16z710WYx/glaEOayGChXrxVasokDEiiTqBz7zwzKLJJsbl3OR+9vFDMWvrXbQcgUqXSRdis89GItVkIX6DFCSSKOr+qblgyh7q/daesxzDT4zBJF4lxZEMIzlF60mAypERJbsEA/vT4Gi+siweukc6PV1f1KzetpJIH7CqH/9dsywhTO/Hfmmv9XT0tksyzdbT4QUWSSxN411hPDECGA==";
    public RSATool() {
    }

    /**
     * 用RSA公钥解密
     *
     * @param pubKeyInByte 公钥打包成byte[]形式
     * @param data         要解密的数据
     * @return 解密数据
     */
    public static byte[] decryptByRSA1(byte[] pubKeyInByte, byte[] data) {
        try {
            KeyFactory mykeyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec pub_spec = new X509EncodedKeySpec(pubKeyInByte);
            PublicKey pubKey = mykeyFactory.generatePublic(pub_spec);
            Cipher cipher = Cipher.getInstance(mykeyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, pubKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 测试
     */
    public static void main(String[] args) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(PUBLICKEY));
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);//  获取公钥

            byte[]signpri_pub = Base64.decodeBase64(ENCODEMSG);
            System.out.println("密文原文："+ENCODEMSG);
            //使用公钥对密文进行解密 返回解密后的数据
            byte[] newSourcepri_pub = decryptByRSA1(publicKey.getEncoded(), signpri_pub);
            String encodeResult = new String(newSourcepri_pub, "UTF-8");
            System.out.println("公钥解密：" + encodeResult);
            String requestParam = URLDecoder.decode(URLDecoder.decode(encodeResult, StandardCharsets.UTF_8.name()), StandardCharsets.UTF_8.name());
            System.out.println("请求原文: " + requestParam);
            
            
            JSONObject json = JSONObject.fromObject(requestParam); 
            String date = json.getString("reqDateTime");
            String cstinfo = json.getString("reqCstInfo");
            System.out.println("请求日期："+date);
            System.out.println("请求客户号："+cstinfo);
            

        } catch (Exception e) {
            e.printStackTrace();
        }



    }



}