package com.zamong.ca.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import com.zamong.ca.service.CandidatesDTO;

public class CandidatesDAO {
	//멤버변수]
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	//톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
	public CandidatesDAO(ServletContext context){
		try {
			Context ctx = new InitialContext();
			DataSource source=(DataSource)ctx.lookup(context.getInitParameter("TOMCAT_JNDI_ROOT")+"/jdbc/zamong");
			conn = source.getConnection();	
			
		} 
		catch (NamingException e) {e.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
		
	}//////////////////zamongtvDAO()
	//자원반납용]-사용한 커넥션 객체를 다시 풀에 반납
	public void close(){
		try {
			//메모리 해제]
			if(rs !=null) rs.close();
			if(psmt !=null) psmt.close();
			// 커넥션 풀에 커넥션 객체 반납-메모리 해제 아님]
			if(conn !=null) conn.close();
		} catch (Exception e) {e.printStackTrace();}
	}/////////////////close()
	
	//전체 목록용]
	public List listAll() {
		List list = new Vector();
		String sql="SELECT * FROM CA_CANDIDATES ORDER BY CA_NO DESC";
		try{
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				CandidatesDTO dto = new CandidatesDTO();
				dto.setCa_no(rs.getString(1));
				dto.setCa_regidate(rs.getDate(2));
				dto.setSs_no(rs.getString(3));
				dto.setAt_no(rs.getString(4));
				dto.setMa_no(rs.getString(5));
				list.add(dto);
				
				/*	private String ca_no;
					private java.sql.Date ca_regidate;
					private String ss_no;
					private String at_no;
					private String ma_no;*/
			}
		}
		catch(SQLException e){e.printStackTrace();}
		return list;
	}/////////////selectList()

	//입력용]
	public int insert(CandidatesDTO dto) {
		int affected=0;
		//String sql="INSERT INTO MA_MUSICAWARD VALUES(MA_MUSICAWARD_SEQ.NEXTVAL,SYSDATE,?,?,TO_DATE(? '23:59:59','yyyy/mm/dd hh24:mi:ss'))";
		String sql="INSERT INTO CA_CANDIDATES VALUES(CA_SEQ.NEXTVAL,SYSDATE,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getSs_no());
			psmt.setString(2, dto.getAt_no());
			psmt.setString(3, dto.getMa_no());
			//System.out.println("dto.getMa_endofday() :"+dto.getMa_endofday());
			affected = psmt.executeUpdate();
			
		} catch (SQLException e) {e.printStackTrace();}
		
		return affected;
	}/////////////////insert
	
	
	//삭제용
	public int delete(String no) {
		int affected =0;
		String sql="DELETE CA_CANDIDATES WHERE CA_NO=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,no);			
			affected = psmt.executeUpdate();
		} 
		catch (SQLException e) {			
			e.printStackTrace();
		}		
		return affected;
	}
	
	
	public CandidatesDTO selectOne(String no) {
		CandidatesDTO dto=null;
		
		String sql="SELECT * FROM CA_CANDIDATES WHERE CA_NO=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			if(rs.next()){
				dto = new CandidatesDTO();
				dto.setCa_no(rs.getString(1));
				dto.setCa_regidate(rs.getDate(2));
				dto.setSs_no(rs.getString(3));
				dto.setAt_no(rs.getString(4));
				dto.setMa_no(rs.getString(5));
			}			
		} catch (SQLException e) {e.printStackTrace();}		
		return dto;
	}/////////////////////selectOne()

	//수정용]
	public int update(CandidatesDTO dto) {
		int affected=0;
		String sql="UPDATE CA_CANDIDATES SET SS_NO=?,AT_NO=?,MA_NO=? WHERE CA_NO=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getSs_no());
			psmt.setString(2, dto.getAt_no());
			psmt.setString(3, dto.getMa_no());
			psmt.setString(4, dto.getCa_no());
			affected = psmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		return affected;
	}////////////////////update
	
	/*	private String ca_no;
	private java.sql.Date ca_regidate;
	private String ss_no;
	private String at_no;
	private String ma_no;*/
	
	public List<CandidatesDTO> selectList(Map<String, Object> map) {

		List<CandidatesDTO> records = new Vector<CandidatesDTO>();

		//페이징 미 적용
		//String sql="SELECT B.*,M.NAME FROM BBS B JOIN MEMBER M ON B.ID=M.ID ORDER BY NO DESC";
		//페이징 적용-구간쿼리로 변경
		String sql="SELECT * FROM CA_CANDIDATES ORDER BY CA_NO DESC";
		/*String sql="SELECT * FROM (SELECT T.*, ROWNUM R FROM (SELECT MA_NO, MA_REGIDATE,MA_TITLE,MA_CONTENTS,TO_CHAR(ma_endofday, 'YYYYMMDDHH24MISS')endofday FROM ma_musicaward ";
		//String sql="SELECT * FROM (SELECT T.*, ROWNUM R FROM (SELECT * FROM MA_MUSICAWARD ";
		//검색용 쿼리 추가
		if(map.get("searchWord") !=null){
			sql+=" WHERE "+map.get("searchColumn")+ " LIKE '%"+map.get("searchWord")+"%' ";
		}		
		//sql+=" ORDER BY MA_NO DESC) T) WHERE R BETWEEN ? AND ?";
		sql+=" ORDER BY MA_NO DESC) T) WHERE R BETWEEN ? AND ?";*/
		
		try {
			psmt = conn.prepareStatement(sql);
			
			//페이징을 위한 시작 및 종료 rownum설정]
			psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
			psmt.setInt(2, Integer.parseInt(map.get("end").toString()));
			
			rs = psmt.executeQuery();
			while(rs.next()){
				CandidatesDTO dto = new CandidatesDTO(
						rs.getString(1),
						rs.getDate(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5));
				records.add(dto);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return records;
	}/////////////////selectList
	

	
	public int getTotalCount(Map<String, Object> map) {
		int totalCount = 0;
		String sql = "SELECT COUNT(*) FROM CA_CANDIDATES";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			totalCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalCount;
	}/////////////////////
	
	

	
	
}
