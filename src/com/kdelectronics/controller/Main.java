package com.kdelectronics.controller;

import com.kdelectronics.dao.ConexionBD;
import com.kdelectronics.dao.ProductoDAO;
import com.kdelectronics.model.Producto;
import com.kdelectronics.service.ProductoService;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection conexion = ConexionBD.getConnection()) {
            ProductoDAO productoDAO = new ProductoDAO(conexion);
            ProductoService productoService = new ProductoService(productoDAO);

            // Crear nuevo producto
            Producto producto = new Producto("P002", "Laptop", "Laptop de alta gama", 800.00, 1000.00, "Electrónica", 50);
            productoService.registrarProducto(producto);
            System.out.println("Producto registrado correctamente.");

            // Leer producto
            Producto productoConsultado = productoService.obtenerProducto("P002");
            System.out.println("Producto consultado: " + productoConsultado.getNombre());

            // Actualizar producto
            productoConsultado.setPrecioVenta(950.00);
            productoService.actualizarProducto(productoConsultado);
            System.out.println("Producto actualizado correctamente.");

            // Eliminar producto (lógica)
            productoService.eliminarProducto("P001");
            System.out.println("Producto eliminado lógicamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
