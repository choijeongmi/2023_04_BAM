import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static List<Article> articles;
	static int lastArticleId = 0;
	static {
		articles = new ArrayList<>();
	}
	
	public static void main(String[] args) {

		System.out.println("== 프로그램 시작 ==");
		makeTestData();
		Scanner sc = new Scanner(System.in);



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

				Article article = new Article(id, title, body, Util.getDateStr());

				articles.add(article);

				System.out.printf("%d번 글이 생성되었습니다\n", id);

			}

			else if (cmd.equals("article list")) {
				System.out.println("== 게시글 목록 ==");
				if (articles.size() == 0) {
					System.out.println("존재하는 게시물이 없습니다");
					continue;
				}

				System.out.println("번호	|	제목	|		작성날짜");

				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);

					System.out.printf("%d	|	%s	|	%s\n", article.id, article.title, article.regDate);
				}
			}

			else if (cmd.startsWith("article delete ")) {
				System.out.println("== 게시글 삭제 ==");
				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = null;

				for (Article article : articles) {
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				articles.remove(foundArticle); // List, ArrayList 의 요소 삭제 방법 = remove

				System.out.printf("%d번 게시물이 삭제 되었습니다.\n", id);

			}

			else if (cmd.startsWith("article modify ")) {
				System.out.println("== 게시글 수정 ==");

				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);
				Article foundArticle = null;
				for (Article article : articles) {

					if (article.id == id) {
						foundArticle = article;
						break;
					}
					if (foundArticle == null) {
						System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
						continue;

					}

				}
				System.out.printf("수정 할 제목 : ");
				String title = sc.nextLine();
				System.out.printf("수정 할 내용 : ");
				String body = sc.nextLine();

				foundArticle.title = title;
				foundArticle.body = body;

				System.out.printf("%d번 게시물이 수정 되었습니다.\n", id);

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
					if (foundArticle == null) {
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

	private static void makeTestData() {
		System.out.println("테스트용 게시물 데이터 5개 생성");
		
		for (int i = 1; i <= 5; i++) {
			
			int id = lastArticleId + 1;
			lastArticleId = id;
			
			String title = "제목" + i;
			String body = "내용" + i;
			
			Article article = new Article(i, Util.getDateStr(), title, body);
			articles.add(article);

		}

	}

}

class Article {
	int id;
	String title;
	String body;
	String regDate;

	public Article(int id, String title, String body, String regDate) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
	}
}
