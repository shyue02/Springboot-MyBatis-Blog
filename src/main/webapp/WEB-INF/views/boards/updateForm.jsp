<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/boards/${boards.id}/update" method="post">	<!-- 모델에 담아서 와야 한다 (mvc 패턴) -->
		<!-- 주소에 동사쓰지말고 명사!, 뒤에 메소드로 동사표현(post) -->
		<div class="mb-3 mt-3">
			<input type="text" class="form-control" placeholder="Enter title" name="title" value="${boards.title}" required="required" maxlength="50">
			<!-- 키값은 변수명과 같게 적어주는게 좋다 -->
		</div>
		<div class="mb-3">
			<textarea class="form-control" rows="8" name="content" required="required">${boards.content}</textarea>
		</div>
		<button type="submit" class="btn btn-primary">수정완료</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>

