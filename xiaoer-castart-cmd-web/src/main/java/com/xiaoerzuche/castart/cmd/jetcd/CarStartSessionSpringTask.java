package com.xiaoerzuche.castart.cmd.jetcd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xiaoerzuche.castart.cmd.dao.imp.CommandDaoCarStartImp;
import com.xiaoerzuche.castart.cmd.model.CarstartSession;

import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class CarStartSessionSpringTask {

	private static final Logger logger = LoggerFactory.getLogger(CarStartSessionSpringTask.class);

	@Autowired
	private CommandDaoCarStartImp commandDaoCarStartImp;

	private final long EXPIRE_TIME = 60000L;

	// 处理carstart的会话
	
	@Scheduled(fixedDelay=2000)
	public void execute()  {
		logger.info("CarStartSessionSpringTask execute start........");
		ConcurrentHashMap<String, CarstartSession> socketHolder = commandDaoCarStartImp.getSessions();
		long now = System.currentTimeMillis();
		if (socketHolder != null && socketHolder.size() > 0) {
			for (String key : socketHolder.keySet()) {
				CarstartSession session = null;
				try {
					session = socketHolder.get(key);
					if (session!=null && now - session.getExpireTime() > EXPIRE_TIME) {
						Socket socket = session.getSocket();
						if(socket!=null){
							socket.close();
						}
						socketHolder.remove(key);
						
					}
				} catch (Exception e) {
					logger.info("CarStartSessionScheduleJob execute error key:{},session:{}",
							new Object[] { key, session });
					logger.error("", e);
				}

			}
		}
	}

}
