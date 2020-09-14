<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    HttpSession s = request.getSession(true);
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>MF0226_3: Ipartek by Elier Otero</title>
	    <meta name="description" content="Ipartek formación"> 
        <meta name="viewport" content="width=device-width, user-scalable=no">
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <header>
            <div id="header_title">
                <div id="header_img">
                    <img src="img/logo.png" alt="Maleta" title="Musix">
                </div>
            </div>
        </header>
        <nav id="menu_principal">
            <ul>
                <li><a href="index.jsp"><div class="icono home"></div>Inicio</a></li>
            </ul>
        </nav>