<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="include.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Trade page</title>
    <style>
        .error {
            color: #ff0000;
            font-style: italic;
            font-weight: bold;
        }
    </style>
</head>
<body>

<label>type</label>
<label>
    <select id="selector">
        <c:forEach var="product" items="${supportedProducts}">
            <option value="${product}">${product}</option>
        </c:forEach>
    </select>
</label>

<c:forEach var="product" items="${supportedProducts}">
    <c:set var="fieldList" value="${supportedProductsMap[product]}"/>
    <table id="${product}" hidden>
        <c:forEach var="field" items="${fieldList}">
            <c:set var="key" value="${product}_${field}"/>
            <tr>
                <td>${field}</td>
                <td><input id="value_${key}" class="value_${product} value" type="text" field="${field}"/></td>
                <td><label id="error_${key}" class="error_${product} error"></label></td>
            </tr>
        </c:forEach>
    </table>
</c:forEach>

<input id="validate_button" type="button" value="Validate">
</body>
</html>