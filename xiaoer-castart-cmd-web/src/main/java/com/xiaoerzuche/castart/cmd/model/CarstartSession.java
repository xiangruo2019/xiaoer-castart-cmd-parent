package com.xiaoerzuche.castart.cmd.model;

import java.net.Socket;


public class CarstartSession {
	
	private  Socket socket ;
	private long expireTime;
	public Socket getSocket() {
		this.expireTime = System.currentTimeMillis();
		return socket;
	}
	public CarstartSession setSocket(Socket socket) {
		this.socket = socket;
		return this;
	}
	public long getExpireTime() {
		return expireTime;
	}
	public CarstartSession setExpireTime(long expireTime) {
		this.expireTime = expireTime;
		return this;
	}
	@Override
	public String toString() {
		return "CarstartSession [socket=" + socket + ", expireTime=" + expireTime + "]";
	}
	
	

}
