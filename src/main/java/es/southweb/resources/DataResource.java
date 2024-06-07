/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.southweb.resources;

import com.google.gson.Gson;
import es.southweb.beans.Episodio;
import es.southweb.beans.Familia;
import es.southweb.beans.Personaje;
import es.southweb.beans.Usuario;
import es.southweb.dao.IEpisodiosDAO;
import es.southweb.dao.IFamiliasDAO;
import es.southweb.dao.IPersonajesDAO;
import es.southweb.dao.IPersonajesEpisodioDAO;
import es.southweb.dao.IUsuariosDAO;
import es.southweb.daofactory.DAOFactory;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Alberto
 */
@Path("/data")
public class DataResource {

    @GET
    @Path("/episodios")
    public Response episodios() {
        DAOFactory daof = DAOFactory.getDAOFactory();
    IEpisodiosDAO edao = daof.getEpisodiosDAO();
    IPersonajesEpisodioDAO pedao = daof.getPersonajesEpisodioDAO();
    IPersonajesDAO pdao = daof.getPersonajesDAO();
    
    String respuesta; 
    JSONObject respuestaJson = new JSONObject();
    respuestaJson.put("status", 200);
    respuestaJson.put("message", "Episodios conseguidos");

    List<Episodio> listaEpisodios = edao.getAllEpisodios();
    if (listaEpisodios != null) {
        for (Episodio episodio : listaEpisodios) {
            System.out.println(episodio.getIdEpisodio());
            List<Integer> idPersonajes = pedao.getPersonajesEpisodio(episodio.getIdEpisodio());
            List<Personaje> personajes = new ArrayList<>();
            for (int idPersonaje : idPersonajes) {
                System.out.println(idPersonaje);
                Personaje personaje = pdao.getPersonajeId(idPersonaje);
                if (!personajes.contains(personaje)) { 
                    personajes.add(personaje);
                }
            }
            episodio.setPersonajes(personajes);
        }
        Gson gson = new Gson();
        String listasJson = gson.toJson(listaEpisodios);
        respuesta = listasJson;
    } else {
        respuestaJson.put("status", 500);
        respuestaJson.put("message", "Error");
        respuesta = respuestaJson.toString();
    }

    return Response.ok(respuesta, MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("/personajes")
    public Response personajes() {
    DAOFactory daof = DAOFactory.getDAOFactory();
    IPersonajesDAO pdao = daof.getPersonajesDAO();
    String respuesta; 
    JSONObject respuestaJson = new JSONObject();
    respuestaJson.put("status", 200);
    respuestaJson.put("message", "Personajes conseguidos");

    List<Personaje> listaPersonajes = pdao.getAllPersonajes();
    if (listaPersonajes != null) {
        Gson gson = new Gson();
        respuesta = gson.toJson(listaPersonajes);
    } else {
        respuestaJson.put("status", 500);
        respuestaJson.put("message", "Error");
        respuesta = respuestaJson.toString();
    }
    return Response.ok(respuesta, MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("/familias")
    public Response familias() {
    DAOFactory daof = DAOFactory.getDAOFactory();
    IFamiliasDAO fdao = daof.getFamiliasDAO();
    String respuesta; 
    JSONObject respuestaJson = new JSONObject();
    respuestaJson.put("status", 200);
    respuestaJson.put("message", "Familias conseguidos");

    List<Familia> listaFamilias = fdao.getAllFamilias();
    if (listaFamilias != null) {
        Gson gson = new Gson();
        respuesta = gson.toJson(listaFamilias);
    } else {
        respuestaJson.put("status", 500);
        respuestaJson.put("message", "Error");
        respuesta = respuestaJson.toString();
    }
    return Response.ok(respuesta, MediaType.APPLICATION_JSON).build();
    }
}
