package org.tarena.netctoss.controller.fee;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tarena.netctoss.dao.CostMapperDao;

@Controller
@RequestMapping("/fee")
public class FeeDeleteController {
	@Resource
	private CostMapperDao dao;
	public void setDao(CostMapperDao dao) {
		this.dao = dao;
	}

	@RequestMapping("/fee_delete")
	public String execute(
	@RequestParam(value="id",required=false) 
		Integer id){
		if(id != null){
			//调用mapperDao的delete
			dao.deleteCost(id);
		}
		//重定向
		return "redirect:/fee/fee_list.from";
	}
}