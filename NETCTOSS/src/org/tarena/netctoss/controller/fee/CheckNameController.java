package org.tarena.netctoss.controller.fee;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.netctoss.dao.CostMapperDao;
import org.tarena.netctoss.entity.Cost;

@Controller
@RequestMapping("/fee")
public class CheckNameController {
	@Resource
	private CostMapperDao dao;
	public void setDao(CostMapperDao dao) {
		this.dao = dao;
	}
	@RequestMapping("/checkName")
	@ResponseBody
	public boolean check(
			@RequestParam(value="name",required=false) String name){
		//System.out.println(name);
		Cost cost = dao.findByName(name);
		if(cost == null){
			return true;
		}else{
			return false;
		}
		
	}
	
}
