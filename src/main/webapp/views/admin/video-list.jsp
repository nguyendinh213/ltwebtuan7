<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<a href="<c:url value='/admin/video/add'></c:url>">Add Video</a>
<table border="1">
    <tr>
        <th>STT</th>
        <th>VideoId</th>
        <th>Poster</th>
        <th>Title</th>
        <th>Description</th>
        <th>Views</th>
        <th>CategoryId</th>
        <th>Active</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${listvideo}" var="video" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${video.videoId}</td>
            <c:choose>
                <c:when test="${video.poster != null && video.poster.length() >= 5 && video.poster.startsWith('https')}">
                    <c:url value="${video.poster}" var="posterUrl"></c:url>
                </c:when>
                <c:otherwise>
                    <c:url value="/image?fname=${video.poster}" var="posterUrl"></c:url>
                </c:otherwise>
            </c:choose>
            <td><img height="150" width="200" src="${posterUrl}" alt="${video.poster}"/></td>
            <td>${video.title}</td>
            <td>${video.description}</td>
            <td>${video.views}</td>
            <td>${video.category.categoryId}</td>
            <td>
                <c:choose>
                    <c:when test="${video.active == 1}">
                        <span>Active</span>
                    </c:when>
                    <c:otherwise>
                        <span>Inactive</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="<c:url value='/admin/video/edit?id=${video.videoId}'/>">Edit</a> |
                <a href="<c:url value='/admin/video/delete?id=${video.videoId}'/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
