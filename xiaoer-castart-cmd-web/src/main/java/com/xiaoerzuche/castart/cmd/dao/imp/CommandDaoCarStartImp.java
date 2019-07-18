package com.xiaoerzuche.castart.cmd.dao.imp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xiaoerzuche.castart.cmd.boot.App;
import com.xiaoerzuche.castart.cmd.dao.CommandDao;
import com.xiaoerzuche.castart.cmd.enu.ErrorCode;
import com.xiaoerzuche.castart.cmd.exception.CarHasOffLineException;
import com.xiaoerzuche.castart.cmd.exception.CarLoginInfoException;
import com.xiaoerzuche.castart.cmd.exception.ErrorCodeException;
import com.xiaoerzuche.castart.cmd.model.CarstartSession;
import com.xiaoerzuche.castart.cmd.util.CheckUtil;

@Repository
public class CommandDaoCarStartImp implements CommandDao {
	ConcurrentHashMap<String, CarstartSession> socketHolder = new ConcurrentHashMap<String, CarstartSession>();
	private static final Logger	logger	= LoggerFactory.getLogger(CommandDaoCarStartImp.class);
	// 卡斯达特前置机的IP
	@Value("${carStart.ip}")
	private String ip;

	// 卡斯达特前置机的监听端口
	@Value("${carStart.port}")
	private String port;

	public static void main(String[] args) throws CarHasOffLineException, CarLoginInfoException {

		CommandDaoCarStartImp a = new CommandDaoCarStartImp();
		a.ip = "112.74.104.89";
		a.port = "40001";
		// a.find("35ffd6054342363823582543", "京L12345", "123456");
		// System.out.println(a.offGas("37ffde054d4d353332531551", "琼A1EV39",
		// "123456"));
		for (int i = 0; i < 10; i++) {
			System.out.println(a.lock("35ffdb05484d303403720643", "琼A0085X", "123456"));
			System.out.println(a.offGas("35ffdb05484d303403720643", "琼A0085X", "123456"));
			System.out.println(a.onGas("35ffdb05484d303403720643", "琼A0085X", "123456"));
			System.out.println(a.unlock("35ffdb05484d303403720643", "琼A0085X", "123456"));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean lock(final String terminalId, final String carNo, final String pwd)
			throws CarHasOffLineException, CarLoginInfoException {
		Map<String, String> response = (Map<String, String>) this.execute(new Invoker() {
			@Override
			public void execute(Socket socket) throws IOException {
				socket.getOutputStream().write(
						("SEND_CMD|" + carNo + "|" + pwd + "|" + terminalId + "|CMD12_00~NG|||||#").getBytes("gbk"));
			}

			@Override
			public String characters() {
				return "锁车动作已经执行";
			}
		}, terminalId, carNo, pwd);
		return response.containsKey("锁车动作已经执行");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean unlock(final String terminalId, final String carNo, final String pwd)
			throws CarHasOffLineException, CarLoginInfoException {
		Map<String, String> response = (Map<String, String>) this.execute(new Invoker() {
			@Override
			public void execute(Socket socket) throws IOException {
				socket.getOutputStream().write(
						("SEND_CMD|" + carNo + "|" + pwd + "|" + terminalId + "|CMD13_00~NG|||||#").getBytes("gbk"));
			}

			@Override
			public String characters() {
				return "解锁动作已经执行";
			}
		}, terminalId, carNo, pwd);
		return response.containsKey("解锁动作已经执行");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean find(final String terminalId, final String carNo, final String pwd)
			throws CarHasOffLineException, CarLoginInfoException {
		Map<String, String> response = (Map<String, String>) this.execute(new Invoker() {
			@Override
			public void execute(Socket socket) throws IOException {
				socket.getOutputStream().write(
						("SEND_CMD|" + carNo + "|" + pwd + "|" + terminalId + "|CMD14_00~NG|||||#").getBytes("gbk"));
			}

			@Override
			public String characters() {
				return "寻车动作已经执行";
			}
		}, terminalId, carNo, pwd);
		return response.containsKey("寻车动作已经执行");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean offGas(final String terminalId, final String carNo, final String pwd)
			throws CarHasOffLineException, CarLoginInfoException {
		Map<String, String> response = (Map<String, String>) this.execute(new Invoker() {
			@Override
			public void execute(Socket socket) throws IOException {
				socket.getOutputStream().write(
						("SEND_CMD|" + carNo + "|" + pwd + "|" + terminalId + "|CMD16_00~NG|||||#").getBytes("gbk"));
			}

			@Override
			public String characters() {
				return "切断油电路动作已经执行";
			}
		}, terminalId, carNo, pwd);
		return response.containsKey("切断油电路动作已经执行");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean onGas(final String terminalId, final String carNo, final String pwd)
			throws CarHasOffLineException, CarLoginInfoException {
		Map<String, String> response = (Map<String, String>) this.execute(new Invoker() {
			@Override
			public void execute(Socket socket) throws IOException {
				socket.getOutputStream().write(
						("SEND_CMD|" + carNo + "|" + pwd + "|" + terminalId + "|CMD17_00~NG|||||#").getBytes("gbk"));
			}

			@Override
			public String characters() {
				return "恢复油电路动作已经执行";
			}
		}, terminalId, carNo, pwd);
		return response.containsKey("恢复油电路动作已经执行");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean resetAdminPwd(final String terminalId, final String carNo, final String oldPwd, final String newPwd)
			throws CarHasOffLineException, CarLoginInfoException {
		Map<String, String> response = (Map<String, String>) this.execute(new Invoker() {
			@Override
			public void execute(Socket socket) throws IOException {
				socket.getOutputStream().write(
						("SEND_CMD|" + carNo + "|" + oldPwd + "|" + terminalId + "|CMD22_00~Admin~" + newPwd + "|||||#")
								.getBytes("gbk"));
			}

			@Override
			public String characters() {
				return "PC_CLIENT|RCVD_DEV";
			}
		}, terminalId, carNo, oldPwd);
		return response.get("PC_CLIENT|RCVD_DEV").contains("ADMIN_KEY设置成功");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean resetUserPwd(final String terminalId, final String carNo, final String oldPwd, final String newPwd)
			throws CarHasOffLineException, CarLoginInfoException {
		Map<String, String> response = (Map<String, String>) this.execute(new Invoker() {
			@Override
			public void execute(Socket socket) throws IOException {
				socket.getOutputStream().write(
						("SEND_CMD|" + carNo + "|" + oldPwd + "|" + terminalId + "|CMD22_00~User~" + newPwd + "|||||#")
								.getBytes("gbk"));
			}

			@Override
			public String characters() {
				return "PC_CLIENT|RCVD_DEV";
			}
		}, terminalId, carNo, oldPwd);
		return response.get("PC_CLIENT|RCVD_DEV").contains("USER_KEY设置成功");
	}

	@Override
	public boolean restartTerminal(final String terminalId, final String carNo, final String pwd)
			throws CarHasOffLineException, CarLoginInfoException {
		this.execute(new Invoker() {
			@Override
			public void execute(Socket socket) throws IOException {
				socket.getOutputStream().write(
						("SEND_CMD|" + carNo + "|" + pwd + "|" + terminalId + "|CMD26_00~NG|||||#").getBytes("gbk"));
			}

			@Override
			public String characters() {
				return "PC_CLIENT";// 相当于直接就放弃所有的response了
			}
		}, terminalId, carNo, pwd);
		return true;
	}

	private interface Invoker {
		public void execute(Socket socket) throws IOException;

		/**
		 * 获取协议关心的返回特征码
		 * 
		 * @return
		 */
		public String characters();
	}

	private Object execute(Invoker invoker, String terminalId, String carNo, String pwd)
			throws CarLoginInfoException, CarHasOffLineException {
		Socket socket = null;
		try {
			socket = getSocket(ip, port, carNo, pwd, terminalId);
		} catch (Exception e) {
			logger.error("", e);
			throw new ErrorCodeException(ErrorCode.NO_RUN.getErrorCode(),
					"服务器连接异常，请检查服务器是否正在运行,ip=" + ip + ",port=" + port);
		}
		try {
			invoker.execute(socket);
			String ret = null;
			Map<String, String> response = new HashMap<String, String>();
			// 接收服务器的反馈
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "gbk"));
			String characterStr = invoker.characters();
			String[] characters = StringUtils.split(characterStr, ",");
			while ((ret = br.readLine()) != null) {
				logger.info("服务器端的response为: " + ret);
				for (String character : characters) {
					if (ret.contains(character) && !ret.contains("状态=")) {
						response.put(character, ret);
						break;
					}
				}
				if (ret != null && response.size() == characters.length) {// 当目标特征都取完了就可以断开连接然后返回了
					break;
				}
			}
			Thread.sleep(4000);
			return response;
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException("指令执行失败,ip=" + ip + ",port=" + port);
		}
	}

	private Socket getSocket(String ip, String port, String carNo, String pwd, String terminalId)
			throws UnknownHostException, IOException, CarLoginInfoException {
		try {
			CarstartSession session = socketHolder.get(carNo);
			Socket socket = session == null ? null : session.getSocket();
			boolean result = isSocketNotEffective(socket, carNo, pwd, terminalId);
			if (result) {
				socket = new Socket(ip, NumberUtils.toInt(port));
				socket.setSoTimeout(60000);
				CheckUtil.assertTrue(socket.isConnected(), ErrorCode.NO_RUN.getErrorCode(),
						"服务器连接异常，请检查服务器是否正在运行,ip=" + ip + ",port=" + port);
				socket.getOutputStream()
						.write(("DENG_LU|" + carNo + "|" + pwd + "|" + terminalId + "||||#").getBytes("gbk"));
				socket.getOutputStream().write(
						("GET_DEV_HEART_TIME|" + carNo + "|" + pwd + "|" + terminalId + "|||||#").getBytes("gbk"));

				// 接收服务器的反馈
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "gbk"));
				String ret = br.readLine();
				logger.info(ret);
				if (ret.startsWith("PC_CLIENT|DENG_LU") && StringUtils.split(ret, "|")[2].equalsIgnoreCase("OK")) {
					logger.info("登录成功");
					socketHolder.put(carNo,
							new CarstartSession().setSocket(socket).setExpireTime(System.currentTimeMillis()));
				} else {
					logger.error("登录失败，请确认登录信息是否正确：terminalId=" + terminalId + " carNo=" + carNo + " pwd=" + pwd);
					throw new CarLoginInfoException("登陆信息不正确，请检查登录信息是否正确");
				}
			}
			return socket;
		} catch (Exception e) {
			logger.error("", e);
		}
		throw new CarLoginInfoException("登陆信息不正确，请检查登录信息是否正确");
	}

	private boolean isSocketNotEffective(Socket socket, String carNo, String pwd, String terminalId) {

		logger.info("进入isSocketNotEffective方法" + "carNo:" + carNo + ",terminalId:" + terminalId);
		if (socket == null || socket.isClosed()) {
			logger.info(
					"结束isSocketNotEffective方法" + "carNo:" + carNo + ",terminalId:" + terminalId + ",result:" + true);
			return true;
		} else {
			try {
				socket.getOutputStream().write(
						("GET_DEV_HEART_TIME|" + carNo + "|" + pwd + "|" + terminalId + "|||||#").getBytes("gbk"));
			} catch (java.net.SocketException se) {
				logger.error(carNo + "," + terminalId + "," + "carNo，terminalId，socket不可用,socke异常", se);
				try {
					socket.close();
				} catch (IOException e) {
					logger.error(carNo + "," + terminalId + "," + "carNo，terminalId，socket不可用，关闭socket时的io异常", e);
				}
				socketHolder.remove(carNo);
			} catch (IOException io) {
				logger.error(carNo + "," + terminalId + "," + "carNo，terminalId，socket不可用,io异常", io);
			}
		}
		logger.info("结束isSocketNotEffective方法" + "carNo:" + carNo + ",terminalId:" + terminalId + ",result:" + false);
		return false;
	}

	@Override
	public boolean start(final String terminalId, final String carNo, final String pwd, final String usePwd)
			throws CarHasOffLineException, CarLoginInfoException {
		Map<String, String> response = (Map<String, String>) this.execute(new Invoker() {
			@Override
			public void execute(Socket socket) throws IOException {
				socket.getOutputStream().write(
						("SEND_CMD|" + carNo + "|" + pwd + "|" + terminalId + "|CMD22_00~Start~" + usePwd + "|||||#")
								.getBytes("gbk"));
			}

			@Override
			public String characters() {
				return "PC_CLIENT|RCVD_DEV";
			}
		}, terminalId, carNo, pwd);
		return response.get("PC_CLIENT|RCVD_DEV").contains("订单开始成功");
	}

	@Override
	public boolean end(final String terminalId, final String carNo, final String pwd, final String usePwd)
			throws CarHasOffLineException, CarLoginInfoException {

		Map<String, String> response = (Map<String, String>) this.execute(new Invoker() {
			@Override
			public void execute(Socket socket) throws IOException {
				socket.getOutputStream().write(
						("SEND_CMD|" + carNo + "|" + pwd + "|" + terminalId + "|CMD22_00~Stop~" + usePwd + "|||||#")
								.getBytes("gbk"));
			}

			@Override
			public String characters() {
				return "PC_CLIENT|RCVD_DEV";
			}
		}, terminalId, carNo, pwd);
		if (response.get("PC_CLIENT|RCVD_DEV").contains("订单结束成功")) {
			return true;
		}
		if (response.get("PC_CLIENT|RCVD_DEV").contains("订单结束失败")) {
			return false;
		}
		return false;

	}

	public ConcurrentHashMap<String, CarstartSession> getSessions() {
		return socketHolder;
	}
}
