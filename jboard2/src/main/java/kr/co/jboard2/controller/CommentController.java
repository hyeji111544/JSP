package kr.co.jboard2.controller;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/comment.do")
public class CommentController extends HttpServlet{

	private static final long serialVersionUID = 1412321321321L;
	private ArticleService service= ArticleService.getInstance();
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void init() throws ServletException {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		String parent = req.getParameter("parent");
		System.out.println("deleteNo :" + no);
		System.out.println("deleteparent :" + parent);
		service.deleteComment(no, parent);
		
/*		 ArticleDTO article = service.selectArticle(no);
		 List<ArticleDTO> comments = service.selectComments(parent);
		
		req.setAttribute("no", no);
		req.setAttribute("article", article);
	    req.setAttribute("comments", comments); */
		resp.sendRedirect("/jboard2/view.do?no="+parent);
	}	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArticleDTO comment = new ArticleDTO();
		String parent = req.getParameter("parent");
		String uid = req.getParameter("uid");
		String content = req.getParameter("content");
		String regip = req.getRemoteAddr();

		comment.setParent(Integer.parseInt(parent));
	    comment.setContent(content);
	    comment.setWriter(uid);
	    comment.setRegip(regip);
		service.insertComment(comment);
		
		resp.sendRedirect("/jboard2/view.do?no="+parent);
	
	}
	
}
