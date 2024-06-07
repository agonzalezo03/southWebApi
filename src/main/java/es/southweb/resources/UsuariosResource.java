package es.southweb.resources;

import com.google.gson.Gson;
import es.southweb.beans.Episodio;
import es.southweb.beans.LineaLista;
import es.southweb.beans.Lista;
import es.southweb.beans.Usuario;
import es.southweb.dao.IEpisodiosDAO;
import es.southweb.dao.ILineaListaDAO;
import es.southweb.dao.IListasDAO;
import es.southweb.dao.IUsuariosDAO;
import es.southweb.daofactory.DAOFactory;
import es.southweb.models.ComprobarFormulario;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author
 */
@Path("/usuario")
public class UsuariosResource {

    @POST
    @Path("/registro")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response registro(
            @FormDataParam("usuario") String usuario,
            @FormDataParam("correo") String correo,
            @FormDataParam("password") String password,
            @FormDataParam("fotoPerfil") InputStream uploadedInputStream,
            @FormDataParam("fotoPerfil") FormDataContentDisposition fileDetail
    ) {
        DAOFactory daof = DAOFactory.getDAOFactory();
        IUsuariosDAO udao = daof.getUsuariosDAO();
        Usuario usuarioId = null;

        Usuario user = new Usuario();
        user.setUsuario(usuario);
        user.setEmail(correo);
        user.setPassword(password);
        if (fileDetail != null && !fileDetail.getFileName().isEmpty()) {
            user.setFoto("si");
        } else {
            user.setFoto("no");
        }

        String respuesta = "{\"status\": 200, \"message\": \"Usuario Creado\"}";
        boolean isSaved = false;

        if (user.camposRellenos()) {

            udao.registrarUsuario(user);
            usuarioId = udao.login(user);
            System.out.println("idUsuario" + user.getIdUsuario());
            if (fileDetail != null && !fileDetail.getFileName().isEmpty() && user != null) {
                String extension = ".png";
                String nombreFoto = usuarioId.getIdUsuario() + extension.trim();
                // Guarda la imagen en el servidor
                isSaved = ComprobarFormulario.saveToFile(uploadedInputStream, nombreFoto);
                if (!isSaved) {
                    // Si falla el guardado de la imagen, establece un estado de error
                    respuesta = "{\"status\": 500, \"message\": \"Failed to save the file.\"}";
                    return Response
                            .ok(respuesta)
                            .build();
                }
                // Establece el nombre de la foto en el usuario
                user.setFoto(nombreFoto);
            }
        } else {
            respuesta = "{'status': 500}";
        }
        System.out.println(usuario + "hola");
        return Response
                .ok(respuesta)
                .build();
    }

    @POST
    @Path("/updateUsuario")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response updateUsuario(
            @FormDataParam("usuario") String usuario,
            @FormDataParam("correo") String correo,
            @FormDataParam("password") String password,
            @FormDataParam("idUsuario") String idUsuario
    ) {
        DAOFactory daof = DAOFactory.getDAOFactory();
        IUsuariosDAO udao = daof.getUsuariosDAO();
        Usuario usuarioId = null;

        Usuario user = new Usuario();
        user.setIdUsuario(Integer.parseInt(idUsuario.trim()));
        user.setUsuario(usuario);
        user.setEmail(correo);
        user.setPassword(password);

        String respuesta = "{\"status\": 200, \"message\": \"Usuario Creado\"}";
        boolean isSaved = false;

        if (user.camposRellenos()) {

            usuarioId = udao.actualizarUsuario(user);
            if (usuarioId != null) {
                System.out.println(usuarioId.getUsuario());
                respuesta = "{\"status\": 200, \"message\": \"Usuario logeado\","
                        + "\"idUsuario\": \" " + usuarioId.getIdUsuario() + "\","
                        + " \"usuario\": \" " + usuarioId.getUsuario() + "\","
                        + " \"correo\": \" " + usuarioId.getEmail() + "\"}";
            } else {
                respuesta = "{\"status\": 500, \"message\": \"Usuario no modificado \"}";
            }

        } else {
            respuesta = "{'status': 500}";
        }
        System.out.println(usuario + "hola");
        return Response
                .ok(respuesta)
                .build();
    }

    @POST
    @Path("/updateFoto")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response updateFoto(
            @FormDataParam("idUsuario") String idUsuario,
            @FormDataParam("fotoPerfil") InputStream uploadedInputStream,
            @FormDataParam("fotoPerfil") FormDataContentDisposition fileDetail
    ) {

        if (idUsuario == null || idUsuario.isEmpty()) {
            String respuesta = "{\"status\": 400, \"message\": \"El ID de usuario es requerido.\"}";
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(respuesta)
                    .build();
        }

        Usuario user = new Usuario();
        try {
            user.setIdUsuario(Short.parseShort(idUsuario.trim()));
        } catch (NumberFormatException e) {
            String respuesta = "{\"status\": 400, \"message\": \"El ID de usuario debe ser un número válido.\"}";
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(respuesta)
                    .build();
        }

        user.setFoto("default.png");
        String respuesta = "{\"status\": 200, \"message\": \"Usuario Creado\"}";
        boolean isSaved = false;
        if (fileDetail != null && !fileDetail.getFileName().isEmpty()) {
            // Guarda la imagen en el servidor
            System.out.println(user.getIdUsuario());
            String extension = ".png";
            String nombreFoto = user.getIdUsuario() + extension.trim();
            isSaved = ComprobarFormulario.saveToFile(uploadedInputStream, nombreFoto);
            if (!isSaved) {
                // Si falla el guardado de la imagen, establece un estado de error
                respuesta = "{\"status\": 500, \"message\": \"Failed to save the file.\"}";
                return Response
                        .status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(respuesta)
                        .build();
            }
            // Establece el nombre de la foto en el usuario
            user.setFoto(nombreFoto);
            // udao.actualizarUsuario(user); // Descomenta esta línea si deseas actualizar el usuario en la base de datos
        }
        return Response
                .ok(respuesta)
                .build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response login(
            @FormDataParam("correo") String correo,
            @FormDataParam("password") String password
    ) {
        DAOFactory daof = DAOFactory.getDAOFactory();
        IUsuariosDAO udao = daof.getUsuariosDAO();
        IListasDAO ldao = daof.getListasDAO();
        ILineaListaDAO lldao = daof.getLineaListasDAO();
        IEpisodiosDAO edao = daof.getEpisodiosDAO();
        System.out.println(correo);
        List<Lista> listas = null;
        Usuario usuario = null;
        Usuario user = new Usuario();
        user.setEmail(correo);
        user.setPassword(password);
        String respuesta = "{\"status\": 200, \"message\": \"Usuario logeado\"}";
        boolean isSaved = false;
        if (user.getEmail() != null && user.getPassword() != null) {
            usuario = udao.login(user);
            if (usuario != null) {
                    listas = ldao.getListasUsuario(usuario.getIdUsuario());
                    for (Lista lista : listas) {
                        lista.setEpisodios(lldao.getLineasLista(lista.getIdLista()));
                        for(LineaLista lineaEpisodio : lista.getEpisodios()){
                            lineaEpisodio.setEpisodio(edao.getEpisodioId(lineaEpisodio.getEpisodio().getIdEpisodio()));
                        }
                    }
                    usuario.setListas(listas);

                    Gson gson = new Gson();
                    String usuarioJson = gson.toJson(usuario);

                    respuesta = "{\"status\": 200, \"message\": \"Usuario logeado\", \"usuario\": " + usuarioJson + "}";

                }else {
                respuesta = "{\"status\": 500, \"message\": \"Usuario no encontrado\"}";
            }

        } else {
            respuesta = "{'status': 500, \"message\": \"No se han rellenado los campos\"}";
        }
        return Response
                .ok(respuesta)
                .build();
    }

    
}
