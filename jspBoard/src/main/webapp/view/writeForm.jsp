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
   <form action="./Board?command=wirteAction" method="post">
   <table border="1">
      <thead><tr><th colspan="2"> 글쓰기</th></tr></thead>
      <tbody> 
          <c:if test="${dto.num!=0 and dto.ref!=0}">
	          <tr><th style="width: 30%">글제목</th>
	              <td><input name="subject" placeholder="제목을 입력하세요" required="required" 
	                   size="50" value="[답글]">
		             <input type="hidden" name="num" value="${dto.num}">
			         <input type="hidden" name="ref" value="${dto.ref}">
			         <input type="hidden" name="step" value="${dto.step}">
			         <input type="hidden" name="lev" value="${dto.lev}">
	              </td>
	          </tr>
          </c:if>
          <c:if test="${dto.num==0}">
	          <tr><th style="width: 30%">글제목</th>
	              <td><input name="subject" placeholder="제목을 입력하세요" required="required" size="50"></td>
	          </tr>
          </c:if>
         <tr><th style="width: 30%">글쓴이</th>
             <td><input name="writer" placeholder="이름을 입력하세요" required="required" size="50"></td>
         </tr>
         <tr><th style="width: 30%">글내용</th>
             <td><textarea rows="15" cols="50" required="required" name="content" placeholder="내용을 입력해주세요. 욕설금지!!!"></textarea></td>
         </tr>
         <tr><th style="width: 30%">이메일</th>
             <td><input size="50" type="email" required="required" name="email" placeholder="이메일 주소 입력해주세요" ></td>
         </tr>
         <tr><th style="width: 30%">비밀번호</th>
             <td><input size="50" type="password" required="required" name="passwd" placeholder="비밀번호 입력해주세요" ></td>
         </tr>
    </tbody>
    <tfoot>
		   <tr> <td colspan="2" style="text-align: center"> 
		         <input type="submit" value="글저장">
		         <input type="button" value="글목록" onclick="javascript:location.href='./Board?command=list'">
		        </td>
		   </tr>
		 <tfoot>
   </table>
   </form>
   
</body>
</html>