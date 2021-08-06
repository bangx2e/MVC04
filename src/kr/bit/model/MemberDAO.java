package kr.bit.model;

// JDBC ->myBatis, JPA
import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	// 데이터베이스 연결객체 생성
	public void getConnect() {

		String url = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimeZone=UTC";
		String user = "root";
		String password = "admin12345";
		// MySQL 드라이버 로딩
		try {
			// 동적로딩(실행시점에서 객체를 생성하는 방법)
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 회원 저장 동작
	public int memberInsert(MemberVO vo) {
		String sql = "insert into member(id,pass,name,age,email,phone) values(?,?,?,?,?,?)";
		int cnt = -1;
		getConnect();
		// SQL 쿼리문 전송 객체 생성
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPass());
			ps.setString(3, vo.getName());
			ps.setInt(4, vo.getAge());
			ps.setString(5, vo.getEmail());
			ps.setString(6, vo.getPhone());
			// 쿼리문 실행
			cnt = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt; // 1 or 0
	}

	// 회훤 저장 완료

	// 회원(VO)전체 리스트(ArrayList) 가져오기
	public ArrayList<MemberVO> memberList() {
		String sql = "select * from member";
		ArrayList<MemberVO> mem = new ArrayList<MemberVO>();
		getConnect();
		try {
			ps=conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setNum(rs.getInt(1));
				member.setId(rs.getString(2));
				member.setPass(rs.getString(3));
				member.setName(rs.getString(4));
				member.setAge(rs.getInt(5));
				member.setEmail(rs.getString(6));
				member.setPhone(rs.getString(7));
				mem.add(member);
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return mem;
	}
	public int memberDelete(int num) {
		String sql = "delete from member where num=?";
		int rs=-1;
		getConnect();
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1, num);
				rs = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}
		return rs;
	}

	public MemberVO memberContent(int num) {
		String sql = "select * from member where num=?";
		getConnect();
		MemberVO vo = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if(rs.next()) {
			vo=new MemberVO();
			vo.setNum(num);
			vo.setId(rs.getString(2));
			vo.setPass(rs.getString(3));
			vo.setName(rs.getString(4));
			vo.setAge(rs.getInt(5));
			vo.setEmail(rs.getString(6));
			vo.setPhone(rs.getString(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return vo;
		
	}
	
	public int memberUpdate(MemberVO vo) {
		String sql="update member set age=?, email=?, phone=? where num=?";
		getConnect();
		int cnt=-1;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getAge());
			ps.setString(2, vo.getEmail());
			ps.setString(3, vo.getPhone());
			ps.setInt(4, vo.getNum());
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;
	}
	// 데이터베이스 연결 끊기
	public void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
