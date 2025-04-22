package bd_gestoria;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Gestion_tb_Visita {

    public static void agregarVisita(Connection conexion, Scanner in) {
        try {
            String nif = Visita.getNIF(in);
            if (conexion != null && Gestion_tb_Usuarios.buscarUsuario(nif)) {
                var stmt = conexion.prepareStatement("INSERT INTO tb_visitas (NIF, Fecha_visita, Consulta, ValorConsulta) VALUES (?,?,?,?)");
                stmt.setString(1, nif);
                stmt.setString(2, Visita.getFecha_visita(in));
                String visita = Visita.getVisita(in);
                stmt.setString(3, visita);
                stmt.setInt(4, Visita.getVisitaValor(visita));
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println("Error al añadir una sentencia:" + e.getMessage());
        }
    }

    public static void eliminarVisita(Connection conexion, Scanner in) {
        if (conexion != null) {
            try {
                var stmt = conexion.prepareStatement("DELETE FROM tb_visitas WHERE NIF=?");
                stmt.setString(1, Visita.getNIF(in));
                stmt.execute();
            } catch (SQLException e) {
                System.out.println("ha ocurrido un error al eliminar a una visita: " + e.getMessage());
            }
        }
    }

}
