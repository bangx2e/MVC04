package kr.bit.frontcontroller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		System.out.println(url);
		String ctx = request.getContextPath();
		System.out.println("CTX : " + ctx);

		String command = url.substring(ctx.length());
		System.out.println("Command : " + command);

		if (command.equals("/memberList.do")) {
			System.out.println("멤리스트 옴");
			MemberDAO dao = new MemberDAO();
			ArrayList<MemberVO> vo = new ArrayList<MemberVO>();
			vo = dao.memberList();
			request.setAttribute("list", vo);
//			System.out.println(vo);
			RequestDispatcher rd = request.getRequestDispatcher("member/memberList.jsp");
			rd.forward(request, response);
		}
	}

}
