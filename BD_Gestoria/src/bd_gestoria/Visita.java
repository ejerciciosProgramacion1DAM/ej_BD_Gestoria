package bd_gestoria;

import java.sql.SQLException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Visita {

    public static void menu() {
        System.out.println("\n===================");
        System.out.println("=====Tipos Visita====");
        System.out.println("=====================");
        System.out.println("___Opciones___");
        System.out.println("1.Contable: el Valor de la Consulta será de 50€");
        System.out.println("2.Finanzas: el Valor de la Consulta será de 65€");
        System.out.println("3.Inversión: el Valor de la Consulta será de 60€");
        System.out.println("4.Otra: el Valor de la Consulta será de 55€");
    }

    //dar valores a columnas tabla usuarios
    public static String setNIF(Scanner in) throws SQLException {
        boolean stay = true;
        String sNif = "12345678A";

        while (stay) {
            System.out.print("Cual es tu NIF: ");
            sNif = in.nextLine().trim().toUpperCase();

            if (!sNif.matches("\\d{8}[A-Z]")&& !Gestion_tb_Usuarios.buscarUsuario(sNif)) {
                System.out.println("\nEl NIF no sigue el formato correcto (8 numeros y 1 letra) o no se ha encontrado");
                System.out.println("Pruebe de nuevo");
            } else {
                stay=false;
            }
        }
        return sNif;
    }

    public static String setFecha_visita(Scanner in) {

        System.out.println("Formato de fecha (dd/mm/yyyy).");
        System.out.print("introduce la fecha de la visita: ");
        String Fecha_visita = in.nextLine().trim();

        if (!Fecha_visita.matches("^([0][1-9]|[12][0-9]|3[01])/([0][1-9]|1[0-2])/\\d{4}$")) {
            System.out.println("Formato de fecha inválido (dd/mm/yyyy)");
            System.out.println("Por gracioso se le aplica la fecha de hoy automaticamene");
            LocalDate hoy = LocalDate.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaFormateada = hoy.format(formato);
            return fechaFormateada;
        } else {
            return Fecha_visita;
        }
    }

    public static String setVisita(Scanner in) {
        menu();
        System.out.print("introduce el nombre de la visita: ");
        String sVisita = in.nextLine().toLowerCase().trim();

        if (sVisita.length() > 20) {
            sVisita = sVisita.substring(0, 20);
            System.out.println("El nombre de la visita ha sido acortado a: " + sVisita);
        }

        return sVisita;

    }

    public static int setVisitaValor(String sVisita) {
        int iValorConsulta;

        switch (sVisita) {
            case "contable" ->
                iValorConsulta = 50;
            case "finanzas" ->
                iValorConsulta = 65;
            case "inversion" ->
                iValorConsulta = 60;
            default ->
                iValorConsulta = 55;
        }

        return iValorConsulta;
    }
}
