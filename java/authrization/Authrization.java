package authrization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import Beans.web.UserBeans;
/**
 * Servlet implementation class Authrization
 */
@WebServlet("/Authrization")
public class Authrization extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authrization() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession(false);
		
		/* ログアウト処理
		 * invalidateを使うとloginUser,Users,productList,session情報全部消える
		 * 再ログイン時session.getAttribute("Users") = nullになる
		 * そのためにSession情報を保持できず初期状態に戻る
		if("logout".equals(action)) {
			if(session != null) {
				session.invalidate();
			}
			response.sendRedirect("index.jsp");
			return;
		}
		*/
		
		// logout処理、この状態だとセッション切れまでは登録した情報が残る
		if("logout".equals(action)) {
			if(session != null) {
				// removeでusers,productListを残す
				session.removeAttribute("loginUser");
			}
			
			response.sendRedirect("index.jsp");
			return;
		}
		
		// 初期登録
		if("register".equals(action)) {
			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			String hn = request.getParameter("hn");
			String ages = request.getParameter("age");
			String mail = request.getParameter("mail");
			
			// 空文字が打たれた時の例外処理
			if(id == null || id.isEmpty() || pass == null || pass.isEmpty() || 
			   hn == null || hn.isEmpty() || mail == null || mail.isEmpty()) {
				request.setAttribute("errMsg", "正しい値を入力してください");
				request.getRequestDispatcher("user.jsp").forward(request, response);
				return;
			}
			
			// 年齢は数値としておきたいので、これのキャストと例外処理
			int age;
			
			try {
				age = Integer.parseInt(ages);
			}catch (NumberFormatException e) {
				request.setAttribute("errMsg", "年齢は数値のみ可");
				request.getRequestDispatcher("user.jsp").forward(request, response);
				return;
			}
			
			if(age <= 0) {
				request.setAttribute("errMsg", "年齢は1以上の値を入力");
				request.getRequestDispatcher("user.jsp").forward(request, response);
				return;
			}
			
			// ログインユーザー情報を保持する
			if(session == null) {
				session = request.getSession();
			}
			
			@SuppressWarnings("unchecked")
			List<UserBeans>users = (List<UserBeans>)session.getAttribute("users");
			if(users == null) {
				users = new ArrayList<>();
			}
			
			// 重複ID禁止
			for(UserBeans u : users) {
				if(u.getUserId().equals(id)) {
					request.setAttribute("errMsg", "既に使用されているIDです");
					request.getRequestDispatcher("user.jsp").forward(request, response);
					return;
				}
			}
			
			UserBeans ub = new UserBeans();
			ub.setUserId(id);
			ub.setPass(pass);
			ub.setHundleName(hn);
			ub.setAge(age);
			ub.seteMail(mail);
			
			users.add(ub);
			
			// 登録成功したユーザーをSessionに保存
			// session = request.getSession();
			
			// インスタンスとリスト情報をセッション属性に入れる
			session.setAttribute("users", users);
			session.setAttribute("loginUser", ub);
			
			// 登録完了後は商品登録画面に進む
			response.sendRedirect("register.jsp");
			return;
		}
		
		// login
		if("login".equals(action)){
						
			// ログイン前のセッションがないなら新規作成
            if (session == null) {
               session = request.getSession();
            }
            
            String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			
			@SuppressWarnings("unchecked")
			List<UserBeans> users = (List<UserBeans>)session.getAttribute("users");
			
			if(users == null || users.isEmpty()) {
				request.setAttribute("errMsg", "登録済みユーザーがいません");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			
			for(UserBeans u : users) {
				if(id != null && pass != null
						&& id.equals(u.getUserId())
						&& pass.equals(u.getPass())) {
					session.setAttribute("loginUser", u);
					response.sendRedirect("register.jsp");
					return;
				}
			}
			
			// 未登録の場合
			request.setAttribute("errMsg", "IDもしくはPasswordが違います");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
			
		// actionがない(入力・登録がない)場合、初期画面に戻す
		response.sendRedirect("index.jsp");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
