package com.zamong.bp.service.impl;

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

import com.zamong.bp.service.BuyproductDTO;
import com.zamong.ch.service.CashDTO;
import com.zamong.nt.service.NotiDataDTO;






public class BuyproductDAO {

	private Connection conn; 
	private PreparedStatement psmt;
	private ResultSet rs;
	//톰캣이 만들어 놓은 커넥션을 커넥션 풀에서 가져다 쓰기]
	public BuyproductDAO(ServletContext context){
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
public List<BuyproductDTO> selectList(){
		
		List<BuyproductDTO> records = new Vector<BuyproductDTO>();
		
		//페이징 미 적용
		String sql="SELECT *  FROM BP_BUYPRODUCT ";
		//페이징 적용-구간쿼리로 변경
		
		try {
			psmt = conn.prepareStatement(sql);
			
			//페이징을 위한 시작 및 종료 rownum설정]			
			rs = psmt.executeQuery();
			while(rs.next()){
				BuyproductDTO dto = new BuyproductDTO(
						rs.getString(1),
						rs.getDate(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6));
				
						
					
						
			
				records.add(dto);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return records;
	}/////////////////selectList
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
	
	
	
	

	
		//입력용]
		public int insert(BuyproductDTO dto,CashDTO Cash) {
			int affected=0;
			try {
				conn.setAutoCommit(false);
			String sql="INSERT INTO BP_BUYPRODUCT VALUES(BP_SEQ.NEXTVAL,SYSDATE,2,?,?,1)";
			
			/*String sql= "INSERT INTO CH_PAYMENT(CH_NO,CH_REGIDATE,ME_NO,BP_NO,CH_HAVECASH)" + 
			"SELECT BP_SEQ.NEXTVAL,SYSDATE,2,?,?,1" + 
			"FROM BP_BUYPRODUCT";*/
			
				psmt = conn.prepareStatement(sql);
			    psmt.setString(1, dto.getMe_no());
				psmt.setString(2,dto.getBp_price());
				//psmt.setString(2,dto.getBp_buyway());
				affected = psmt.executeUpdate();
			if(affected == 1) {
				sql ="INSERT INTO CH_PAYMENT VALUES(CH_SEQ.NEXTVAL,SYSDATE,?,?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1,Cash.getMe_no());
				psmt.setString(2, Cash.getCh_havecash());
				affected = psmt.executeUpdate();
				conn.commit();
			} 
			}catch (SQLException e) {e.printStackTrace();}
			
			return affected;
		}/////////////////insert
		
		
		public BuyproductDTO selectOne(String no) {
			BuyproductDTO dto=null;
			String sql="SELECT * FROM BP_BUYPRODUCT  WHERE BP_NO=?";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if(rs.next()){
					dto = new BuyproductDTO();
					dto.setBp_no(rs.getString(1));
					dto.setBp_regidate(rs.getDate(2));
					dto.setPd_no(rs.getString(3));
					dto.setMe_no(rs.getString(4));
					dto.setBp_price(rs.getString(5));
					dto.setBp_buyway(rs.getString(6));
					
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