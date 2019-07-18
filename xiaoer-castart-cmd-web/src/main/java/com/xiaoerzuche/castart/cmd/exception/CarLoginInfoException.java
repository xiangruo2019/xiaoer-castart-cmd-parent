package com.xiaoerzuche.castart.cmd.exception;

/**
 * 车辆发送指令前的登陆信息异常
 * @author Nick C
 *
 */
public class CarLoginInfoException extends Exception {
	private static final long serialVersionUID = 6385607482498927816L;

	public CarLoginInfoException(String msg){
		super(msg);
	}
}
