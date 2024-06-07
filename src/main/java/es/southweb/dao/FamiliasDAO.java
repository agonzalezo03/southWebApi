/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.southweb.dao;

import es.southweb.beans.Familia;
import es.southweb.daofactory.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AntoManueh
 */
public class FamiliasDAO implements IFamiliasDAO{
    
    
    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

    @Override
    public List<Familia> getAllFamilias() {
       List<Familia> lista = new ArrayList<>();
        String consulta = "SELECT * FROM `mydb`.`familia`";
    
    try (Connection conexion = ConnectionFactory.getConnection();
         Statement sentencia = conexion.createStatement();
         ResultSet resultado = sentencia.executeQuery(consulta)) {

        while (resultado.next()) {
            Familia familia = new Familia();
            familia.setNombre(resultado.getString("nombre"));
            familia.setIdFamilia(resultado.getInt("idFamilia"));


            lista.add(familia);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        Logger.getLogger(EpisodiosDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return lista;    }
}
