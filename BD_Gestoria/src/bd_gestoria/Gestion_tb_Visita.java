package bd_gestoria;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Gestion_tb_Visita {

    public static void menu() {
        System.out.println("\n==========================");
        System.out.println("=====Visiatas====");
        System.out.println("==========================");
        System.out.println("___Opciones___");
        System.out.println("1.Fecha visita");
        System.out.println("2.Consulta");
        System.out.println("3.Salir");
    }

    public static boolean buscarVisita(Connection conexion, int codigo) throws SQLException {
        if (conexion != null) {
            try {
                var stmt = conexion.prepareStatement("SELECT * FROM tb_visitas WHERE Idvisitas=?");
                stmt.setInt(1, codigo);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    int idVisita = resultado.getInt(1);
                    if (idVisita==codigo) {
                        return true;
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error. Debe introducir un numero.\n");
            }
        }
        return false;
    }

    public static void agregarVisita(Connection conexion, Scanner in) {
        try {
            String nif = Visita.getNIF(in);
            if (conexion != null || Gestion_tb_Usuarios.buscarUsuario(nif)) {
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
        try {
            String nif = Visita.getNIF(in);
            if (conexion != null || Gestion_tb_Usuarios.buscarUsuario(nif)) {
                var stmt = conexion.prepareStatement("DELETE FROM tb_visitas WHERE NIF=?");
                stmt.setString(1, nif);
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println("ha ocurrido un error al eliminar a una visita: " + e.getMessage());
        }
    }

    public static void modificarAlumno(Connection conexion, Scanner in) {
        try {
            System.out.print("Cual es el id de la visita que desea modificar: ");
            int iId = in.nextInt();
            if (conexion != null || !buscarVisita(conexion,iId)) {
                throw new Excepciones.VisitaNotFound();
            }
            menu();
            System.out.print("Que valor va a modificar: ");
            int opci = in.nextInt();
            in.nextLine();

            switch (opci) {
                case 1 -> {
                    var stmt = conexion.prepareStatement("UPDATE alumno SET Fecha_visita=? WHERE Idvisitas=?");
                    stmt.setString(1, "");
                    stmt.setInt(2, iId);
                    stmt.execute();
                }
                case 2 -> {
                    Visita.menu();
                    var stmt = conexion.prepareStatement("UPDATE alumno SET Consulta=?, ValorConsulta=? WHERE Idvisitas=?");
                    String consulta= in.nextLine();
                    stmt.setString(1, consulta);
                    stmt.setInt(2, Visita.getVisitaValor(consulta));
                    stmt.setInt(3, iId);
                    stmt.execute();
                    
                }
                case 3 -> {
                    System.out.println("Saliendo...");
                }
                default ->
                    System.out.println("Opcion inexistente :/");
            }

        } catch (InputMismatchException e) {
            System.out.println("ha ocurrido un error al ingresar el numero: " + e.getMessage());
        } catch (Excepciones.VisitaNotFound e) {
            System.out.println("ha ocurrido un error al seleccionar la visita: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error al modificar un parametro: " + e.getMessage());
        }
    }
}
