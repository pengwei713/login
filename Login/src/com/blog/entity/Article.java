package com.blog.entity;

public class Article {
	private Integer articleid;
	private Integer userid;
	private String title;
	private String authour;
	private String gen;
	private String publishtime;
	private String context;
	private String image;
	public Integer getArticleid() {
		return articleid;
	}
	public void setArticleid(Integer articleid) {
		this.articleid = articleid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthour() {
		return authour;
	}
	public void setAuthour(String authour) {
		this.authour = authour;
	}
	public String getGen() {
		return gen;
	}
	public void setGen(String gen) {
		this.gen = gen;
	}
	public String getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Article(Integer articleid, Integer userid, String title, String authour, String gen, String publishtime,
			String context, String image) {
		super();
		this.articleid = articleid;
		this.userid = userid;
		this.title = title;
		this.authour = authour;
		this.gen = gen;
		this.publishtime = publishtime;
		this.context = context;
		this.image = image;
	}
	public Article() {
		super();
	}
}
