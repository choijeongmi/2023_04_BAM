package bam.service;

import java.util.List;

import bam.dao.ArticleDao;
import bam.dto.Article;
import container.Container;

public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public void add(Article article) {
		articleDao.add(article);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public int setLastId() {
		return articleDao.setLastId();
	}

	public List<Article> getArticles(String searchKeyword) {
		return articleDao.getArticles(searchKeyword);

	}

	public void remove(Article foundArticle) {
		articleDao.remove(foundArticle);

	}

	public void makeTestData() {

		articleDao.makeTestData();

	}
<<<<<<< HEAD
//
=======

>>>>>>> 22d1f2dba577cb01243d2476485f692e51bd55f6
}