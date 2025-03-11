package com.app.panaderia.modelo.repositorios;

import com.app.panaderia.modelo.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    // Puedes añadir métodos personalizados si lo necesitas
    // Ejemplo: List<Producto> findByCategoria(Categoria categoria);
}
