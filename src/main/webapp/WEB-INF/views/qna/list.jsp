<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <!-- Custom fonts for this template-->
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
       			
       			<!-- topbar -->
       			<c:import url="/WEB-INF/views/template/topbar.jsp"></c:import>
            	<!-- topbar -->
            	
            	<!-- Begin Page Content -->
                <div class="container-fluid">
                	<!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">공지사항</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>
                    
                    <!-- Content Row -->
                    <div class="row justify-content-center">
                    <!-- 생성한 contents 작성 -->
					<table class="table col-sm-8 mt-5">
  <thead>
    <tr>
      <th scope="col">글번호</th>
      <th scope="col">제목</th>
      <th scope="col">작성자</th>
      <th scope="col">작성일</th>
      <th scope="col">조회수</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${list}" var="list">
    <tr>
      <th scope="row">${list.boardNum}</th>
      		<td><a href="detail?boardNum=${list.boardNum}">${list.boardTitle}</a></td>
      		<td>${list.boardWriter}</td>
      		<td>${list.boardDate}</td>
      		<td>${list.boardHit}</td>
    	</tr>
  	</c:forEach>
   
  </tbody>
</table>
                    </div>
                </div>
<div class="row justify-content-center">
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <!-- 이전 블록 링크 -->
            <c:if test="${pager.prev}">
                <li class="page-item">
                    <a class="page-link" href="./list?page=${pager.startBlock - 1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>

            <!-- 페이지 번호 링크 -->
            <c:forEach begin="${pager.startBlock}" end="${pager.endBlock}" var="i">
                <li class="page-item ${pager.page == i ? 'active' : ''}">
                    <a class="page-link" href="./list?page=${i}">${i}</a>
                </li>
            </c:forEach>

            <!-- 다음 블록 링크 -->
            <c:if test="${pager.next}">
                <li class="page-item">
                    <a class="page-link" href="./list?page=${pager.endBlock + 1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
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