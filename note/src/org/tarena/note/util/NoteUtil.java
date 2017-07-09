package org.tarena.note.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

public class NoteUtil {
	public static String createId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	public static String createToken(){
		return createId().replaceAll("-", "");//ȥ��"-"
	}
	
	public static String md5(String msg){
		//����ժҪ�㷨
		try {
			//��msg��Ϣ����md5����
			MessageDigest md = 
					MessageDigest.getInstance("MD5");
			byte[] input = msg.getBytes();
			byte[] output = md.digest(input);
			//��base64�㷨��output���ܺ���ֽ�����ת���ַ���
			return Base64.encodeBase64String(output);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static void main(String[] args){
		System.out.println(md5("1234"));
		System.out.println(md5("13241234123341234"));
	}
	
}
