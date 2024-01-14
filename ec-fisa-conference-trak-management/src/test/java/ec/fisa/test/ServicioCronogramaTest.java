package ec.fisa.test;

import ec.fisa.aplication.core.domain.Cronograma;
import ec.fisa.aplication.core.dtos.TareaDto;
import ec.fisa.aplication.core.services.ServicioCreacionCronograma;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCronogramaTest {


    @Test
    public void testOrganizarCronograma() {
        List<TareaDto> test = List.of(
                new TareaDto("Writing Fast Tests Against Enterprise Rails", 60),
                new TareaDto("Overdoing it in Python", 45),
                new TareaDto("Lua for the Masses", 30),
                new TareaDto("Ruby Errors from Mismatched Gem Versions", 45),
                new TareaDto("Common Ruby Errors", 45),
                new TareaDto("Rails for Python Developers lightning", 60),
                new TareaDto("Communicating Over Distance", 60),
                new TareaDto("Accounting-Driven Development", 45),
                new TareaDto("Woah", 30),
                new TareaDto("Sit Down and Write", 30),
                new TareaDto("Pair Programming vs Noise", 45),
                new TareaDto("Rails Magic", 60),
                new TareaDto("Ruby on Rails: Why We Should Move On", 60),
                new TareaDto("Clojure Ate Scala (on my project)", 45),
                new TareaDto("Programming in the Boondocks of Seattle", 30),
                new TareaDto("Ruby vs. Clojure for Back-End Development", 30),
                new TareaDto("Ruby on Rails Legacy App Maintenance", 60),
                new TareaDto("A World Without HackerNews", 30),
                new TareaDto("User Interface CSS in Rails Apps", 30)
        );

        ServicioCreacionCronograma servicioCronograma = new ServicioCreacionCronograma();

        int tiempoSession1 = 180;
        int tiempoSession2 = 240;

        List<Cronograma> salidaEsperada = servicioCronograma.organizarCronograma(test, tiempoSession1, tiempoSession2);

        Cronograma cronograma = salidaEsperada.get(0);

        assertEquals("* 09:00 - 10:00 ->  Writing Fast Tests Against Enterprise Rails ( 60 min )", cronograma.getTareas().get(0).toString());
        assertEquals("* 17:00 - 18:00 ->  NETWORKING EVENT ( 60 min )", cronograma.getTareas().get(cronograma.getTareas().size() - 1).toString());
    }
}

