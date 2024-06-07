/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.southweb.dao;

import es.southweb.beans.Usuario;
import es.southweb.daofactory.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author AntoManueh
 */
public class UsuariosDAO implements IUsuariosDAO {

    @Override
    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO `mydb`.`usuarios` (email, password, usuario, foto) VALUES (?,MD5(?),?,?)";
    String sqlUpdate = "UPDATE `mydb`.`usuarios` SET foto = ? WHERE idUsuario = LAST_INSERT_ID()";
    Connection conexion = (Connection) ConnectionFactory.getConnection();
    System.out.println("es.apisouthweb.dao.UsuariosDAO.registrarUsuario()");
    System.out.println(conexion);
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
            preparada.setString(1, usuario.getEmail());
            preparada.setString(2, usuario.getPassword());
            preparada.setString(3, usuario.getUsuario());
            preparada.setString(4, "default.jpg");
            
            preparada.executeUpdate();
            
            // Obtener el ID generado
            try (ResultSet generatedKeys = preparada.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    userId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener ID");
                }
            }
        }

        // Actualizar la foto con el ID del usuario
        if (userId > 0) {
            if(usuario.getFoto() == "si"){
                String newFotoName = userId + ".png"; // Cambia la extensión según sea necesario
            try (PreparedStatement preparadaUpdate = conexion.prepareStatement(sqlUpdate)) {
                preparadaUpdate.setString(1, newFotoName);
                preparadaUpdate.executeUpdate();
            }
            }
            
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
    public Usuario login(Usuario user) {
        Usuario usuario = null;
        String sql = "SELECT * FROM `mydb`.`usuarios` WHERE email = ? AND password = MD5(?)";
        try (Connection conexion = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()){
                usuario = new Usuario();
                usuario.setIdUsuario(resultSet.getShort("idUsuario"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setPassword(resultSet.getString("password"));
                usuario.setUsuario(resultSet.getString("usuario"));
                usuario.setFoto(resultSet.getString("foto"));
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
            
        }finally{
            this.closeConnection();
        }
        return usuario;
    }
    
    
    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        String sqlUpdate = "UPDATE `mydb`.`usuarios` SET email = ?, password = MD5(?), usuario = ? WHERE idUsuario = ?";
        Connection conexion = (Connection) ConnectionFactory.getConnection();
        System.out.println("es.apisouthweb.dao.UsuariosDAO.registrarUsuario()");
        System.out.println(conexion);
        if (conexion == null) {
            try {
                throw new SQLException("No se pudo obtener la conexión a la base de datos.");
            } catch (SQLException ex) {
                Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        boolean exito = false;
        try (PreparedStatement preparada = conexion.prepareStatement(sqlUpdate)) {
            conexion.setAutoCommit(false);

            preparada.setString(1, usuario.getEmail());
            preparada.setString(2, usuario.getPassword());
            preparada.setString(3, usuario.getUsuario());
            preparada.setInt(4, usuario.getIdUsuario());

            preparada.executeUpdate();
            conexion.commit();
            exito = true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);

            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

 // Recuperar el usuario actualizado de la base de datos
    Usuario usuarioActualizado = null;
    String sqlSelect = "SELECT * FROM `mydb`.`usuarios` WHERE idUsuario = ?";
    try (PreparedStatement preparedStatement = conexion.prepareStatement(sqlSelect)) {
        preparedStatement.setInt(1, usuario.getIdUsuario());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            usuarioActualizado = new Usuario();
            usuarioActualizado.setIdUsuario(resultSet.getShort("idUsuario"));
            usuarioActualizado.setEmail(resultSet.getString("email"));
            usuarioActualizado.setPassword(resultSet.getString("password"));
            usuarioActualizado.setUsuario(resultSet.getString("usuario"));
            usuarioActualizado.setFoto(resultSet.getString("foto"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
            this.closeConnection();
    }

    return usuarioActualizado;
    }
    


    public void testConnection() {
        Connection connection = null;
        connection = ConnectionFactory.getConnection();
        if (connection != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("No se pudo establecer la conexión a la base de datos.");
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }
    
    

    
    
    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

   



}
