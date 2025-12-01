<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세</title>
<c:import url="/WEB-INF/views/template/head.jsp"></c:import>
</head>
<body id="page-top">
	<div id="wrapper">
		<c:import url="/WEB-INF/views/template/sidebar.jsp"></c:import>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<c:import url="/WEB-INF/views/template/topbar.jsp"></c:import>

				<div class="container-fluid">
					<h1 class="h3 mb-4 text-gray-800">상품 상세</h1>

					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">
								상품 번호: ${dto.productNum} - ${dto.productTitle}</h6>
						</div>
						<div class="card-body">
							<p>
								<strong>상품 이름:</strong> ${dto.productName}
							</p>
							<p>
								<strong>카테고리:</strong> ${dto.productCategory}
							</p>
							<p>
								<strong>금리:</strong> ${dto.productRate}%
							</p>
							<p>
								<strong>판매 여부:</strong> 
								<c:choose>
									<c:when test="${dto.productSale == true}">판매 중</c:when>
									<c:otherwise>판매 중지</c:otherwise>
								</c:choose>
							</p>
							<hr>
							<p> ${dto.productTitle} 상품에 대한 정보입니다. </p>
						</div>
					</div>

					<div class="d-flex justify-content-end">
					    <a href="./update?productNum=${dto.productNum}"
							class="btn btn-primary mr-2">수정하기</a>
						<button type="button" class="btn btn-danger" data-toggle="modal"
							data-target="#deleteModal">삭제하기</button>
						<a href="javascript:history.back()" class="btn btn-secondary ml-2">목록으로</a>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
		aria-labelledby="deleteModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="deleteModalLabel">정말 삭제하시겠습니까?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">${dto.productNum}번 상품을 삭제합니다.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">취소</button>

					<form action="./delete" method="post">
						<input type="hidden" name="productNum" value="${dto.productNum}">
						<button type="submit" class="btn btn-danger">삭제하기</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/views/template/foot.jsp"></c:import>
</body>
</html>