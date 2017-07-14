package org.tarena.netctoss.controller.role;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tarena.netctoss.dao.RoleMapperDao;

@Controller
@RequestMapping("/role")
public class RoleDeleteController {
	@Resource
	private RoleMapperDao dao;
	public void setDao(RoleMapperDao dao) {
		this.dao = dao;
	}

	@RequestMapping("/role_delete")
	public String delete(@RequestParam(value="id",required=false) Integer id){
		if(id!=null){
			dao.deleteRole(id);
		}
		return "redirect:/role/role_list.from";
	}
}
