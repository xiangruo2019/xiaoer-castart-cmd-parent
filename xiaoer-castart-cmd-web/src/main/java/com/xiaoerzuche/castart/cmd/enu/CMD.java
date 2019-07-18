package com.xiaoerzuche.castart.cmd.enu;

import com.xiaoerzuche.castart.cmd.handle.CloseHandle;
import com.xiaoerzuche.castart.cmd.handle.FindHandle;
import com.xiaoerzuche.castart.cmd.handle.OffGasHandle;
import com.xiaoerzuche.castart.cmd.handle.OnGasHandle;
import com.xiaoerzuche.castart.cmd.handle.OpenHandle;
import com.xiaoerzuche.castart.cmd.handle.ResetAdminPwdHandle;
import com.xiaoerzuche.castart.cmd.handle.ResetUserPwdHandle;
import com.xiaoerzuche.castart.cmd.handle.RestartHandle;

public enum CMD {
	
	lock("lock",CloseHandle.class),
	unlock("unlock",OpenHandle.class),
	on("on",OnGasHandle.class),
	off("off",OffGasHandle.class),
	find("find",FindHandle.class),
	resetUserPwd("resetUserPwd",ResetUserPwdHandle.class),
	restart("restart",RestartHandle.class),
	resetAdminPwd("resetAdminPwd",ResetAdminPwdHandle.class)
	;
	private Class<?> handleClazz;
	private String code;

	private CMD(String code,Class<?> clazz) {
		this.code = code;
		this.handleClazz = clazz;
	}

	public Class<?> getHandleClazz() {
		return handleClazz;
	}
	
	
	public String getCode() {
		return code;
	}

	public static CMD get(String code){
		
		for (CMD each : CMD.values()) {
			if(each.getCode().equals(code)){
				return each;
			}
		}
		return null;
		
	}
	
}
