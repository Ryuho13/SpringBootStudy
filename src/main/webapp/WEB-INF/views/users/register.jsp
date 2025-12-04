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
                                    <label for="username">Username:</label>
                                    
                                    <form:input path="username" cssClass="form-control" id="username"/>
                                    <!-- <input type="text" class="form-control" id="username" name="username" required> -->
                                    <form:errors path="username"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password:</label>
                                    
                                    <form:input path="password" cssClass="form-control" id="password"/>
                                    <!-- <input type="password" class="form-control" id="password" name="password" required> -->
                                    <form:errors path="password"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="name">Name:</label>
                                    
                                    <form:input path="name" cssClass="form-control" id="name"/>
                                    <!-- <input type="text" class="form-control" id="name" name="name" required> -->
                                    <form:errors path="name"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="email">Email:</label>
                                    
                                    <form:input path="email" cssClass="form-control" id="email"/>
                             		<!-- <input type="email" class="form-control" id="email" name="email" required> -->
                             		<form:errors path="email"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="phone">Phone:</label>
                                    
                                    <form:input path="phone" cssClass="form-control" id="phone"/>
                                    <!-- <input type="text" class="form-control" id="phone" name="phone" required> -->
                                    <form:errors path="phone"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="birth">Birth Date:</label>
                                    
                                    <form:input type="date" path="birth" cssClass="form-control" id="birth"/>
                                    <!-- <input type="date" class="form-control" id="birth" name="birth" required> -->
                                    <form:errors path="birth"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="profile">Profile Image:</label>
                                    <input type="file" class="form-control-file" id="profile" name="profile">
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
