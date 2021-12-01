<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>    
<%@ page import="java.util.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트 처리</title>
</head>
<body>
<%
   BoardDAO dao= BoardDAO.getInstance();
   List<BoardDTO> list = dao.getBoards();
   session.setAttribute("list", list);
   response.sendRedirect("./Board?command=listView");
%>
</body>
</html>