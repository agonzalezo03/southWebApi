/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.southweb.dao;

import es.southweb.beans.Episodio;
import es.southweb.beans.LineaLista;
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
public class LineaListaDAO implements ILineaListaDAO{

    @Override
    public List<LineaLista> getLineasLista(int idLista) {
        Episodio episodio = null;
        List<LineaLista> episodios = null;
        String sql = "SELECT * FROM `mydb`.`linealista` WHERE idLista = ?";
        try (Connection conexion = ConnectionFactory.getConnection(); PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            preparedStatement.setInt(1, idLista);

            ResultSet resultSet = preparedStatement.executeQuery();

            episodios = new ArrayList<>();
            while (resultSet.next()) {
                LineaLista episodioLista = new LineaLista(); 
                episodio = new Episodio();
                episodioLista.setIdLinea(resultSet.getShort("idLinea"));
                episodioLista.setIdLista(resultSet.getInt("idLista"));
                episodio.setIdEpisodio(resultSet.getInt("idEpisodio"));
                episodioLista.setEpisodio(episodio);
                episodios.add(episodioLista);
}
            

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            this.closeConnection();
        }
        return episodios;    
    }
    
    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

    @Override
    public boolean addLineaLista(int idLista, int idEpisodio) {
        String sql = "INSERT INTO `mydb`.`linealista` (idLista, idEpisodio) VALUES (?, ?)";
        Connection conexion = (Connection) ConnectionFactory.getConnection();
        if (conexion == null) {
            try {
                throw new SQLException("No se pudo obtener la conexión a la base de datos.");
            } catch (SQLException ex) {
                Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        boolean exito = false;
        int userId = 0;
        try {
            conexion.setAutoCommit(false);

            // Insertar nuevo usuario
            try (PreparedStatement preparada = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparada.setInt(1, idLista);
                preparada.setInt(2, idEpisodio);


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
public boolean deleteLineaLista(int idLinea) {
    String sql = "DELETE FROM `mydb`.`linealista` WHERE idLinea = ?";
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
            preparada.setInt(1, idLinea);
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

    @Override
    public boolean deleteLineasListaId(int idLista) {
String sql = "DELETE FROM `mydb`.`linealista` WHERE idLista = ?";
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
    return exito;    }
    
}
