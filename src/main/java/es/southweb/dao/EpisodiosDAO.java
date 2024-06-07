/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.southweb.dao;

import es.southweb.beans.Episodio;
import es.southweb.beans.LineaLista;
import es.southweb.beans.Lista;
import es.southweb.beans.Usuario;
import es.southweb.daofactory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AntoManueh
 */
public class EpisodiosDAO implements IEpisodiosDAO {

    @Override
    public List<Episodio> getAllEpisodios() {
       List<Episodio> lista = new ArrayList<>();
    String consulta = "SELECT * FROM `mydb`.`episodios`";
    
    try (Connection conexion = ConnectionFactory.getConnection();
         Statement sentencia = conexion.createStatement();
         ResultSet resultado = sentencia.executeQuery(consulta)) {

        while (resultado.next()) {
            Episodio episodio = new Episodio();
            episodio.setIdEpisodio(resultado.getShort("idEpisodio"));
            episodio.setWiki_url(resultado.getString("wiki_url"));
            episodio.setNombre(resultado.getString("nombre"));
            episodio.setDescripcion(resultado.getString("descripcion"));
            episodio.setTemporada(resultado.getInt("temporada"));
            episodio.setEpisodio(resultado.getInt("episodio"));
            episodio.setImagen(resultado.getString("imagen"));

            lista.add(episodio);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        Logger.getLogger(EpisodiosDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return lista;

    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

    @Override
    public Episodio getEpisodioId(int idEpisodio) {
        Episodio episodio = null;
        String sql = "SELECT * FROM `mydb`.`episodios` WHERE idEpisodio = ?";
        try (Connection conexion = ConnectionFactory.getConnection(); PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            preparedStatement.setInt(1, idEpisodio);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                episodio = new Episodio();
                episodio.setIdEpisodio(resultSet.getShort("idEpisodio"));
                episodio.setWiki_url(resultSet.getString("wiki_url"));
                episodio.setNombre(resultSet.getString("nombre"));
                episodio.setDescripcion(resultSet.getString("descripcion"));
                episodio.setTemporada(resultSet.getInt("temporada"));
                episodio.setEpisodio(resultSet.getInt("episodio"));
                episodio.setImagen(resultSet.getString("imagen"));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            this.closeConnection();
        }
        return episodio;
    }

}
