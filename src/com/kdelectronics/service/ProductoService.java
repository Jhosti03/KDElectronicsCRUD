package com.kdelectronics.service;

import com.kdelectronics.dao.ProductoDAO;
import com.kdelectronics.model.Producto;
import java.sql.SQLException;

public class ProductoService {
    private final ProductoDAO productoDAO;

    public ProductoService(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public void registrarProducto(Producto producto) throws SQLException {
        productoDAO.agregarProducto(producto);
    }

    public Producto obtenerProducto(String codigoProducto) throws SQLException {
        return productoDAO.obtenerProductoPorCodigo(codigoProducto);
    }

    public void actualizarProducto(Producto producto) throws SQLException {
        productoDAO.actualizarProducto(producto);
    }

    public void eliminarProducto(String codigoProducto) throws SQLException {
        productoDAO.eliminarProducto(codigoProducto);
    }
}
