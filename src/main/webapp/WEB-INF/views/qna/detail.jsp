<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 상세</title>
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
                        <h1 class="h3 mb-0 text-gray-800">Q&A 상세</h1>
                    </div>
                    
                    <!-- Content Row -->
                    <div class="row justify-content-center">
                    	<div class="col-lg-8">
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">${dto.boardTitle}</h6>
                                </div>
                                <div class="card-body">
                                    <div class="form-group row">
                                        <label class="col-sm-2 col-form-label">작성자</label>
                                        <div class="col-sm-10">
                                            <input type="text" readonly class="form-control-plaintext" value="${dto.boardWriter}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2 col-form-label">작성일</label>
                                        <div class="col-sm-10">
                                            <input type="text" readonly class="form-control-plaintext" value="${dto.boardDate}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2 col-form-label">조회수</label>
                                        <div class="col-sm-10">
                                            <input type="text" readonly class="form-control-plaintext" value="${dto.boardHit}">
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="form-group">
                                        <p>${dto.boardContents}</p>
                                    </div>
                                    <div class="text-right">
                                        <a href="<c:url value='/qna/list'/>" class="btn btn-secondary">목록으로</a>
                                        <a href="<c:url value='/qna/reply?boardNum=${dto.boardNum}'/>" class="btn btn-primary">답글</a>
                                        <a href="<c:url value='/qna/update?boardNum=${dto.boardNum}'/>" class="btn btn-info">수정</a>
                                        <a href="<c:url value='/qna/delete?boardNum=${dto.boardNum}'/>" class="btn btn-danger">삭제</a>
                                    </div>
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