<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="/WEB-INF/views/template/header.jsp" %>	
		<div class="container-wrapper">
		<div class="container">
			<div class="page-header" style="padding-top: 65px">
				<h1>All Products</h1>

				<p class="lead">Checkout all the awesome products now!</p>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr class="bg-success">
						<th>Photo Thumb</th>
						<th>Product Name></th>
						<th>Category</th>
						<th>Condition</th>
						<th>Price</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach items="${productList}" var="product">
					<tr>
						<td><img src="<c:url value="/resources/images/${product.productId}.png" />" alt="image" /></td>
						<td>${product.productName}</td>
						<td>${product.productCategory}</td>
						<td>${product.productCondition}</td>
						<td>${product.productPrice}USD</td>
						<td><a href="<spring:url value="/productList/viewProduct/${product.productId}"/>"><span class="glyphicon glyphicon-info-sign"></span></a></td>
					</tr>
				</c:forEach>

			</table>


<%@include file="/WEB-INF/views/template/footer.jsp" %>	