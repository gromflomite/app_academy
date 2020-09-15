<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${param.title}| App Academy</title>

<!-- Using relative paths without including the context root name -->
<base href="${pageContext.request.contextPath}/" />
<!-- CSS normalize (https://necolas.github.io/normalize.css/) -->
<link rel="stylesheet" href="./css/normalize.css">
<!-- Bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<!-- Font awesome -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css">
<!-- Datatables plugin -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
<!-- Favicon -->
<link rel="apple-touch-icon" sizes="57x57" href="./img/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60" href="./img/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72" href="./img/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76" href="./img/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114" href="./img/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120" href="./img/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144" href="./img/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152" href="./img/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180" href="./img/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192" href="./img/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32" href="./img/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96" href="./img/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16" href="./img/favicon-16x16.png">
<link rel="manifest" href="/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="./img/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">
<!-- Own CSS -->
<link rel="stylesheet" href="./css/styles.css">

</head>

<body>

<header>
<!-- Including the .jsp to show feedback -->
<%@ include file="feedback.jsp"%>
</header>