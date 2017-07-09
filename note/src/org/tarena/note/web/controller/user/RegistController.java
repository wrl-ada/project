package org.tarena.note.web.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.User;
import org.tarena.note.service.UserService;

@Controller
@RequestMapping("/user")
public class RegistController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/regist.do")
	@ResponseBody
	public NoteResult execute(User user){
		NoteResult result = userService.addUser(user);
		return result;
	}
	
	
	
	
	
}
