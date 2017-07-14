package org.tarena.netctoss.controller.fee;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tarena.netctoss.dao.CostMapperDao;
import org.tarena.netctoss.entity.Cost;

@Controller
@RequestMapping("/fee")
public class FeeAddController {
	@Resource
	public CostMapperDao dao;
	public void setDao(CostMapperDao dao) {
		this.dao = dao;
	}
	@RequestMapping("/toAdd")
	public String toAdd(){
		return "fee/fee_add";
	}
	//对应/fee/add.from请求。将表单参数封装成cost对象传入，jsp表单中组件name属性要与Cost中属性一致
	@RequestMapping("/add")
	public String add(Cost cost){
		//调用Dao.saveCost
		dao.saveCost(cost);
		System.out.println(cost.getId());
		return "redirect:/fee/fee_list.from";
	}
}
