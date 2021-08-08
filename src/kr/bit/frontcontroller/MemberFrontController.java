package kr.bit.frontcontroller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.controller.Controller;
import kr.bit.controller.MemberContentController;
import kr.bit.controller.MemberDeleteController;
import kr.bit.controller.MemberInsertController;
import kr.bit.controller.MemberListController;
import kr.bit.controller.MemberRegisterController;
import kr.bit.controller.MemberUpdateController;
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

		Controller controller = null;
		String nextPage = null;

		// 페이지 분기 시작
		if (command.equals("/memberList.do")) { // 회원 리스트
			controller = new MemberListController();
			nextPage = controller.requestHandler(request, response);
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);
		} else if (command.equals("/memberInsert.do")) { // 회원가입
			controller = new MemberInsertController();
			nextPage = controller.requestHandler(request, response);
			response.sendRedirect(nextPage);
		} else if (command.equals("/memberRegister.do")) { // 회원 가입화면
			controller = new MemberRegisterController();
			nextPage = controller.requestHandler(request, response);
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);
		} else if (command.equals("/memberContent.do")) { // 회원 수정화면
			controller = new MemberContentController();
			nextPage = controller.requestHandler(request, response);
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);
		} else if (command.equals("/memberUpdate.do")) { // 회원 정보수정
			controller = new MemberUpdateController();
			nextPage = controller.requestHandler(request, response);
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);
		} else if (command.equals("/memberDelete.do")) { // 회원 삭제
			controller = new MemberDeleteController();
			nextPage = controller.requestHandler(request, response);
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);

		} // 페이지 분기 종료
	}

}
