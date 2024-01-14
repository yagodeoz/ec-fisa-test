package ec.fisa.aplication.core.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Cronograma {
    private final List<Tarea> tareas;

    public Cronograma() {
        this.tareas = new ArrayList<>();
    }

    public void agregarTarea(Tarea tarea) {
            tareas.add(tarea);
    }

    public Boolean estaVacio(){
       return tareas.isEmpty();
    }

    public Tarea obtenerUltimaTarea(){
        return tareas.get(tareas.size() - 1);
    }


}
