package com.zamong.web.magazine;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.zamong.mg.service.MagazineDTO;
import com.zamong.mg.service.impl.MagazineDAO;

import model.FileUtils;


public class ZamongMagazineWriteController extends HttpServlet {
	String getStringValue(String value){
	      switch(value){
	         case "1":return "금주의 신보";
	         case "2":return "금주의 차트";
	         case "3":return "아티스트 갤러리";
	         case "4":return "이슈 포커스";
	         case "5":return "멜론 스테이지";
	         case "6":return "HIPHOPLE";
	         case "7":return "원더풀 재즈";
	         case "8":return "스쿨 오브 록";
	         case "9":return "클래식 AtoZ";
	         case "10":return "EDM Floor";
	         case "11":return "인디 스트리트";
	         case "12":return "TV별책부록";
	         case "13":return "아이돌 탐구생활";
	         case "14":return "STATION H";
	         case "15":return "SM STATION";
	         case "16":return "Hi-Fi 가이드";
	         default : return "뮤직툰";
	      }
	   }
	
	
	
	
	
	//입력폼으로 이동
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//3[요청분석 -입력폼 요청
			//4]모델호출 및 결과값 받기
			//5]결과값이 있으면 ,리퀘스트 영역에 저장
			//6]뷰 선택
			/*
			 절대경로 지정시
			 포워딩:컨텍스트 루트 경로 미 포함
			 리다이렉트:컨텍스트 루트 포함
			 단, server.xml에 Context태그의 path속성 값을
			 지울시에는 신경 쓸 필요 없다
			 */
			//리다이렉트
			//resp.sendRedirect(req.getContextPath()+"/DATAROOM_13/Write.jsp");
			
			//포워드
			//req.getRequestDispatcher("/bbs/magazine/ZamongMagazineWrite.jsp").forward(req, resp);
			doPost(req, resp);
		}//doGet
		
		//입력 처리 즉 파일 업로드 및 데이터베이스 입력
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//한글처리
			req.setCharacterEncoding("UTF-8");
			//오류시 입력값 보존을 위한 변수 선언
			String title;
			String contents;
			String division;
			//3]요청분석 -입력처리 요청
			//4]모델호출 및 결과값 받기
			MultipartRequest mr = FileUtils.upload(req, req.getServletContext().getRealPath("/upload"));
			
			int sucorfail;
			
			//기타 파라미터 받아서 테이블에 입력처리
			
				if (mr != null) {
					division = getStringValue(mr.getParameter("Magazine_category"));
					title = mr.getParameter("title");
					contents = mr.getParameter("content");
					//데이터베이스 CRUD작업과 관련된 모델 호출
					MagazineDAO dao = new MagazineDAO(req.getServletContext());
					MagazineDTO dto = new MagazineDTO();
					dto.setMg_title(title);
					dto.setMg_contents(contents);
					dto.setMg_division(division);
					
					sucorfail = dao.insert(dto);
					dao.close();
				} // if
				else {
					sucorfail = -1;
				} // else
			
			//5]리퀘스트 영역에 결과값 혹은 필요한 값 저장
			//5-1]DB입력 성공 여부 및 파일용량 초과 판단용 속성 저장
			req.setAttribute("SUC_FAIL",sucorfail);
			
			//5-2]컨트롤러 구분용-입력:INS, 수정:EDT, 삭제:DEL
			req.setAttribute("WHERE", "INS");
			
			
				if(sucorfail == 1){//입력성공
					//※입력후 바로 목록으로 이동]-반드시 List.jsp가 아닌 List.do로 이동
					req.getRequestDispatcher("/bbs/magazine/Magazine_Message.jsp").forward(req, resp);
				}//if
				else{//입력실패 혹은 파일용량 초과
					req.setAttribute("errorMessage", sucorfail == 0 ? "입력 실패" : "파일용량 초과");
					//MultipartRequest가 파라미터를 가로채니까
					//포워드하더라도 전달 안됨
					/*
					if(mr == null){
						mr = new MultipartRequest(
								req, 
								req.getServletContext().getRealPath("/Upload"),
								"UTF-8"
								);
					}//if
					req.setAttribute("name", mr.getParameter("name"));
					req.setAttribute("title", mr.getParameter("title"));
					req.setAttribute("content", mr.getParameter("content"));
					req.setAttribute("pass", mr.getParameter("pass"));
					
					req.getRequestDispatcher("/DATAROOM_13/Write.jsp").forward(req, resp);
					 */
					
					resp.setContentType("text/html; charset=UTF-8");
					PrintWriter out = resp.getWriter();
					out.println("<script>");
					out.println("alert('"+(sucorfail == 0 ? "입력실패" : "파일용량초과")+"')");
					out.println("history.back();");
					out.println("</script>");
					
				}//else
			//req.getRequestDispatcher("/bbs/magazine/Magazine_Message.jsp").forward(req, resp);
			
		}//doPost
	
	
	
}
