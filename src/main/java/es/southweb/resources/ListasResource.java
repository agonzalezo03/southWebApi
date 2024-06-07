/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.southweb.resources;

import com.google.gson.Gson;
import es.southweb.beans.Lista;
import es.southweb.dao.ILineaListaDAO;
import es.southweb.dao.IListasDAO;
import es.southweb.daofactory.DAOFactory;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author Alberto
 */
@Path("/listas")
public class ListasResource {

    @POST
    @Path("/addLista")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response addLista(
            @FormDataParam("idUsuario") String idUsuario,
            @FormDataParam("nombre") String nombre
    ) {
        DAOFactory daof = DAOFactory.getDAOFactory();
        IListasDAO ldao = daof.getListasDAO();
        ILineaListaDAO lldao = daof.getLineaListasDAO();

        List<Lista> listas = null;
        String respuesta = "{\"status\": 200, \"message\": \"Lista Creada\"}";

        if (!ldao.añadirLista(Integer.parseInt(idUsuario.trim()), nombre)) {
            respuesta = "{\"status\": 500, \"message\": \"Lista no se pudo crear\"}";
        } else {
            listas = ldao.getListasUsuario(Integer.parseInt(idUsuario.trim()));
            for (Lista lista : listas) {
                lista.setEpisodios(lldao.getLineasLista(lista.getIdLista()));
            }

            Gson gson = new Gson();
            String listasJson = gson.toJson(listas);

            respuesta = "{\"status\": 200, \"message\": \"Lista Creada\", \"listas\": " + listasJson + "}";

        }
        return Response
                .ok(respuesta)
                .build();
    }

    @POST
    @Path("/addEpisodio")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response addEpisodio(
            @FormDataParam("idLista") String idLista,
            @FormDataParam("idEpisodio") String idEpisodio
    ) {
        DAOFactory daof = DAOFactory.getDAOFactory();
        IListasDAO ldao = daof.getListasDAO();
        ILineaListaDAO lldao = daof.getLineaListasDAO();

        List<Lista> listas = null;
        String respuesta = "{\"status\": 200, \"message\": \"Lista Creada\"}";

        if (!lldao.addLineaLista(Integer.parseInt(idLista.trim()), Integer.parseInt(idEpisodio.trim()))) {
            respuesta = "{\"status\": 500, \"message\": \"Lista no se pudo crear\"}";
        } else {

            respuesta = "{\"status\": 200, \"message\": \"Episodio Añadido\" }";

        }
        return Response
                .ok(respuesta)
                .build();
    }

    @POST
    @Path("/deleteEpisodio")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response deleteEpisodio(
            @FormDataParam("idLinea") String idLinea
    ) {
        DAOFactory daof = DAOFactory.getDAOFactory();
        ILineaListaDAO lldao = daof.getLineaListasDAO();

        String respuesta = "{\"status\": 200, \"message\": \"Lista Creada\"}";

        if (!lldao.deleteLineaLista(Integer.parseInt(idLinea.trim()))) {
            respuesta = "{\"status\": 500, \"message\": \"Lista no se pudo eliminar\"}";
        } else {

            respuesta = "{\"status\": 200, \"message\": \"Episodio eliminado\" }";

        }
        return Response
                .ok(respuesta)
                .build();
    }

    @POST
    @Path("/deleteLista")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response deleteLista(
            @FormDataParam("idLista") String idLista
    ) {
        DAOFactory daof = DAOFactory.getDAOFactory();
        IListasDAO ldao = daof.getListasDAO();
        ILineaListaDAO lldao = daof.getLineaListasDAO();

        String respuesta = "{\"status\": 200, \"message\": \"Lista Creada\"}";
        if (lldao.deleteLineasListaId(Integer.parseInt(idLista.trim()))) {

            if (!ldao.deleteLista(Integer.parseInt(idLista.trim()))) {
                respuesta = "{\"status\": 500, \"message\": \"Lista no se pudo eliminar\"}";
            } else {

                respuesta = "{\"status\": 200, \"message\": \"Episodio eliminado\" }";
                

            }
        } else {
            respuesta = "{\"status\": 500, \"message\": \"Lista no se pudo eliminar\"}";

        }
        return Response
                .ok(respuesta)
                .build();
    }
}
