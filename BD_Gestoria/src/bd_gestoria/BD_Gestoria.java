package bd_gestoria;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class BD_Gestoria {

    public static void main(String[] args) throws SQLException {
        Scanner in = new Scanner(System.in);
        Connection conexion = ConexionBaseDatos.getConnection();
        
        Gestion_tb_Usuarios.agregarUsuario(conexion, in);
        Gestion_tb_Usuarios.buscarUsuario("77964877");
        Gestion_tb_Usuarios.costeVisitas(conexion, in);
        Gestion_tb_Usuarios.agregarUsuario(conexion, in);
        Gestion_tb_Usuarios.eliminarUsuario(conexion, in);
        Gestion_tb_Usuarios.numeroVisitas(conexion, in);
        Gestion_tb_Usuarios.modificarUsuario(conexion, in);
        
        Gestion_tb_Visita.agregarVisita(conexion, in);
        Gestion_tb_Visita.buscarVisita(conexion, 1);
        Gestion_tb_Visita.modificarVisita(conexion, in);
        Gestion_tb_Visita.eliminarVisita(conexion, in);
    }
}
