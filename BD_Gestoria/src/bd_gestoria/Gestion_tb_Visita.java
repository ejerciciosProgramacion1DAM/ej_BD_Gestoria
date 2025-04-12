package bd_gestoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Gestion_tb_Visita {

    public static void agregarVisita(Scanner in) {
        Connection conexion = ConexionBaseDatos.getConnection();
        if (conexion != null) {
            try {
                var stmt=conexion.prepareStatement("INSERT INTO tb_visitas (NIF, Fecha_visita, Consulta, ValorConsulta) VALUES (?,?,?,?)");
                stmt.setString(1, Visita.setNIF(in));
                stmt.setString(2, Visita.setFecha_visita(in));
                String visita=Visita.setVisita(in);
                stmt.setString(3, visita);
                stmt.setInt(4, Visita.setVisitaValor(visita));
                stmt.execute();
            } catch (SQLException e) {
                System.out.println("Error al añadir una sentencia:" + e.getMessage());
            }
        }
    }




}
