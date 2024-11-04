<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
    }
    .right-topbar {
        margin: 0;
        padding: 10px;
        background-color: #fff;
        border-bottom: 1px solid #ddd;
    }
    .list-inline {
        list-style: none;
        margin: 0;
        padding: 0;
    }
    .list-inline li {
        display: inline;
        margin-right: 15px;
    }
    .list-inline li a {
        text-decoration: none;
        color: #333;
        font-weight: bold;
    }
    .list-inline li a:hover {
        color: #007bff;
    }
    .search-button {
        cursor: pointer;
        color: #007bff;
    }
    .col-sm-6 {
        width: 50%;
        float: right;
        text-align: right;
    }
</style>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.account == null}">
			<div class="col-sm-6">
				<ul class="list-inline right-topbar pull-right">
					<li><a href="${pageContext.request.contextPath }/login">Đăng nhập</a> | <a href="${pageContext.request.contextPath }/register">Đăng
							ký</a></li>
					<li><i class="search fa fa-search search-button"></i></li>
				</ul>
			</div>
		</c:when>
		<c:otherwise>
			<div class="col-sm-6">
				<ul class="list-inline right-topbar pull-right">
					<li><a
						href="${pageContext.request.contextPath}/member/myaccount">${sessionScope.account.fullName}</a>
						| <a href="${pageContext.request.contextPath}/logout">Đăng Xuất</a></li>
					<li><i class="search fa fa-search search-button"></i></li>
				</ul>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>