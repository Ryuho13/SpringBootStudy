<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
                        <h1 class="h3 mb-0 text-gray-800">Add Form</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>
                    
                    <!-- Content Row -->
                    <div class="row justify-content-center mt-5">
                    <div class="col-lg-6 mt-5">

                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">${category} ${sub}</h6>
                                </div>
                                <div class="card-body">
                                
 			<!--       Form 		-->
 								   <form:form modelAttribute="dto" method="post" enctype="multipart/form-data">
 								   <form:hidden path="boardNum"/>
 								   
                                   	  <input type="hidden" name="boardNum" value="${dto.boardNum}">
									  <div class="form-group">
									  	
									    <label for="writer">Writer</label>
									    <!-- 밑에꺼를 대체  -->
									    <form:input path="boardWriter" cssClass="form-control" id="writer"  />
									    <!-- <input type="text" class="form-control" value="${dto.boardWriter}" id="writer" name="boardWriter" aria-describedby="emailHelp">  -->
									  </div>
									  <div class="form-group">
									    <label for="title">Title</label>
									    
									    <!-- 입력은 path = name, class = cssclass, id = id -->
									    <form:input path="boardTitle" cssClass="form-control" id="title"/>
									    <!--  <input type="text" class="form-control" value="${dto.boardTitle}" name="boardTitle" id="title"> -->
									    <form:errors path="boardTitle"></form:errors>
									  </div>
									  
									  <div class="form-group">
									    <label for="contents">Contents</label>
									    <form:textarea path="boardContents" cssClass="form-control" id="contents"/>
									    <!-- <textarea class="form-control" name="boardContents" id="contents" rows="3">${dto.boardContents}</textarea> -->
									  </div>
									  <div class="form-group">
									  	<button type="button" id="fileAdd" class="form-control btn ">File Add</button>
									  </div>
									  <div id="files" class="form-group">
									  	
									  </div>
									  	
										
									  <button type="submit"  class="btn btn-primary">Submit</button>
									</form>
									</form:form>
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
<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-bs4.min.js"></script>	
<script type="text/javascript">
	$("#contents").summernote()
</script>
<script src="/js/board/board.js"></script>
</body>
</html>