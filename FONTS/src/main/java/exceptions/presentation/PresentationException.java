package exceptions.presentation;

import exceptions.GeneralException;

/**
 * Excepció de la qual hereten la resta d'excepcions procedents de la capa de presentació
 * @author Albert Canales Ros
 */
public class PresentationException extends GeneralException {

    /**
     * Constructor de la excepció
     * @param str Missatge que es mostrarà juntament amb l'excepció
     */
    PresentationException(String str) {
        super(str);
    }

}
