<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원리스트</title>
<script src="resources/jquery/jquery-3.6.0.js"></script>
<script src="resources/js/member.js"></script>
<link href="resources/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<h3 style="color: lightcoral; text-align: center; font-size: 30px; padding: 10px;">신규 회원 리스트</h3>
	<p style="text-align: left; width: 900px;">
	</p>
	<table>
		<thead>
			<tr style="background: mistyrose;">
				<th>아이디</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>우편번호</th>
				<th>주소</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(mList)>0}">
					<c:forEach var="dto" items="${mList}">
					<tr>
						<td style="text-align: center; width: 10%">${dto.id}</td>
						<td style="text-align: center; width: 10%">
							<a href="memberDetail?id=${dto.id}">${dto.name}</a>
						</td>
						<td><a href="mailto:${dto.email}">${dto.tel}</a></td>
						<td>${dto.zipcode}</td>
						<td style="text-align: left; width: 55%">
						${dto.address}&nbsp;${dto.address2}</td>
					</tr>
					</c:forEach>
				</c:when>
				<c:when test="${fn:length(list)==0}">
					<tr>
					<td colspan="5" style="text-align: center">게시글이 없습니다.</td>
					</tr>
				</c:when>
			</c:choose>
		</tbody>
<!-- 		<t:foot> -->
<!-- 			<tr> -->
<%-- 				<td colspan="6" style="text-align: center"><c:forEach var="i" --%>
<%-- 						begin="${1}" end="${pvo.pagAllCnt}"> --%>
<%-- 						<a href="list?currentPage=${i}">[${i}]</a>&nbsp;&nbsp; --%>
<%-- 						</c:forEach></td> --%>
<!-- 			</tr> -->
<!-- 		</t:foot> -->
	</table>
</body>
</html>