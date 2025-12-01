<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 수정</title>
<c:import url="/WEB-INF/views/template/head.jsp"></c:import>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
</head>
<body id="page-top">
	<div id="wrapper">
		<c:import url="/WEB-INF/views/template/sidebar.jsp"></c:import>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">

				<c:import url="/WEB-INF/views/template/topbar.jsp"></c:import>
				<div class="container-fluid">
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">상품 수정하기</h1>
						<a href="#"
							class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
							class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
					</div>
					<div class="container-fluid">
					
					<form action="./update" method="post">
						
						<input type="hidden" name="productNum" value="${dto.productNum}">
						
						<div class="mb-3">
						  <label for="productTitle" class="form-label">상품 제목 (Title)</label>
						  <input type="text" class="form-control" id="productTitle" name="productTitle" value="${dto.productTitle}" required>
						</div>
						<div class="mb-3">
						  <label for="productName" class="form-label">상품 이름 (Name)</label>
						  <input type="text" class="form-control" id="productName" name="productName" value="${dto.productName}" required>
						</div>
						<div class="mb-3">
							<label for="productCategory" class="form-label">카테고리</label>
							<input type="text" class="form-control" id="productCategory" name="productCategory" value="${dto.productCategory}" required>
						</div>
						<div class="mb-3">
							<label for="productRate" class="form-label">금리 (Rate)</label>
							<input type="number" step="0.01" class="form-control" id="productRate" name="productRate" value="${dto.productRate}" required>
						</div>
						<div class="mb-3">
							<label for="productSale" class="form-label">판매 여부</label>
							<select class="form-control" id="productSale" name="productSale">
								<option value="1" ${dto.productSale == true ? 'selected' : ''}>판매 중</option>
								<option value="0" ${dto.productSale == false ? 'selected' : ''}>판매 중지</option>
							</select>
						</div>
						
						<div class="d-grid gap-2">
							<button type="submit" class="btn btn-warning">수정하기</button>
							<a href="javascript:history.back()" class="btn btn-secondary">취소</a>
						</div>
					</form>
					</div>

				</div>
				</div>
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center 
my-auto">
						<span>Copyright &copy; Your Website 2021</span>
					</div>
				</div>
			</footer>
			</div>

	</div>

	<c:import url="/WEB-INF/views/template/foot.jsp"></c:import>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script> 
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-bs4.min.js"></script>
    
    <script>
    	$(document).ready(function() {
    	  $('#boardContents').summernote({ // **NOTE:** boardContents ID는 사용하지 않으므로, 이 스크립트는 제거하거나 ID를 맞춰야 합니다. (일단 유지)
    	  	height: 300,
            focus: true
    	  });
    	});
</script>
</body>
</html>