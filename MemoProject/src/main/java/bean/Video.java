package bean;

import java.io.Serializable;

public class Video implements Serializable{
	// フィールド
	private String title; // タイトル
	private String status; // ステータス
	private String genre; // ジャンル
	private String url; // URL
	private int age; // 年齢層
	private int gender; // 性別

	// ゲッター
	// タイトル
	public String getTitle() {
		return this.title;
	}
	// ステータス
	public String getStatus() {
		return this.status;
	}
	// ジャンル
	public String getGenre() {
		return this.genre;
	}
	// URL
	public String getUrl() {
		return this.url;
	}
	// 年齢層
	public int getAge() {
		return this.age;
	}
	// 性別
	public int getGender() {
		return this.gender;
	}

	// セッター
	// タイトル
	public void setTitle(String title) {
		this.title = title;
	}
	// ステータス
	public void setStatus(String status) {
		this.status = status;
	}
	// ジャンル
	public void setGenre(String genre) {
		this.genre = genre;
	}
	// URL
	public void setUrl(String url) {
		this.url = url;
	}
	// 年齢層
	public void setAge(int age) {
		this.age = age;
	}
	// 性別
	public void setGender(int gender) {
		this.gender = gender;
	}
}
