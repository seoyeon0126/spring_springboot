<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.BoardDAO" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 작업</title>
</head>
<body>
<jsp:useBean id="bdto" class="model.BoardDTO"/>
<jsp:setProperty property="*" name="bdto"/>
<%
      BoardDAO dao= BoardDAO.getInstance();
      int r = dao.deleteBoard(bdto);
      String url = "./Board?command=list";  	 
      if(r==1){
    	  %>
    	  <script type="text/javascript">
    	     alert('삭제성공')
    	  </script>
    	 <%
      }else{
    	  %>
    	  <script type="text/javascript">
    	     alert('삭제실패')
    	  </script>
    	 <%
      }
%>
  <script type="text/javascript">
     document.location.href='<%=url%>';
  </script>
</body>
</html>