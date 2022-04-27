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

@WebServlet(urlPatterns={"/video/owner"})
public class Owner extends HttpServlet{
	public void doPost (
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {
		// 定数
		// ステータス
		final  String[] STATUS = {"新作", "準新作", "旧作"};
		// ジャンル
		final String[]  GENRE
				= {"アニメ・キッズ", "お笑い", "邦画（青春・恋愛・ミステリー）",  "邦画（ホラー・オカルト）", "洋画（アクション）", "洋画（SF・ファンタジー）"};
		final String[] PROCESS = {"追加", "更新", "削除"}; // 処理内容
		// 年齢
		final String[] AGE
			= {"～10歳未満", "10歳～19歳", "20歳～29歳", "30歳～39歳", "40歳～49歳", "50歳～59歳", "60歳～69歳", "70歳～79歳", "80歳～89歳", "90歳～99歳", "100歳～" };
		// 性別
		final String[] GENDER = {"男性", "女性"};

		// 書き出し用のoutを作成
		PrintWriter out = response.getWriter();
		Page.header(out);

		try {
			// 入力項目のパラメータを取得
			String title = request.getParameter("ownerTitle"); // タイトル
			String status = STATUS[Integer.parseInt(request.getParameter("status"))]; // ステータス
			String genre = GENRE[Integer.parseInt(request.getParameter("genre"))]; // ジャンル
			String url = request.getParameter("url"); // URL
			int age = Integer.parseInt(request.getParameter("age")); // 年齢
			int gender = Integer.parseInt(request.getParameter("gender")); // 性別
			String process = PROCESS[Integer.parseInt(request.getParameter("process"))]; // 処理内容

			// 追加・更新・削除のbeanを作成する
			Video video = new Video();
			video.setTitle(title); // タイトル
			video.setStatus(status); // ステータス
			video.setGenre(genre); // ジャンル
			video.setAge(age); // 年齢
			video.setGender(gender); // 性別
			video.setUrl(url); // URL

			// タイトルを用いてsearch(完全一致)
			VideoDAO dao = new VideoDAO();
			List<Video> list = dao.search(video, "owner");

			// 商品の追加をする場合
			if (process == "追加") {
				// 在庫が無ければinsertする
				if (list.size() == 0) {
					int line = dao.insert(video);
					// 追加に成功した場合
					if (line > 0) {
						out.println
							("<p>「"+ video.getTitle() + "」(" + video.getStatus() + ")が、<br>「" + video.getGenre() +  "」に追加されました。<br>");
						out.println("ランダム検索の条件は「" + AGE[video.getAge()] + "」 「" + GENDER[video.getGender()] + "」で設定しました。</p>");
					}
					// 追加に失敗した
					else {
						out.println("<p>追加に失敗しました。</p>");
					}
				}
				// 在庫があれば既に登録済みである事を表示する
				else {
					out.println("<p>すでに登録されています。</p>");
				}
			}
			// 商品を更新する場合
			else if (process == "更新") {
				// 在庫が無ければまだ登録されていない事を表示する
				if (list.size() == 0) {
					out.println
						("<p>該当の商品が見つかりません。<br>新規で登録する場合は、「処理内容」を「追加」にして実行してください。</p>");
					}
				// 在庫があればupdateする
				else {
					int line = dao.update(video);
					// 更新に成功した場合
					if (line > 0) {
						out.println("<p>更新が正常に完了しました。</p>");
					}
					// 更新に失敗した場合
					else {
						out.println("<p>更新が出来ませんでした。</p>");
					}
				}
			}
			// 商品を削除する場合
			else if (process == "削除") {
				// 在庫が無ければ登録がない事を表示する
				if (list.size() == 0) {
					out.println("<p>該当の商品が見つかりません。</p>");
				}
				// 在庫があればdeleteする
				else {
					int line = dao.delete(video);
					// 削除に成功した場合
					if (line > 0) {
						out.println("<p>削除が正常に完了しました。</p>");
					}
					// 削除に失敗した場合
					else {
						out.println("<p>削除が出来ませんでした。</p>");
					}
				}
			}
			// その他(エラー回避）
			else {
				out.println("<p>設定内容が上手く読み込めませんでした。<br>再度設定してください。</p>");
			}
		} catch (Exception e) {
			e.printStackTrace(out);
		}
		Page.footer(out);
	}
}
