<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tr>
<td colspan="2"><input type="checkbox" value="1" name="ss_albumtitle">노래 대표곡</td>
</tr>
<tr>
<td>노래 제목</td>
<td id="ss_title"><input type="text" value="${dto.ss_title }" name="ss_title" /></td>
</tr>
<tr>
<td>노래 장르</td>
<td><input type="text" value="${empty dto.ss_genre ? '' : dto.ss_genre }" name="ss_genre" /></td>
</tr>
<tr>
<td>노래 가사</td>
<td id="li_contents">
<textarea cols="100%" rows="30" name="ly_contents">${dto.ly_contents }</textarea>
</td>
</tr>
<tr>
<td>노래 재생시간</td>
<td id="ss_duration"><input type="text" name="ss_duration" value="1"></td>
</tr>
<tr>
<td>노래 이미지</td>
<td id="al_albumimage"><img style="width:100px; height: 100px;" src="<c:url value='/Images/Sound/${dto.al_albumimage }'/>" alt="엘범 대표 이미지"></td>
</tr>
<input type="hidden" name="ss_path" value="${dto.ss_path }">
<input type="hidden" name="al_albumimage" value="${dto.al_albumimage }">
