<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
    //Comprobamos que se recibe el id de la reseña
    if(request.getParameter("idReseina")==null){
        response.sendRedirect("index.jsp");
    }else{

        String driver="org.sqlite.JDBC";
        Connection conexion=null;
        try{
            //Accedemos a la base de datos y borramos la reseña cuyo id es el que recivimos como parametro
            Class.forName(driver);
            conexion= DriverManager.getConnection("jdbc:sqlite:" + getServletContext().getRealPath("/") + "/db_ipartek.db");
            
            String sql = "DELETE FROM reseinas WHERE id="+request.getParameter("idReseina");
            Statement statement = conexion.createStatement();
            
            //Cuando borramos regresamos al curso cuyo id coincide con el del curso de la reseña. En caso de haber algún fallo volvemos a index
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
