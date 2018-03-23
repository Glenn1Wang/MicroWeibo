package com.example.wangguilong.microweibo.bean;

import java.util.List;

/**
 * Created by WangGuiLong on 2018/3/10.
 */

public class HomeBean {
	private String userName;
	private String text;
	private int repostsCount; //转发数
	private int commentsCount; //评论数
	private int attitudesCount; //表态数
//	private String created_at;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getRepostsCount() {
		return repostsCount;
	}

	public void setRepostsCount(int repostsCount) {
		this.repostsCount = repostsCount;
	}

	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	public int getAttitudesCount() {
		return attitudesCount;
	}

	public void setAttitudesCount(int attitudesCount) {
		this.attitudesCount = attitudesCount;
	}
}