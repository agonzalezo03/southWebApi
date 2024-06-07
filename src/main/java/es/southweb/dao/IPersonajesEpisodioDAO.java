/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.southweb.dao;

import java.util.List;

/**
 *
 * @author AntoManueh
 */
public interface IPersonajesEpisodioDAO {
    public List<Integer> getPersonajesEpisodio(int idEpisodio);
    public void closeConnection();
}
