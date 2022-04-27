package chapter6;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Page;


@WebServlet(urlPatterns="/chapter6/chapter6Control")
public class Chapter6Control extends HttpServlet{
	public void doPost(
			HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		request.setCharacterEncoding("UTF-8");
		String count = request.getParameter("count"); // 人数
		String payment = request.getParameter("payment"); // 購入方法
		String review = request.getParameter("review"); // 感想
		String mail = request.getParameter("mail"); // メール
		
		Page.header(out);
		out.println("<p>" + count + "個の商品をカートに入れました。</p>");
		out.println("<p>お支払い方法を" + payment + "に設定しました。</p>");
		out.println("<p>「" + review + "」</p>");
		if(mail != null) {
			out.println("<p>メールをお送りします。</p>");
		} else {
			out.println("<p>メールはお送りしません。</p>");
		}
		
		Page.footer(out);
		
		
	}
}
