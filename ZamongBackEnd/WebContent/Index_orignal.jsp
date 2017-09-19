<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en"> 
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<link rel="icon" href="<c:url value='/Image/Zamonglogo.jpg'/>"/>
	
    <title>Zamong BackEnd 페이지</title>
    <!-- Bootstrap core CSS -->
    <!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- Custom styles for this template -->
    <link href="<c:url value='/Css/Signin.css'/>" rel="stylesheet">
	<!-- 부가적인 테마(Bootstrap theme) -->
	<link rel="stylesheet" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
  </head>

  <body role="document">
    <!-- Fixed navbar -->
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">관리자페이지</a>
        </div>
         <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="<c:url value='/bbs/Main.jsp'/>">홈</a></li>
            <!-- class="active" -->
            <li><a href="<c:url value='/bbs/membermgmt/Member.jsp'/>">회원관리</a></li>
            <li class="dropdown">
              <a href="<c:url value='/bbs/chartofsong/top100/Daily.jsp'/>" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">멜론 차트<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="<c:url value='/bbs/chartofsong/top100/Daily.jsp'/>">TOP100</a></li>
                <li><a href="#">시대별 차트</a></li>
                <!-- <li class="divider"></li>  -->
              </ul>
            </li>
            <li><a href="#contact">최신</a></li>
            <li><a href="<c:url value='/bbs/chartofsong/genre/Balad.jsp'/>">장르</a></li>
            <li><a href="<c:url value='/bbs/Zamongtv/ZamongTV.jsp'/>">자몽TV</a></li>
            <li><a href="<c:url value='/bbs/musicaward/MusicAward.jsp'/>">뮤직어워드</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">매거진 <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="#">자몽 매거진</a></li>
                <li><a href="#">오늘의 매거진</a></li>
              </ul>
            </li>
            <li class="dropdown">
             <a href="<c:url value='/bbs/notice/NoticeList.jsp'/>" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">공지사항 <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                
                <li><a href="<c:url value='/bbs/notice/NoticeList.jsp'/>">공지사항 목록</a></li>
                <li><a href="#">자주하는 질문</a></li>
                <li><a href="#">문의사항</a></li>
              </ul>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav><!-- 고정된 nav바 끝 -->

	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h2 align="center">자몽 관리자 페이지</h2>
			<p align="center"><img src="Image/zamonglogo.gif"/></p>
		</div>
		<!-- 실제내용의 제목표시 -->
		<% if(session.getAttribute("USER_ID")==null){ %>
		<div class="page-header">
		</div>
		<%}else{ %>
		<span style="color:red;font-weight:bold"><%=request.getAttribute("ERROR_MASSAGE")==null ? "" : request.getAttribute("ERROR_MASSAGE")%></span>
		<%} %>
		<!-- 아래에 실제내용 표시 -->
		<% if(session.getAttribute("USER_ID")==null){ %>
		<form method="post" action="adminlogin/LoginProcess.jsp" class="form-signin">
			<h4 class="form-signin-heading" align="center" style="text-agign: center;">자몽 관리자로 로그인</h4>
			<label for="inputID" class="sr-only">아이디</label> 
			<input type="text" name="user" id="id" class="form-control" placeholder="아이디" required autofocus> 
			<label for="inputPassword" class="sr-only">비밀번호</label> 
			<input type="password" name="pass" id="inputPassword" class="form-control" placeholder="비밀번호" required>
			<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
		</form>
		<%}else{ %>
		<a href="${pageContext.request.contextPath }/adminlogin/Logout.jsp">로그아웃</a></li>
        <%} %>
	</div>
	<!-- /container(내용 끝) -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
  </body>
</html>