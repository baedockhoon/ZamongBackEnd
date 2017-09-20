package com.zamong.web.broadcast;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.mv.service.impl.MusicVideoDAO;
import com.zamong.br.service.BroadCastDAO;
import com.zamong.br.service.impl.BroadCastDTO;
import com.zamong.mv.service.MusicVideoDTO;


public class BroadCastViewController extends HttpServlet {
	//2]service()혹은 doGet() 혹은 doPost()오버라이딩
	@Override
	protected void service(
			HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		//3]요청분석
		String br_no = req.getParameter("br_no");
		//4]모델 호출 및 결과값 받기
		BroadCastDAO dao = new BroadCastDAO(req.getServletContext());
		BroadCastDTO dto = dao.selectOne(br_no);
		
		//줄바꿈 처리
		dto.setBr_contents(dto.getBr_contents().replace("\r\n","<br/>"));
		dao.close();
		
		//5]필요한 값 리퀘스트 영역에 저장
		req.setAttribute("dto", dto);
		//6]뷰 선택후 포워딩
		req.getRequestDispatcher("/bbs/zamongtv/Broadcast/BroadCastView.jsp").forward(req, resp);
		
		
	}/////////////////////service()
	
	
}