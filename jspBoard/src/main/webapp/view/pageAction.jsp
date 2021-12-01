<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int rowPerPage = 7;
	BoardDAO dao= BoardDAO.getInstance();
	int allCount = dao.getAllCount();
	int pageCnt = 0;
	if(allCount%rowPerPage==0) {
		pageCnt = allCount/rowPerPage;		
	} else {
		pageCnt = allCount / rowPerPage+1;
	}
	session.setAttribute("pageCnt", pageCnt);
%>
</body>
</html>