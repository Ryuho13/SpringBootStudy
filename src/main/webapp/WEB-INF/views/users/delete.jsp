<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
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
                    <h1 class="h3 mb-4 text-gray-800">회원 탈퇴</h1>

                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <h4 class="text-danger mb-4">정말로 회원 탈퇴를 하시겠습니까?</h4>
                            <p>회원 탈퇴 시 모든 정보가 삭제되며, 복구할 수 없습니다.</p>
                            <p>신중하게 결정해 주세요.</p>
                            
                            <form action="./delete" method="post">
                                <button type="submit" class="btn btn-danger">확인 (회원 탈퇴)</button>
                                <a href="/users/mypage" class="btn btn-secondary">취소</a>
                            </form>
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
