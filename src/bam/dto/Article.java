package bam.dto;

public class Article {

	public int id;
	public int memberId;
	public String title;
	public String body;
	public String regDate;
	

	public  Article(int id, String title, String body, int memberId, String regDate) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.memberId = memberId;

	}

}
