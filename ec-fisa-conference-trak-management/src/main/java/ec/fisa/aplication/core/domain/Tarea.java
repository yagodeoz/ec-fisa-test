package ec.fisa.aplication.core.domain;


import ec.fisa.aplication.core.util.GeneralUtil;
import lombok.Getter;
import lombok.ToString;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Charla.
 */
@Getter
public class Tarea {


    protected  String tema;
    protected Integer duracion;//minutos
    protected Date fechaInicial;
    protected Date fechaFinal;

    /**
     * Instantiates a new Charla.
     *
     * @param tema     the tema
     * @param duracion the duracion
     */
    public Tarea(String tema, Integer duracion, Date fechaInicial) {
        this.tema = tema;
        this.duracion = duracion;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = GeneralUtil.agregarMinutosFechaBase(fechaInicial, duracion);
    }

    @Override
    public String toString() {

        return "* " + GeneralUtil.formatearHora(fechaInicial) + " - " + GeneralUtil.formatearHora(fechaFinal) + " ->  " + tema + " ( " + duracion + " min )";
    }
}
