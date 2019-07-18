package com.xiaoerzuche.castart.cmd.vo;

public class CmdVO {

	String cmd;
	String code;
	String carCard;
	String password;
	String newPassword;
	public String getCmd() {
		return cmd;
	}
	public CmdVO setCmd(String cmd) {
		this.cmd = cmd;
		return this;
	}
	public String getCode() {
		return code;
	}
	public CmdVO setCode(String code) {
		this.code = code;
		return this;
	}
	public String getCarCard() {
		return carCard;
	}
	public CmdVO setCarCard(String carCard) {
		this.carCard = carCard;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public CmdVO setPassword(String password) {
		this.password = password;
		return this;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@Override
	public String toString() {
		return "CmdVO [cmd=" + cmd + ", code=" + code + ", carCard=" + carCard + ", password=" + password
				+ ", newPassword=" + newPassword + "]";
	}
	
	
	

}
