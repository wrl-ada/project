package org.tarena.netctoss.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.tarena.netctoss.util.PrivilegeReader;

public class Role implements Serializable{
	private Integer id;
	private String name;
	//追加rolePrivilege
	private List<RolePrivilege> pris = new ArrayList<RolePrivilege>();
	//追加一个方法，根据pris权限ID信息返回权限名
	public String getPrisName(){
		String name="";
		for(RolePrivilege rp:pris){
			Integer pid = rp.getPrivilege_id();
			name += PrivilegeReader.getModuleNameById(pid+"");
			name += "、";
		}
		//name = name.substring(0, name.lastIndexOf("、"));
		return name;
	}
	/**
	 * @return the pris
	 */
	public List<RolePrivilege> getPris() {
		return pris;
	}
	/**
	 * @param pris the pris to set
	 */
	public void setPris(List<RolePrivilege> pris) {
		this.pris = pris;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
