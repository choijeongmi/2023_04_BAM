package container;

import bam.dao.ArticleDao;
import bam.dao.MemberDao;
import bam.service.ArticleService;
import bam.service.MemberService;

public class Container {
<<<<<<< HEAD
	//
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	public static ArticleService articleService;
	public static MemberService memberService;
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		articleService = new ArticleService();
		memberService = new  MemberService();
=======
	
	public static ArticleService articleService;
	public static ArticleDao articleDao;
	public static MemberService memberService;
	public static MemberDao memberDao;
	
	static {
		articleService = new ArticleService();
		articleDao = new ArticleDao();
		memberService = new  MemberService();
		memberDao = new MemberDao();
>>>>>>> 22d1f2dba577cb01243d2476485f692e51bd55f6
	}

}
