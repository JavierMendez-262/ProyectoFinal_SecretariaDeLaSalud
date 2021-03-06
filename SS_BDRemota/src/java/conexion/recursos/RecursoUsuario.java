/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion.recursos;

import conexion.filtro.JWTokenHelper;
import dao.PersistenciaListas;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import negocio.Usuario;

/**
 * REST Web Service
 *
 * @author javie
 */
@Path("login")
public class RecursoUsuario {

    private PersistenciaListas persistenciaListas;

    @Context
    private UriInfo context;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validar(Usuario usuarioValidar) {
        try {
            persistenciaListas = PersistenciaListas.getInstance();

            if (usuarioValidar == null) {
                Response.status(Response.Status.UNAUTHORIZED).build();
            }

            if (usuarioValidar.getNickname().equals("admin") || usuarioValidar.getPassword().equals("admin")) {
                String token = JWTokenHelper.getInstance().crearToken(usuarioValidar.getNickname(), usuarioValidar.getPassword());
                return Response.status(Response.Status.CREATED).entity(token).build();
            }
            
            Response.status(Response.Status.UNAUTHORIZED).build();

            String token = JWTokenHelper.getInstance().crearToken(usuarioValidar.getNickname(), usuarioValidar.getPassword());
            return Response.status(Response.Status.CREATED).entity(token).build();

        } catch (SQLException ex) {
            Logger.getLogger(RecursoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecursoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
