package bd_gestoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Gestion_tb_Visita {

    public static boolean ComprobarUsuario(String codigo) throws SQLException {
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

}
