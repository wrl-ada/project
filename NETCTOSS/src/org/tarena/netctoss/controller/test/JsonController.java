package org.tarena.netctoss.controller.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.netctoss.dao.CostMapperDao;
import org.tarena.netctoss.entity.Cost;
import org.tarena.netctoss.entity.Page;

@Controller
@RequestMapping("/test")
public class JsonController {
	@Resource
	private CostMapperDao dao;
	public void setDao(CostMapperDao dao) {
		this.dao = dao;
	}
	@RequestMapping("/test1")
	@ResponseBody
	public boolean f1(){
		return true;
	}
	@RequestMapping("/test2")
	@ResponseBody
	public Map<String,Object> f2(){
		Map map = new HashMap<String,Object>();
		map.put("id", 1001);
		map.put("name","tom");
		return map;
	}
	@RequestMapping("/test3")
	@ResponseBody
	public Cost f3(){
		Cost cost = dao.findById(1);
		return cost;
	}
	@RequestMapping("/test4")
	@ResponseBody
	public List f4(){
		Page page = new Page();
		List<Cost> list = dao.findPage(page);
		return list;
	}
}
