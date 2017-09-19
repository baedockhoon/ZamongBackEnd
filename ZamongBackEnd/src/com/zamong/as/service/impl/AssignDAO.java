package com.zamong.as.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import com.zamong.as.service.AssignDTO;
import com.zamong.mg.service.MagazineDTO;

public class AssignDAO {
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	// 톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
	public AssignDAO(ServletContext context) {
		try {
			Context ctx = new InitialContext();
			DataSource source = (DataSource) ctx.lookup(context.getInitParameter("TOMCAT_JNDI_ROOT") + "/jdbc/zamong");
			conn = source.getConnection();

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}////////////////// DataRoomDAO()

	public void close() {
		try {

			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}///////////////// close()
	
	//입력용
	public int insert(AssignDTO dto) {
		int affected = 0;
		String sql = "INSERT INTO AS_ASSIGN VALUES(AS_SEQ.NEXTVAL,SYSDATE,?,?,?)";
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getAs_no());
			psmt.setString(2, dto.getAs_regidate());
			psmt.setInt(3, dto.getMe_no());
			psmt.setInt(4, dto.getAl_no());
			psmt.setString(5, dto.getAs_getpoint());
			
			affected = psmt.executeUpdate();
			
		}//try
		catch(SQLException e){e.printStackTrace();}//catch
		
		return affected;
		
	}//insert
	
	
	
}
