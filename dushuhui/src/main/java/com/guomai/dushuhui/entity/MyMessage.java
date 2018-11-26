package com.guomai.dushuhui.entity;

import org.litepal.crud.DataSupport;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class MyMessage extends LitePalSupport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4214270322857809667L;
	private long id;
	private String text;
	private String sendTime;
	private int cmd;
	/**0是未读，1是已读，2是删除*/
	private int state = 0;
	/**我的ID*/
	private String mineId;

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMineId() {
		return mineId;
	}
	public void setMineId(String mineId) {
		this.mineId = mineId;
	}

	
	
	
}
