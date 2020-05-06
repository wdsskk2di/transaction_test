<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

송금은 ${result.getTotmoney() }원이 최대입니다.
<h3>은행처리 시스템</h3><br>
<a href="deposit">입금</a> | 
<a href="check">잔액 확인</a> | 
<a href="remit">송금</a>

</body>
</html>