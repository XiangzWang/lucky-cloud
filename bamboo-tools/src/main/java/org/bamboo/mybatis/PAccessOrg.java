package org.bamboo.mybatis;

import java.util.List;

public class PAccessOrg implements java.io.Serializable {

	private static final long serialVersionUID = -1101408219761705031L;
	private String staffNo;
	private String orgNo;
	private String orgType;
	
	private OOrg org;
	
	private List<PSysUser> users;
	
	public List<PSysUser> getUsers() {
		return users;
	}

	public void setUsers(List<PSysUser> users) {
		this.users = users;
	}
	// Constructors

	/** default constructor */
	public PAccessOrg() {
	}

	/** minimal constructor */
	public PAccessOrg(String orgNo) {
		this.orgNo = orgNo;
	}

	/** full constructor */
	public PAccessOrg(String staffNo, String orgNo, String orgType) {
		this.staffNo = staffNo;
		this.orgNo = orgNo;
		this.orgType = orgType;
	}

	// Property accessors

	public String getStaffNo() {
		return this.staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getOrgNo() {
		return this.orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getOrgType() {
		return this.orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PAccessOrg))
			return false;
		PAccessOrg castOther = (PAccessOrg) other;

		return ((this.getStaffNo() == castOther.getStaffNo()) || (this
				.getStaffNo() != null
				&& castOther.getStaffNo() != null && this.getStaffNo().equals(
				castOther.getStaffNo())))
				&& ((this.getOrgNo() == castOther.getOrgNo()) || (this
						.getOrgNo() != null
						&& castOther.getOrgNo() != null && this.getOrgNo()
						.equals(castOther.getOrgNo())))
				&& ((this.getOrgType() == castOther.getOrgType()) || (this
						.getOrgType() != null
						&& castOther.getOrgType() != null && this.getOrgType()
						.equals(castOther.getOrgType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getStaffNo() == null ? 0 : this.getStaffNo().hashCode());
		result = 37 * result
				+ (getOrgNo() == null ? 0 : this.getOrgNo().hashCode());
		result = 37 * result
				+ (getOrgType() == null ? 0 : this.getOrgType().hashCode());
		return result;
	}

	public OOrg getOrg() {
		return org;
	}

	public void setOrg(OOrg org) {
		this.org = org;
	}

}