package exceptions.persistance;

import exceptions.GeneralException;

/**
 * Excepció de la qual hereten la resta d'excepcions procedents de la capa de persistència
 * @author Albert Canales Ros
 */
public class PersistanceException extends GeneralException {

    /**
     * Constructor de la excepció
     * @param str Missatge que es mostrarà juntament amb l'excepció
     */
    public PersistanceException(String str) {
        super(str);
    }
}
