package org.tarena.netctoss.controller.role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tarena.netctoss.dao.RoleMapperDao;
import org.tarena.netctoss.entity.Role;

@Controller
@RequestMapping("/role")
public class RoleListController {
	@Resource
	private RoleMapperDao dao;
	public void setDao(RoleMapperDao dao) {
		this.dao = dao;
	}

	@RequestMapping("/role_list")
	public String execute(Model model){
		List<Role> list = dao.findAll();
		model.addAttribute("roles", list);
		return "role/role_list";
	}
}
