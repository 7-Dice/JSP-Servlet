package marchandises;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import Beans.web.ProductsBeans;
import Beans.web.UserBeans;

/**
 * Servlet implementation class management
 */
@WebServlet("/mgServlet")
public class mgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 初期状態Falseでログイン判定を必ず通す
		HttpSession session = request.getSession(false);
		
		// 最初にログインしているかどうかの判定をする
		if(session == null || session.getAttribute("loginUser") == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		
		response.setCharacterEncoding("UTF-8");
				
		try {
			String name = request.getParameter("name");
			String code = request.getParameter("code");
			String pc = request.getParameter("price");
			String maker = request.getParameter("maker");
			String size = request.getParameter("size");

			// String o = request.getParameter("option");
			
			/*
			 *  Case Boolean
			 */
			String op = request.getParameter("option");
			boolean option = "あり".equals(op); // あり＝True、なし＝False
			 			
			// 今回はNULLを許容しない仕様として、すべてのフィールドの空文字は例外として弾く
			if(
				name == null || name.isEmpty() || code == null || code.isEmpty() || 
				pc == null || pc.isEmpty() || maker == null || maker.isEmpty()
			   ) {
				request.setAttribute("errMsg", "入力内容を確認してください");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
			
			int price = Integer.parseInt(pc);
			
			// 価格入力の例外処理
			if(price <= 0) {
				request.setAttribute("errMsg", "価格は1以上の値を入力してください");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
			
			// ここでUserBeans型のインスタンスを生成し、loginUserの情報を持たせている
			UserBeans loginUser = (UserBeans)session.getAttribute("loginUser");
			
			// ユーザーが商品情報を入れた時にloginUser情報を付帯して、出品者情報として使う
			ProductsBeans pb = new ProductsBeans();
			pb.setName(name);
			pb.setCode(code);
			pb.setPrice(price);
			pb.setMaker(maker);
			pb.setSize(size);
			// pb.setOption(o);
			pb.setOption(option);
			
			// これはユーザーが持つべき情報でなく、商品が持つ情報
			// 故にこれはProductsBeansに持たせるのが正しい
			// ProductsBeansのGetterで現在のloginUser情報を持ってきてListの出品者情報に表示
			pb.setSeller(loginUser.getHundleName()); // 結果表示用
			pb.setSellerId(loginUser.getUserId());	// システム管理用
			
			/*
			 *  session.getAttributeで得られる値はObject型
			 *  これをList型にキャストしていて、この際コンパイル実行まで中身がわからない
			 *  なので警告を出すのだが、これは意図的に行っていることであり予期せぬエラーではない
			 *  よってそれを明示的に示すためにアノテーションを付けている
			 */
			@SuppressWarnings("unchecked")
			List<ProductsBeans> productList = (List<ProductsBeans>)session.getAttribute("productList"); 
			
			// もしProductListが空なら、新たに作成
			if(productList == null) {
				// <>はなくても動くが入ってくる型をproductList型に制限してエラーハンドリングをする
				// もし付けないと入ってくるのがObject型になる
				// List ⇔ ArrayList は ArrayList ⇔ ArrayListでも動くが、これに関しては定型文
				productList = new ArrayList<>();
			}
			
			// 作成したArrayListにpb情報を追加
			productList.add(pb);
			
			// productListをListに入れる処理と、最後の登録の反映
			session.setAttribute("productList", productList);
			session.setAttribute("lastProduct", pb);
			
			// Forwardは2回書くと、最初の1回のみが実行されて以降の記述は無効
			request.setAttribute("pd", pb);
			request.getRequestDispatcher("registered.jsp").forward(request, response);
			return;
			
			// forwardを2回書くとか、書いた後にReturnしないでRedirectしようとするとエラー踏む
			// 処理がすでにCommitされているのにOnemoreしようとするとバッティングする
			// request.getRequestDispatcher("list.jsp").forward(request, response);
			
		}catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("errMsg", "登録に失敗しました");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
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
