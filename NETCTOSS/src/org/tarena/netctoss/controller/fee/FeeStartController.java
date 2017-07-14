package org.tarena.netctoss.controller.fee;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.netctoss.dao.CostMapperDao;

@Controller
//@RequestMapping("/fee")
public class FeeStartController {
	@Resource
	public CostMapperDao dao;
	public void setDao(CostMapperDao dao) {
		this.dao = dao;
	}
	@RequestMapping(value="/start/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public boolean start(@PathVariable("id") Integer id){
		dao.updateStatus(id);
		System.out.println(id);
		return true;
		
	}
}
