<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="${pageContext.request.contextPath}/admin/category/insert" method="post" enctype="multipart/form-data">
  
  <label for="categoryname">Category name:</label><br>
  <input type="text" id="categoryname" name="categoryname"><br>
  
  <label for="images">Link images:</label><br>
  <input type="text" id="images" name="images">
  
  <label for="images">Upload file:</label><br>
  <input type="file" id="images1" name="images1">
  
  <label for="lname">Status:</label><br>
  <input type="radio" id="ston" name="status" value="1" checked>
  <label for="css">Hoạt động</label><br>
  <input type="radio" id="stoff" name="status" value="0">
  <label for="javascript">Khóa</label>
  
  <input type="submit" value="Insert"><br>
</form>