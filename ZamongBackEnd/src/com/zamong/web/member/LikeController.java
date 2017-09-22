package com.zamong.web.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.me.service.LikeDTO;
import com.zamong.me.service.impl.LikeDAO;

public class LikeController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int affected;
		int total;
		LikeDAO dao = new LikeDAO(req.getServletContext());
		LikeDTO dto = new LikeDTO();
		
		String me_no = req.getParameter("me_no");
		String lk_flag = req.getParameter("lk_flag");
		String lk_targetno = req.getParameter("lk_targetno");
		dto.setMe_no(me_no);
		dto.setLk_flag(lk_flag);
		dto.setLk_targetno(lk_targetno);	
		
		affected =  dao.insert(dto);
		
		
		System.out.println(lk_targetno);
		if(dao.getTotalLikeCount(lk_targetno) == 1) {
			/*dto.setMe_no(me_no);
			dto.setLk_flag(lk_flag);
			dto.setLk_targetno(lk_targetno);	
			
			affected =  dao.insert(dto);*/
			
		total = dao.getTotalLikeCount(lk_targetno);
		/*System.out.println("total="+total);
		System.out.println("lk_targetno="+lk_targetno);*/
		dao.close();
		
		req.setAttribute("dto", dto);
		req.setAttribute("total", total);
		}
		else {
			
			dao.delete(me_no);
			total = dao.getTotalLikeCount(lk_targetno);
			req.setAttribute("total", total);
			
		}
		req.getRequestDispatcher("/ZAMONG/Artist/List.do").forward(req, resp);
		
		
		
		
		
	}////////////////////service
	
}//////////////////////LikeController
