<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
<c:import url="/WEB-INF/views/template/head.jsp"></c:import>
</head>
<body id="page-top">
	<div id="wrapper">
		<c:import url="/WEB-INF/views/template/sidebar.jsp"></c:import>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<c:import url="/WEB-INF/views/template/topbar.jsp"></c:import>
				<div class="container-fluid">
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4 ">
						
						<h1 class="h3 mb-0 text-gray-800">${category}</h1>
				<div class="row justify-content-center mb-3">
					<form class="form-inline method-get" action="./list">
						<select name="kind" class="form-control mr-2">
						    <option value="k1" ${param.kind eq 'k1' ?
'selected="selected"' : ''}>제목 (productTitle)</option>
						    <option value="k2" ${param.kind eq 'k2' ?
'selected="selected"' : ''}>상품 이름 (productName)</option>
						    <option value="k3" ${param.kind eq 'k3' ?
'selected="selected"' : ''}>카테고리 (productCategory)</option>
						</select> 
						<input placeholder="검색어를 입력하세요" type="text" name="search"
							class="form-control mr-2" value="${param.search}">
						<button class="btn btn-primary" type="submit">검색</button>
					</form>
				</div>
						<a href="#"
							class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
							class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
					</div>
					<c:if test="${not empty result}">
						<div class="alert alert-info alert-dismissible fade show"
							role="alert">
							<strong>알림:</strong> ${result}
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
					</c:if>

					<div class="row justify-content-center">
						<table class="table col-sm-10 mt-5">
							<thead class="thead-dark">
								<tr>
									<th>번호 (Num)</th>
									<th>제목 (Title)</th>
									<th>상품 이름 (Name)</th>
									<th>카테고리 (Category)</th>
									<th>금리 (Rate)</th>
									<th>판매 (Sale)</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="dto">
									<tr>
										<td>${dto.productNum}</td>
										<td>
										
											<a href="./detail?productNum=${dto.productNum}">${dto.productTitle}</a>
										
											
										</td>
										<td>${dto.productName}</td>
										<td>${dto.productCategory}</td>
										<td>${dto.productRate}%</td>
										<td>
											<c:choose>
												<c:when test="${dto.productSale == true}">판매 중</c:when>
												<c:otherwise>판매 중지</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="row justify-content-between col-sm-5 offset-sm-5">
				    <nav aria-label="Page navigation example">
				        <ul class="pagination ">
				            
				            <li class="page-item">
				    
            <a class="page-link"
				                   href="./list?page=${pager.begin-1}&kind=${param.kind}&search=${param.search}" 
				                   aria-label="Previous">
				                    <span aria-hidden="true">&laquo;</span>
				                </a>
				           
 </li>
            
					            <c:forEach begin="${pager.begin}" end="${pager.end}" var="i">
					                <li class="page-item ${i == pager.page ?
'active' : ''}">
					                    <a class="page-link"
					                       href="./list?page=${i}&kind=${param.kind}&search=${param.search}">
					                        ${i}
					                    </a>
					          
      </li>
					            </c:forEach>
					            
					            <li class="page-item">
					                <a class="page-link"
					                   href="./list?page=${pager.end+1}&kind=${param.kind}&search=${param.search}" 
					                   aria-label="Next"> 

					                       <span aria-hidden="true">&raquo;</span>
					                </a>
					            </li>
					        </ul>
					    </nav>
					    <div>
								<a href="./add"  class="btn btn-primary">상품 등록</a>
						</div>
    		</div>
				</div>
				</div>
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy;
Your Website 2021</span>
					</div>
				</div>
			</footer>
			</div>

	</div>

	<c:import url="/WEB-INF/views/template/foot.jsp"></c:import>
</body>
</html>