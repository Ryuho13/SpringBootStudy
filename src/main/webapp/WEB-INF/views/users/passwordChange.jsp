<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>
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
                    <h1 class="h3 mb-4 text-gray-800">Change Your Password</h1>

                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <form:form method="post" modelAttribute="usersDTO">
                                <div class="form-group">
                                    <label for="username">Username:</label>
                                    <form:input path="username" cssClass="form-control" id="username" readonly="true"/>
                                    <form:errors path="username" cssClass="error"></form:errors>
                                </div>
                                
                                <div class="form-group">
                                    <label for="oldPassword">Current Password:</label>
                                    <form:password path="oldPassword" cssClass="form-control" id="oldPassword"/>
                                    <form:errors path="oldPassword" cssClass="error"></form:errors>
                                </div>

                                <div class="form-group">
                                    <label for="password">New Password:</label>
                                    <form:password path="password" cssClass="form-control" id="password"/>
                                    <form:errors path="password" cssClass="error"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="passwordCheck">Confirm New Password:</label>
                                    <form:password path="passwordCheck" cssClass="form-control" id="passwordCheck"/>
                                    <form:errors path="passwordCheck" cssClass="error"></form:errors>
                                </div>
                                
                                <button type="submit" class="btn btn-primary">Change Password</button>
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