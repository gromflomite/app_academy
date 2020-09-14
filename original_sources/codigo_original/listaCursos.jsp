<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="java.sql.*"%>
<%
    HttpSession s = request.getSession(true);
    request.setCharacterEncoding("UTF-8");
    
    String driver="org.sqlite.JDBC";
    Connection conexion=null;
    try{
        //Accedemos a la base de datos y obtemeos los datos del curso
        Class.forName(driver);
        conexion= DriverManager.getConnection("jdbc:sqlite:" + getServletContext().getRealPath("/") + "/db_ipartek.db");
        
        String sql = "SELECT c.id, c.identificador, c.curso, c.horas, p.nombre, p.apellidos"
                    +" FROM cursos c"
                    +" INNER JOIN profesores p ON c.idProfesor=p.id"
                    +" GROUP BY c.id";
        Statement statement = conexion.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        //comprobamos que se hayan obtenido resultados y devolvemos las filas de la tabla
        while(resultSet.next()){
            %>
            <tr>
                <td><a href="curso.jsp?curso=<%=resultSet.getString("id")%>"><%=resultSet.getString("identificador")%>-<%=resultSet.getString("curso")%></a></td>
                <td><%=resultSet.getString("nombre")%> <%=resultSet.getString("apellidos")%></td>
                <td><%=resultSet.getString("horas")%>h.</td>
            </tr>
            <%
        }

    }catch(Exception e){
        s.setAttribute("codError", 1);
        response.sendRedirect("index.jsp");
    }finally{
        conexion.close();
    }
%>
