<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글쓰기</title>
    <!-- Custom fonts for this template-->
	<c:import url="/WEB-INF/views/template/head.jsp"></c:import>   
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-bs4.min.css" rel="stylesheet">
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
                        <h1 class="h3 mb-0 text-gray-800">공지사항 작성</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>
                    
                    <!-- ===== 새롭게 추가된 글쓰기 폼 ===== -->
                    <!-- Content Row -->
                    <div class="row justify-content-center">
                    	<div class="col-lg-8">
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">새 글 작성</h6>
                                </div>
                                <div class="card-body">
                                    <form action="./add" method="post">
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
                                        <a href="./list" class="btn btn-secondary">목록으로</a>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ===== 새롭게 추가된 글쓰기 폼 ===== -->
                
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
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-bs4.min.js"></script>
    <script type="text/javascript">
    	$("#boardContents").summernote()
    </script>
</body>
</html>