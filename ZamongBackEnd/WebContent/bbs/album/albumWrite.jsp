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
	<div class="container theme-showcase" role="main">
		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>엘범 등록</h1>
		</div>

		<!-- 실제내용의 제목표시 -->
		<div class="page-header">
			<h1>엘범 등록</h1>
		</div>
		<!-- 아래에 실제내용 표시 -->
		<div>
			<form id="frm" name="frm" action="<c:url value='/ZAMONG/Album/Write.do'/>" method="post">
				<table class="table table-striped" id="grop_info">
					<tr>
						<td width="120px">
							<img id="at_image" src="<c:url value='/Images/Artist/bagicPerson.png'/>" style="width: 100px; height: 100px;"/>
						</td>
						<td>가수</td>
						<td>
							<input type="hidden" name="al_artistno" id="al_artistno" value="" />
							<input type="hidden" name="al_divide" id="al_divide" value="" />
							<input type="text" name="al_artist" id="at_name" value="" onclick="javascript:search();"/>
							<input type="button" value="가수검색" onclick="javascript:search();"/>
						</td>
					</tr>
				<!-- </table>
				<table class="table table-striped" id="artist_info"> -->
					<tr class="artist_info">
						<td>엘범명</td>
						<td>
							<input type="text" name="al_albumname" />
						</td>
					</tr>
					<tr>
						<td>발매일</td>
						<td colspan="2">
							<input type="text" name="al_releasedate" />
						</td>
					</tr>
					<tr>
						<td>발매사</td>
						<td colspan="2">
							<input type="text" name="al_publishcorp" />
						</td>
					</tr>
					<tr>
						<td>기획사</td>
						<td colspan="2">
							<input type="text" name="al_entertaincorp" />
						</td>
					</tr>
					<tr>
						<td>앨범소개</td>
						<td colspan="2">
							<input type="text" name="al_albuminfo" />
						</td>
					</tr>
					<tr>
						<td>음원 등록</td>
						<td colspan="2">
							<input type="file" id="ss_name" name="ss_name" onchange="test();"/>
							<table class="table table-striped" style="display: none;" id="sound_info">
							</table>
						</td>
					</tr>
				</table>
				<!-- <a id="artist_next" class="btn btn-sm btn-info" align="center">다음</a> -->
				<input type="submit" id="album_submit" class="btn btn-sm btn-info" value="등록">
			</form>
		</div>
	</div>
	<!-- /container(내용 끝) -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
	<script type="text/javascript">
		/* $(function(){
			$("#artist_info").css("display", "none");
			$("#artist_submit").css("display", "none");
			
			$("#artist_next").click(function(){
				$("#grop_info").css("display", "none");
				$("#artist_info").css("display", ""); /* .attr({"type": "submit", "value": "등록"}); */
				/*$("#artist_next").css("display", "none");
				$("#artist_submit").css("display", "");
			});
		}); */
		
		function search(){
			window.open("${pageContext.request.contextPath}/Common/FindArtistPopup.jsp", "_blank"
						, "toolbar=yes,scrollbars=yes,resizable=no,location=no,top=500,left=500,width=400,height=400");
		}
		
		function test(){
			 var formData = new FormData($("#frm")[0]); 
			 if (formData != null){
		       $.ajax({
		            type : 'post',
		            url : "<c:url value='/ZAMONG/Sound/Write.do' />",
		            enctype : "multipart/form-data",
		            data: formData,
		            processData : false,
		            contentType : false,
		            success : function(data) {
		            	$("#sound_info").css("display", "block");
		            	$("#sound_info").html("").html(data);
		            },
		            error : function(error) {
		                alert("파일 업로드에 실패하였습니다.");
		                console.log(error);
		                //console.log(error.status);
		            }
		        });
			 }
		}
	</script>
</body>
</html>