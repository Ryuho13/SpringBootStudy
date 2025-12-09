<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
<c:import url="/WEB-INF/views/template/head.jsp"/>
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
                    <h1 class="h3 mb-4 text-gray-800">My Page</h1>

                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-4 text-center d-flex flex-column justify-content-center align-items-center">
                                    <c:choose>
                                        <c:when test="${not empty dto.userFileDTO and not empty dto.userFileDTO.fileName}">
                                            <img src="/files/${dto.userFileDTO.fileName}" alt="Profile Image" class="img-fluid" style="width: 200px; height: 200px; object-fit: cover; border-radius: 15px;">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="/img/undraw_profile.svg" alt="Default Profile Image" class="img-fluid" style="width: 200px; height: 200px; object-fit: cover; border-radius: 5px;">
                                        </c:otherwise>
                                    </c:choose>
                                    <h5 class="mt-3 font-weight-bold text-primary">${dto.name}</h5>
                                </div>
                                <div class="col-md-8">
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item d-flex justify-content-between align-items-center px-0 py-3">
                                            <strong class="text-primary">ID</strong>
                                            <span class="text-gray-800">${dto.username}</span>
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between align-items-center px-0 py-3">
                                            <strong class="text-primary">Email</strong>
                                            <span class="text-gray-800">${dto.email}</span>
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between align-items-center px-0 py-3">
                                            <strong class="text-primary">Phone</strong>
                                            <span class="text-gray-800">${dto.phone}</span>
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between align-items-center px-0 py-3">
                                            <strong class="text-primary">Birth Date</strong>
                                            <span class="text-gray-800">${dto.birth}</span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-12 text-right">
                                    <a href="/users/update" class="btn btn-primary">Update Information</a>
                                    <a href="/users/passwordChange" class="btn btn-warning">Change Password</a>
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
