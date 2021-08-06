package kr.bit.frontcontroller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

@WebServlet("*.do")
public class MemberFrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청 URL 가져오기
		String url = request.getRequestURI();

		// 서버 컨텍스트경로 가져오기
		String ctx = request.getContextPath();

		// 실질적인 요청 처리
		String command = url.substring(ctx.length());

		// 페이지 분기 시작
		if (command.equals("/memberList.do")) { // 회원 리스트
			MemberDAO dao = new MemberDAO();
			List<MemberVO> list = dao.memberList();
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("member/memberList.jsp");
			rd.forward(request, response);
		} else if (command.equals("/memberInsert.do")) { // 회원가입
			MemberVO vo = new MemberVO();
			MemberDAO dao = new MemberDAO();
			int cnt = 0;
			request.setCharacterEncoding("UTF-8");
			vo.setName(request.getParameter("name"));
			vo.setId(request.getParameter("id"));
			vo.setPass(request.getParameter("pass"));
			vo.setEmail(request.getParameter("email"));
			vo.setPhone(request.getParameter("phone"));
			vo.setAge(Integer.parseInt(request.getParameter("age")));
			cnt = dao.memberInsert(vo);
			if (cnt > 0) {
				response.sendRedirect("/MVC04/memberList.do");
			} else {
				throw new ServletException("Insert Error");
			}
			System.out.println("가입완료");
		} else if (command.equals("/memberRegister.do")) { // 회원 가입화면
			RequestDispatcher rd = request.getRequestDispatcher("member/memberRegister.html");
			rd.forward(request, response);
		} else if (command.equals("/memberContent.do")) { // 회원 수정화면

		} else if (command.equals("/memberUpdate.do")) { // 회원 정보수정

		} else if (command.equals("/memberDelete.do")) { // 회원 삭제

		} // 페이지 분기 종료
	}

}
