package org.tarena.netctoss.entity;

import java.io.Serializable;

public class RolePrivilege implements Serializable{
	private Integer role_id;
	private Integer privilege_id;
	/**
	 * @return the role_id
	 */
	
	public Integer getRole_id() {
		return role_id;
	}
	/**
	 * @param roleId the role_id to set
	 */
	public void setRole_id(Integer roleId) {
		role_id = roleId;
	}
	/**
	 * @return the privilege_id
	 */
	public Integer getPrivilege_id() {
		return privilege_id;
	}
	/**
	 * @param privilegeId the privilege_id to set
	 */
	public void setPrivilege_id(Integer privilegeId) {
		privilege_id = privilegeId;
	}
	
}
