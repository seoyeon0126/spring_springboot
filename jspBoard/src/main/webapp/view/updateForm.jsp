<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>         
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
  * { margin: auto  }
   table{ text-align: center; 
      width: 600px;
      border:1px solid gray;
   }
   td{padding:5px; 
      text-align: left;}
  th{ padding: 1em;
   }   
</style>
<title>글쓰기</title>
</head>
<body>
<jsp:useBean id="dto" class="model.BoardDTO"/>
<jsp:setProperty property="*" name="dto"/>
   <form action="./Board?command=updateAction" method="post">
   <table border="1">
      <thead><tr><th colspan="2"> 글수정</th></tr></thead>
      <tbody> 
		     <tr><th style="width: 30%">글제목</th>
		    	 <td><input name="subject" value='${dto.subject}' required="required" size="50">
		         	 <input type="hidden" value='${dto.num}' name="num">
		   		</td>
		     </tr>
	        <tr><th style="width: 30%">글쓴이</th>
	       		<td><input name="writer" value='${dto.writer}' required="required" size="50"></td>
	        </tr>
		    <tr><th style="width: 30%">글내용</th>
		        <td><textarea rows="15" cols="50" required="required" name="content">${dto.content}</textarea></td>
		    </tr>
		    <tr><th style="width: 30%">이메일</th>
		         <td><input size="50" type="email" required="required" name="email" value='${dto.email}'></td>
		     </tr>
		     <tr><th style="width: 30%">비밀번호</th>
		         <td><input size="50" type="password" required="required" name="passwd" value='${dto.passwd}'></td>
		     </tr>
    </tbody>
    <tfoot>
		   <tr> <td colspan="2" style="text-align: center"> 
		         <input type="submit" value="글수정">
		         <input type="button" value="글목록" onclick="javascript:location.href='./Board?command=list'">
		        </td>
		   </tr>
		 <tfoot>
   </table>
   </form>
   
</body>
</html>