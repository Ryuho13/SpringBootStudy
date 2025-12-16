<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<c:import url="/WEB-INF/views/template/head.jsp"/>
</head>
<body id="page-top">
    <div id="wrapper">
        <!-- side bar -->
        <c:import url="/WEB-INF/views/template/sidebar.jsp"/>
        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
            <!-- Main Content -->
            <div id="content">
                <!-- top bar -->
                <c:import url="/WEB-INF/views/template/topbar.jsp"/>
                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-4 text-gray-800">Login</h1>

                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <form action="./login" method="post">
                                <c:if test="${param.error}">
                                    <div class="alert alert-danger" role="alert">
                                        ${param.message}
                                    </div>
                                </c:if>
                                <div class="form-group">
                                    <label for="username">Username:</label>
                                    <input type="text" class="form-control" id="username" name="username" required>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password:</label>
                                    <input type="password" class="form-control" id="password" name="password" required>
                                </div>
                                 <div class="form-group form-check">
    								<input type="checkbox" class="form-check-input" id="exampleCheck1" name="rememberme">
    								<label class="form-check-label" for="exampleCheck1">Remember Me</label>
  								</div>
                                <button type="submit" class="btn btn-primary">Login</button>
                            </form>
                            
                            <!-- ===== [START] 카카오 로그인 버튼 추가 ===== -->
                            <div class="mt-3">
                                <a href="/oauth2/authorization/kakao" class="btn btn-warning btn-block">
                                    <i class="fas fa-comment"></i> Kakao Login
                                </a>
                            </div>
                            <!-- ===== [END] 카카오 로그인 버튼 추가 ===== -->
                            
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