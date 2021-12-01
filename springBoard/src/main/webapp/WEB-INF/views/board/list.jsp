<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글목록</title>
<style>
* {
	margin: auto
}


table {
	text-align: center;
	width: 960px;
	border: 1px solid gray;
}

td {
	padding: 5px;
	text-align: left;
}

th {
	padding: 1em;
}

th, td {
	border: 1px solid gray;
}

h3 {
	padding: 1em;
	text-align: center;
	font-family: 카페24 단정해;
	font-weight: normal;
	font-size: 30px;
}

a:link {
	color: black;
	text-decoration: none
}

a:visited {
	color: black;
	text-decoration: none
}

a:hover {
	color: black;
	text-decoration: none
}
</style>
</head>
<body>
	<h3 style="color: lightcoral;">실시간 게시글 목록</h3>
	<p style="text-align: right; width: 960px;">전체게시글 수: &nbsp;
		${pvo.allCount}</p>
	<p style="text-align: left; width: 960px;">
		<input type="button" value="글쓰기"
			onclick="javascript:location.href='writeForm'"></input>
	</p>
	<table>
		<thead>
			<tr style="background: mistyrose;">
				<th>번호</th>
				<th>글제목</th>
				<th >작성자</th>
				<th>작성일자</th>
				<th>아이피</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(list)>0}">
					<c:forEach var="dto" items="${list}">
						<tr>
							<td style="text-align: right; width: 7%">${dto.no}</td>
							<td style="text-align: left; width: 45%"><c:choose>
									<c:when test="${article.lev==0}">
										<img src="resources/images/level.gif" width="5px">
									</c:when>
									<c:when test="${dto.lev>0}">
										<img src="resources/images/level.gif" width="${dto.lev*10}px">
										<img src="resources/images/re.gif">
									</c:when>
								</c:choose> <a href="content?num=${dto.num}&amp;currentPage=${pvo.currentPage}">${dto.subject}</a>
								<c:if test="${dto.readcount>=100}">
									<img src="resources/images/love.gif" width="30px" />
								</c:if>
								<c:if test="${dto.readcount>=20}">
									<img src="resources/images/hot.gif" width="30px" />
								</c:if>
								<c:if test="${dto.readcount>=15}">
									<img src="resources/images/luv.gif" width="30px" />
								</c:if>
								<c:if test="${dto.readcount>=10}">
									<img src="resources/images/star.gif" width="30px" />
								</c:if>
								<c:if test="${dto.readcount>=5}">
									<img src="resources/images/stemp.gif" width="30px" />
								</c:if>
								<c:if test="${dto.readcount>=1}">
									<img src="resources/images/heart.gif" width="30px" />
								</c:if>
								</td>
							<td><a href="mailto:${dto.email}">${dto.writer}</a></td>
							<td>${dto.reg_date}</td>
							<td>${dto.ip}</td>
							<td style="text-align: right; width: 9%">${dto.readcount}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:when test="${fn:length(list)==0}">
					<tr>
						<td colspan="6" style="text-align: center">게시글이 없습니다.</td>
					</tr>
				</c:when>
			</c:choose>
		</tbody>
		<t:foot>
			<tr>
				<td colspan="6" style="text-align: center"><c:forEach var="i"
						begin="${1}" end="${pvo.pagAllCnt}">
						<a href="list?currentPage=${i}">[${i}]</a>&nbsp;&nbsp;
						</c:forEach></td>
			</tr>
		</t:foot>
	</table>
</body>
</html>