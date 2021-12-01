<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글내용</title>
</head>
<body>
<jsp:useBean id="dto" class="model.BoardDTO"/>
<jsp:setProperty property="*" name="dto"/>
<%
   BoardDAO dao= BoardDAO.getInstance();
   BoardDTO sdto = dao.getBoard(dto);
    request.setAttribute("dto", sdto);
//    RequestDispatcher rd = request.getRequestDispatcher("contentView.jsp");
//    rd.forward(request, response);
	pageContext.forward("contentView.jsp");
%>
</body>
</html>