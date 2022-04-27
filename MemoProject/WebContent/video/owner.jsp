<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html"%>

<p><b>▼ 以下の項目を入力してください。</b></p>
<form action="owner" method="post">
<p>タイトル　　 <input type="text" name="ownerTitle"></p>

<p>ステータス
<input type="radio" name="status" value=0 checked>新作
<input type="radio" name="status" value=1>準新作
<input type="radio" name="status" value=2>旧作
</p>

<p>ジャンル　　
<select name="genre">
	<option value="0">アニメ・キッズ</option>
	<option value="1">お笑い</option>
	<option value="2">邦画（青春・恋愛・ミステリー）</option>
	<option value="3">邦画（ホラー・オカルト）</option>
	<option value="4">洋画（アクション）</option>
	<option value="5">洋画（SF・ファンタジー）</option>
</select>
</p>

<p>URL　　　　　<input type="text" name="url"></p>

<p>年齢　　　　　<select name="age">
	<option value=0>～10歳未満</option>
	<option value=1>10歳～19歳</option>
	<option value=2>20歳～29歳</option>
	<option value=3>30歳～39歳</option>
	<option value=4>40歳～49歳</option>
	<option value=5>50歳～59歳</option>
	<option value=6>60歳～69歳</option>
	<option value=7>70歳～79歳</option>
	<option value=8>80歳～89歳</option>
	<option value=9>90歳～99歳</option>
	<option value=10>100歳～</option>
</select>※ランダム検索用</p>
<p>性別　　　　　<select name="gender">
	<option value=0>男性</option>
	<option value=1>女性</option>
</select>※ランダム検索用</p>

<p>処理内容　
<input type="radio" name="process" value=0 checked>追加
<input type="radio" name="process" value=1>更新
<input type="radio" name="process" value=2>削除
</p>

<p>
<input type="submit" value="実行">
</p>
</form>

<%@include file="../footer.html" %>
