package bam.dto;

public class Article {

	public int id;
	public String title;
	public String body;
	public String regDate;

	public  Article(int id, String title, String body, String regDate) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;

	}

}
