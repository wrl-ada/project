package org.tarena.netctoss.dao;

import java.util.List;

import org.tarena.netctoss.entity.Cost;
import org.tarena.netctoss.entity.Page;
import org.tarena.netctoss.util.MyBatisDao;
@MyBatisDao
public interface CostMapperDao {
	public List<Cost> findAll();
	public void deleteCost(int id);
	public void saveCost(Cost cost);
	public Cost findById(int id);
	public void updateCost(Cost cost);
	public void updateStatus(int id);
	public List<Cost> findPage(Page page);
	public int findRows();
	public Cost findByName(String name);
}
