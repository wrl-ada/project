package org.tarena.note.web.controller.user;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.UserService;

@Controller
@RequestMapping("/user")
public class LoginController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/login.do")
	@ResponseBody
	public NoteResult execute(
				HttpServletRequest request) throws Exception{
		System.out.println("Ω¯»ÎLoginController");
		String author = request.getHeader("Authorization");
		System.out.println("author:"+author);
		NoteResult result = 
			userService.checkLogin(author);
		return result;
	}

}
