package com.example.chukc.encapsulationframework.network;

import java.io.Serializable;

public class BackResult implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = -6274699282897043903L;
	private int Result;
	private String Msg;
	private String Url;
	public int getResult() {
		return Result;
	}
	public void setResult(int result) {
		Result = result;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}

}
