package com.blog.dao;

import java.util.List;

import com.blog.entity.Article;

public interface ArticleDao {
	public int add(Article article);
	public int delete(Integer articleid);
	public int edit(Article article);
	public List<Article> getList();
	public List<Article> getUserList(Integer userid);
	public Article getone(Integer articleid);
}
