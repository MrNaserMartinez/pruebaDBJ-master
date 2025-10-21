package org.example;

import config.DatabaseConnection;
import dao.*;
import model.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // ===== EJEMPLO 1: CRUD DE PRODUCTOS =====
        System.out.println("===== CRUD PRODUCTOS =====");
        ProductoDAO productoDAO = new ProductoDAO();

        // CREATE - Insertar producto
        Producto producto = new Producto();
        producto.setSkuProducto("PROD-001");
        producto.setNombreProducto("Laptop HP");
        producto.setDescripcionProducto("Laptop HP Core i7 16GB RAM");
        producto.setCostoUnitarioProducto(new BigDecimal("15000.00"));
        producto.setDescuentoProducto(new BigDecimal("10.00"));
        producto.setEstadoProducto("ACTIVO");
        producto.setStockProducto(50);
        producto.setIdCategoria(1);
        producto.setIdTipoProducto(1);
        producto.setIdUnidadMedida(1);

        if (productoDAO.insertar(producto)) {
            System.out.println("✓ Producto insertado correctamente");
        }

        // READ - Obtener todos los productos
        List<Producto> productos = productoDAO.obtenerTodos();
        System.out.println("Total de productos: " + productos.size());
        productos.forEach(System.out::println);

        // READ - Obtener por ID
        Producto productoBuscado = productoDAO.obtenerPorId(1);
        if (productoBuscado != null) {
            System.out.println("Producto encontrado: " + productoBuscado);
        }

        // READ - Buscar por nombre
        List<Producto> productosEncontrados = productoDAO.buscarPorNombre("Laptop");
        System.out.println("Productos encontrados: " + productosEncontrados.size());

        // UPDATE - Actualizar stock
        if (productoDAO.actualizarStock(1, 45)) {
            System.out.println("✓ Stock actualizado");
        }

        // UPDATE - Actualizar producto completo
        producto.setIdProducto(1);
        producto.setStockProducto(40);
        if (productoDAO.actualizar(producto)) {
            System.out.println("✓ Producto actualizado");
        }

        // DELETE - Eliminación lógica
        if (productoDAO.eliminarLogico(1)) {
            System.out.println("✓ Producto desactivado");
        }


        // ===== EJEMPLO 2: CRUD DE PROVEEDORES =====
        System.out.println("\n===== CRUD PROVEEDORES =====");
        ProveedorDAO proveedorDAO = new ProveedorDAO();

        // CREATE
        Proveedor proveedor = new Proveedor();
        proveedor.setNombreProveedor("Distribuidora Tech S.A.");
        proveedor.setTelefonoProveedor("12345678");
        proveedor.setCorreoProveedor("ventas@disttech.com");
        proveedor.setDireccionProveedor("Zona 10, Guatemala");
        proveedor.setNitProveedor("1234567-8");

        if (proveedorDAO.insertar(proveedor)) {
            System.out.println("✓ Proveedor insertado con ID: " + proveedor.getIdProveedor());
        }

        // READ
        List<Proveedor> proveedores = proveedorDAO.obtenerTodos();
        System.out.println("Total proveedores: " + proveedores.size());

        // UPDATE
        proveedor.setTelefonoProveedor("87654321");
        if (proveedorDAO.actualizar(proveedor)) {
            System.out.println("✓ Proveedor actualizado");
        }


        // ===== EJEMPLO 3: CRUD DE USUARIOS =====
        System.out.println("\n===== CRUD USUARIOS =====");
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // CREATE
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("admin");
        usuario.setRolUsuario("ADMINISTRADOR");
        usuario.setCorreoUsuario("admin@devsolutions.com");
        usuario.setContrasena("admin123456");

        if (usuarioDAO.insertar(usuario)) {
            System.out.println("✓ Usuario creado con ID: " + usuario.getIdUsuario());
        }

        // LOGIN
        Usuario usuarioLogin = usuarioDAO.login("admin@devsolutions.com", "admin123456");
        if (usuarioLogin != null) {
            System.out.println("✓ Login exitoso: " + usuarioLogin.getNombreUsuario());
        }

        // READ por rol
        List<Usuario> administradores = usuarioDAO.obtenerPorRol("ADMINISTRADOR");
        System.out.println("Administradores: " + administradores.size());


        // ===== EJEMPLO 4: CRUD DE INVENTARIOS =====
        System.out.println("\n===== CRUD INVENTARIOS =====");
        InventarioDAO inventarioDAO = new InventarioDAO();

        // CREATE
        Inventario inventario = new Inventario();
        inventario.setCantidadInventario(100);
        inventario.setPrecioVentaInventario(new BigDecimal("17500.00"));
        inventario.setFechaActualizacionInventario(new Timestamp(System.currentTimeMillis()));
        inventario.setIdBodega(1);
        inventario.setIdProducto(1);

        if (inventarioDAO.insertar(inventario)) {
            System.out.println("✓ Inventario creado");
        }

        // READ - Stock bajo
        List<Inventario> stockBajo = inventarioDAO.obtenerStockBajo(50);
        System.out.println("Productos con stock bajo: " + stockBajo.size());

        // UPDATE - Actualizar cantidad
        if (inventarioDAO.actualizarCantidad(1, 80)) {
            System.out.println("✓ Cantidad de inventario actualizada");
        }


        // ===== EJEMPLO 5: CRUD DE COMPRAS CON DETALLES =====
        System.out.println("\n===== CRUD COMPRAS =====");
        CompraDAO compraDAO = new CompraDAO();

        // CREATE - Compra completa con detalles
        Compra compra = new Compra();
        compra.setFechaCompra(new Date(System.currentTimeMillis()));
        compra.setTotalCompra(new BigDecimal("50000.00"));
        compra.setEstadoCompra("PENDIENTE");
        compra.setIdProveedor(1);

        // Crear detalles
        List<CompraDetalle> detalles = new ArrayList<>();

        CompraDetalle detalle1 = new CompraDetalle();
        detalle1.setCantidadDetalle(10);
        detalle1.setPrecioUnitarioDetalle(new BigDecimal("5000.00"));
        detalle1.setIdProducto(1);
        detalles.add(detalle1);

        CompraDetalle detalle2 = new CompraDetalle();
        detalle2.setCantidadDetalle(5);
        detalle2.setPrecioUnitarioDetalle(new BigDecimal("3000.00"));
        detalle2.setIdProducto(2);
        detalles.add(detalle2);

        if (compraDAO.insertarCompraCompleta(compra, detalles)) {
            System.out.println("✓ Compra creada con ID: " + compra.getIdCompra());
        }

        // READ - Obtener detalles de una compra
        List<CompraDetalle> detallesCompra = compraDAO.obtenerDetalles(compra.getIdCompra());
        System.out.println("Detalles de la compra: " + detallesCompra.size());
        detallesCompra.forEach(System.out::println);

        // READ - Compras por proveedor
        List<Compra> comprasProveedor = compraDAO.obtenerPorProveedor(1);
        System.out.println("Compras del proveedor: " + comprasProveedor.size());


        // ===== EJEMPLO 6: CRUD DE BODEGAS =====
        System.out.println("\n===== CRUD BODEGAS =====");
        BodegaDAO bodegaDAO = new BodegaDAO();

        // CREATE
        Bodega bodega = new Bodega();
        bodega.setNombreBodega("Bodega Central");
        bodega.setUbicacionBodega("Zona 12, Guatemala");
        bodega.setDescripcionBodega("Bodega principal de almacenamiento");
        bodega.setTelefonoBodega("22334455");
        bodega.setCapacidadBodega("1000");
        bodega.setMpioId(1);
        bodega.setDeptoId(1);

        if (bodegaDAO.insertar(bodega)) {
            System.out.println("✓ Bodega creada con ID: " + bodega.getIdBodega());
        }

        // READ
        List<Bodega> bodegas = bodegaDAO.obtenerTodas();
        System.out.println("Total de bodegas: " + bodegas.size());

        // READ - Por departamento
        List<Bodega> bodegasDepto = bodegaDAO.obtenerPorDepartamento(1);
        System.out.println("Bodegas en el departamento: " + bodegasDepto.size());


        // Cerrar conexión
        DatabaseConnection.closeConnection();
        System.out.println("\n✓ Programa finalizado");
    }
}