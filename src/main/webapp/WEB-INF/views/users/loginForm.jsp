<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/login" method="post">	<!-- action="전송 될 위치의 주소 (컨트롤러의 주소)"  / select 하는데 post 쓰는건 login 뿐 -->
		<div class="mb-3 mt-3">
			<input
				type="text" class="form-control"
				placeholder="Enter username"	name="username">
		</div>
		<div class="mb-3">
			<input
				type="password" class="form-control" 
				placeholder="Enter password"  name="password">
		</div>
		<button type="submit" class="btn btn-primary">로그인</button>	<!-- 데이터를 한 번에 전송 -->
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>
