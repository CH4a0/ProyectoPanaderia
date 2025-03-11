package com.app.panaderia.modelo.entidades;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {

    @Id // Primary Key
    private int id;

    @Column(nullable = false)
    @NotEmpty
    @Size(min = 3, max = 50)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria", foreignKey = @ForeignKey(name = "FK_categoria_producto"), nullable = false)
    private Categoria categoria;

    @Column(nullable = false)
    @Size(max = 100)
    private String descripcion;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private String imagen;

    public Producto() {
    }

    public Producto(int id, String nombre, Categoria categoria, String descripcion, double precio, String imagen) {
        this.setId(id);
        this.setNombre(nombre);
        this.setCategoria(categoria);
        this.setDescripcion(descripcion);
        this.setPrecio(precio);
        this.setImagen(imagen);
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public String getImagen() {
        return imagen;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "<" + this.id + "," 
                + this.nombre + ","
                + this.categoria.getTipo() + ","
                + this.descripcion + "," 
                + this.precio + ","
                + this.imagen + ">";
    }
}
