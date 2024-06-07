/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.southweb.beans;

import java.util.List;

/**
 *
 * @author AntoManueh
 */
public class Lista {
    private int idLista;
    private int idUsuario;
    private String nombre;
    private List<LineaLista> episodios;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<LineaLista> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<LineaLista> episodios) {
        this.episodios = episodios;
    }


    
    
}
