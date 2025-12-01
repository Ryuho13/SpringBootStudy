<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/template/head.jsp"></c:import>
</head>
<body id="page-top">
	<div id="wrapper">
		<!-- side bar -->
		<c:import url="/WEB-INF/views/template/sidebar.jsp"></c:import>
		<!-- side bar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<c:import url="/WEB-INF/views/template/topbar.jsp"></c:import>
				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4 ">
						
						<h1 class="h3 mb-0 text-gray-800">${category}</h1>
				<!-- topbar -->
				<div class="row justify-content-center mb-3">
					<form class="form-inline method-get" action="./list">
						<select name="kind" class="form-control mr-2">
						    <option value="k1" ${param.kind eq 'k1' ? 'selected="selected"' : ''}>제목</option>
						    <option value="k2" ${param.kind eq 'k2' ? 'selected="selected"' : ''}>내용</option>
						    <option value="k3" ${param.kind eq 'k3' ? 'selected="selected"' : ''}>작성자</option>
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

					<!-- Content Row -->
					<div class="row justify-content-center">
						<table class="table col-sm-8 mt-5">
							<thead class="thead-dark">
								<tr>
									<th>Num</th>
									<th>Title</th>
									<th>Writer</th>
									<th>Date</th>
									<th>Hit</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="dto">
									<tr>
										<td>${dto.boardNum}</td>
										<td>
										<c:catch>
										<c:forEach begin="1" end="${dto.boardDepth}">--</c:forEach>										
										</c:catch>
										
											<a href="./detail?boardNum=${dto.boardNum}">${dto.boardTitle}</a>
										
											
										</td>
										<td>${dto.boardWriter}</td>
										<td>${dto.boardDate}</td>
										<td>${dto.boardHit}</td>
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
					                <li class="page-item ${i == pager.page ? 'active' : ''}">
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
								<a href="./add"  class="btn btn-primary">글쓰기</a>
						</div>
    		</div>
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Your Website 2021</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->
		</div>

	</div>

	<c:import url="/WEB-INF/views/template/foot.jsp"></c:import>
</body>
</html>