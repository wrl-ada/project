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
		//��ȡ���ɵ���֤��
		String scode = (String)session.getAttribute("scode");
		//�����֤���Ƿ���ȷ
		if(!admin.getCode().equals(scode)){
			model.addAttribute("err", "��֤�����");
			return "login";
		}
		//����û����������Ƿ���ȷ
		AdminInfo info = dao.findByAdminCodeAndPwd(admin);
		if(info != null){
			session.setAttribute("name", admin.getAdmin_code());
			return "index";
		}else{
			model.addAttribute("error", "�û������������������");
			return "login";
		}
		
	}
}
