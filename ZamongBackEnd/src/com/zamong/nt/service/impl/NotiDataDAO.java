package com.zamong.nt.service.impl;

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

import com.zamong.nt.service.NotiDataDTO;





public class NotiDataDAO {

	private Connection conn; 
	private PreparedStatement psmt;
	private ResultSet rs;
	//톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
	public NotiDataDAO(ServletContext context){
		try {
			Context ctx = new InitialContext();
			DataSource source=(DataSource)ctx.lookup(context.getInitParameter("TOMCAT_JNDI_ROOT")+"/jdbc/zamong");
			conn = source.getConnection();	
			
		} 
		catch (NamingException e) {e.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
		
	}//////////////////DataRoomDAO()
	
	public void close(){
		try {
			
			if(rs !=null) rs.close();
			if(psmt !=null) psmt.close();
			if(conn !=null) conn.close();
			
		} catch (Exception e) {e.printStackTrace();}
	}/////////////////close()
	public List<NotiDataDTO> selectList(Map<String, Object> map) {

		List<NotiDataDTO> records = new Vector<NotiDataDTO>();

		// 페이징 미 적용
			
		String sql = "SELECT * FROM (SELECT T.*, ROWNUM R FROM (SELECT * FROM NT_NOTICE ";
		//검색용 쿼리 추가

		if (!(map.get("Notice_category") == null && map.get("searchWord") == null)
				&& !(map.get("Notice_category").equals("") && map.get("searchWord").equals(""))) {
			
			sql += " WHERE ";
			
			String notice = map.get("Notice_category").equals("") ? " LIKE '%%' " : " = '" + map.get("Notice_category").toString() + "'";
			String searchWord = map.get("searchWord") == null ? " '%%' " : "'%"+map.get("searchWord").toString()+"%'";
			
			sql += " NT_CLASSIFICATION "+ notice;
			sql+=" AND "+map.get("searchColumn")+ " LIKE " + searchWord;
			System.out.println(notice);
		}
		sql+=" ORDER BY NT_NO DESC) T) WHERE R BETWEEN ? AND ?";

		try {
			psmt = conn.prepareStatement(sql);

			// 페이징을 위한 시작 및 종료 rownum설정]
			psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
			psmt.setInt(2, Integer.parseInt(map.get("end").toString()));

			rs = psmt.executeQuery();
			while (rs.next()) {
				NotiDataDTO dto = new NotiDataDTO(rs.getString(1),rs.getDate(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6), rs.getString(7));

				records.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}///////////////// selectList
	public int getTotalRecordCount(Map<String, Object> map) {
		int totalCount = 0;
		String sql = "SELECT COUNT(*) FROM NT_NOTICE ";
		// 검색용 쿼리 추가
		if (!(map.get("Notice_category") == null && map.get("searchWord") == null)
				&& !(map.get("Notice_category").equals("") && map.get("searchWord").equals(""))) {
			
			sql += " WHERE ";
			
			String notice = map.get("Notice_category").equals("") ? " LIKE '%%' " : " = '" + map.get("Notice_category").toString() + "'";
			String searchWord = map.get("searchWord") == null ? " '%%' " : "'%"+map.get("searchWord").toString()+"%'";
			
			sql += " NT_CLASSIFICATION "+ notice;
			sql+=" AND "+map.get("searchColumn")+ " LIKE " + searchWord;
		}

		try {
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			rs.next();
			totalCount = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalCount;
	}
	
	
	
	
	/*public int getTotalRecordCount(Map<String, Object> map) {
		int totalCount = 0;
		String sql = "SELECT COUNT(*) FROM NT_NOTICE ";
		// 검색용 쿼리 추가
		if (map.get("searchWord") != null) {
			sql += " WHERE " + map.get("searchColumn") + " LIKE '%" + map.get("searchWord") + "%' ";
		}

		try {
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			rs.next();
			totalCount = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalCount;
	}/////////////// getTotalRecordCount
*/	
	
	/*public List selectList() {
		List list = new Vector();
		String sql="SELECT * FROM NT_NOTICE ORDER BY NT_NO DESC";
		try{
			
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				NotiDataDTO dto = new NotiDataDTO();
				dto.setNt_no(rs.getString(1));
				dto.setNt_regidate(rs.getDate(2));
				dto.setAd_no(rs.getString(3));
				dto.setNt_classification(rs.getString(4));
				dto.setNt_title(rs.getString(5));
				dto.setNt_contents(rs.getString(6));
				dto.setNt_hitcount(rs.getString(7));
				list.add(dto);
			}
		}
		catch(SQLException e){e.printStackTrace();}
		return list;
	}/////////////selectList()
*/	
	public void updateVisitCount(String no) {
		String sql = "UPDATE NT_NOTICE SET NT_HITCOUNT=NT_HITCOUNT+1 WHERE NT_NO=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//////////////////// updateVisitCount
	
		//입력용]
		public int insert(NotiDataDTO dto) {
			int affected=0;
			String sql="INSERT INTO NT_NOTICE(nt_no,nt_regidate,ad_no,nt_classification,nt_title,nt_contents) VALUES(NT_SEQ.NEXTVAL,SYSDATE,2,?,?,?)";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getNt_classification());
				psmt.setString(2, dto.getNt_title());
				psmt.setString(3, dto.getNt_contents());
				affected = psmt.executeUpdate();
				
			} catch (SQLException e) {e.printStackTrace();}
			
			return affected;
		}/////////////////insert
		
		
		public NotiDataDTO selectOne(String no) {
			NotiDataDTO dto=null;
			String sql="SELECT * FROM NT_NOTICE WHERE NT_NO=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if(rs.next()){
					dto = new NotiDataDTO();
					dto.setNt_no(rs.getString(1));
					dto.setNt_regidate(rs.getDate(2));
					dto.setAd_no(rs.getString(3));
					dto.setNt_classification(rs.getString(4));
					dto.setNt_title(rs.getString(5));
					dto.setNt_contents(rs.getString(6));
					dto.setNt_hitcount(rs.getString(7));
					
				}			
			} catch (SQLException e) {e.printStackTrace();}		
			return dto;
		}/////////////////////selectOne()
		
		public int update(NotiDataDTO dto) {
			int affected=0;
			String sql="UPDATE NT_NOTICE "
					+ "SET NT_CLASSIFICATION=?,NT_TITLE=?,NT_CONTENTS=? "
					+ "WHERE NT_NO=?";
			
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getNt_classification());
				psmt.setString(2, dto.getNt_title());
				psmt.setString(3, dto.getNt_contents());			
				psmt.setString(4, dto.getNt_no());
				affected = psmt.executeUpdate();
				
			} catch (SQLException e) {e.printStackTrace();}
			
			return affected;
		}////////////////////update
		
		//삭제용
		public int delete(String no) {
			int affected=0;
			String sql="DELETE NT_NOTICE WHERE NT_NO=?";		
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);			
				affected = psmt.executeUpdate();			
			} catch (SQLException e) {e.printStackTrace();}		
			return affected;
		}
		
		public Map<String, NotiDataDTO> prevNnext(String no) {
			Map<String, NotiDataDTO> map = new HashMap<String, NotiDataDTO>();

			String sql = "SELECT NT_NO, NT_TITLE FROM NT_NOTICE WHERE NT_NO=(SELECT MIN(NT_NO) FROM NT_NOTICE WHERE NT_NO > ?)";
			try {
				// 이전글 구하기]
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if (rs.next()) {// 이전글 존재
					map.put("PREV", new NotiDataDTO(rs.getString(1),null,null,null,rs.getString(2),null,null));

				}
				// 다음글 구하기
				sql = "SELECT NT_NO,NT_TITLE FROM NT_NOTICE WHERE NT_NO=(SELECT MAX(NT_NO) FROM NT_NOTICE WHERE NT_NO < ?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if (rs.next()) {// 다음글 존재
					map.put("NEXT", new NotiDataDTO(rs.getString(1),null,null,null,rs.getString(2),null,null));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return map;
		}//////////////// prevNnext
	
}