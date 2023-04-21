import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Util.Util;
import bam.controller.ArticleController;
import bam.controller.Controller;
import bam.controller.MemberController;
import bam.dto.Article;
import bam.dto.Member;

public class App {

	private List<Article> articles;
	private List<Member> members;
	private int lastArticleId;
	

	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
		lastArticleId = 0;
		
	}

	public void run() {
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);

		articleController.makeTestData();
		memberController.makeTestData();
		
		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				break;
			}
			
			String[] cmdBits = cmd.split(" ");
			
			if(cmdBits.length == 1) {
				System.out.println("명령어를 확인 해주세요.");
				continue;
			}
			
			String controllerName = cmdBits[0];
			String methodName = cmdBits[1];
			
			Controller controller = null;
			
			if(controllerName.equals("member")) {
				controller = memberController;
				
			}else if(controllerName.equals("article")) {
				controller = articleController;
			}
			else {
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}
			
			controller.doAtcion(cmd, methodName);



		}

		sc.close();

		System.out.println("== 프로그램 끝 ==");

	}


	

	

}
