package com.xiaoerzuche.castart.cmd.vo;

public class ResultVo {
	
	private Integer code=200;
	private String msg;
	public Integer getCode() {
		return code;
	}
	public ResultVo setCode(Integer code) {
		this.code = code;
		return this;
	}
	public String getMsg() {
		return msg;
	}
	public ResultVo setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	@Override
	public String toString() {
		return "ResultVo [code=" + code + ", msg=" + msg + "]";
	}
	
	

}
