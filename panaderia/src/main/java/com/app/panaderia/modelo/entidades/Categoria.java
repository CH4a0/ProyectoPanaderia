package com.app.panaderia.modelo.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {
    @Id //Primary Key
    private int id;

    @Column(nullable = false) 
    private String tipo;

    @Column(nullable = false) 
    private String descripcion;

    @Column(nullable = false)
    private String imagen;

    public Categoria() {
    }

    public Categoria(int id, String tipo, String descripcion, String imagen) {
        this.setId(id);
        this.setTipo(tipo);
        this.setDescripcion(descripcion);
        this.setImagen(imagen); 
    }


    // Getters
    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return 
                "<" + this.id + ","
                + this.tipo + ","  
                + this.descripcion + "," 
                + this.imagen + ">";
    }
}
