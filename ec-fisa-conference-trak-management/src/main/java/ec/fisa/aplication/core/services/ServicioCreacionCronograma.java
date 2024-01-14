package ec.fisa.aplication.core.services;


import ec.fisa.aplication.core.domain.Tarea;
import ec.fisa.aplication.core.domain.Cronograma;
import ec.fisa.aplication.core.dtos.TareaDto;
import ec.fisa.aplication.core.util.GeneralUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioCreacionCronograma {


    public static final String LUNCH = "LUNCH";
    public static final String EVENTO_NETWORK = "17:00";
    public static final int DURACION = 60;
    public static final String NETWORKING_EVENT = "NETWORKING EVENT";

    private Integer tiempoTareaLunch;

    public static final int HORA = 9;
    public static final int MINUTO = 0;
    private final List<Cronograma> cornogramas = new ArrayList<>();

    public List<Cronograma> organizarCronograma(List<TareaDto> tareas, Integer duracionSesion1, Integer duracionSesion2 ){

        tiempoTareaLunch = duracionSesion1;
        List<TareaDto> tareasFiltradas = tareas.stream()
                .filter(tarea -> !GeneralUtil.tieneNumerosEnTitulo(tarea.getTema()))
                .collect(Collectors.toList());


        List<List<TareaDto>> combinaciones = encontrarCombinaciones(tareasFiltradas, duracionSesion1 + duracionSesion2);

        List<Cronograma> cronogramas = new ArrayList<>();
        // Imprimir las combinaciones encontradas
        for (List<TareaDto> combinacion : combinaciones) {

            boolean esCombinacionIdeal = combinacion.stream()
                    .anyMatch(tarea -> LUNCH.equals(tarea.getTema()));

            if (esCombinacionIdeal) {
                Cronograma cronograma = new Cronograma();
                combinacion.forEach(e -> {
                    if (cronograma.estaVacio()) {
                        cronograma.agregarTarea(new Tarea(e.getTema(), e.getDuracion(), GeneralUtil.calcularFechaPorHora(HORA, MINUTO)));
                    } else {
                        cronograma.agregarTarea(new Tarea(e.getTema(), e.getDuracion(), cronograma.obtenerUltimaTarea().getFechaFinal()));
                    }
                });
                boolean contieneFinideal = cronograma.getTareas().stream()
                        .anyMatch(tarea -> EVENTO_NETWORK.equals(GeneralUtil.formatearHora(tarea.getFechaFinal())));
                if (contieneFinideal) {
                    cronograma.getTareas().remove(cronograma.getTareas().size() - 1);
                    cronograma.agregarTarea(new Tarea(NETWORKING_EVENT, DURACION, cronograma.obtenerUltimaTarea().getFechaFinal()));
                }

                boolean cronogramaIdeal = cronograma.getTareas().stream()
                        .reduce((primero, segundo) -> segundo)
                        .filter(tarea -> NETWORKING_EVENT.equals(tarea.getTema()) && EVENTO_NETWORK.equals(GeneralUtil.formatearHora(tarea.getFechaInicial())))
                        .isPresent();

                if (cronogramaIdeal)
                    cronogramas.add(cronograma);

            }
        }
        return cronogramas;
    }

    private  List<List<TareaDto>> encontrarCombinaciones(List<TareaDto> tareas, int total) {
        List<List<TareaDto>> resultado = new ArrayList<>();
        encontrarCombinacionesAux(tareas, total, 0, new ArrayList<>(), resultado);
        return resultado;
    }

    private  void encontrarCombinacionesAux(List<TareaDto> tareas, int total, int indice, List<TareaDto> combinacionParcial, List<List<TareaDto>> resultado) {

        if (cobinacionIdeal(combinacionParcial, tiempoTareaLunch )) {
            combinacionParcial.add(new TareaDto(LUNCH, DURACION));
        }

        if (total <= 0) {
            resultado.add(new ArrayList<>(combinacionParcial));
            return;
        }

        for (int i = indice; i < tareas.size(); i++) {
            TareaDto tareaActual = tareas.get(i);
            if (!combinacionParcial.contains(tareaActual) && total >= tareaActual.getDuracion()) {
                combinacionParcial.add(tareaActual);
                encontrarCombinacionesAux(tareas, total - tareaActual.getDuracion(), i + 1, combinacionParcial, resultado);
                combinacionParcial.remove(combinacionParcial.size() - 1);
            }
        }
    }

    private static boolean cobinacionIdeal(List<TareaDto> combinacion, Integer valorSesion) {
        int suma = combinacion.stream()
                .mapToInt(TareaDto::getDuracion)
                .sum();
        return suma == valorSesion;
    }

}
