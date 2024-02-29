package kr.co.jboard2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.service.ArticleService;

@WebServlet("/modify.do")
public class ModifyController extends HttpServlet{

	private static final long serialVersionUID = 32423421L;
	private ArticleService service= ArticleService.getInstance();
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Override
	public void init() throws ServletException {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pg = req.getParameter("pg");
		String no = req.getParameter("no");
		System.out.println("modifyPg:" + pg);
		ArticleDTO article = service.selectArticle(no);
		
		req.setAttribute("article", article);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/modify.jsp");
		dispatcher.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String no = req.getParameter("no");
		String pg = req.getParameter("pg");
		
		System.out.println("title: " + title);
		System.out.println("doPostpg: " + pg);
		
		ArticleDTO article = new ArticleDTO();
		article.setTitle(title);
		article.setContent(content);
		article.setNo(no);

		service.updateArticle(article);
		
		resp.sendRedirect("/jboard2/view.do?no="+article.getNo()+"&pg="+pg);
	}
}
