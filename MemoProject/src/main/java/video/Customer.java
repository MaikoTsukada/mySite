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

@WebServlet(urlPatterns={"/video/customer"})
public class Customer extends HttpServlet{
	public void doPost (
			HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
			// 書き出しを作る
			PrintWriter out = response.getWriter();
			Page.header(out);

			try {
				// タイトルを取得する
				String title = request.getParameter("customerTitle");
				Video video = new Video();
				video.setTitle(title);

				// searchする（あいまい検索）
				VideoDAO dao = new VideoDAO();
				List<Video> list = dao.search(video, "customer");

				// 在庫がない場合、その旨表示する
				if (list.size() == 0) {
					out.println
					("<p>お探しのDVD/ブルーレイが見つかりません。<br>申し訳ございません。</p>");
				}
				// 在庫がある場合、ヒットした分すべて表示する
				else {
					out.println("<p><b>▼お探しのDVD/ブルーレイが見つかりました。</b></p>");
					out = writeData(out, list);
				}
			} catch (Exception e) {
				e.printStackTrace(out);
			}
			Page.footer(out);
	}

	// DBからの検索結果を書き出すメソッド
	public static PrintWriter writeData (PrintWriter out, List<Video> list) {
		for (Video registeredVideo : list) {
			out.println("<p>＊ タイトル　　　：" + registeredVideo.getTitle() + "<br>");
			out.println
				("　 レンタル料金　：" + calcFee(registeredVideo.getStatus()) + "円(" + registeredVideo.getStatus() + ")<br>");
			out.println("　 ジャンル　　　：" + registeredVideo.getGenre() + "<br>");
			out.println("　<b><a href=\"" + registeredVideo.getUrl() + "\">詳細ページへ</a></b><br><br></p>");
		}
		return out;
	}

	// ステータスによってレンタル料金を返すメソッド
	public static int calcFee (String status) {
		// 返却値
		int result = 0;

		switch(status){
			case "新作":
				result = 400;
				break;
			case "準新作":
				result = 250;
				break;
			case "旧作":
				result = 100;
				break;
			default:
				break;
		}

		return result;
	}
}
