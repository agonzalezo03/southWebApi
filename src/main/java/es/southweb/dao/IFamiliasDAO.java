/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.southweb.dao;

import es.southweb.beans.Familia;
import java.util.List;

/**
 *
 * @author AntoManueh
 */
public interface IFamiliasDAO {
    public List<Familia> getAllFamilias();
    public void closeConnection();
}
