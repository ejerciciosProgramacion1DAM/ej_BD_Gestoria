package bd_gestoria;

import java.sql.Connection;
import java.util.Scanner;

public class BD_Gestoria {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Connection conexion = ConexionBaseDatos.getConnection();
    }
}
