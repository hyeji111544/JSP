package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import dto.MemberDTO;

@WebServlet ("/member/register.do")
public class ModifyController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private MemberDAO dao = MemberDAO.getInstance();
	
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("member");
		
		// 수정 사용자 조회
		MemberDTO member = dao.selectMember(member);
		
		req.setAttribute("member", member);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/modify.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
