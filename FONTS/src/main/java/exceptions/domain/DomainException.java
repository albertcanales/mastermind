package exceptions.domain;

import exceptions.GeneralException;

/**
 * Excepció de la qual hereten la resta d'excepcions procedents de la capa de domini
 * @author Albert Canales Ros
 */
@SuppressWarnings("WeakerAccess")
public class DomainException extends GeneralException {

    /**
     * Constructor de la excepció
     * @param str Missatge que es mostrarà juntament amb l'excepció
     */
    DomainException(String str) {
        super(str);
    }

}
