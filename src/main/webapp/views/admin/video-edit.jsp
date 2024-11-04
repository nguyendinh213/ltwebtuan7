<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<h2>Edit Video</h2>

<form action="<c:url value='/admin/video/update'/>" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>Video ID:</td>
            <td>
                <input type="hidden" name="videoid" value="${video.videoId}" />
                ${video.videoId}
            </td>
        </tr>
        <tr>
            <td>Title:</td>
            <td><input type="text" name="title" value="${video.title}" required /></td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><textarea name="description" rows="5" cols="40" required>${video.description}</textarea></td>
        </tr>
        <tr>
            <td>Views:</td>
            <td><input type="number" name="views" value="${video.views}" required /></td>
        </tr>
        <tr>
            <td>Active:</td>
            <td>
                <select name="active">
                    <option value="1" <c:if test="${video.active == 1}">selected</c:if>>Active</option>
                    <option value="0" <c:if test="${video.active == 0}">selected</c:if>>Inactive</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Category:</td>
            <td>
                <select name="categoryId" required>
                    <option value="">-- Select Category --</option>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.categoryId}" <c:if test="${video.category.categoryId == category.categoryId}">selected</c:if>>${category.categoryname}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
            <td>Current Poster:</td>
            <td>
                <img src="<c:url value='/upload/${video.poster}'/>" alt="Current Poster" width="100" />
            </td>
        </tr>
        <tr>
            <td>Poster (Link):</td>
            <td><input type="text" name="poster" placeholder="Enter image URL" /></td>
        </tr>
        <tr>
            <td>Poster (File):</td>
            <td><input type="file" name="poster1" /></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="Update Video" />
                <a href="<c:url value='/admin/videos'/>">Cancel</a>
            </td>
        </tr>
    </table>
</form>
