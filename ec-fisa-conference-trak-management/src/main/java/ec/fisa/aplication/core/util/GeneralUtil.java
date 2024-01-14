package ec.fisa.aplication.core.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class GeneralUtil {
    public static final String HH_MM = "HH:mm";
    public static Date agregarMinutosFechaBase (Date fechaInicial, Integer  duracion){
        LocalDateTime fechaBaseLocalDateTime = fechaInicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime fechaConMinutosAgregados = fechaBaseLocalDateTime.plusMinutes(duracion);
        return java.util.Date.from(fechaConMinutosAgregados.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static boolean tieneNumerosEnTitulo(String titulo) {
        return titulo.matches(".*\\d+.*");
    }

    public static Date calcularFechaPorHora (Integer hora, Integer minuto ){
        LocalDate hoy = LocalDate.now();
        LocalTime hora9AM = LocalTime.of(hora, minuto);
        LocalDateTime fechaYHora = LocalDateTime.of(hoy, hora9AM);
        return java.util.Date.from(fechaYHora.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String formatearHora(Date fecha) {

        String formatoHorasYMinutos = HH_MM;
        SimpleDateFormat formateador = new SimpleDateFormat(formatoHorasYMinutos);
        return formateador.format(fecha);
    }
}
