package com.xiaoerzuche.castart.cmd.dao;

import com.xiaoerzuche.castart.cmd.exception.CarHasOffLineException;
import com.xiaoerzuche.castart.cmd.exception.CarLoginInfoException;

/**
 * 车辆操作命令Dao
 * @author Nick C
 *
 */
public interface CommandDao {
	/**
	 * 锁车指令
	 * @param terminalId	车载终端ID
	 * @param carNo			车牌号
	 * @param pwd			车载终端的密码
	 * @return
	 * @throws CarLoginInfoException 
	 */
	boolean lock(final String terminalId, final String carNo, final String pwd) throws CarHasOffLineException, CarLoginInfoException;
	
	/**
	 * 解锁指令
	 * @param terminalId	车载终端ID
	 * @param carNo			车牌号
	 * @param pwd			车载终端的密码
	 * @return
	 * @throws CarLoginInfoException 
	 */
	boolean unlock(final String terminalId, final String carNo, final String pwd) throws CarHasOffLineException, CarLoginInfoException;
	
	/**
	 * 寻车指令
	 * @param terminalId	车载终端ID
	 * @param carNo			车牌号
	 * @param pwd			车载终端的密码
	 * @return
	 * @throws CarLoginInfoException 
	 */
	boolean find(final String terminalId, final String carNo, final String pwd) throws CarHasOffLineException, CarLoginInfoException;
	
	/**
	 * 切换油路指令
	 * @param terminalId	车载终端ID
	 * @param carNo			车牌号
	 * @param pwd			车载终端的密码
	 * @return
	 * @throws CarLoginInfoException 
	 */
	boolean offGas(final String terminalId, final String carNo, final String pwd) throws CarHasOffLineException, CarLoginInfoException;
	
	/**
	 * 恢复油路指令
	 * @param terminalId	车载终端ID
	 * @param carNo			车牌号
	 * @param pwd			车载终端的密码
	 * @return
	 * @throws CarLoginInfoException 
	 */
	boolean onGas(final String terminalId, final String carNo, final String pwd) throws CarHasOffLineException, CarLoginInfoException;
	boolean start(final String terminalId, final String carNo, final String pwd,String usePwd) throws CarHasOffLineException, CarLoginInfoException;
	boolean end(final String terminalId, final String carNo, final String pwd,String usePwd) throws CarHasOffLineException, CarLoginInfoException;
	
	/**
	 * 重置管理员的数字键盘密码
	 * @param terminalId	车载终端ID
	 * @param carNo			车牌号
	 * @param oldPwd		原始密码
	 * @param newPwd		新密码
	 * @return
	 * @throws CarLoginInfoException 
	 */
	boolean resetAdminPwd(final String terminalId, final String carNo, final String oldPwd, final String newPwd) throws CarHasOffLineException, CarLoginInfoException;
	
	/**
	 * 重置用户的数字键盘密码
	 * @param terminalId	车载终端ID
	 * @param carNo			车牌号
	 * @param oldPwd		原始密码
	 * @param newPwd		新密码
	 * @return
	 * @throws CarLoginInfoException 
	 */
	boolean resetUserPwd(final String terminalId, final String carNo, final String oldPwd, final String newPwd) throws CarHasOffLineException, CarLoginInfoException;
	
	/**
	 * 重置管理员的数字键盘密码
	 * @param terminalId	车载终端ID
	 * @param carNo			车牌号
	 * @param oldPwd		原始密码
	 * @param newPwd		新密码
	 * @return
	 * @throws CarLoginInfoException 
	 */
	boolean restartTerminal(final String terminalId, final String carNo, final String pwd) throws CarHasOffLineException, CarLoginInfoException;
	
	
}
