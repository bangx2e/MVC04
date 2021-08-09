package kr.bit.frontcontroller;

import java.util.HashMap;

import kr.bit.controller.*;

public class HandlerMapping {
	private HashMap<String, Controller> mappings;

	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		mappings.put("/memberList.do", new MemberListController());
		mappings.put("/memberDelete.do", new MemberDeleteController());
		mappings.put("/memberUpdate.do", new MemberUpdateController());
		mappings.put("/memberContent.do", new MemberContentController());
		mappings.put("/memberRegister.do", new MemberRegisterController());
		mappings.put("/memberInsert.do", new MemberInsertController());
	
	}
	public Controller getContoller(String key) { // key => /memberList.do
		return mappings.get(key);
	}
}
