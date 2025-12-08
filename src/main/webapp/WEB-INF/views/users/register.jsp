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
                            <form:form method="post" modelAttribute="userDTO" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label for="username">Username:</label>
                                    <form:input path="username" cssClass="form-control" id="username"/>
                                    <form:errors path="username" cssClass="error"/>
                                </div>
                                
                                <div class="form-group">
                                    <label for="password">Password:</label>
                                    <form:input path="password" type="password" cssClass="form-control" id="password"/>
                                    <form:errors path="password" cssClass="error"/>
                                </div>
                                <div class="form-group">
                                    <label for="passwordCheck">Password Check:</label>
                                    <form:input path="passwordCheck" type="password" cssClass="form-control" id="passwordCheck"/>
                                    <form:errors path="passwordCheck" cssClass="error"/>
                                </div>
                                
                                <div class="form-group">
                                    <label for="name">Name:</label>
                                    <form:input path="name" cssClass="form-control" id="name"/>
                                    <form:errors path="name" cssClass="error"/>
                                </div>
                                <div class="form-group">
                                    <label for="email">Email:</label>
                                    <form:input path="email" cssClass="form-control" id="email"/>
                             		<form:errors path="email" cssClass="error"/>
                                </div>
                                <div class="form-group">
                                    <label for="phone">Phone:</label>
                                    <form:input path="phone" cssClass="form-control" id="phone"/>
                                    <form:errors path="phone" cssClass="error"/>
                                </div>
                                <div class="form-group">
                                    <label for="birth">Birth Date:</label>
                                    <form:input type="date" path="birth" cssClass="form-control" id="birth"/>
                                    <form:errors path="birth" cssClass="error"/>
                                </div>
                                <div class="form-group">
                                    <label for="profile">Profile Image:</label>
                                    <input type="file" class="form-control-file" id="profile" name="attach">
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