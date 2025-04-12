package bd_gestoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Gestion_tb_Visita {

    public static void agregarVisita() {
        Connection conexion = ConexionBaseDatos.getConnection();
        if (conexion != null) {
            try {
                var stmt=conexion.prepareStatement("INSERT INTO tb_visitas (NIF, Fecha_visita, Consulta, ValorConsulta) VALUES (?,?,?,?)");
                
            } catch (SQLException e) {
                System.out.println("Error al añadir una sentencia:" + e.getMessage());
            }
        }
    }
}
