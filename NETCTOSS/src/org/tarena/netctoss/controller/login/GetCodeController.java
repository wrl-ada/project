package org.tarena.netctoss.controller.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class GetCodeController {
	@RequestMapping("/getCode")
	public void execute(HttpServletResponse response,HttpSession session) throws IOException{
		//1������һ�ſհ�ͼƬ
		BufferedImage image = 
			new BufferedImage(100, 30, BufferedImage.TYPE_INT_RGB);
		//2���������ͼƬ��ȡ����
		Graphics g = image.getGraphics();
		//3.������������ɫ
		Random r = new Random();
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		//4.����ʵ�ĵı���
		g.fillRect(0, 0, 100, 30);
		//5.�ı仭�ʵ���ɫ
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		//5.1������֤�������
		g.setFont(new Font(null,Font.BOLD,24));
		//6.������֤�������
		String number = getNumber(5);
		//��ͼƬ�ַ�����session,������֤����ʹ��
		session.setAttribute("scode", number);
		g.drawString(number, 12, 20);
		//6.1���Ƹ�����
		for(int i =0;i<8;i++){
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.drawLine(r.nextInt(100), r.nextInt(30), r.nextInt(300), r.nextInt(30));
		}
		response.setContentType("image/jpeg");
		OutputStream ops = response.getOutputStream();
		ImageIO.write(image, "jpeg", ops);
	}
	public String getNumber(int size){
		String number = "";
		String str ="QWERTYUIOPLKJHGFDSAZXCVBNM0987654321";
		Random r = new Random();
		for(int i = 0;i<size;i++){
			number += str.charAt(r.nextInt(str.length()));
		}
		return number;
	}
}
