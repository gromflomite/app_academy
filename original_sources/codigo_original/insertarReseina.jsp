<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
    HttpSession s = request.getSession(true);
    request.setCharacterEncoding("UTF-8");

    //Comprobamos que recibimos los parametros adecuados
    if(request.getParameter("idCurso")==null|| request.getParameter("idAlumno")==null|| request.getParameter("reseina")==null){
        response.sendRedirect("index.jsp");
    }else{

        String driver="org.sqlite.JDBC";
        Connection conexion=null;
        try{
            //Accedemos a la base de datos e insertamos la reseña con los datos pasados por parametro
            Class.forName(driver);
            conexion= DriverManager.getConnection("jdbc:sqlite:" + getServletContext().getRealPath("/") + "/db_ipartek.db");
       
            String sql = "INSERT INTO reseinas (reseina, idCurso, idAlumno) VALUES ('"+request.getParameter("reseina")+"',"+request.getParameter("idCurso")+","+request.getParameter("idAlumno")+")";
            Statement statement = conexion.createStatement();
            
            //comprobamos que se haya insertado y volvemos al curso de la reseña
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
