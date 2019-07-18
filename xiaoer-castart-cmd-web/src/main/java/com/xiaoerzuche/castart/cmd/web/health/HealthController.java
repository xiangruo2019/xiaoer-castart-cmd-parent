package com.xiaoerzuche.castart.cmd.web.health;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("health")
public class HealthController {
	
	@RequestMapping(value="dialing/monitor",method=RequestMethod.GET)
	@ResponseBody
	public Object health(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		map.put("msg", "操作成功");
		map.put("data", null);
		return map;
	}

}