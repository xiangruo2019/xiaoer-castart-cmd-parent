package com.xiaoerzuche.castart.cmd.exception;

/**
 * 车辆已经处于离线状态，不能接收指令，这种异常需要外部自己去处理
 * @author Nick C
 *
 */
public class CarHasOffLineException extends Exception {
	private static final long serialVersionUID = 6385607482498927813L;

	public CarHasOffLineException(String msg){
		super(msg);
	}
}
