package base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Main {

	public static void main(String[] args) throws IOException {
		
		final BASE64Encoder encoder = new BASE64Encoder();
		final BASE64Decoder decoder = new BASE64Decoder();
		final String text = "字串文字";
		System.out.println("原文  ："+text);
		final byte[] textByte = text.getBytes("UTF-8");
		//编码
		final String encodedText = encoder.encode(textByte);
		System.out.println("base64后："+encodedText);
		
		//解码
		System.out.println("解码后 ："+new String(decoder.decodeBuffer(encodedText), "UTF-8"));
		
	}
}
