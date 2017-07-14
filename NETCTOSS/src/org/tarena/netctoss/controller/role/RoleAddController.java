package org.tarena.netctoss.controller.role;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tarena.netctoss.dao.RoleMapperDao;
import org.tarena.netctoss.entity.Role;

@Controller
@RequestMapping("/role")
public class RoleAddController {
	@Resource
	private RoleMapperDao dao;
	public void setDao(RoleMapperDao dao) {
		this.dao = dao;
	}
	@RequestMapping("/role_toAdd")
	public String toAdd(){
		return "role/role_add";
	}
	@RequestMapping("/role_add")
	public String add(Role role){
		dao.saveRole(role);
		return "redirect:/role/role_list.from";
	}
}
