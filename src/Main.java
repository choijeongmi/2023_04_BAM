import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;

		List<Article> articles = new ArrayList<>();

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				break;
			}

			if (cmd.equals("article write")) {
				System.out.println("== 게시글 작성 ==");
				int id = lastArticleId + 1;
				lastArticleId = id;
				
				
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				Date regDate = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				
				System.out.println(formatter.format(regDate));

				Article article = new Article(id, title, body, regDate);

				articles.add(article);

				System.out.printf("%d번 글이 생성되었습니다\n", id);

			}
			else if (cmd.equals("article list")) {
				System.out.println("== 게시글 목록 ==");
				if (articles.size() == 0) {
					System.out.println("존재하는 게시물이 없습니다");
					continue;
				}

				System.out.println("번호	|	제목	");

				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);

					System.out.printf("%d	|	%s	\n", article.id, article.title);
				}
			}

			else if (cmd.startsWith("article detail ")) {
				String[] cmdBits = cmd.split(" "); // array를 쓸 수 없는 이유는 split이 string의 배열이라 사용 불가.
//				int id = cmdBits[2]; // > cmdBits는 string(문자)이므로  int는 안되서 형변환을 해줘야 한다.

				int id = Integer.parseInt(cmdBits[2]);
				Article foundArticle = null;
				for (Article article : articles) {
					
					if (article.id == id) {
						foundArticle = article;
						break;
					}  
					if ( foundArticle == null) {
						System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
						continue;
						
					}
				}
				System.out.println("== 게시글 상세보기 ==");
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("작성날짜 : %s\n", foundArticle.regDate);

			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}
//				split : 문장 자르기 
		}

		sc.close();

		System.out.println("== 프로그램 끝 ==");

	}


}

class Article {
	int id;
	String title;
	String body;
	Date regDate;

	public Article(int id, String title, String body, Date regDate) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
	}
}
