package pack1.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class countServlet
 */
@WebServlet("/countServlet")
public class countServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public countServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 文字エンコーディング
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 入力処理させるので例外処理する
		try {
			String c = request.getParameter("cost");
			String n = request.getParameter("num");
			String s = request.getParameter("shipping");
			
			// 未入力Return、空文字入力で元のJSPに戻し続ける
			if(
				c == null || c.isEmpty() || n == null || n.isEmpty() || s == null || s.isEmpty()) {
				
				// setAttributeで属性設定をしておいて、例外時にJSPに表示させる変数と一緒にフォワード
				request.setAttribute("errMsg", "内容を入力してください");
				request.getRequestDispatcher("countNum.jsp").forward(request, response);
				return;
			}
			
			// 計算できるようにキャスト
			int cost = Integer.parseInt(c);
			int num = Integer.parseInt(n);
			int shipping = Integer.parseInt(s);
			
			// 要件に合わせて計算
			int sum = ((cost * num) + shipping);
			
			// 合計を投げる変数を用意
			request.setAttribute("sum", sum);
			
			// 結果用JSPにフォワード
			request.getRequestDispatcher("result.jsp").forward(request, response);
		}catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
