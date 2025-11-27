<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A</title>
    <!-- Custom fonts for this template-->
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
                        <h1 class="h3 mb-0 text-gray-800">Q&A</h1>
                    </div>

                    <!-- Content Row -->
                    <div class="row justify-content-center">
                        <!-- 검색 UI -->
                        <div class="col-sm-8 mt-5">
                            <form action="<c:url value='/qna/list'/>" method="get" class="form-inline justify-content-end">
                                <div class="form-group mr-2">
                                    <select class="form-control" name="kind">
                                        <option value="boardTitle">제목</option>
                                        <option value="boardContents">내용</option>
                                        <option value="boardWriter">작성자</option>
                                    </select>
                                </div>
                                <div class="form-group mr-2">
                                    <input type="text" class="form-control" name="search" value="${pager.search}" placeholder="검색어를 입력하세요">
                                </div>
                                <button type="submit" class="btn btn-primary">검색</button>
                            </form>
                        </div>

                        <table class="table col-sm-8 mt-2">
                            <thead>
                                <tr>
                                    <th scope="col">글번호</th>
                                    <th scope="col">제목</th>
                                    <th scope="col">작성자</th>
                                    <th scope="col">작성일</th>
                                    <th scope="col">조회수</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${list}" var="dto">
                                    <tr>
                                        <td>${dto.boardNum}</td>
                                        <td>
                                            <c:forEach begin="1" end="${dto.boardDepth}">
                                                &nbsp;&nbsp;
                                            </c:forEach>
                                            <c:if test="${dto.boardDepth > 0}">
                                                <i class="fas fa-reply fa-rotate-180"></i>
                                            </c:if>
                                            <a href="<c:url value='/qna/detail?boardNum=${dto.boardNum}'/>">${dto.boardTitle}</a>
                                        </td>
                                        <td>${dto.boardWriter}</td>
                                        <td>${dto.boardDate}</td>
                                        <td>${dto.boardHit}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <!-- Pagination and Write Button -->
                <div class="row justify-content-center ">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <!-- 이전 블록 링크 -->
                            <c:if test="${pager.begin > 1}">
                                <li class="page-item">
                                    <a class="page-link" href="<c:url value='/qna/list'>
                                        <c:param name='page' value='${pager.begin - 1}'/>
                                        <c:if test='${pager.kind != null and pager.kind != ""}'><c:param name='kind' value='${pager.kind}'/></c:if>
                                        <c:if test='${pager.search != null and pager.search != ""}'><c:param name='search' value='${pager.search}'/></c:if>
                                    </c:url>" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>

                            <!-- 페이지 번호 링크 -->
                            <c:forEach begin="${pager.begin}" end="${pager.end}" var="i">
                                <li class="page-item ${pager.page == i ? 'active' : ''}">
                                    <a class="page-link" href="<c:url value='/qna/list'>
                                        <c:param name='page' value='${i}'/>
                                        <c:if test='${pager.kind != null and pager.kind != ""}'><c:param name='kind' value='${pager.kind}'/></c:if>
                                        <c:if test='${pager.search != null and pager.search != ""}'><c:param name='search' value='${pager.search}'/></c:if>
                                    </c:url>">${i}</a>
                                </li>
                            </c:forEach>

                            <!-- 다음 블록 링크 -->
                            <c:if test="${pager.end < pager.totalPage}">
                                <li class="page-item">
                                    <a class="mr-5 page-link" href="<c:url value='/qna/list'>
                                        <c:param name='page' value='${pager.end + 1}'/>
                                        <c:if test='${pager.kind != null and pager.kind != ""}'><c:param name='kind' value='${pager.kind}'/></c:if>
                                        <c:if test='${pager.search != null and pager.search != ""}'><c:param name='search' value='${pager.search}'/></c:if>
                                    </c:url>" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                    
                    <div>
                        <a href="<c:url value='/qna/add'/>" class="btn btn-primary mx-5">새 질문 작성</a>
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
	
</body>
</html>