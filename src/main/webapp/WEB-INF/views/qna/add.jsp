<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 새 질문 작성</title>
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
                        <h1 class="h3 mb-0 text-gray-800">Q&A 새 질문 작성</h1>
                    </div>
                    
                    <!-- Content Row -->
                    <div class="row justify-content-center">
                    	<div class="col-lg-8">
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">새 질문 작성</h6>
                                </div>
                                <div class="card-body">
                                    <form action="<c:url value='/qna/add'/>" method="post">
                                        <div class="form-group">
                                            <label for="boardTitle">제목</label>
                                            <input type="text" class="form-control" id="boardTitle" name="boardTitle" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="boardWriter">작성자</label>
                                            <input type="text" class="form-control" id="boardWriter" name="boardWriter" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="boardContents">내용</label>
                                            <textarea class="form-control" id="boardContents" name="boardContents" rows="10" required></textarea>
                                        </div>
                                        <button type="submit" class="btn btn-primary">작성</button>
                                        <a href="<c:url value='/qna/list'/>" class="btn btn-secondary">목록으로</a>
                                    </form>
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