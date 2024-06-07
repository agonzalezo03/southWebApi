/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.southweb.dao;

import es.southweb.beans.Episodio;
import java.util.List;

/**
 *
 * @author AntoManueh
 */
public interface IEpisodiosDAO {
    public List<Episodio> getAllEpisodios();
    public Episodio getEpisodioId(int idEpisodio);
        public void closeConnection();

}
