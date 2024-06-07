/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.southweb.daofactory;

import es.southweb.dao.IEpisodiosDAO;
import es.southweb.dao.IFamiliasDAO;
import es.southweb.dao.ILineaListaDAO;
import es.southweb.dao.IListasDAO;
import es.southweb.dao.IPersonajesDAO;
import es.southweb.dao.IPersonajesEpisodioDAO;
import es.southweb.dao.IUsuariosDAO;





public abstract class DAOFactory {
    /**
     * Objeto DAO de Productos
     * @return interface de dicho objeto DAO
     */

      public abstract IUsuariosDAO getUsuariosDAO();
      public abstract IEpisodiosDAO getEpisodiosDAO();
      public abstract IListasDAO getListasDAO();
      public abstract ILineaListaDAO getLineaListasDAO();
      public abstract IPersonajesDAO getPersonajesDAO();
      public abstract IPersonajesEpisodioDAO getPersonajesEpisodioDAO();
      public abstract IFamiliasDAO getFamiliasDAO();



    /**
     * Obtiene la fábrica concreta a la fuente de datos
     * @return la fábrica concreta
     */
    public static DAOFactory getDAOFactory() {
        
        DAOFactory daof = null;

        daof = new MySQLDAOFactory();

        return daof;
    }

}