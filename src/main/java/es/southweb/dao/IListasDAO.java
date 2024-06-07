/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.southweb.dao;

import es.southweb.beans.Lista;
import java.util.List;

/**
 *
 * @author AntoManueh
 */
public interface IListasDAO {
        public boolean añadirLista(int idUsuario, String nombre);
        public List<Lista> getListasUsuario(int idUsuario);
        public boolean deleteLista(int idLista);
        public void closeConnection();

}
