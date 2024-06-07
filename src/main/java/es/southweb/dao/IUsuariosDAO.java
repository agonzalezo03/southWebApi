/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.southweb.dao;

import es.southweb.beans.Usuario;



/**
 *
 * @author AntoManueh
 */
public interface IUsuariosDAO {
     public void testConnection();
     public boolean registrarUsuario(Usuario usuario);
    public Usuario login(Usuario usuario);
    public Usuario actualizarUsuario(Usuario usuario);

    public void closeConnection();
}
