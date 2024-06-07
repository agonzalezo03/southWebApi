/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.southweb.dao;

import es.southweb.beans.Personaje;
import java.util.List;

/**
 *
 * @author AntoManueh
 */
public interface IPersonajesDAO {
    public List<Personaje> getAllPersonajes();
    public Personaje getPersonajeId(int idPersonaje);
    public void closeConnection();
}
