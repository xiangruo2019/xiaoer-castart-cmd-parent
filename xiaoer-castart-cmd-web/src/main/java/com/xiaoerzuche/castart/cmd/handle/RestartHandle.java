package com.xiaoerzuche.castart.cmd.handle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.xiaoerzuche.castart.cmd.dao.CommandDao;
import com.xiaoerzuche.castart.cmd.enu.ErrorCode;
import com.xiaoerzuche.castart.cmd.exception.CarHasOffLineException;
import com.xiaoerzuche.castart.cmd.exception.CarLoginInfoException;
import com.xiaoerzuche.castart.cmd.vo.CmdVO;
import com.xiaoerzuche.castart.cmd.vo.ResultVo;

@Component
public class RestartHandle extends BaseHandle<ResultVo>{
	
	@Autowired
	CommandDao commandDao;

	@Override
	public ResultVo handle(CmdVO vo) {

		try {
			boolean result = commandDao.restartTerminal(vo.getCode(), vo.getCarCard(), vo.getPassword());
			if(result){
				return new ResultVo();
			}
		} catch (CarHasOffLineException e) {
			return new ResultVo().setCode(ErrorCode.NO_ACCEPT.getErrorCode()).setMsg("设备处于离线状态，指令执行失败");
		} catch (CarLoginInfoException e) {
			return new ResultVo().setCode(ErrorCode.NO_ACCEPT.getErrorCode()).setMsg("设备操作密码不正确");
		}
		return new ResultVo().setCode(ErrorCode.UNKOWN.getErrorCode()).setMsg("设备操作失败");
	
	}

}
