<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<title>공지사항</title>
	<!-- Bootstrap core CSS -->
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마(Bootstrap theme) -->
	<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
	<script type="text/javascript" src="<c:url value='/Editor/js/service/HuskyEZCreator.js' />" charset="utf-8"></script>
	
	<!-- SmartEditor를 사용하기 위해서 다음 js파일을 추가 (경로 확인) -->
	<script type="text/javascript" src="<%=ctx %>/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
	<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
</head>

<body role="document">
	<jsp:include page="/Template/Top.jsp" />
	<script type="text/javascript">
		$(function(){
			$("#artist_info").css("display", "none");
			$("#artist_submit").css("display", "none");
			
			$("#artist_next").click(function(){
				$("#grop_info").css("display", "none");
				$("#artist_info").css("display", ""); /* .attr({"type": "submit", "value": "등록"}); */
				$("#artist_next").css("display", "none");
				$("#artist_submit").css("display", "");
			});
		});
	</script>
	<div class="container theme-showcase" role="main">
		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>아티스트 등록</h1>
		</div>

		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1>아티스트 등록</h1>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<div>
			<form id="frm" action="<c:url value='/ZAMONG/Artist/Write.do'/>" method="post" enctype="multipart/form-data">
				<table class="table table-striped" id="grop_info">
					<tr>
						<td>그룹여부</td>
						<td>
							<input type="radio" name="grop" value="그룹" />그룹
							<input type="radio" name="grop" value="솔로" />솔로
						</td>
					</tr>
					<tr>
						<td>그룹명</td>
						<td>
							<input type="text" name="gp_name" />
						</td>
					</tr>
					<tr>
						<td>그룹 성별</td>
						<td>
							<input type="radio" name="gp_gender" value="혼성" />혼성 
							<input type="radio" name="gp_gender" value="여성" />여성 
							<input type="radio" name="gp_gender" value="님성" />남성
						</td>
					</tr>
					<tr>
						<td>그룹 타이틀 이미지</td>
						<td><input type="file" name="gp_image" /></td>
					</tr>
					<!-- <tr bgcolor="white" align="center">
						<td colspan="2">
							<a herf="#" id="artist_next" class="btn btn-sm btn-info">다음</a>
						</td>
					</tr> -->
				</table>
				<table class="table table-striped" id="artist_info">
					<tr class="artist_info">
						<td>아티스트 명</td>
						<td>
							<input type="text" name="at_name" />
						</td>
					</tr>
					<tr>
						<td>소속사</td>
						<td>
							<input type="text" name="at_belong" />
						</td>
					</tr>
					<tr>
						<td>데뷔일</td>
						<td>
							<input type="text" name="at_debutdate" />
						</td>
					</tr>
					<tr>
						<td>데뷔곡</td>
						<td>
							<input type="text" name="at_debutsong" />
						</td>
					</tr>
					<tr>
						<td>생일</td>
						<td>
							<input type="text" name="at_birth" />
						</td>
					</tr>
					<tr>
						<td>국적</td>
						<td>
							<input type="text" name="at_contry" />
						</td>
					</tr>
					<tr>
						<td>성별</td>
						<td>
							<input type="radio" name="at_gender" value="F" />여성 
							<input type="radio" name="at_gender" value="M" />남성 
						</td>
					</tr>
					<tr>
						<td>아티스트 정보</td>
						<td>
							<input type="text" name="at_artistinfo" />
						</td>
					</tr>
					<tr>
						<td>아티스트 프로필 사진</td>
						<td>
							<input type="file" name="at_image" />
						</td>
					</tr>
				</table>
				<a id="artist_next" class="btn btn-sm btn-info" align="center">다음</a>
				<input type="submit" id="artist_submit" class="btn btn-sm btn-info" value="등록">
			</form>
		</div>
	</div>
	<!-- /container(내용 끝) -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
</body>
</html>