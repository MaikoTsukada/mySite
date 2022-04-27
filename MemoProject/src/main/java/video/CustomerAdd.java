package video;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Video;
import dao.VideoDAO;
import tool.Page;

@WebServlet(urlPatterns={"/video/customerAdd"})
public class CustomerAdd extends HttpServlet{
	public void doPost (
			HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
			//	定数
			// ジャンル
			final String[]  GENRE
			= {"アニメ・キッズ", "お笑い", "邦画（青春・恋愛・ミステリー）",  "邦画（ホラー・オカルト）", "洋画（アクション）", "洋画（SF・ファンタジー）"};
			// 年齢
			final String[] AGE
				= {"～10歳未満", "10歳～19歳", "20歳～29歳", "30歳～39歳", "40歳～49歳", "50歳～59歳", "60歳～69歳", "70歳～79歳", "80歳～89歳", "90歳～99歳", "100歳～" };
			// 性別
			final String[] GENDER = {"男性", "女性"};

			// 書き出し用作成
			PrintWriter out = response.getWriter();
			Page.header(out);

			try {
				// パラメータを取得する
				int age = Integer.parseInt(request.getParameter("age")); // 年齢
				int gender = Integer.parseInt(request.getParameter("gender")); // 性別
				String genre = GENRE[Integer.parseInt(request.getParameter("genre"))];

				// 	取得したパラメータをBeanに代入する
				Video video = new Video();
				video.setAge(age); // 年齢
				video.setGender(gender); // 性別
				video.setGenre(genre); // ジャンル

				// DB接続
				VideoDAO dao = new VideoDAO();
				// ランダム検索する
				List<Video> list = dao.search(video, "customerAdd");
				// 検索結果を表示する
				if (list.size() != 0) {
					out.println
					("<p><b>▼「" + AGE[video.getAge()] + "」の「" + GENDER[video.getGender()] + "」にオススメの「" + video.getGenre() + "」はこちらです。</b><br>");
					out = Customer.writeData(out, list);
				} else {
					out.println("<p>条件に一致する商品が見つかりません。</p>");
				}
			} catch (Exception e) {
				e.printStackTrace(out);
			}
			Page.footer(out);
	}
}
