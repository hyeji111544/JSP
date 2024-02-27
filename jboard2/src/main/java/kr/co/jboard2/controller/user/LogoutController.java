package kr.co.jboard2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.UserService;

@WebServlet ("/user/logout.do")
public class LogoutController extends HttpServlet{

	private static final long serialVersionUID = 3412314141L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void init() throws ServletException {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//로그아웃 처리(세션 해제)
		HttpSession session = req.getSession();

		session.removeAttribute("sessUser");
		session.invalidate();
		
		resp.sendRedirect("/jboard2/user/login.do");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}


}
