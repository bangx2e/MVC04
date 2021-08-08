package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberInsertController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		String nextPage = null;
		if (cnt > 0) {
			nextPage = "/MVC04/memberList.do";
		} else {
			throw new ServletException("Insert Error");
		}
		return nextPage;
	}

}
