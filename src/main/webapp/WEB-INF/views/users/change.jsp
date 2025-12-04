<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration</title>
<c:import url="/WEB-INF/views/template/head.jsp"/>
<style>
    .error {
        color: red;
        font-weight: bold;
    }
</style>
</head>
<body id="page-id">
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
                    <h1 class="h3 mb-4 text-gray-800">Register New User</h1>

                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <form:form method="post" modelAttribute="usersDTO" enctype="multipart/form-data">
                                
                                
                                <div class="form-group">
                                    <label for="password">기존 비밀번호:</label>
                                    <input type="password" class="form-control" id="password" name="password" required> 
                                    <form:errors path="password"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="password">새 비밀번호:</label>
                                    <form:input path="passwordCheck" cssClass="form-control" id="passwordCheck"/>
                                    <form:errors path="passwordCheck"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="password">새 비밀번호 확인:</label>
                                    <form:input path="passwordCheck" cssClass="form-control" id="passwordCheck"/>
                                    <form:errors path="passwordCheck"></form:errors>
                                </div>
                                
                                <button type="submit" class="btn btn-primary">Register</button>
                            </form:form>
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
