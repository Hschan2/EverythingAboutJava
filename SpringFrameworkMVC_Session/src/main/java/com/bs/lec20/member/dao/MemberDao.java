package com.bs.lec20.member.dao;

import java.awt.List;
import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bs.lec20.member.Member;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DriverManagerDataSource;

@Repository
public class MemberDao implements IMemberDao {
	
	// JDBC => 드라이버 로딩 - DB 연결 - SQL 작성 및 전송 - 자원해제
//	private String driver = "oracle.jdbc.driver.OracleDriver";
//	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//	private String userid = "hseongchan2";
//	private String userpw = "448744";
	
	// c3p0 version (Data Connection Pool 사용을 위해)
//	private DriverManagerDataSource dataSource; // For Templete
//	private ComboPooledDataSource dataSource; // ConnectionPool
	private JdbcTemplate template;
	
	
	
	// Spring ver.
	// private org.springframework.jdbc.datasource.DriverManagerDataSource dataSource;
	
//	private Connection conn = null;
//	private PreparedStatement pstmt = null;
//	private ResultSet rs = null;
	
//	private HashMap<String, Member> dbMap;
	
	// servlet-context에서 자동 주입
	@Autowired
	public MemberDao(ComboPooledDataSource dataSource) {
//		dbMap = new HashMap<String, Member>();
//		dataSource = new DriverManagerDataSource();
//		dataSource = new ComboPooledDataSource();
		
		// ComboPooledDataSource를 사용할 때는 try catch 문 (예외 처리 필수)
//		try {
//			dataSource.setDriverClass(driver); // 드라이버 설정
//			dataSource.setJdbcUrl(url); // jdbc url 설정
//			dataSource.setUser(userid); // DB 아이디 설정
//			dataSource.setPassword(userpw); // DB 패스워드 설정
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//		Spring ver.
//		dataSource = new org.springframework.jdbc.datasource.DriverManagerDataSource();
//		dataSource.setDriverClassName(driver);
//		dataSource.setUrl(url);
//		dataSource.setUsername(userid);
//		dataSource.setPassword(userpw);
		
//		template = new JdbcTemplate();
//		template.setDataSource(dataSource);
		this.template = new JdbcTemplate(dataSource);
	}
	
	// 가입
	@Override
	public int memberInsert(Member member) {
		
//		templete 사용 시
		int result = 0;
		
		// final => 외부에서 변경하는 것을 방지
		final String sql = "INSERT INTO member (memId, memPw, memMail) values (?, ?, ?)";
		result = template.update(sql, member.getMemId(), member.getMemPw(), member.getMemMail());
		
//		다른 방법 2
//		result = template.update(new PreparedStatementCreator() {
//		
//		@Override
//		public PreparedStatement createPreparedStatement(Connection conn)
//				throws SQLException {
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, member.getMemId());
//			pstmt.setString(2, member.getMemPw());
//			pstmt.setString(3, member.getMemMail());
//			
//			return pstmt;
//		}
//	});
		
//		다른 방법 3
//		result = template.update(sql, new PreparedStatementSetter() {
//			
//			@Override
//			public void setValues(PreparedStatement pstmt) throws SQLException {
//				pstmt.setString(1, member.getMemId());
//				pstmt.setString(2, member.getMemPw());
//				pstmt.setString(3, member.getMemMail());
//				
//			}
//		});
		
		
//		templete 사용 전
		
//		try { // DB 연결할 때는 무조건 try catch문! (예외 처리 필수)
//			Class.forName(driver); // Driver 로딩
//			conn = DriverManager.getConnection(url, userid, userpw); // DB 연결
//			String sql = "INSERT INTO member (memId, memPw, memMail) values (?, ?, ?)"; // DB 데이터 삽입 쿼리
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, member.getMemId()); // Id 값
//			pstmt.setString(2, member.getMemPw()); // Pw 값
//			pstmt.setString(3, member.getMemMail()); // Mail 값
//			result = pstmt.executeUpdate(); // 삽입 실행
//		} catch (ClassNotFoundException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} finally { // 자원 해제 (연결 해제)
//			try {
//				if(pstmt != null) pstmt.close(); // pstmt가 실행중이면 닫기
//				if(conn != null) conn.close(); // db 연결중이면 끊기
//			} catch (SQLException e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		}
		
//		dbMap.put(member.getMemId(), member);
		return result;
		
	}

	// 로그인
	@Override
	public Member memberSelect(Member member) {
		
		// templete 사용 시
		java.util.List<Member> members = null;
		
		// final => 다른 곳에서 변경되는 경우는 방지하기 위해
		final String sql = "SELECT * FROM member WHERE memId = ? and memPw = ?";
		
		members = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, member.getMemId());
				pstmt.setString(2, member.getMemPw());
			}
		}, new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Member mem = new Member();
				mem.setMemId(rs.getString("memId"));
				mem.setMemPw(rs.getString("memPw"));
				mem.setMemMail(rs.getString("mMailId"));
				mem.setMemPurcNum(rs.getInt("memPurcNum"));
				
				return mem;
			}
			
		});
		
//		다른 방법 1
//		members = template.query(new PreparedStatementCreator() {
//		
//			@Override
//			public PreparedStatement createPreparedStatement(Connection conn)
//				throws SQLException {
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, member.getMemId());
//				pstmt.setString(2, member.getMemPw());
//				return pstmt;
//			}
//		}, new RowMapper<Member>() {
//
//			@Override
//			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Member mem = new Member();
//				mem.setMemId(rs.getString("memId"));
//				mem.setMemPw(rs.getString("memPw"));
//				mem.setMemMail(rs.getString("memMail"));
//				mem.setMemPurcNum(rs.getInt("memPurcNum"));
//				return mem;
//			}
//		});
	
//		다른 방법 2
//		members = template.query(sql, new RowMapper<Member>() {
//
//		@Override
//		public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//			Member mem = new Member();
//			mem.setMemId(rs.getString("memId"));
//			mem.setMemPw(rs.getString("memPw"));
//			mem.setMemMail(rs.getString("memMail"));
//			mem.setMemPurcNum(rs.getInt("memPurcNum"));
//			return mem;
//		}
//		
//	}, member.getMemId(), member.getMemPw());
	
//		다른 방법 3
//		members = template.query(sql, new Object[]{member.getMemId(), member.getMemPw()}, new RowMapper<Member>() {
//
//			@Override
//			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Member mem = new Member();
//				mem.setMemId(rs.getString("memId"));
//				mem.setMemPw(rs.getString("memPw"));
//				mem.setMemMail(rs.getString("memMail"));
//				mem.setMemPurcNum(rs.getInt("memPurcNum"));
//				return mem;
//			}
//		
//		});
		
		if(members.isEmpty()) return null; // members가 없으면 null 리턴
		
		return members.get(0);
		
//		templete 사용 전
		
//		Member mem = null;
		
//		try {
//			Class.forName(driver); 
//			conn = DriverManager.getConnection(url, userid, userpw);
//			String sql = "SELECT * FROM member WHERE memId = ? and memPw = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, member.getMemId());
//			pstmt.setString(2, member.getMemPw());
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				String memId = rs.getString("memid");
//				String memPw = rs.getString("mempw");
//				String memMail = rs.getString("memMail");
//				int memPurcNum = rs.getInt("memPurcNum");
//				
//				mem = new Member();
//				mem.setMemId(memId);
//				mem.setMemPw(memPw);
//				mem.setMemMail(memMail);
//				mem.setMemPurcNum(memPurcNum);
//			}
//		} catch (ClassNotFoundException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} finally {
//			try {
//				if(rs != null) rs.close();
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			} catch (SQLException e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		}
		
//		Member mem = dbMap.get(member.getMemId());
//		return mem;
		
	}

	// 정보 수정
	@Override
	public int memberUpdate(Member member) {
		
//		templete 사용 시
		int result = 0;

		final String sql = "UPDATE member SET memPw = ? AND memMail = ? WHERE memId = ?";
		result = template.update(sql, member.getMemPw(), member.getMemMail(), member.getMemId());
		
//		다른 방법 1
//		result = template.update(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection conn)
//					throws SQLException {
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, member.getMemPw());
//				pstmt.setString(2, member.getMemMail());
//				pstmt.setString(3, member.getMemId());
//				
//				return pstmt;
//			}
//		});
		
//		다른 방법 2
//		result = template.update(sql, new PreparedStatementSetter() {
//					
//			@Override
//			public void setValues(PreparedStatement pstmt) throws SQLException {
//				pstmt.setString(1, member.getMemPw());
//				pstmt.setString(2, member.getMemMail());
//				pstmt.setString(3, member.getMemId());
//			}
//		});
		
//		templete 사용 전
		
//		try {
//			Class.forName(driver); 
//			conn = DriverManager.getConnection(url, userid, userpw);
//			String sql = "UPDATE member SET memPw = ? AND memMail = ? WHERE memId = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, member.getMemPw());
//			pstmt.setString(2, member.getMemMail());
//			pstmt.setString(3, member.getMemId());
//			result = pstmt.executeUpdate();
//		} catch (ClassNotFoundException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} finally {
//			try {
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			} catch (SQLException e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		}
		
//		dbMap.put(member.getMemId(), member);
		return result;
		
	}

	@Override
	public int memberDelete(Member member) {
		
//		templete 사용 시
		int result = 0;

		final String sql = "DELETE member WHERE memId = ? AND memPw = ?";
		result = template.update(sql, member.getMemId(), member.getMemPw());
		
//		다른 방법 1
//		result = template.update(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection conn)
//					throws SQLException {
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, member.getMemId());
//				pstmt.setString(2, member.getMemPw());
//				
//				return pstmt;
//			}
//		});
		
//		다른 방법 2
//		result = template.update(sql, new PreparedStatementSetter() {
//					
//			@Override
//			public void setValues(PreparedStatement pstmt) throws SQLException {
//				pstmt.setString(1, member.getMemId());
//				pstmt.setString(2, member.getMemPw());
//			}
//		});
		
//		templete 사용 전
		
//		try {
//			Class.forName(driver); 
//			conn = DriverManager.getConnection(url, userid, userpw);
//			String sql = "DELETE member WHERE memId = ? AND memPw = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, member.getMemId());
//			pstmt.setString(2, member.getMemPw());
//			result = pstmt.executeUpdate();
//		} catch (ClassNotFoundException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} finally {
//			try {
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();
//			} catch (SQLException e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		}
		
//		dbMap.remove(member.getMemId());
		return result;
		
	}

}
