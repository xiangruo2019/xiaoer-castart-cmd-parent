package com.xiaoerzuche.castart.cmd.enu;

/**
 * 错误代码枚举类
 *
 */
public enum ErrorCode {
	/*
	 * 3xx 重定向 301 已移动 — 请求的数据具有新的位置且更改是永久的。 302 已找到 — 请求的数据临时具有不同 URI。 303
	 * 请参阅其它 — 可在另一 URI 下找到对请求的响应，且应使用 GET 方法检索此响应。 304 未修改 — 未按预期修改文档。 305 使用代理
	 * — 必须通过位置字段中提供的代理来访问请求的资源。 306 未使用 — 不再使用；保留此代码以便将来使用。 4xx 客户机中出现的错误 400
	 * 错误请求 — 请求中有语法问题，或不能满足请求。 401 未授权 — 未授权客户机访问数据。 402 需要付款 — 表示计费系统已有效。 403
	 * 禁止 — 即使有授权也不需要访问。 404 找不到 — 服务器找不到给定的资源；文档不存在。 405 资源被禁止 406 无法接受 407
	 * 代理认证请求 — 客户机首先必须使用代理认证自身。 412 先决条件失败 415 介质类型不受支持 —
	 * 服务器拒绝服务请求，因为不支持请求实体的格式。 5xx 服务器中出现的错误 500 内部错误 — 因为意外情况，服务器不能完成请求。 501
	 * 未执行 — 服务器不支持请求的工具。 502 错误网关 — 服务器接收到来自上游服务器的无效响应。 503 无法获得服务 —
	 * 由于临时过载或维护，服务器无法处理请求。
	 */
	PARAM(400, "参数错误"), NOLOGIN(401, "未授权登录"), NEED_PAY(402, "需要支付才能访问"), LIMIT(403, "禁止访问"), NOT_FOUND(404,
			"找不到对应的记录"), FORBID(405, "方法禁止访问"), NO_ACCEPT(406, "不接受的请求"), NOENOUGH(412, "先决条件失败"), UNKOWN(500,
					"服务器异常"), NO_RUN(501, "指令未执行");

	// 错误代码
	private int errorCode;
	// 错误消息
	private String message;

	private ErrorCode(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return String.valueOf("errorCode为：" + this.errorCode + "  meaasge为：" + this.message);
	}
}