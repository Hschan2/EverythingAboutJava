package com.bs.lec20.member.dao;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bs.lec20.member.Member;

@Repository
public class MemberDao implements IMemberDao {
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String userid = "hseongchan2";
	private String userpw = "448744";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private HashMap<String, Member> dbMap;
	
	public MemberDao() {
//		dbMap = new HashMap<String, Member>();
	}
	
	// 가입
	@Override
	public int memberInsert(Member member) {
		
		int result = 0;
		
		try { // DB 연결할 때는 무조건 try catch문!
			Class.forName(driver); // Driver 로딩
			conn = DriverManager.getConnection(url, userid, userpw); // DB 연결
			String sql = "INSERT INTO member (memId, memPw, memMail) values (?, ?, ?)"; // DB 데이터 삽입 쿼리
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemId()); // Id 값
			pstmt.setString(2, member.getMemPw()); // Pw 값
			pstmt.setString(3, member.getMemMail()); // Mail 값
			result = pstmt.executeUpdate(); // 삽입 실행
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally { // 자원 해제 (연결 해제)
			try {
				if(pstmt != null) pstmt.close(); // pstmt가 실행중이면 닫기
				if(conn != null) conn.close(); // db 연결중이면 끊기
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
//		dbMap.put(member.getMemId(), member);
		return result;
		
	}

	// 로그인
	@Override
	public Member memberSelect(Member member) {
		
		Member mem = null;
		
		try {
			Class.forName(driver); 
			conn = DriverManager.getConnection(url, userid, userpw);
			String sql = "SELECT * FROM member WHERE memId = ? and memPw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPw());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String memId = rs.getString("memid");
				String memPw = rs.getString("mempw");
				String memMail = rs.getString("memMail");
				int memPurcNum = rs.getInt("memPurcNum");
				
				mem = new Member();
				mem.setMemId(memId);
				mem.setMemPw(memPw);
				mem.setMemMail(memMail);
				mem.setMemPurcNum(memPurcNum);
			}
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
//		Member mem = dbMap.get(member.getMemId());
		return mem;
		
	}

	// 정보 수정
	@Override
	public int memberUpdate(Member member) {
		
		int result = 0;
		
		try {
			Class.forName(driver); 
			conn = DriverManager.getConnection(url, userid, userpw);
			String sql = "UPDATE member SET memPw = ? AND memMail = ? WHERE memId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemPw());
			pstmt.setString(2, member.getMemMail());
			pstmt.setString(3, member.getMemId());
			result = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
//		dbMap.put(member.getMemId(), member);
		return result;
		
	}

	@Override
	public int memberDelete(Member member) {
		
		int result = 0;
		
		try {
			Class.forName(driver); 
			conn = DriverManager.getConnection(url, userid, userpw);
			String sql = "DELETE member WHERE memId = ? AND memPw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPw());
			result = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
//		dbMap.remove(member.getMemId());
		return result;
		
	}

}
