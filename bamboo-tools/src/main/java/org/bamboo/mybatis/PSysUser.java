package org.bamboo.mybatis;

import java.util.Date;

public class PSysUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String staffNo;
	private String empNo;
	private String orgNo;
	private String deptNo;
	private String name;
	private String pwd;
	private String accessLevel;
	private String ip;
	private String mac;
	private String curStatusCode;
	private Date pwdRemindTime;
	private Date lockTime;
	private Date planUnlockTime;
	private Date unlockTime;
	private String lockIp;
	private String autoUnlockFlag;
	private String lockReason;
	private String unlockEmpNo;
	private String mobileNo;
	private String accessSvg;
	private Date createDate;
	private Date lastLoginDate;
	private Integer loginCnt;
	private String resetPwdFlag;
	
	private OOrg org;
	
	// Constructors

	/** default constructor */
	public PSysUser() {
	}

	/** minimal constructor */
	public PSysUser(String empNo, String deptNo) {
		this.empNo = empNo;
		this.deptNo = deptNo;
	}

	/** full constructor */
	public PSysUser(String empNo, String orgNo, String deptNo, String name,
			String pwd, String ip, String mac, String curStatusCode,
			Date pwdRemindTime, Date lockTime, Date planUnlockTime,
			Date unlockTime, String lockIp, String autoUnlockFlag,
			String lockReason, String unlockEmpNo, String accessSvg, Date createDate, 
			Date lastLoginDate, Integer loginCnt, String resetPwdFlag) {
		this.empNo = empNo;
		this.orgNo = orgNo;
		this.deptNo = deptNo;
		this.name = name;
		this.pwd = pwd;
		this.ip = ip;
		this.mac = mac;
		this.curStatusCode = curStatusCode;
		this.pwdRemindTime = pwdRemindTime;
		this.lockTime = lockTime;
		this.planUnlockTime = planUnlockTime;
		this.unlockTime = unlockTime;
		this.lockIp = lockIp;
		this.autoUnlockFlag = autoUnlockFlag;
		this.lockReason = lockReason;
		this.unlockEmpNo = unlockEmpNo;
		this.accessSvg = accessSvg;
		this.createDate = createDate;
		this.lastLoginDate = lastLoginDate;
		this.loginCnt = loginCnt;
		this.resetPwdFlag = resetPwdFlag;
	}

	// Property accessors

	public String getStaffNo() {
		return this.staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getEmpNo() {
		return this.empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getOrgNo() {
		return this.orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getCurStatusCode() {
		return this.curStatusCode;
	}

	public void setCurStatusCode(String curStatusCode) {
		this.curStatusCode = curStatusCode;
	}

	public Date getPwdRemindTime() {
		return this.pwdRemindTime;
	}

	public void setPwdRemindTime(Date pwdRemindTime) {
		this.pwdRemindTime = pwdRemindTime;
	}

	public Date getLockTime() {
		return this.lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	public Date getPlanUnlockTime() {
		return this.planUnlockTime;
	}

	public void setPlanUnlockTime(Date planUnlockTime) {
		this.planUnlockTime = planUnlockTime;
	}

	public Date getUnlockTime() {
		return this.unlockTime;
	}

	public void setUnlockTime(Date unlockTime) {
		this.unlockTime = unlockTime;
	}

	public String getLockIp() {
		return this.lockIp;
	}

	public void setLockIp(String lockIp) {
		this.lockIp = lockIp;
	}

	public String getAutoUnlockFlag() {
		return this.autoUnlockFlag;
	}

	public void setAutoUnlockFlag(String autoUnlockFlag) {
		this.autoUnlockFlag = autoUnlockFlag;
	}

	public String getLockReason() {
		return this.lockReason;
	}

	public void setLockReason(String lockReason) {
		this.lockReason = lockReason;
	}

	public String getUnlockEmpNo() {
		return this.unlockEmpNo;
	}

	public void setUnlockEmpNo(String unlockEmpNo) {
		this.unlockEmpNo = unlockEmpNo;
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setAccessSvg(String accessSvg) {
		this.accessSvg = accessSvg;
	}

	public String getAccessSvg() {
		return accessSvg;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Integer getLoginCnt() {
		return loginCnt;
	}

	public void setLoginCnt(Integer loginCnt) {
		this.loginCnt = loginCnt;
	}

	public String getResetPwdFlag() {
		return resetPwdFlag;
	}

	public void setResetPwdFlag(String resetPwdFlag) {
		this.resetPwdFlag = resetPwdFlag;
	}

	public OOrg getOrg() {
		return org;
	}

	public void setOrg(OOrg org) {
		this.org = org;
	}
	

}