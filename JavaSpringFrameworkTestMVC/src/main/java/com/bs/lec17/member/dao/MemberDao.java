package com.bs.lec17.member.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bs.lec17.member.Member;
import com.mchange.v2.c3p0.ComboPooledDataSource;

//@Component
@Repository
public class MemberDao implements IMemberDao {

	private JdbcTemplate template;
	
	@Autowired
	public MemberDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int memberInsert(Member member) {
		
		int result = 0;
		
		final String sql = "INSERT INTO memjoin (memId, memPw, memMail, memPhones, memAge, memAdult, memGender, memFSports) values (?, ?, ?, ?, ?, ?, ?, ?)";
		result = template.update(sql, member.getMemId(), member.getMemPw(), member.getMemMail(), member.getMemPhones(), member.getMemAge(), member.isMemAdult(), member.getMemGender(), member.getMemFSports());
		
		return result;
	}

	@Override
	public Member memberSelect(Member member) {
		
		List<Member> members = null;
		
		final String sql = "SELECT * FROM memjoin WHERE memId = ? AND memPw = ?";
		
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
				mem.setMemMail(rs.getString("memMail"));
				mem.setMemPhones(rs.getString("memPhones"));
				mem.setMemAge(rs.getInt("memAge"));
				mem.setMemAdult(rs.getBoolean("memAdult"));
				mem.setMemGender(rs.getString("memGender"));
				mem.setMemFSports(rs.getString("memFSports"));

				return mem;
			}
			
		});

		if(members.isEmpty()) return null; // members가 없으면 null 리턴
		
		return members.get(0);
	}

	@Override
	public int memberUpdate(Member member) {
		
		int result = 0;

		final String sql = "UPDATE memjoin SET memPw = ? AND memMail = ? AND memPhones = ? AND memAge = ? AND memAdult = ? AND memGender = ? AND memFSports = ? WHERE memId = ?";
		result = template.update(sql, member.getMemPw(), member.getMemMail(), member.getMemPhones(), member.getMemAge(), member.isMemAdult(), member.getMemGender(), member.getMemFSports(), member.getMemId());
		
		return result;
	}

	@Override
	public int memberDelete(Member member) {
		
		int result = 0;

		final String sql = "DELETE memjoin WHERE memId = ? AND memPw = ?";
		result = template.update(sql, member.getMemId(), member.getMemPw());
		
		
		return result;
	}
	
//	** 이전 버전
	
//	private HashMap<String, Member> dbMap;
//	
//	public MemberDao() {
//		dbMap = new HashMap<String, Member>();
//	}
//	
//	@Override
//	public void memberInsert(String memId, String memPw, String memMail, String memPhone1, String memPhone2, String memPhone3) {
//		System.out.println("memberInsert()");
//		System.out.println("memId : " + memId);
//		System.out.println("memPw : " + memPw);
//		System.out.println("memMail : " + memMail);
//		System.out.println("memPhone : " + memPhone1 + " - " + memPhone2 + " - " + memPhone3);
//		
//		Member member = new Member();
//		member.setMemId(memId);
//		member.setMemPw(memPw);
//		member.setMemMail(memMail);
//		member.setMemPhone1(memPhone1);
//		member.setMemPhone2(memPhone2);
//		member.setMemPhone3(memPhone3);
//		
//		dbMap.put(memId, member);
//		
//		Set<String> keys = dbMap.keySet();
//		Iterator<String> iterator = keys.iterator();
//		
//		while (iterator.hasNext()) {
//			String key = iterator.next();
//			Member mem = dbMap.get(key);
//			System.out.print("memberId:" + mem.getMemId() + "\t");
//			System.out.print("|memberPw:" + mem.getMemPw() + "\t");
//			System.out.print("|memberMail:" + mem.getMemMail() + "\t");
//			System.out.print("|memberPhone:" + mem.getMemPhone1() + " - " + 
//											   mem.getMemPhone2() + " - " + 
//											   mem.getMemPhone3() + "\n");
//		}
//		
//	}

}