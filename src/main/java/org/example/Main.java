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

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║     SISTEMA DE GESTIÓN - CRUD COMPLETO                     ║");
        System.out.println("║     Db_DevSolutionsF                                       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // ===== EJEMPLO 1: CRUD DE PRODUCTOS =====
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("          1. CRUD DE PRODUCTOS");
        System.out.println("═══════════════════════════════════════════════════════════");
        ProductoDAO productoDAO = new ProductoDAO();

        // CREATE - Insertar un nuevo producto (los IDs 1, 2, 3 ya existen)
        System.out.println("\n[CREATE] Insertando nuevo producto...");
        Producto nuevoProducto = new Producto();
        nuevoProducto.setSkuProducto("PROD-004");
        nuevoProducto.setNombreProducto("Laptop Dell Inspiron");
        nuevoProducto.setDescripcionProducto("Laptop Dell Core i5, 8GB RAM, 256GB SSD");
        nuevoProducto.setCostoUnitarioProducto(new BigDecimal("4500.00"));
        nuevoProducto.setDescuentoProducto(new BigDecimal("5.00"));
        nuevoProducto.setImagenURLProducto("laptop-dell.jpg");
        nuevoProducto.setEstadoProducto("ACTIVO");
        nuevoProducto.setStockProducto(15);
        nuevoProducto.setIdCategoria(1); // Computadoras
        nuevoProducto.setIdTipoProducto(1); // Electrónica
        nuevoProducto.setIdUnidadMedida(1); // Unidad

        if (productoDAO.insertar(nuevoProducto)) {
            System.out.println("✓ Producto insertado correctamente");
        } else {
            System.out.println("✗ Error al insertar producto (puede ser que ya exista)");
        }

        // READ - Obtener todos los productos
        System.out.println("\n[READ] Consultando todos los productos...");
        List<Producto> productos = productoDAO.obtenerTodos();
        System.out.println("Total de productos en BD: " + productos.size());
        productos.forEach(p -> System.out.println("  → " + p));

        // READ - Obtener por ID
        System.out.println("\n[READ] Buscando producto con ID = 1...");
        Producto productoBuscado = productoDAO.obtenerPorId(1);
        if (productoBuscado != null) {
            System.out.println("✓ Producto encontrado: " + productoBuscado);
        } else {
            System.out.println("✗ Producto no encontrado");
        }

        // READ - Buscar por nombre
        System.out.println("\n[READ] Buscando productos que contengan 'Arroz'...");
        List<Producto> productosEncontrados = productoDAO.buscarPorNombre("Arroz");
        System.out.println("Productos encontrados: " + productosEncontrados.size());
        productosEncontrados.forEach(p -> System.out.println("  → " + p.getNombreProducto()));

        // READ - Por estado
        System.out.println("\n[READ] Consultando productos ACTIVOS...");
        List<Producto> productosActivos = productoDAO.obtenerPorEstado("ACTIVO");
        System.out.println("Productos activos: " + productosActivos.size());

        // UPDATE - Actualizar stock
        System.out.println("\n[UPDATE] Actualizando stock del producto ID=1 a 45...");
        if (productoDAO.actualizarStock(1, 45)) {
            System.out.println("✓ Stock actualizado correctamente");
        } else {
            System.out.println("✗ Error al actualizar stock");
        }

        // UPDATE - Actualizar producto completo
        System.out.println("\n[UPDATE] Actualizando datos completos del producto ID=1...");
        if (productoBuscado != null) {
            productoBuscado.setStockProducto(40);
            productoBuscado.setCostoUnitarioProducto(new BigDecimal("16.00"));
            if (productoDAO.actualizar(productoBuscado)) {
                System.out.println("✓ Producto actualizado correctamente");
            } else {
                System.out.println("✗ Error al actualizar producto");
            }
        }

        // DELETE - Eliminación lógica
        System.out.println("\n[DELETE LÓGICO] Desactivando producto ID=2...");
        if (productoDAO.eliminarLogico(2)) {
            System.out.println("✓ Producto desactivado (eliminación lógica)");
        } else {
            System.out.println("✗ Error al desactivar producto");
        }


        // ===== EJEMPLO 2: CRUD DE PROVEEDORES =====
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("          2. CRUD DE PROVEEDORES");
        System.out.println("═══════════════════════════════════════════════════════════");
        ProveedorDAO proveedorDAO = new ProveedorDAO();

        // CREATE - Ya existen 3 proveedores, agregar uno nuevo
        System.out.println("\n[CREATE] Insertando nuevo proveedor...");
        Proveedor nuevoProveedor = new Proveedor();
        nuevoProveedor.setNombreProveedor("Tecnología Global S.A.");
        nuevoProveedor.setTelefonoProveedor("23456789");
        nuevoProveedor.setCorreoProveedor("ventas@tecglobal.com");
        nuevoProveedor.setDireccionProveedor("Zona 9, Guatemala");
        nuevoProveedor.setNitProveedor("7777777-7");

        if (proveedorDAO.insertar(nuevoProveedor)) {
            System.out.println("✓ Proveedor insertado con ID: " + nuevoProveedor.getIdProveedor());
        } else {
            System.out.println("✗ Error al insertar proveedor (puede ser que ya exista)");
        }

        // READ - Obtener todos
        System.out.println("\n[READ] Consultando todos los proveedores...");
        List<Proveedor> proveedores = proveedorDAO.obtenerTodos();
        System.out.println("Total de proveedores: " + proveedores.size());
        proveedores.forEach(p -> System.out.println("  → " + p.getNombreProveedor() + " - NIT: " + p.getNitProveedor()));

        // READ - Por ID
        System.out.println("\n[READ] Buscando proveedor con ID = 1...");
        Proveedor proveedor1 = proveedorDAO.obtenerPorId(1);
        if (proveedor1 != null) {
            System.out.println("✓ Proveedor encontrado: " + proveedor1);
        }
//ojo
        // READ - Buscar por nombre
        System.out.println("\n[READ] Buscando proveedores que contengan 'Central'...");
        List<Proveedor> proveedoresEncontrados = proveedorDAO.buscarPorNombre("Central");
        System.out.println("Proveedores encontrados: " + proveedoresEncontrados.size());

        // UPDATE
        System.out.println("\n[UPDATE] Actualizando teléfono del proveedor ID=1...");
        if (proveedor1 != null) {
            proveedor1.setTelefonoProveedor("22334466");
            if (proveedorDAO.actualizar(proveedor1)) {
                System.out.println("✓ Proveedor actualizado correctamente");
            }
        }


        // ===== EJEMPLO 3: CRUD DE USUARIOS =====
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("          3. CRUD DE USUARIOS");
        System.out.println("═══════════════════════════════════════════════════════════");
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // CREATE - Agregar un nuevo usuario
        System.out.println("\n[CREATE] Creando nuevo usuario...");
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombreUsuario("supervisor1");
        nuevoUsuario.setRolUsuario("SUPERVISOR");
        nuevoUsuario.setCorreoUsuario("supervisor@devsolutions.com");
        nuevoUsuario.setContrasena("super123456");

        if (usuarioDAO.insertar(nuevoUsuario)) {
            System.out.println("✓ Usuario creado con ID: " + nuevoUsuario.getIdUsuario());
        } else {
            System.out.println("✗ Error al crear usuario (puede ser que ya exista)");
        }

        // READ - Todos los usuarios
        System.out.println("\n[READ] Consultando todos los usuarios...");
        List<Usuario> usuarios = usuarioDAO.obtenerTodos();
        System.out.println("Total de usuarios: " + usuarios.size());
        usuarios.forEach(u -> System.out.println("  → " + u.getNombreUsuario() + " (" + u.getRolUsuario() + ")"));

        // LOGIN - Autenticación
        System.out.println("\n[LOGIN] Autenticando usuario admin...");
        Usuario usuarioLogin = usuarioDAO.login("admin@devsolutions.com", "admin123");
        if (usuarioLogin != null) {
            System.out.println("✓ Login exitoso: " + usuarioLogin.getNombreUsuario() + " - " + usuarioLogin.getRolUsuario());
        } else {
            System.out.println("✗ Credenciales incorrectas");
        }

        // READ - Por rol
        System.out.println("\n[READ] Consultando usuarios con rol ADMINISTRADOR...");
        List<Usuario> administradores = usuarioDAO.obtenerPorRol("ADMINISTRADOR");
        System.out.println("Administradores en el sistema: " + administradores.size());

        // UPDATE - Cambiar contraseña
        System.out.println("\n[UPDATE] Cambiando contraseña del usuario ID=1...");
        if (usuarioDAO.cambiarContrasena(1, "nuevaPassword123")) {
            System.out.println("✓ Contraseña actualizada");
        }


        // ===== EJEMPLO 4: CRUD DE INVENTARIOS =====
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("          4. CRUD DE INVENTARIOS");
        System.out.println("═══════════════════════════════════════════════════════════");
        InventarioDAO inventarioDAO = new InventarioDAO();

        // READ - Todos los inventarios (ya existen 5 registros iniciales)
        System.out.println("\n[READ] Consultando todos los inventarios...");
        List<Inventario> inventarios = inventarioDAO.obtenerTodos();
        System.out.println("Total de inventarios: " + inventarios.size());
        inventarios.forEach(inv -> System.out.println("  → Bodega: " + inv.getIdBodega() +
                ", Producto: " + inv.getIdProducto() +
                ", Cantidad: " + inv.getCantidadInventario()));

        // READ - Por bodega
        System.out.println("\n[READ] Consultando inventario de la bodega ID=1...");
        List<Inventario> inventarioBodega1 = inventarioDAO.obtenerPorBodega(1);
        System.out.println("Productos en bodega 1: " + inventarioBodega1.size());

        // READ - Por producto
        System.out.println("\n[READ] Consultando en qué bodegas está el producto ID=1...");
        List<Inventario> inventarioProducto1 = inventarioDAO.obtenerPorProducto(1);
        System.out.println("Bodegas con producto 1: " + inventarioProducto1.size());

        // READ - Stock bajo
        System.out.println("\n[READ] Consultando productos con stock menor a 20 unidades...");
        List<Inventario> stockBajo = inventarioDAO.obtenerStockBajo(20);
        System.out.println("Productos con stock bajo: " + stockBajo.size());
        stockBajo.forEach(inv -> System.out.println("  → Producto " + inv.getIdProducto() +
                " en Bodega " + inv.getIdBodega() +
                ": " + inv.getCantidadInventario() + " unidades"));

        // UPDATE - Actualizar cantidad
        System.out.println("\n[UPDATE] Actualizando cantidad del inventario ID=1 a 80 unidades...");
        if (inventarioDAO.actualizarCantidad(1, 80)) {
            System.out.println("✓ Cantidad de inventario actualizada");
        }

        // UPDATE - Actualizar precio
        System.out.println("\n[UPDATE] Actualizando precio de venta del inventario ID=1...");
        if (inventarioDAO.actualizarPrecio(1, new BigDecimal("19.50"))) {
            System.out.println("✓ Precio actualizado");
        }

        // Verificar existencia
        System.out.println("\n[CHECK] Verificando si existe inventario para Bodega=2, Producto=3...");
        boolean existe = inventarioDAO.existeInventario(2, 3);
        System.out.println(existe ? "✓ El inventario existe" : "✗ El inventario no existe");


        // ===== EJEMPLO 5: CRUD DE COMPRAS CON DETALLES =====
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("          5. CRUD DE COMPRAS (CON DETALLES)");
        System.out.println("═══════════════════════════════════════════════════════════");
        CompraDAO compraDAO = new CompraDAO();

        // CREATE - Compra completa con detalles (TRANSACCIÓN)
        System.out.println("\n[CREATE] Creando nueva compra con detalles...");
        Compra nuevaCompra = new Compra();
        nuevaCompra.setFechaCompra(new Date(System.currentTimeMillis()));
        nuevaCompra.setTotalCompra(new BigDecimal("1350.00"));
        nuevaCompra.setEstadoCompra("PENDIENTE");
        nuevaCompra.setIdProveedor(1); // Proveedor existente

        // Crear detalles de la compra
        List<CompraDetalle> detalles = new ArrayList<>();

        CompraDetalle detalle1 = new CompraDetalle();
        detalle1.setCantidadDetalle(50);
        detalle1.setPrecioUnitarioDetalle(new BigDecimal("15.00"));
        detalle1.setIdProducto(1); // Producto existente: Arroz
        detalles.add(detalle1);

        CompraDetalle detalle2 = new CompraDetalle();
        detalle2.setCantidadDetalle(30);
        detalle2.setPrecioUnitarioDetalle(new BigDecimal("20.00"));
        detalle2.setIdProducto(2); // Producto existente: Desinfectante
        detalles.add(detalle2);

        if (compraDAO.insertarCompraCompleta(nuevaCompra, detalles)) {
            System.out.println("✓ Compra creada exitosamente con ID: " + nuevaCompra.getIdCompra());
        } else {
            System.out.println("✗ Error al crear la compra");
        }

        // READ - Obtener todas las compras
        System.out.println("\n[READ] Consultando todas las compras...");
        List<Compra> compras = compraDAO.obtenerTodas();
        System.out.println("Total de compras: " + compras.size());
        compras.forEach(c -> System.out.println("  → Compra #" + c.getIdCompra() +
                " - Total: Q" + c.getTotalCompra() +
                " - Estado: " + c.getEstadoCompra()));

        // READ - Detalles de una compra
        if (nuevaCompra.getIdCompra() > 0) {
            System.out.println("\n[READ] Consultando detalles de la compra #" + nuevaCompra.getIdCompra() + "...");
            List<CompraDetalle> detallesCompra = compraDAO.obtenerDetalles(nuevaCompra.getIdCompra());
            System.out.println("Detalles de la compra: " + detallesCompra.size());
            detallesCompra.forEach(d -> System.out.println("  → Producto: " + d.getIdProducto() +
                    ", Cantidad: " + d.getCantidadDetalle() +
                    ", Precio Unit: Q" + d.getPrecioUnitarioDetalle()));
        }

        // READ - Compras por proveedor
        System.out.println("\n[READ] Consultando compras del proveedor ID=1...");
        List<Compra> comprasProveedor = compraDAO.obtenerPorProveedor(1);
        System.out.println("Compras del proveedor: " + comprasProveedor.size());

        // READ - Compras por estado
        System.out.println("\n[READ] Consultando compras con estado PENDIENTE...");
        List<Compra> comprasPendientes = compraDAO.obtenerPorEstado("PENDIENTE");
        System.out.println("Compras pendientes: " + comprasPendientes.size());

        // UPDATE - Actualizar estado
        if (nuevaCompra.getIdCompra() > 0) {
            System.out.println("\n[UPDATE] Cambiando estado de compra a COMPLETADA...");
            if (compraDAO.actualizarEstado(nuevaCompra.getIdCompra(), "COMPLETADA")) {
                System.out.println("✓ Estado actualizado");
            }
        }


        // ===== EJEMPLO 6: CRUD DE BODEGAS =====
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("          6. CRUD DE BODEGAS");
        System.out.println("═══════════════════════════════════════════════════════════");
        BodegaDAO bodegaDAO = new BodegaDAO();

        // CREATE - Ya existen 3 bodegas, agregar una nueva
        System.out.println("\n[CREATE] Creando nueva bodega...");
        Bodega nuevaBodega = new Bodega();
        nuevaBodega.setNombreBodega("Bodega Villa Nueva");
        nuevaBodega.setUbicacionBodega("Km 22.5 Carretera a Villa Nueva");
        nuevaBodega.setDescripcionBodega("Bodega regional sur");
        nuevaBodega.setTelefonoBodega("55667788");
        nuevaBodega.setCapacidadBodega(2500);
        nuevaBodega.setMpioId(3); // Villa Nueva

        if (bodegaDAO.insertar(nuevaBodega)) {
            System.out.println("✓ Bodega creada con ID: " + nuevaBodega.getIdBodega());
        } else {
            System.out.println("✗ Error al crear bodega (puede ser que ya exista)");
        }

        // READ - Todas las bodegas
        System.out.println("\n[READ] Consultando todas las bodegas...");
        List<Bodega> bodegas = bodegaDAO.obtenerTodas();
        System.out.println("Total de bodegas: " + bodegas.size());
        bodegas.forEach(b -> System.out.println("  → " + b.getNombreBodega() +
                " - Capacidad: " + b.getCapacidadBodega() +
                " - Municipio: " + b.getMpioId()));

        // READ - Por ID
        System.out.println("\n[READ] Buscando bodega con ID=1...");
        Bodega bodega1 = bodegaDAO.obtenerPorId(1);
        if (bodega1 != null) {
            System.out.println("✓ Bodega encontrada: " + bodega1);
        }

        // READ - Por municipio
        System.out.println("\n[READ] Consultando bodegas en el municipio ID=1 (Guatemala)...");
        List<Bodega> bodegasMunicipio = bodegaDAO.obtenerPorMunicipio(1);
        System.out.println("Bodegas en Guatemala: " + bodegasMunicipio.size());

        // READ - Por departamento (usa JOIN con municipios)
        System.out.println("\n[READ] Consultando bodegas en el departamento ID=1...");
        List<Bodega> bodegasDepto = bodegaDAO.obtenerPorDepartamento(1);
        System.out.println("Bodegas en el departamento: " + bodegasDepto.size());

        // UPDATE
        System.out.println("\n[UPDATE] Actualizando capacidad de bodega ID=1...");
        if (bodega1 != null) {
            bodega1.setCapacidadBodega(5500);
            if (bodegaDAO.actualizar(bodega1)) {
                System.out.println("✓ Bodega actualizada");
            }
        }


        // ===== EJEMPLO 7: CRUD DE CATEGORÍAS =====
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("          7. CRUD DE CATEGORÍAS");
        System.out.println("═══════════════════════════════════════════════════════════");
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        // CREATE
        System.out.println("\n[CREATE] Creando nueva categoría...");
        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setNombreCategoria("Electrodomésticos");
        nuevaCategoria.setDescripcionCategoria("Aparatos eléctricos para el hogar");

        if (categoriaDAO.insertar(nuevaCategoria)) {
            System.out.println("✓ Categoría creada con ID: " + nuevaCategoria.getIdCategoria());
        } else {
            System.out.println("✗ Error al crear categoría (puede ser que ya exista)");
        }

        // READ
        System.out.println("\n[READ] Consultando todas las categorías...");
        List<Categoria> categorias = categoriaDAO.obtenerTodas();
        System.out.println("Total de categorías: " + categorias.size());
        categorias.forEach(c -> System.out.println("  → " + c.getNombreCategoria()));


        // ===== FINALIZACIÓN =====
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("          RESUMEN DE OPERACIONES");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("✓ Productos consultados: " + productos.size());
        System.out.println("✓ Proveedores consultados: " + proveedores.size());
        System.out.println("✓ Usuarios consultados: " + usuarios.size());
        System.out.println("✓ Inventarios consultados: " + inventarios.size());
        System.out.println("✓ Compras consultadas: " + compras.size());
        System.out.println("✓ Bodegas consultadas: " + bodegas.size());
        System.out.println("✓ Categorías consultadas: " + categorias.size());

        // Cerrar conexión
        DatabaseConnection.closeConnection();

        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║          ✓ PROGRAMA FINALIZADO EXITOSAMENTE               ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}