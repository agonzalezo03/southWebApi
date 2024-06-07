/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.southweb.dao;

import es.southweb.beans.Personaje;
import es.southweb.daofactory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AntoManueh
 */
public class PersonajesEpisodioDAO implements IPersonajesEpisodioDAO{

    @Override
    public List<Integer> getPersonajesEpisodio(int idEpisodio) {
    List<Integer> idPersonajes = new ArrayList<>();

    String sql = "SELECT * FROM `mydb`.`personajesepisodios` WHERE idEpisodio = ?";
        try (Connection conexion = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            preparedStatement.setInt(1, idEpisodio);

            ResultSet resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()){
                int idPersonaje;
                idPersonaje = resultSet.getInt("idPersonaje");
                idPersonajes.add(idPersonaje);
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
            
        }finally{
            this.closeConnection();
        }

    return idPersonajes;
    }
    
    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

    
}
