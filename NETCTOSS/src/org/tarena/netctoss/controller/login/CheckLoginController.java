package org.tarena.netctoss.controller.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tarena.netctoss.dao.AdminInfoMapperDao;
import org.tarena.netctoss.entity.AdminInfo;

@Controller
@RequestMapping("/login")
public class CheckLoginController {
	@Resource
	private AdminInfoMapperDao dao;
	public void setDao(AdminInfoMapperDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping("/login")
	public String login(AdminInfo admin,Model model,HttpSession session){
		//获取生成的验证码
		String scode = (String)session.getAttribute("scode");
		//检查验证码是否正确
		if(!admin.getCode().equals(scode)){
			model.addAttribute("err", "验证码错误！");
			return "login";
		}
		//检查用户名和密码是否正确
		AdminInfo info = dao.findByAdminCodeAndPwd(admin);
		if(info != null){
			session.setAttribute("name", admin.getAdmin_code());
			return "index";
		}else{
			model.addAttribute("error", "用户名或密码错误，请重试");
			return "login";
		}
		
	}
}
