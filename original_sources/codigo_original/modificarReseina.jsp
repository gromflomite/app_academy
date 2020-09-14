<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
    //Comprobamos que recibimos los parametros adecuados
    if(request.getParameter("idCurso")==null|| request.getParameter("idReseina")==null|| request.getParameter("reseina")==null){
        response.sendRedirect("index.jsp");
    }else{

        String driver="org.sqlite.JDBC";
        Connection conexion=null;
        try{
            //Accedemos a la base de datos y modificamos la reseña que coincida con el id pasado por parametro
            Class.forName(driver);
            conexion= DriverManager.getConnection("jdbc:sqlite:" + getServletContext().getRealPath("/") + "/db_ipartek.db");
                
            String sql = "UPDATE reseinas SET reseina ='"+request.getParameter("reseina")+"' WHERE id="+request.getParameter("idReseina");
            Statement statement = conexion.createStatement();
            
            //comprobamos que se haya modificado y regresamos al curso de la reseña
            if(statement.executeUpdate(sql)>0){
                response.sendRedirect("curso.jsp?curso="+request.getParameter("idCurso"));
            }else{
                response.sendRedirect("index.jsp");
            }
            
        }catch(Exception e){
            response.sendRedirect("index.jsp");
        }finally{
            conexion.close();
        }
    }
%>
