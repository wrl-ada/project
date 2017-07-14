package org.tarena.netctoss.controller.fee;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.tarena.netctoss.dao.CostMapperDao;
import org.tarena.netctoss.entity.Cost;
import org.tarena.netctoss.entity.Page;

@Controller
@RequestMapping("/fee")
@SessionAttributes("page")
public class FeeListController {
	@Resource
	private CostMapperDao dao;
	
	public void setDao(CostMapperDao dao) {
		this.dao = dao;
	}

	//对应/fee/fee_list.from请求
	//resut风格--------->对应/fee/list/1请求
	@RequestMapping("/fee_list")
	public String execute(Page page,Model model){//默认显示第一页，一页五行
		List<Cost> list = dao.findPage(page);
		//计算总页数
		int totalRows = dao.findRows();//总行数
		int totalPage = 1;//总页数默认为1
		if(totalRows%page.getPagesize()==0){
			//能整除，按10/5=2计算
			totalPage = totalRows/page.getPagesize();
		}else{//不能整除时，按7/5=1+1计算
			totalPage = totalRows/page.getPagesize()+1;
		}//将总页数放入page对象
		page.setTotalPage(totalPage);
		//将结果放到Model,供页面访问
		model.addAttribute("page", page);
		model.addAttribute("costs", list);
		return "fee/fee_list";//进入fee/fee_list.jsp
	}
}
