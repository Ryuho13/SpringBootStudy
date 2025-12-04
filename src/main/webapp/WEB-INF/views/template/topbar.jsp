<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

            <!-- Sidebar Toggle (Topbar) -->
            <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                <i class="fa fa-bars"></i>
            </button>

            <!-- Topbar Search -->
            <form
                class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                <div class="input-group">
                    <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                        aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                        <button class="btn btn-primary" type="button">
                            <i class="fas fa-search fa-sm"></i>
                        </button>
                    </div>
                </div>
            </form>

            <!-- Topbar Navbar -->
            <ul class="navbar-nav ml-auto">

                <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                <li class="nav-item dropdown no-arrow d-sm-none">
                    <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-search fa-fw"></i>
                    </a>
                    <!-- Dropdown - Messages -->
                    <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                        aria-labelledby="searchDropdown">
                        <form class="form-inline mr-auto w-100 navbar-search">
                            <div class="input-group">
                                <input type="text" class="form-control bg-light border-0 small"
                                    placeholder="Search for..." aria-label="Search"
                                    aria-describedby="basic-addon2">
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="button">
                                        <i class="fas fa-search fa-sm"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </li>
                <!-- Nav Item - Language -->
                <li class="nav-item dropdown no-arrow">
                    <a class="nav-link dropdown-toggle" href="#" id="languageDropdown" role="button"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-globe fa-fw"></i>
                    </a>
                    <!-- Dropdown - Language Information -->
                    <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in bg-gradient-primary"
                        aria-labelledby="languageDropdown">
                        <a class="dropdown-item text-white" href="?lang=ko" style="background-color: #4e73df;">
							한국어
                        </a>
                        <a class="dropdown-item text-white" href="?lang=en" style="background-color: #4e73df;">
							English
                        </a>
                        <a class="dropdown-item text-white" href="?lang=ja" style="background-color: #4e73df;">
							日本語
                        </a>
                    </div>
                </li>

                <div class="topbar-divider d-none d-sm-block"></div>

                <!-- Nav Item - User Information -->
                <li class="nav-item dropdown no-arrow">
                    <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <c:choose>
                            <c:when test="${not empty user}">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${user.username}</span>
                            </c:when>
                            <c:otherwise>
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Login</span>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${not empty user.fileDTOs and not empty user.fileDTOs[0].fileName}">
                                <img class="img-profile rounded-circle"
                                    src="/files/${user.fileDTOs[0].fileName}">
                            </c:when>
                            <c:otherwise>
                                <img class="img-profile rounded-circle"
                                    src="${pageContext.request.contextPath}/img/undraw_profile.svg">
                            </c:otherwise>
                        </c:choose>
                    </a>
                    <!-- Dropdown - User Information -->
                    <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                        aria-labelledby="userDropdown">
                        <c:if test="${not empty user}">
                            <a class="dropdown-item" href="/users/mypage">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                My Page
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/users/logout" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Logout
                            </a>
                        </c:if>
                        <c:if test="${empty user}">
                            <a class="dropdown-item" href="/users/login">
                                <i class="fas fa-sign-in-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Login
                            </a>
                            <a class="dropdown-item" href="/users/register">
                                <i class="fas fa-user-plus fa-sm fa-fw mr-2 text-gray-400"></i>
                                Register
                            </a>
                        </c:if>
                    </div>
                </li>

            </ul>

        </nav>
        <!-- End of Topbar -->