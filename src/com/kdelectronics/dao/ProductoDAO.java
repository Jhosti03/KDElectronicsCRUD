package com.kdelectronics.dao;

import com.kdelectronics.model.Producto;
import java.sql.*;

public class ProductoDAO {
    private final Connection conexion;

    public ProductoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // Create (Registrar nuevo producto)
    public void agregarProducto(Producto producto) throws SQLException {
        String sql = "INSERT INTO productos (codigo_producto, nombre, descripcion, precio_base, precio_venta, categoria, cantidad_disponible) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, producto.getCodigoProducto());
            stmt.setString(2, producto.getNombre());
            stmt.setString(3, producto.getDescripcion());
            stmt.setDouble(4, producto.getPrecioBase());
            stmt.setDouble(5, producto.getPrecioVenta());
            stmt.setString(6, producto.getCategoria());
            stmt.setInt(7, producto.getCantidadDisponible());
            stmt.executeUpdate();
        }
    }

    // Read (Consultar producto por código)
    public Producto obtenerProductoPorCodigo(String codigoProducto) throws SQLException {
        String sql = "SELECT * FROM productos WHERE codigo_producto = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, codigoProducto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Producto(
                    rs.getString("codigo_producto"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio_base"),
                    rs.getDouble("precio_venta"),
                    rs.getString("categoria"),
                    rs.getInt("cantidad_disponible")
                );
            }
        }
        return null;
    }

    // Update (Actualizar producto)
    public void actualizarProducto(Producto producto) throws SQLException {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, precio_base = ?, precio_venta = ?, categoria = ?, cantidad_disponible = ? WHERE codigo_producto = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setDouble(3, producto.getPrecioBase());
            stmt.setDouble(4, producto.getPrecioVenta());
            stmt.setString(5, producto.getCategoria());
            stmt.setInt(6, producto.getCantidadDisponible());
            stmt.setString(7, producto.getCodigoProducto());
            stmt.executeUpdate();
        }
    }

    // Delete (Eliminar lógicamente producto)
    public void eliminarProducto(String codigoProducto) throws SQLException {
        String sql = "UPDATE productos SET activo = 0 WHERE codigo_producto = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, codigoProducto);
            stmt.executeUpdate();
        }
    }
}
