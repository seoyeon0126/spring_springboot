<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
<script src="resources/jquery/jquery-3.6.0.js"></script>
<script src="resources/jquery/jquery-1.10.2.min.js"></script>
<script src="resources/js/member.js"></script>
<script src="resources/js/zipCheck.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link href="resources/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="updateProc" method="post" name="wForm">
	<table border="1">
		<thead>
			<tr>
				<th colspan="2" style="text-align: center; background: mistyrose; color: lightcoral;
				font-size: 34px; font-family: 카페24 단정해; font-weight: normal;">회원정보</th>
			</tr>
		</thead>
		<tbody>
		<tr>
			<th>아이디</th>
			<td>  
				<input type="text" id="idchck" name="id" size="50" class="chk" 
				value="${mvo.id}" readonly="readonly"/>
			</td>
		</tr>
		<tr> 
			<th>이름</th>
			<td>
				<input type="text" class="chk" name="name" size="50" value="${mvo.name}"
				placeholder="이름을 입력해주세요" readonly="readonly" />
			</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td> 
				<input type="email" class="chk" name="email" size="50" class="chk" title="이메일"
				value="${mvo.email}" placeholder="이메일를 입력해주세요">
			</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>
				<input type="text" name="tel" size="34" value="${mvo.tel}" readonly="readonly" />
			</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>
				<input type="text" name="zipcode" size="34" id="sample6_postcode"
				placeholder="우편번호를 입력해주세요" value="${mvo.zipcode}">
				<input type="button" onclick="zipCheck()" class="chk" title="우편번호"
				title="우편번호" value="우편번호 찾기">
			</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>
				<input type="text" class="chk" name="address" size="50" id="sample6_address"
				placeholder="주소를 입력해주세요" value="${mvo.address}" title="주소" />
			</td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td>
				<input type="text" class="chk" name="address2" size="50" id="sample6_address2"
				placeholder="상세주소를 입력해주세요" value="${mvo.address2}" title="상세주소" />
			</td>
		</tr>
		<tr>
			<th>암호</th>
			<td>
				<input type="password" class="chk" name="pass" size="50"
				value="${mvo.pass}"/>
				<input size="50" type="hidden" value="${mvo.pass}">
			</td>
		</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2" id="submit1" style="text-align: center; font-weight: bold;">
		         <input type="button" value="회원수정" onclick="javascript:memUpdate('update')">
		         <input type="button" value="회원목록으로.." onclick="javascript:location.href='/memberList'">
				</td>
			</tr>
		</tfoot>
	</table>
</form>
</body>
</html>