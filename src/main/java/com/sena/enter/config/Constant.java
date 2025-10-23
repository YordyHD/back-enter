package com.sena.enter.config;

public class Constant {
        // Evita que se pueda instanciar la clase
    private Constant() {
    }

    /**
     * Expresión regular para validar logins (nombres de usuario).
     * Permite letras, números, puntos, guiones, guiones bajos y arrobas.
     * No permite espacios ni caracteres especiales.
     */
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

    /**
     * Nombre del usuario administrador por defecto.
     */
    public static final String SYSTEM = "system";

    /**
     * Zona horaria por defecto.
     */
    public static final String DEFAULT_LANGUAGE = "es";

}

