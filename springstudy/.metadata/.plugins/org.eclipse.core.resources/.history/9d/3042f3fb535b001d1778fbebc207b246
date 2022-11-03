package com.gdu.app07.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdu.app07.domain.ContactDTO;

@Repository
public class ContactDAO {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// private 메소드
	// 이 메소드는 BoardDAO에서만 사용.
	private Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	// private 메소드
	// 이 메소드는 BoardDAO에서만 사용.
	private void close() {
		try {
			if(rs != null) { rs.close(); }
			if(ps != null) { ps.close(); }
			if(con != null) { con.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ContactDTO> selectAllBoards() {
		List<ContactDTO> boards = new ArrayList<ContactDTO>();
		try {
			con = getConnection();
			sql = "SELECT NO, NAME, TEL, ADDR, EMAIL, NOTE FROM CONTACT ORDER BY NO DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ContactDTO board = new ContactDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				boards.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return boards;
	}
	
	public ContactDTO selectBoardByNo(int no) {
		ContactDTO board = null;
		try {
			con = getConnection();
			sql = "SELECT NO, NAME, TEL, ADDR, EMAIL, NOTE FROM CONTACT WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				board = new ContactDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return board;
	}
	
	public int insertBoard(ContactDTO contact) {
		int result = 0;
		try {
			con = getConnection();
			sql = "INSERT INTO CONTACT (NO, NAME, TEL, ADDR, EMAIL, NOTE) "
						   + "VALUES (CONTACT_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setNString(1, contact.getName());	// 첫번째 ? 값을 받아온다.
			ps.setNString(2, contact.getTel());	
			ps.setNString(3, contact.getAddr());	
			ps.setNString(3, contact.getEmail());	
			ps.setNString(3, contact.getNote());	
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
