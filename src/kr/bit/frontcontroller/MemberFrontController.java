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
		
		// 핸들러매핑 -> HandlerMapping
		HandlerMapping mapping = new HandlerMapping();
		controller = mapping.getContoller(command);
		nextPage=controller.requestHandler(request, response);
		
		// forward, redirect 간소화
		if(nextPage.indexOf("redirect")!=-1) {
			response.sendRedirect(nextPage.split(":")[1]); //redirect
		} else {
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);//forward
			rd.forward(request, response);
		}
	}

}
