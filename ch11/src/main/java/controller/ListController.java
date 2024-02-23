package controller;

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

import dao.MemberDAO;
import dto.MemberDTO;


@WebServlet("/member/list.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO dao = MemberDAO.getInstance();
	
	// Java 기본 로거
	//private Logger logger = Logger.getGlobal();

	// Logback 로거 생성
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init() throws ServletException {
		// 로그 출력
		logger.info("ListController - init()...");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/**
		//로그 레벨 테스트 (자바 기본 Logger를 이용해서 로그를 console에 찍어 볼 것임)
		logger.error("ListController error - doGet()...");
		logger.warn("ListController warn - doGet()...");
		logger.info("ListController info - doGet()...");
		logger.debug("ListController debug - doGet()...");
		logger.trace("ListController trace - doGet()...");
		**/
		List<MemberDTO> members = dao.selectMembers();
		
		req.setAttribute("members", members);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/list.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
