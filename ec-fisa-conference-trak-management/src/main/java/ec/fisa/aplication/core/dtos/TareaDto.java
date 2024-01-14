package ec.fisa.aplication.core.dtos;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TareaDto {

        protected  String tema;
        protected Integer duracion;//minutos

        /**
         * Instantiates a new Charla.
         *
         * @param tema     the tema
         * @param duracion the duracion
         */
        public TareaDto(String tema, Integer duracion) {
            this.tema = tema;
            this.duracion = duracion;
        }
}


