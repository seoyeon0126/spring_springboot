<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글내용</title>
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
<script src="resources/jquery/jquery-3.6.0.js"></script>
<script src="resources/js/board.js"></script>
</head>
<body>
<form action="" method="post" name="content">
 <table border="1">
      <thead><tr><th colspan="2"> 글내용</th></tr></thead>
      <tbody> 
          <tr><th style="width: 30%">글제목</th>
              <td><input name="subject" value="${dto.subject}" readonly="readonly"  size="50"></td>
          </tr>
         <tr><th style="width: 30%">글쓴이</th>
             <td><input name="writer" value="${dto.writer}" readonly="readonly" size="50"></td>
         </tr>
         <tr><th style="width: 30%">글내용</th>
             <td><textarea rows="15" cols="50"  readonly="readonly" name="content">${dto.content}</textarea></td>
         </tr>
         <tr><th style="width: 30%">이메일</th>
             <td><input size="50" type="email"  readonly="readonly" name="email" value="${dto.email}" ></td>
         </tr>
    </tbody>
    <tfoot>
		   <tr> 
		       <td colspan="2" style="text-align: center"> 
		         <input type="hidden" name="num" value="${dto.num}">
		         <input type="hidden" name="ref" value="${dto.ref}">
		         <input type="hidden" name="step" value="${dto.step}">
		         <input type="hidden" name="lev" value="${dto.lev}">
		         <input type="hidden" name="passwd" value="${dto.passwd}">
		         <input type="hidden" name="currentPage" value="${pvo.currentPage}"/>
		         <input type="button" value="글목록" onclick="javascript:location.href='list?currentPage=${pvo.currentPage}'">
		         <input type="button" value="답글"  onclick="javascript:sendConfirm('reply')">
		         <input type="button" value="글수정" onclick="javascript:sendConfirm('update')">
		         <input type="button" value="글삭제" onclick="javascript:sendConfirm('delete')">
		        </td>
		   </tr>
		 <tfoot>
   </table>
  </form>
</body>
</html>