<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<style>
 * { margin: auto  }
   table{ text-align: center; 
      width: 960px;
      border: 1px solid gray;
   }
   td{padding:5px; 
      text-align: left;}
   th { padding: 1em; }
   th, td { border: 1px solid gray; }
   h3{ padding: 1em;
   		text-align: center; }
   a:link{color: black; text-decoration: none}   
   a:visited{color: black; text-decoration: none}   
   a:hover{color: black; text-decoration: none}   
</style>
</head>
<body>
	<h3>게시글 목록</h3>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>글제목</th>
				<th>작성자</th>
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
					<td style="text-align: left; width: 45%">
					<c:choose>
						<c:when test="${article.lev==0}">
							<img src="../images/level.gif" width="5px">
						</c:when>
						<c:when test="${dto.lev>0}">
							<img src="../images/level.gif" width="${dto.lev*10}px">
							<img src="../images/re.gif"> 
						</c:when>
					</c:choose>
					<a href="./Board?command=content&amp;num=${dto.num}">${dto.subject}</a>
					<c:if test="${dto.readcount>=9}"><img src="../images/hot.gif" width="30px" /></c:if>
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
					<td colspan="6" style="text-align: center"> 게시글이 없습니다.</td>
				</tr>
			</c:when>
		</c:choose>
		</tbody> 
		<t:foot>
				<tr>
					<td colspan="6" style="text-align: center"> 
					<input type="button" value="글쓰기" onclick="javascript:location.href='./Board?command=writeForm'"></input></td>
				</tr>
		</t:foot>
	</table>
</body>
</html>