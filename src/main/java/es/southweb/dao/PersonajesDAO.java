/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.southweb.dao;

import es.southweb.beans.Personaje;
import es.southweb.beans.Usuario;
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
public class PersonajesDAO implements IPersonajesDAO{

    @Override
    public List<Personaje> getAllPersonajes() {
    List<Personaje> personajes = new ArrayList<>();

    try (Connection conexion = ConnectionFactory.getConnection();
         Statement statement = conexion.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT * FROM `mydb`.`personajes`")) {

        while (resultSet.next()) {
            Personaje personaje = new Personaje();
            personaje.setIdPersonaje(resultSet.getInt("idPersonaje"));
            personaje.setNombre(resultSet.getString("nombre"));
            personaje.setGenero(resultSet.getString("genero"));
            personaje.setColorPelo(resultSet.getString("colorPelo"));
            personaje.setOcupacion(resultSet.getString("ocupacion"));
            personaje.setReligion(resultSet.getString("religion"));
            personaje.setGrado(resultSet.getString("grado"));
            personaje.setEdad(resultSet.getInt("edad"));
            personaje.setFamilia(resultSet.getInt("familia"));

            personajes.add(personaje);
        }
    } catch (SQLException e) {
        e.printStackTrace();
     }finally{
            this.closeConnection();
        }

    return personajes;
  }

    @Override
    public Personaje getPersonajeId(int idPersonaje) {

        Personaje personaje = null;
        String sql = "SELECT * FROM `mydb`.`personajes` WHERE idPersonaje = ?";
        try (Connection conexion = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            preparedStatement.setInt(1, idPersonaje);

            ResultSet resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()){
                personaje = new Personaje();
                personaje.setIdPersonaje(resultSet.getInt("idPersonaje"));
            personaje.setNombre(resultSet.getString("nombre"));
            personaje.setGenero(resultSet.getString("genero"));
            personaje.setColorPelo(resultSet.getString("colorPelo"));
            personaje.setOcupacion(resultSet.getString("ocupacion"));
            personaje.setReligion(resultSet.getString("religion"));
            personaje.setGrado(resultSet.getString("grado"));
            personaje.setEdad(resultSet.getInt("edad"));
            personaje.setFamilia(resultSet.getInt("familia"));
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
            
        }finally{
            this.closeConnection();
        }
        return personaje;
   }
    
    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

    
}
