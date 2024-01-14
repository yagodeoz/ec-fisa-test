package ec.fisa.aplication;

import ec.fisa.aplication.core.domain.Cronograma;
import ec.fisa.aplication.core.dtos.TareaDto;
import ec.fisa.aplication.core.services.ServicioCreacionCronograma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private ServicioCreacionCronograma servicioCronogramna;

    @Value("${app.tiempominutos.sesion1}")
    private Integer tiempoSession1;

    @Value("${app.tiempominutos.sesion2}")
    private Integer tiempoSession2;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<TareaDto> test = List.of(new TareaDto("Writing Fast Tests Against Enterprise Rails", 60),
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
                new TareaDto("User Interface CSS in Rails Apps", 30));

        List<Cronograma> cronogramas = servicioCronogramna.organizarCronograma(test, tiempoSession1, tiempoSession2);

        cronogramas.forEach(c -> {
            c.getTareas().forEach( t -> {
                System.out.println(t);
            });
        });
    }
}