package bd_gestoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Usuario {

    //comprobar si es un numero
    public static boolean isDigit(String word) {
        boolean stay = true;
        while (stay) {
            try {
                Integer.valueOf(word);
                stay = false;
            } catch (NumberFormatException e) {
                System.out.println("+==========================+");
                System.out.println("|ERROR 000, No es un numero|");
                System.out.println("+==========================+");
                System.out.print("pruebe de nuevo");
                System.out.print("\nIntrouzca el telefono: ");
                return false;
            }
        }
        return true;
    }

    //dar valores a columnas tabla usuarios
    public static String setNIF(Scanner in) {
        System.out.print("Cual es tu NIF: ");
        String sNif = in.nextLine().trim().toUpperCase();

        if (!sNif.matches("\\d{8}[A-Z]")) {
            System.out.println("El NIF no sigue el formato correcto (8 numeros y 1 letra)");
            return "123456879";
        }

        return sNif;
    }

    public static String setsNombre(Scanner in) {
        String sNombre = "User";
        System.out.print("Introduce el nombre del usuario: ");
        sNombre = in.nextLine();

        // Recortar si excede los 30 caracteres
        if (sNombre.length() > 50) {
            sNombre = sNombre.substring(0, 50);
            System.out.println("El nombre ha sido acortado a: " + sNombre);
        }
        return sNombre;
    }

    public static String setsApellidos(Scanner in) {
        String sApellidos = "User";
        System.out.print("Introduce los apellidos del usuario: ");
        sApellidos = in.nextLine();

        // Recortar si excede los 30 caracteres
        if (sApellidos.length() > 50) {
            sApellidos = sApellidos.substring(0, 50);
            System.out.println("Los apellidos han sido acortados a: " + sApellidos);
        }
        return sApellidos;
    }

    public static String setCorreoElectronico(Scanner in) {
        System.out.print("Cual es tu NIF: ");
        String sCorreo = in.nextLine();

        return sCorreo;
    }

    public static int setTelefono(Scanner in) {
        int iTelefono;
        System.out.print("Cual es tu telefono: ");
        String sNumero = in.nextLine().trim().toLowerCase();

        if (!sNumero.matches("\\d{9}") && !isDigit(sNumero)) {
            System.out.println("El telefono no sigue el formato correcto (9 numeros)");
            return 123456879;
        }
        iTelefono = Integer.valueOf(sNumero);
        return iTelefono;
    }

    //gestion de la tabla
    public static boolean ComprobarCliente(String codigo) throws SQLException {
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
