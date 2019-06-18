//package com.xx.common.util;
package com.hong.tools.SE;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

public class ScnException extends Exception {
	private String errorCode;
	private String errorMsg;
	private Exception exception;
	private transient String trace;

	public ScnException(Exception e) {
		super(e);
		this.exception = e;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return this.errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("错误代码:" + this.getErrorCode());
		sb.append("\r\n");
		sb.append("错误信息:" + this.getErrorMsg());
		sb.append("\r\n");
		sb.append("StackTrace:" + this.getTrace());
		sb.append("\r\n");
		return sb.toString();
	}

	public String getTrace() {
		if(this.trace == null) {
			this.makeTrace();
		}

		return this.trace;
	}

	private void makeTrace() {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		PrintWriter po = new PrintWriter(bo);
		this.exception.printStackTrace(po);
		po.flush();
		byte[] ba = bo.toByteArray();
		po.close();
		bo = null;
		this.trace = new String(ba);
	}
}