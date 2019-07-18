package com.xiaoerzuche.castart.cmd.jetcd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.promises.EtcdResponsePromise;
import mousio.etcd4j.responses.EtcdKeysResponse;

@Service
public class EtcdServerNodeExpireRefreshTimer {

	private static final Logger logger = LoggerFactory.getLogger(EtcdServerNodeExpireRefreshTimer.class);

	@Autowired
	EtcdClient etcdClient;
	
	@Autowired
	ConfigurableApplicationContext context;

	@Value("${etcd.dir}")
	private String etcdDir;

	@Value("${node.ip}")
	private String ip = null;

	@Scheduled(fixedDelay = 1000)
	public void refresh() {
		try {
			EtcdResponsePromise<EtcdKeysResponse> rep = etcdClient.refresh(etcdDir + "/" + ip, 5).send();
			logger.info(rep.get().getNode().getValue());
		} catch (Exception e) {
			logger.error("", e);
			try {
				EtcdResponsePromise<EtcdKeysResponse> rep = etcdClient.put(etcdDir + "/" + ip, ip).send();
				logger.info(rep.get().getNode().getValue());
			} catch (Exception e1) {
				logger.error("", e1);
			}

		}

	}

}
