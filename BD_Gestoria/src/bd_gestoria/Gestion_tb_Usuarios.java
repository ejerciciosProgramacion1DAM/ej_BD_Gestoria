package bd_gestoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Gestion_tb_Usuarios {

    public static boolean buscarUsuario(String codigo) throws SQLException {
        try (Connection conexion = ConexionBaseDatos.getConnection()) {
            if (conexion != null) {
                try {
                    PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM clientes WHERE dni=?");
                    stmt.setString(1, codigo);
                    ResultSet resultado = stmt.executeQuery();
                    while (resultado.next()) {
                        String dni = resultado.getString(1);
                        if (dni.equals(codigo)) {
                            return true;
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Error. Debe introducir un numero.\n");
                }
            }
        }
        return false;
    }
    
    public static  void agregarUsuario(Connection conexion, Scanner in){
        try {
            if (conexion != null) {
                var stmt = conexion.prepareStatement("INSERT INTO clientes (NIF, Nombre, Apellidos, Correo_electronico, Telefono) VALUES (?,?,?,?,?)");
                stmt.setString(1, Visita.getNIF(in));
                stmt.setString(2, Usuario.getsNombre(in));
                stmt.setString(3, Usuario.getsApellidos(in));
                stmt.setString(4, Usuario.getCorreoElectronico(in));
                stmt.setInt(5, Usuario.getTelefono(in));
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println("Error al añadir una sentencia:" + e.getMessage());
        }
    }
    
    public static void eliminarUsuario(Connection conexion, Scanner in){
        
    }
    
    public static void modificarUsuario(Connection conexion, Scanner in){
        
    }    
}
