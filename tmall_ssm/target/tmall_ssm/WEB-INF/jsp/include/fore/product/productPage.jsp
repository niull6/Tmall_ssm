<%--
  Created by IntelliJ IDEA.
  User: 64607
  Date: 2020/2/25
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<title>模仿天猫官网 ${p.name}</title>
<div class="categoryPictureInProductPageDiv">
    <img class="categoryPictureInProductPage" src="img/category/${p.category.id}.jpg">
</div>

<div class="productPageDiv">

    <%@include file="imgAndInfo.jsp" %>

    <%@include file="productReview.jsp" %>

    <%@include file="productDetail.jsp" %>
</div>