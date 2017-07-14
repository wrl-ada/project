package org.tarena.netctoss.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.tarena.netctoss.util.PrivilegeReader;

public class Role implements Serializable{
	private Integer id;
	private String name;
	//׷��rolePrivilege
	private List<RolePrivilege> pris = new ArrayList<RolePrivilege>();
	//׷��һ������������prisȨ��ID��Ϣ����Ȩ����
	public String getPrisName(){
		String name="";
		for(RolePrivilege rp:pris){
			Integer pid = rp.getPrivilege_id();
			name += PrivilegeReader.getModuleNameById(pid+"");
			name += "��";
		}
		//name = name.substring(0, name.lastIndexOf("��"));
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
