package org.tarena.netctoss.controller.fee;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tarena.netctoss.dao.CostMapperDao;
import org.tarena.netctoss.entity.Cost;
import org.tarena.netctoss.entity.Page;
@Controller
@RequestMapping("/fee")
public class FeeUpdateController {
	@Resource
	public CostMapperDao dao;
	public void setDao(CostMapperDao dao) {
		this.dao = dao;
	}
	//请求/fee/{id}/toUpdate-->/fee/toUpdate.from?id=
	@RequestMapping("/toUpdate")
	public String toUpdate(@RequestParam(value="id",required=false) Integer id,Model model){
		Cost cost = dao.findById(id);
		model.addAttribute("cost", cost);
		return "fee/fee_modi";
	}
	@RequestMapping("/update")
	public String update(Cost cost,HttpSession session){
		//获取更新记录,调用dao更新
		dao.updateCost(cost);
		//获取session中存储的page信息
		Page page = (Page)
			session.getAttribute("page");
		return "redirect:/fee/fee_list.from?page="+page.getPage();
	}
}
