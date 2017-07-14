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

	//��Ӧ/fee/fee_list.from����
	//resut���--------->��Ӧ/fee/list/1����
	@RequestMapping("/fee_list")
	public String execute(Page page,Model model){//Ĭ����ʾ��һҳ��һҳ����
		List<Cost> list = dao.findPage(page);
		//������ҳ��
		int totalRows = dao.findRows();//������
		int totalPage = 1;//��ҳ��Ĭ��Ϊ1
		if(totalRows%page.getPagesize()==0){
			//����������10/5=2����
			totalPage = totalRows/page.getPagesize();
		}else{//��������ʱ����7/5=1+1����
			totalPage = totalRows/page.getPagesize()+1;
		}//����ҳ������page����
		page.setTotalPage(totalPage);
		//������ŵ�Model,��ҳ�����
		model.addAttribute("page", page);
		model.addAttribute("costs", list);
		return "fee/fee_list";//����fee/fee_list.jsp
	}
}
