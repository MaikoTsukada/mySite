<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="../header.html"%>

<p><b>▼タイトル検索</b></p>
<form action="customer" method="post">
<p><input type="text" name="customerTitle"></p>
<p><input type="submit" value="タイトル検索"></p>
</form>
<br><br><b>▼ランダム検索(見たいDVD/ブルーレイが決まっていない方向け)</b>
<form action="customerAdd" method="post">
年齢<br><select name="age">
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
</select>
<br>性別<br><select name="gender">
	<option value=0>男性</option>
	<option value=1>女性</option>
</select>
<br>ジャンル<br><select name="genre">
	<option value="0">アニメ・キッズ</option>
	<option value="1">お笑い</option>
	<option value="2">邦画（青春・恋愛・ミステリー）</option>
	<option value="3">邦画（ホラー・オカルト）</option>
	<option value="4">洋画（アクション）</option>
	<option value="5">洋画（SF・ファンタジー）</option>
</select>
<br><p><input type="submit" value="ランダム検索"></p>
</form>
<%@include file="../footer.html"%>