<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/template/head.jsp"/>
</head>
<body id="page-id">
	<div id="wrapper">
		<!-- side bar -->
		<c:import url="/WEB-INF/views/template/sidebar.jsp"/>
		<%-- <c:import url="./template/sidebar.jsp"/> --%>
		<!-- side bar -->
		
		<!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
        	<!-- Main Content -->
            <div id="content">
            	<!-- top bar -->
            	<c:import url="/WEB-INF/views/template/topbar.jsp"/>
            	<!-- top bar -->
            	
            	<!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Detail Page</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>
                    
                    <!-- Content Row -->
					<div class="row justify-content-center">
					    <div class="col-lg-8">
					
					        <!-- Board Detail Card -->
					        <div class="card shadow mb-4">
					
					            <!-- Card Header -->
					            <div class="card-header py-3 bg-light d-flex justify-content-between align-items-center">
					                <h5 class="m-0 font-weight-bold text-primary">${dto.boardTitle}</h5>
					                <span class="text-muted small">조회수: ${dto.boardHit}</span>
					            </div>
					
					            <!-- Card Body -->
					            <div class="card-body">
					
					                <!-- Writer -->
					                <div class="mb-3">
					                    <label class="font-weight-bold text-dark mr-2">작성자:</label>
					                    <span>${dto.boardWriter}</span>
					                </div>
					
					                <hr>
					
					                <!-- Contents -->
					                <div class="board-contents" style="white-space: pre-line; line-height: 1.6;">
					                    ${dto.boardContents}
					                </div>
					
					            </div>
					
					            <!-- Card Footer -->
					            <div class="card-footer bg-white text-right">
					                <a href="./reply?boardNum=${dto.boardNum}" class="btn btn-secondary btn-sm">답글</a>
					                <a href="./list" class="btn btn-secondary btn-sm">목록으로</a>
					            </div>
					
					        </div>
					
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
                        <span>Copyright &copy; Your Website 2025</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->
            
    	</div>    
	</div>
	<c:import url="/WEB-INF/views/template/foot.jsp"/>
</body>
</html>