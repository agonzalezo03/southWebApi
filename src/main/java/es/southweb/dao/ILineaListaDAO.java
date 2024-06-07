/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.southweb.dao;

import es.southweb.beans.LineaLista;
import java.util.List;

/**
 *
 * @author AntoManueh
 */
public interface ILineaListaDAO {
    public List<LineaLista> getLineasLista(int idLista);
    public boolean addLineaLista (int idLista, int idEpisodio);
    public boolean deleteLineaLista (int idLinea); 
    public boolean deleteLineasListaId(int idLista);
    public void closeConnection();

}
