<%@page import="com.zamong.me.service.MemberDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.zamong.me.service.MemberDAO"%>
<%@page import="com.zamong.nt.service.impl.NotiDataDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%-- <%
request.setCharacterEncoding("UTF-8");
//검색과 관련된 파라미터 받기]
//검색후 페이징과 관련된 파라미터를 전달할 값을 저장할 변수]
//1]파라미터(키값) 받기
	String no=request.getParameter("me_no");
	//현재 페이지번호 받기

	//2]CRUD작업용 BbsDAO생성
	MemberDAO dao = new MemberDAO(application);
	//조회수 업데이트

	//상세보기용 메소드 호출	
	MemberDTO dto = dao.selectOne(no);
	//이전글/다음글 조회
	//3]자원반납
	dao.close();


%> --%>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<link rel="icon" href="<c:url value='/Images/zamonglogo.gif'/>" />

<title>공지사항</title>
<!-- Bootstrap core CSS -->
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마(Bootstrap theme) -->
<link rel="stylesheet"
	href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
</head>
<script>
	function iswrite() {
		
		location.href="<c:url value='/bbs/notice/NoticeWrite.jsp'/>";
	}
			
</script>

<body role="document">

	<jsp:include page="/Template/Top.jsp" />

	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->


		<hr />
		<div class="page-header">
			<h1>결제내역 보기</h1>
		</div>
		<div class="page-header">
			<!-- 아래에 실제내용 표시 -->
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active"><a
					href="<c:url value='/ZAMONG/Cash/List.do'/>">결제내역</a></li>
				<li role="presentation"><a
					href="<c:url value='/ZAMONG/UserQuestionList.do'/>">스트리밍</a></li>
				<li role="presentation"><a
					href="<c:url value='/ZAMONG/ProblemList.do'/>">상품</a></li>
			</ul>
		</div>



		<hr />
		
		
		<div>
					<%-- <%=dto.getMe_id() %> --%>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>NO</th>
						<th>결제 날짜</th>
						<th>포인트</th>

					</tr>
			
				</thead>
				
				<c:choose>
					<c:when test="${empty list }">
						<tr bgcolor="white" align="center">
							<td colspan="6">등록된 자료가 없어요</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="item" items="${list}" varStatus="loop">
							<tr bgcolor="white" align="center">
								<td>${item.ch_no}</td>
								<td>${item.ch_regidate}</td>
								<td>${item.ch_havecash}원</td>

								<%-- <td><a href='javascript:isDelete(${item.nt_no})'><button
											type="button" class="btn btn-xs btn-primary">삭제</button></a></td> --%>

							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>

			</table>
			<table width="100%">
				<tr align="center">
					<td>${pagingString }</td>
				</tr>
			</table>
			<hr />





		</div>
	</div>
	<!-- /container(내용 끝) -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script> 

	function isDelete(nt_no){
		if(confirm("정말로 삭제 할래?")){
			location.href="NoticeDelete.do?nt_no="+nt_no;
		}//////////////////			
		
	}/////////////////////	
	function isabc(){
		alert($("form").get(0));
		$("form").submit();
	}
	
</script>
	<script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
</body>
</html>