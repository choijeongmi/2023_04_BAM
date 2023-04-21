package bam.dao;

import java.util.ArrayList;
import java.util.List;

import Util.Util;
import bam.dto.Article;

public class ArticleDao extends Dao {

	private List<Article> articles;
	private int lastArticleId;

	public ArticleDao() {
		this.articles = new ArrayList<>();
		this.lastArticleId = 0;
	}



	public void add(Article article) {
		articles.add(article);
		lastId++;

	}
	
	

	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.id == id) { // 순회해서 일치하는게 있으면
				return article; // 아티클을 리턴하겠다.

			}
		}
		return null;

	}

	public List<Article> getArticles(String searchKeyword) {

		if (searchKeyword.length() > 0) {
			System.out.println("입력된 검색어 : " + searchKeyword);

			List<Article> printArticles = new ArrayList<>();

			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					printArticles.add(article);
				}

			}

			return printArticles;
		}
		return articles;
	}

	public void remove(Article foundArticle) {
		articles.remove(foundArticle);

	}

	public void makeTestData() {

		for (int i = 1; i <= 5; i++) {

			int id = lastArticleId + 1;
			lastArticleId = id;

			String title = "제목" + i;
			String body = "내용" + i;

			Article article = new Article(setLastId(), title, body, 2, Util.getDateStr());
			add(article);
		}

	}

}
