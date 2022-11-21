package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Free;
import repository.FreeDAO;

public class FreeModifyService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		long freeNo = Integer.parseInt(request.getParameter("freeNo"));
		
		Free free = new Free();
		free.setTitle(title);
		free.setContent(content);
		free.setFreeNo(freeNo);
		
		int result = FreeDAO.getInstance().updateFree(free);
		
		// 수정 성공/실패
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>");
			out.println("alert('게시글이 수정되었습니다.')");
			out.println("location.href='" + request.getContextPath() + "/free/detail.do?freeNo=" + freeNo + "'");
			out.println("</script>");
		}
		out.close();
		
		return null;
	}

}
