package com.xiaoerzuche.castart.cmd.web.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoerzuche.castart.cmd.enu.CMD;
import com.xiaoerzuche.castart.cmd.enu.ErrorCode;
import com.xiaoerzuche.castart.cmd.handle.BaseHandle;
import com.xiaoerzuche.castart.cmd.util.CheckUtil;
import com.xiaoerzuche.castart.cmd.vo.CmdVO;
import com.xiaoerzuche.castart.cmd.vo.ResultVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "carstart controller", tags = { "命令操作接口接口" })
@RestController
public class CarstartCmdController {
	
	private static final Logger logger = LoggerFactory.getLogger(CarstartCmdController.class);
	
	@Autowired
	ApplicationContext context;
	
	@ApiOperation("指令发送")
	@RequestMapping(value = "/rpc/v1.0/cmd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResultVo open(@RequestBody CmdVO vo,String carCard
					) {
		logger.info("CarstartCmdController cmd:{}", vo);
		vo.setCarCard(carCard);
		logger.info("CarstartCmdController cmd:{}", vo);
		CheckUtil.assertNotNull(vo.getCmd(), ErrorCode.PARAM.getErrorCode(), "命令类型不能为空");
		CheckUtil.assertNotNull(vo.getCode(), ErrorCode.PARAM.getErrorCode(), "设备信息不能为空");
		CheckUtil.assertNotNull(vo.getCarCard(), ErrorCode.PARAM.getErrorCode(), "车牌号不能为空");
		CheckUtil.assertNotNull(vo.getPassword(), ErrorCode.PARAM.getErrorCode(), "密码不能为空");
		CMD cmdEnum = CMD.get(vo.getCmd());
		
		CheckUtil.assertNotNull(cmdEnum, ErrorCode.PARAM.getErrorCode(), "命令类型错误");
		logger.info("CarstartCmdController cmd:{}", vo);
		BaseHandle<ResultVo> handle = (BaseHandle<ResultVo>) context.getBean(cmdEnum.getHandleClazz());
		return handle.handle(vo);
	}

}
