package bd_gestoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Gestion_tb_Usuarios {

    public static void menu() {
        System.out.println("\n==========================");
        System.out.println("=====Usuario====");
        System.out.println("==========================");
        System.out.println("___Opciones___");
        System.out.println("1.Nombre");
        System.out.println("2.Apellidos");
        System.out.println("3.Correo electronico");
        System.out.println("4.Telefono");
        System.out.println("5.Salir");
    }

    public static boolean sinVisitas(Connection conexion, Scanner in) {
        try {
            String nif = Visita.getNIF(in);
            if (conexion != null || !Gestion_tb_Usuarios.buscarUsuario(nif)) {
                var stmt = conexion.prepareStatement("SELECT COUNT(*) FROM visitas WHERE nif = ?");
                stmt.setString(1, nif);
                ResultSet resultado = stmt.executeQuery();
                while (resultado.next()) {
                    if (resultado.equals(0)) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error en la busqueda: " + e.getMessage());
        }
        return false;
    }
    
    public static void numeroVisitas(Connection conexion, Scanner in) {
        try {
            String nif = Visita.getNIF(in);
            if (conexion != null || !Gestion_tb_Usuarios.buscarUsuario(nif)) {
                var stmt = conexion.prepareStatement("SELECT COUNT(*) FROM visitas WHERE nif = ?");
                stmt.setString(1, nif);
                ResultSet resultado = stmt.executeQuery();
                System.out.println("Este usuario ha realizado: "+resultado+" visitas");
            }
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error en la busqueda: " + e.getMessage());
        }
    }
    
    public static void costeVisitas(Connection conexion, Scanner in) {
        try {
            String nif = Visita.getNIF(in);
            if (conexion != null || !Gestion_tb_Usuarios.buscarUsuario(nif)) {
                var stmt = conexion.prepareStatement("SELECT COUNT(ValorConsulta) FROM visitas WHERE nif = ?");
                stmt.setString(1, nif);
                ResultSet resultado = stmt.executeQuery();
                System.out.println("Este usuario ha de pagar por las consultas: "+resultado+"€");
            }
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error en la busqueda: " + e.getMessage());
        }
    }

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

    public static void agregarUsuario(Connection conexion, Scanner in) {
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

    public static void eliminarUsuario(Connection conexion, Scanner in) {
        try {
            String nif = Visita.getNIF(in);
            if (conexion != null || buscarUsuario(nif) || sinVisitas(conexion, in)) {
                var stmt = conexion.prepareStatement("DELETE FROM clientes WHERE NIF=?");
                stmt.setString(1, nif);
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println("ha ocurrido un error al eliminar a una visita: " + e.getMessage());
        }
    }

    public static void modificarUsuario(Connection conexion, Scanner in) {
        try {
            System.out.print("Cual es el NiF del usuario que desea modificar: ");
            String nif = in.nextLine();
            if (conexion != null || !buscarUsuario(nif)) {
                throw new Excepciones.UsuarioNotFound();
            }

            menu();
            System.out.print("Que valor va a modificar: ");
            int opci = in.nextInt();
            in.nextLine();

            switch (opci) {
                case 1 -> {
                    System.out.print("Cual es el nuevo nombre: ");
                    String nombre = in.nextLine();
                    var stmt = conexion.prepareStatement("UPDATE tb_usuarios SET Nombre=? WHERE NIF=?");
                    stmt.setString(1, nombre);
                    stmt.setString(2, nif);
                    stmt.execute();
                }
                case 2 -> {
                    System.out.print("Cual es el nuevo apellido: ");
                    String apellido = in.nextLine();
                    var stmt = conexion.prepareStatement("UPDATE tb_usuarios SET Apellidos=? WHERE NIF=?");
                    stmt.setString(1, apellido);
                    stmt.setString(2, nif);
                    stmt.execute();
                }
                case 3 -> {
                    System.out.print("Cual es el nuevo correo electronico: ");
                    String correo = in.nextLine();
                    var stmt = conexion.prepareStatement("UPDATE tb_usuarios SET Correo_electronico=? WHERE NIF=?");
                    stmt.setString(1, correo);
                    stmt.setString(2, nif);
                    stmt.execute();
                }
                case 4 -> {
                    System.out.print("Cual es el nuevo telefono: ");
                    int telefono = in.nextInt();
                    var stmt = conexion.prepareStatement("UPDATE tb_usuarios SET Telefono=? WHERE NIF=?");
                    stmt.setInt(1, telefono);
                    stmt.setString(2, nif);
                    stmt.execute();
                }
                case 5 -> {
                    System.out.println("Saliendo...");
                }
                default -> {
                    System.out.println("Opcion inexistente :/");
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("ha ocurrido un error al ingresar el numero: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("ha ocurrido un error al eliminar a una visita: " + e.getMessage());
        } catch (Excepciones.UsuarioNotFound e) {
            System.out.println("ha ocurrido un error al eliminar a una visita: " + e.getMessage());
        }
    }
}
