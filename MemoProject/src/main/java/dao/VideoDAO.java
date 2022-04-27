package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Video;

public class VideoDAO extends DAO{
	// 検索
	// title:タイトル（これで検索
	// user:お店側（完全一致）かお客様側（あいまい検索）か
	public List<Video> search (Video video, String user) throws Exception{
		// 返却用のリストを作成
		List<Video> list = new ArrayList<>();
		//DB接続
		Connection con = getConnection();
		// お店側であれば完全一致用のselect文を作成し、
		//お客様側であればあいまい検索用のselect文を作成する
		PreparedStatement st;
		if(user == "owner") {
			st = con.prepareStatement("select * from video where title= ?");
			st.setString(1, video.getTitle());
		} else if(user == "customer") {
			st = con.prepareStatement("select * from video where title like ?");
			st.setString(1, "%"+ video.getTitle() + "%" );
		} else {
			st = con.prepareStatement("select * from video where age=?and gender=? and genre=?");
			st.setInt(1, video.getAge());
			st.setInt(2, video.getGender());
			st.setString(3, video.getGenre());
		}
		// SQL文を実行し、レコードを取得する
		ResultSet rs = st.executeQuery();

		// 取得したレコードをリストに追加する
		while(rs.next()) {
			Video registeredVideo = new Video();
			registeredVideo.setTitle(rs.getString("title")); // タイトル
			registeredVideo.setStatus(rs.getString("status")); // ステータス
			registeredVideo.setGenre(rs.getString("genre")); // ジャンル
			registeredVideo.setUrl(rs.getString("url")); // URL
			registeredVideo.setAge(rs.getInt("age")); // 年齢
			registeredVideo.setGender(rs.getInt("gender")); // 性別
			list.add(registeredVideo);
		}

		// DB接続を切断
		st.close();
		con.close();

		// listを返却
		return list;
	}

	// 追加
	// video:入力内容
	public int insert(Video video) throws Exception {
		// DB接続
		Connection con = getConnection();
		// insert文の作成
		PreparedStatement st = con.prepareStatement(
				"insert into video(title, status, genre, url, age, gender) values(?, ?, ?, ?, ?, ?)");
		st.setString(1, video.getTitle()); // タイトル
		st.setString(2, video.getStatus()); // ステータス
		st.setString(3, video.getGenre()); // ジャンル
		st.setString(4, video.getUrl()); // URL
		st.setInt(5, video.getAge()); // 年齢
		st.setInt(6, video.getGender()); // 性別
		// SQL文の実行
		int line = st.executeUpdate();
		// DB接続を切断
		st.close();
		con.close();
		// line（何行追加したか）を返却
		return line;
	}

	// 更新
	// video:入力項目
	// setting：null(statusもgenreも更新)か、status(statusのみ更新）か、genre(genreのみ更新）
	public int update(Video video) throws Exception {
		// DB接続
		Connection con = getConnection();
		// update文の作成
		PreparedStatement st;
		// ステータスもジャンルも更新する場合
		st = con.prepareStatement(
				"update video set status=?, genre=?, url=?, age=?, gender=? where title=?");
		st.setString(1, video.getStatus());
		st.setString(2, video.getGenre());
		st.setString(3, video.getUrl());
		st.setInt(4, video.getAge());
		st.setInt(5, video.getGender());
		st.setString(6, video.getTitle());

		// SQL文の実行
		int line = st.executeUpdate();
		//DB接続の切断
		st.close();
		con.close();
		// line(何行更新したか）を返却
		return line;
	}

	// 削除
	// 入力項目（タイトルが一致していれば消すため、使用するのはtitleのみ）
	public int delete(Video video) throws Exception {
		// DB接続
		Connection con = getConnection();
		// delete文の作成
		PreparedStatement st = con.prepareStatement(
				"delete from video where title=?");
		st.setString(1, video.getTitle()); // タイトル
		// SQL文の実行
		int line = st.executeUpdate();
		// DB接続の切断
		st.close();
		con.close();
		// line(何行削除したか）を返却
		return line;
	}
}
