<%@page import="com.zamong.bp.service.BuyproductDTO"%>
<%@page import="com.zamong.bp.service.impl.BuyproductDAO"%>
<%@page import="com.zamong.nt.service.impl.NotiDataDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String no = request.getParameter("me_no");

%>
<!DOCTYPE html>
<html>
<head>


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<link rel="icon" href="<c:url value='/Images/zamonglogo.gif'/>" />

<title>결제 페이지</title>
<!-- Bootstrap core CSS -->
<!-- 합쳐지고 최소화된 최신 CSS -->
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마(Bootstrap theme) -->
<link rel="stylesheet"
	href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
	<script>
	function goSubmit() {
	    window.opener.name = "parentPage"; // 부모창의 이름 설정
	    document.myForm.target = "parentPage"; // 타켓을 부모창으로 설정 
	    document.myForm.action = "<c:url value='/ZAMONG/Payment/Write.do'/>";
	    document.myForm.submit();
	    alert("충전성공");
	    self.close();
	}
	
	</script>  
</head>

<body>
	<div class="popup_title cash">


		<div class="txt_title type01">
			<h1>자몽캐쉬 충전</h1>
			<p class="txt_title_info">자몽이용권, 곡, 뮤직비디오 등 결제 시 사용가능합니다.</p>
		</div>



	</div>


	<div class="popup_cntt box_scroll">
	  <%--   <form action="${pageContext.request.contextPath}/ZAMONG/Payment/Write.do" method="post">     --%>
<form name="myForm" method="post">    
	 <input type="hidden" name="me_no" value="<%=no%>"/>

		<div class="tbl_style">
		
			<table border="1" style="width: 100%" class="board_style03">
				<caption>이 표는 자몽캐쉬 이벤트성 포인트 충전 결제 리스트로 관리자에서  최종 포인트금액, 결제방법 내용을 포함하고 있습니다.</caption>
				<colgroup>
					<col style="width: 119px; *width: 103px;">
					<col style="width: 160px; *width: 144px;">
					<col>
				</colgroup>
				<tbody>
					 
					<tr>
						<th scope="row" class="bg_gray">충전금액</th>
						<!--141021 수정 lyr-->
				 <td style="vertical-align: middle"> <input type="radio"
							name="chargeRates" class="input_radio" value="selectRate"
							checked="checked">
							<input type="hidden" value="3" name="pd_no"/>
                             <select id="anwser" name="price" title="결제금액" onchange="javascript:selChange(this.value);">
                                <option value="">결제금액 선택</option>
                                 
			                    <option value="sel">직접입력</option>

								<option value="1000">1,000 원</option>

								<option value="2000">2,000 원</option>

								<option value="3000">3,000 원</option>

								<option value="4000">4,000 원</option>

								<option value="5000">5,000 원</option>

								<option value="6000">6,000 원</option>

								<option value="7000">7,000 원</option>

								<option value="8000">8,000 원</option>

								<option value="9000">9,000 원</option>

								<option value="10000">10,000 원</option>
								
								<option value="11000">11,000 원</option>
						</select> 원</td> 
							<td><span class="boxEmailEndInput"><input type="text" title="결제 도메인 입력란" name="price1"  value="" />원</span></td>
			
					</tr>
					
				</tbody>
			</table>
					
			
			<div class="popup_cntt box_scroll" style="text-align: center;">
			 	 <input type="button" onclick="goSubmit()" value="확인" /> 
<!-- 		 <input type="submit" value="확인"/>    --> 
			</div>

		</div>
	 	</form> 
	</div>
<script>
function selChange(val){
    if(val=="sel"){
         $(".boxEmailEndInput > input").val("");
       }else{
         $(".boxEmailEndInput > input").val(val); 
       }
    
}
</script>

</body>
</html>