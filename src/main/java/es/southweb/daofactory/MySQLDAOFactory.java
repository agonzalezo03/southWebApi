/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.southweb.daofactory;

import es.southweb.dao.EpisodiosDAO;
import es.southweb.dao.FamiliasDAO;
import es.southweb.dao.IEpisodiosDAO;
import es.southweb.dao.IFamiliasDAO;
import es.southweb.dao.ILineaListaDAO;
import es.southweb.dao.IListasDAO;
import es.southweb.dao.IPersonajesDAO;
import es.southweb.dao.IPersonajesEpisodioDAO;
import es.southweb.dao.IUsuariosDAO;
import es.southweb.dao.LineaListaDAO;
import es.southweb.dao.ListasDAO;
import es.southweb.dao.PersonajesDAO;
import es.southweb.dao.PersonajesEpisodioDAO;
import es.southweb.dao.UsuariosDAO;







public class MySQLDAOFactory extends DAOFactory{
@Override
    public IUsuariosDAO getUsuariosDAO() {
        return new UsuariosDAO();
    }

    @Override
    public IEpisodiosDAO getEpisodiosDAO() {
        return new EpisodiosDAO();
    }
    
    @Override
    public IListasDAO getListasDAO() {
        return new ListasDAO();
    }

    @Override
    public ILineaListaDAO getLineaListasDAO() {
        return new LineaListaDAO();
    }

    @Override
    public IPersonajesDAO getPersonajesDAO() {
        return new PersonajesDAO();
    }

    @Override
    public IPersonajesEpisodioDAO getPersonajesEpisodioDAO() {
        return new PersonajesEpisodioDAO();
    }

    @Override
    public IFamiliasDAO getFamiliasDAO() {
        return new FamiliasDAO();
    }


}


