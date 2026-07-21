package dao;

import conexion.Conexion;
import modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO {

    private Connection conexion;
    
   /**
 * Clase encargada de gestionar las operaciones sobre la base de datos
 * relacionadas con los productos. Implementa las operaciones CRUD
 * (Crear, Consultar, Actualizar y Eliminar) de la aplicación SwissCol.
 *
 * @author Marlon Pulido
 */

    public ProductoDAO() {
        conexion = Conexion.conectar();

        if (conexion == null) {
            throw new RuntimeException("No fue posible conectar con la base de datos.");
        }
    }

    
    
    
    // INSERTAR PRODUCTO
    
    /**
     * Inserta un nuevo producto en la base de datos utilizando la información
     * almacenada en un objeto Producto.
     *
     * @param producto Objeto que contiene los datos del producto que se desea registrar.
     * 
     */
    
    public void insertarProducto(Producto producto) {

        String sql = "INSERT INTO product(product_id, name, description, price, stock, image, category_id) VALUES(?,?,?,?,?,?,?)";

        try {

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, producto.getProductId());
            ps.setString(2, producto.getName());
            ps.setString(3, producto.getDescription());
            ps.setDouble(4, producto.getPrice());
            ps.setInt(5, producto.getStock());
            ps.setString(6, producto.getImage());
            ps.setInt(7, producto.getCategoryId());

            ps.executeUpdate();

            System.out.println("Producto registrado correctamente.");

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }
    
   


    // LISTAR PRODUCTOS
    
     /**
        * Consulta y muestra todos los productos registrados
        * en la base de datos.
     */
    public void listarProductos() {

        String sql = "SELECT * FROM product";

        try {

            PreparedStatement ps = conexion.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("-----------------------------");
                System.out.println("ID: " + rs.getInt("product_id"));
                System.out.println("Nombre: " + rs.getString("name"));
                System.out.println("Descripción: " + rs.getString("description"));
                System.out.println("Precio: $" + rs.getDouble("price"));
                System.out.println("Stock: " + rs.getInt("stock"));
                System.out.println("Imagen: " + rs.getString("image"));
                System.out.println("Categoría: " + rs.getInt("category_id"));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    // ACTUALIZAR PRODUCTO
    
    /**
     * Actualiza la información de un producto existente
     * en la base de datos.
     *
     * @param producto Objeto con la información actualizada del producto.
    */

    public void actualizarProducto(Producto producto) {

        String sql = "UPDATE product SET name=?, description=?, price=?, stock=?, image=?, category_id=? WHERE product_id=?";

        try {

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, producto.getName());
            ps.setString(2, producto.getDescription());
            ps.setDouble(3, producto.getPrice());
            ps.setInt(4, producto.getStock());
            ps.setString(5, producto.getImage());
            ps.setInt(6, producto.getCategoryId());
            ps.setInt(7, producto.getProductId());

            ps.executeUpdate();

            System.out.println("Producto actualizado correctamente.");

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    // ELIMINAR PRODUCTO
    
    /**
    * Elimina un producto de la base de datos utilizando
    * su identificador.
    *
    * @param id Identificador del producto que será eliminado.
     */

    public void eliminarProducto(int id) {

        String sql = "DELETE FROM product WHERE product_id=?";

        try {

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Producto eliminado correctamente.");

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}