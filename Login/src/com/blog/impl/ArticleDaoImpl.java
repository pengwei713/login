package com.blog.impl;

import java.util.List;

import com.blog.dao.ArticleDao;
import com.blog.dao.BaseDAO;
import com.blog.entity.Article;

public class ArticleDaoImpl extends BaseDAO implements ArticleDao{

	@Override
	public int add(Article article) {
		String sql = "insert into articletbl(userid,title,authour,gen,publishtime,context,image) value(?,?,?,?,?,?,?)";
		Object[] params = {article.getUserid(),article.getTitle(),article.getAuthour(),article.getGen(),article.getPublishtime(),article.getContext(),article.getImage()};
		return executeUpdate(sql, params);
	}

	@Override
	public int delete(Integer articleid) {
		String sql = "delete from articletbl where articleid = ?";
		Object[] params = {articleid};
		return executeUpdate(sql, params);
	}

	@Override
	public int edit(Article article) {
		String sql = "update articletbl set userid = ?,title = ?,authour = ?,gen = ?,publishtime = ?,context = ?,image = ? where articleid = ?";
		Object[] params = {article.getUserid(),article.getTitle(),article.getAuthour(),article.getGen(),article.getPublishtime(),article.getContext(),article.getImage(),article.getArticleid()};
		return executeUpdate(sql, params);
	}

	@Override
	public List<Article> getList() {
		String sql = "select * from articletbl";
		Object[] params = {};
		return find(sql, params, Article.class);
	}

	@Override
	public Article getone(Integer articleid) {
		String sql = "select * from articletbl where articleid = ?";
		Object[] params = {articleid};
		return find(sql, params, Article.class).get(0);
	}

	@Override
	public List<Article> getUserList(Integer userid) {
		String sql = "select * from articletbl where userid = ?";
		Object[] params = {userid};
		return find(sql, params, Article.class);
	}

}
