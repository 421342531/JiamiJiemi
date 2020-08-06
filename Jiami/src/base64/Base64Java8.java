package base64;

import java.io.IOException;
import java.util.Base64;

public class Base64Java8 {

	public static void main(String[] args) throws IOException {
		final Base64.Decoder decoder = Base64.getDecoder();
		final Base64.Encoder encoder = Base64.getEncoder();
		final String text = "字串文字";
		System.out.println("原文是："+text);
		final byte[] textByte = text.getBytes("UTF-8");
		//编码
		final String encodedText = encoder.encodeToString(textByte);
		System.out.println("加密后："+encodedText);
		//解码
		System.out.println("解密后："+new String(decoder.decode(encodedText), "UTF-8"));
		
		
		
	}
	
	
}
