<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<h2>Add New Video</h2>

<form action="<c:url value='/admin/video/insert'/>" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>Title:</td>
            <td><input type="text" name="title" required /></td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><textarea name="description" rows="5" cols="40" required></textarea></td>
        </tr>
        <tr>
            <td>Views:</td>
            <td><input type="number" name="views" value="0" required /></td>
        </tr>
        <tr>
            <td>Active:</td>
            <td>
                <select name="active">
                    <option value="1">Active</option>
                    <option value="0">Inactive</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Category:</td>
            <td>
                <select name="categoryId" required>
                    <option value="">-- Select Category --</option>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.categoryId}">${category.categoryname}</option>
                    </c:forEach>
                </select>
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
                <input type="submit" value="Add Video" />
                <a href="<c:url value='/admin/videos'/>">Cancel</a>
            </td>
        </tr>
    </table>
</form>
