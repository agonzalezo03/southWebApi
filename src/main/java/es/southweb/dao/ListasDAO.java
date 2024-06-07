/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.southweb.dao;

import es.southweb.beans.Lista;
import es.southweb.daofactory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class ListasDAO implements IListasDAO {

    @Override
    public boolean añadirLista(int idUsuario, String nombre) {
        String sql = "INSERT INTO `mydb`.`listasepisodios` (nombre, idUsuario) VALUES (?, ?)";
        Connection conexion = (Connection) ConnectionFactory.getConnection();
        if (conexion == null) {
            try {
                throw new SQLException("No se pudo obtener la conexión a la base de datos.");
            } catch (SQLException ex) {
                Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        boolean exito = false;
        try {
            conexion.setAutoCommit(false);
            try (PreparedStatement preparada = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparada.setString(1, nombre);
                preparada.setInt(2, idUsuario);

                preparada.executeUpdate();

            }

            conexion.commit();
            exito = true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            this.closeConnection();
        }
        return exito;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

    @Override
    public List<Lista> getListasUsuario(int idUsuario) {
        List<Lista> listas = null;
        String sql = "SELECT * FROM `mydb`.`listasepisodios` WHERE idUsuario = ?";
        try (Connection conexion = ConnectionFactory.getConnection(); PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            preparedStatement.setInt(1, idUsuario);

            ResultSet resultSet = preparedStatement.executeQuery();

            listas = new ArrayList<>();
            while (resultSet.next()) {
                Lista lista = new Lista(); 
                lista.setIdUsuario(resultSet.getShort("idUsuario"));
                lista.setIdLista(resultSet.getInt("idLista"));
                lista.setNombre(resultSet.getString("nombre"));
                listas.add(lista);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            this.closeConnection();
        }
        return listas;
    }

    @Override
    public boolean deleteLista(int idLista) {
String sql = "DELETE FROM `mydb`.`listasEpisodios` WHERE idLista = ?";
    Connection conexion = ConnectionFactory.getConnection();
    if (conexion == null) {
        try {
            throw new SQLException("No se pudo obtener la conexión a la base de datos.");
        } catch (SQLException ex) {
            Logger.getLogger(LineaListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    boolean exito = false;
    try {
        conexion.setAutoCommit(false);

        try (PreparedStatement preparada = conexion.prepareStatement(sql)) {
            preparada.setInt(1, idLista);
            preparada.executeUpdate();
        }

        conexion.commit();
        exito = true;
    } catch (SQLException ex) {
        Logger.getLogger(LineaListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        try {
            conexion.rollback();
        } catch (SQLException ex1) {
            Logger.getLogger(LineaListaDAO.class.getName()).log(Level.SEVERE, null, ex1);
        }
    } finally {
        ConnectionFactory.closeConnection();
    }
    return exito;
  }

}
