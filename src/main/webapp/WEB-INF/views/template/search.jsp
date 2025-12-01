<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="row justify-content-center">
					    <div class="col-md-6 col-lg-5">
					        <form action="./list" method="get" class="input-group mb-4">
					
					            <select class="custom-select w-10" name="kind">
					                <option value="k1" ${pager.kind == 'k1' ? 'selected' : ''}>Title</option>
					                <option value="k2" ${pager.kind == 'k2' ? 'selected' : ''}>Contents</option>
					                <option value="k3" ${pager.kind == 'k3' ? 'selected' : ''}>Writer</option>
					            </select>
					
					            <input type="text" class="form-control w-50" name="search"
					                   value="${pager.search}" placeholder="검색어 입력">
					
					            <div class="input-group-append">
					                <button class="btn btn-outline-primary" type="submit">검색</button>
					            </div>
					
					        </form>
					    </div>
					</div> 	