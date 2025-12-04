<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
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
       			
       			<!-- topbar -->
       			<c:import url="/WEB-INF/views/template/topbar.jsp"></c:import>
            	<!-- topbar -->
            	
            	<!-- Begin Page Content -->
                <div class="container-fluid">
                	<!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Index</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>
                    
                    <!-- Content Row -->
                    <div class="row">
                    <div>
                    <spring:message code="hi"></spring:message>
                    <spring:message code="hello" text="--키가 없을 경우 출력--  "></spring:message>
                    </div>
                    <!-- 생성한 contents 작성 -->
                    <c:if test="${not empty user}">
                    	<h1>Login 성공</h1>
                    	<spring:message code="message.welcome" arguments="${user.username},${user.birth}" argumentSeparator="," var="m"></spring:message>
                    	<h3>${m}</h3>
                    </c:if>
                    
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
<!-- Page level plugins -->
<script src="/vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="/js/demo/chart-area-demo.js"></script>
<script src="/js/demo/chart-pie-demo.js"></script>
<script src="./js/index/index.js"></script>
</body>
</html>