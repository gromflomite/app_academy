<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="java.sql.*"%>
<jsp:include page="head.jsp"/>
        <main id="curso">
        <%
            String driver="org.sqlite.JDBC";
            Connection conexion=null;

            try{
                //Accedemos a la base de datos y conseguimos la información del curso
                Class.forName(driver);
                conexion= DriverManager.getConnection("jdbc:sqlite:" + getServletContext().getRealPath("/") + "/db_ipartek.db");
                
                String sql = "SELECT c.id, c.identificador, c.curso, c.horas, p.nombre, p.apellidos"
                                +" FROM cursos c"
                                +" INNER JOIN profesores p ON c.idProfesor=p.id"
                                +" WHERE c.id="+request.getParameter("curso")
                                +" GROUP BY c.id";
                Statement statement = conexion.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                //comprobamos que se hayan obtenido resultados y montamos una tabla con ellos
                while(resultSet.next()){
                    %>
                    <h2><%=resultSet.getString("identificador")%>-<%=resultSet.getString("curso")%></h2>
                    <section class='ficha_entrada'>
                        <h3>Datos Curso</h3>
                        <table>
                            <thead>
                                <th>Curso</th>
                                <th>Identificador</th>
                                <th>Horas</th>
                                <th>Profesor</th>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><%=resultSet.getString("curso")%></td>
                                    <td><%=resultSet.getString("identificador")%></td>
                                    <td><%=resultSet.getInt("horas")%></td>
                                    <td><%=resultSet.getString("nombre")%> <%=resultSet.getString("apellidos")%></td>
                                </tr>
                            </tbody>
                        </table>
                        <h3>Reseñas</h3>
                        <%
                            //Si al entrar en cursos.jsp no llegan el id o la reseña es porque hay que cargar el formulario de insertar, si llegan se carga el formulario de modificar con los datos de la reseña
                            if(request.getParameter("idReseina")==null || request.getParameter("reseina")==null){
                                %>
                                <h4>Insertar Reseña</h4>
                                <form id="form_reseina" action="insertarReseina.jsp" method="post">
                                    <input type="text" name="idCurso" value="<%=resultSet.getInt("id")%>" class="hide">
                                    <select name="idAlumno">
                                        <option value="1">Elier</value>
                                        <option value="2">Beatriz</value>
                                        <option value="3">Asier</value>
                                        <option value="4">Lander</value>
                                    </select>
                                    <textarea name="reseina" required></textarea>
                                    <input type="submit" value="Insertar Reseña">
                                </form>
                                <%
                            }else{
                                %>
                                <h4>Modificar Reseña</h4>
                                <form id="form_reseina" action="modificarReseina.jsp" method="post">
                                    <input type="text" name="idCurso" value="<%=request.getParameter("curso")%>" class="hide">
                                    <input type="text" name="idReseina" value="<%=request.getParameter("idReseina")%>" class="hide">
                                    <textarea name="reseina" required><%=request.getParameter("reseina")%></textarea>
                                    <input type="submit" value="Modificar Reseña">
                                </form>
                                <%
                            }
                            
                            //Accedemos a la base de datos y obtenemos las reseñas del curso en el que estamos
                            String sql2 = "SELECT r.id, r.reseina, a.nombre, a.apellidos "
                                +" FROM reseinas r"
                                +" INNER JOIN alumnos a ON r.idAlumno=a.id"
                                +" WHERE r.idCurso="+request.getParameter("curso")
                                +" GROUP BY r.id";
                            Statement statement2 = conexion.createStatement();
                            ResultSet resultSet2 = statement2.executeQuery(sql2);

                            //Si la consulta nos devuelve resultados pintamos una caja con las reseñas del curso y les añadimos un boton para borrarlas y otro para modificarlas
                            while(resultSet2.next()){
                                %>
                                <div class="reseina">
                                    <p class="reseina_alumno">Alumno: <%=resultSet2.getString("nombre")%> <%=resultSet2.getString("apellidos")%></p>
                                    <p><%=resultSet2.getString("reseina")%></p>
                                    <div class="reseina_opciones">
                                        <form action="curso.jsp?curso=<%=resultSet.getInt("id")%>" method="post">
                                            <input type="text" name="idReseina" value="<%=resultSet2.getInt("id")%>" class="hide">
                                            <input type="text" name="reseina" value="<%=resultSet2.getString("reseina")%>" class="hide">
                                            <input type="submit" class="icono editar" value="">
                                        </form>
                                        <form action="borrarReseina.jsp" method="post">
                                            <input type="text" name="idCurso" value="<%=resultSet.getInt("id")%>" class="hide">
                                            <input type="text" name="idReseina" value="<%=resultSet2.getInt("id")%>" class="hide">
                                            <input type="submit" class="icono borrar" value="">
                                        </form>
                                    </div>
                                </div>
                                <%
                            }
                        %>
                    </section>
                    <%
                }

            }catch(Exception e){
                //En caso de haber algún fallo, volvemos al principio
                response.sendRedirect("index.jsp");
            }finally{
                conexion.close();
            }

        %>
            
        </main>
<jsp:include page="footer.jsp"/>
        