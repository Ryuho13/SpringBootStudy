<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="row justify-content-center mt-4">
                    <nav>
                        <ul class="pagination">

                            <li class="page-item">
                                <a class="page-link" href="./list?page=${pager.begin-1}&kind=${pager.kind}&search=${pager.search}">
                                    &laquo;
                                </a>
                            </li>

                            <c:forEach begin="${pager.begin}" end="${pager.end}" var="i">
                                <li class="page-item ${pager.page == i ? 'active' : ''}">
                                    <a class="page-link"
                                       href="./list?page=${i}&kind=${pager.kind}&search=${pager.search}">
                                        ${i}
                                    </a>
                                </li>
                            </c:forEach>

                            <li class="page-item">
                                <a class="page-link" href="./list?page=${pager.end+1}&kind=${pager.kind}&search=${pager.search}">
                                    &raquo;
                                </a>
                            </li>

                        </ul>
                    </nav>
                </div>