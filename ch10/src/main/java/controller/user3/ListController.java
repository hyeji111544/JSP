package controller.user3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.User3Service;

@WebServlet("/user3/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	//private User3Service service = User3Service.getInstance();

	@Override
	public void init() throws ServletException {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
