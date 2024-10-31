package com.example.proyectologin;

import java.util.HashMap;

public class UserManager {

    // Instancia única de UserManager
    private static UserManager instance;
    // HashMap para almacenar usuarios y contraseñas
    private HashMap<String, String> users;

    // Constructor privado para evitar que se creen nuevas instancias
    private UserManager() {
        users = new HashMap<>();
        // Usuario de prueba
        users.put("admin", "admin");
    }

    // Método para obtener la instancia única de UserManager
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    // Método para validar las credenciales de inicio de sesión
    public boolean validateLogin(String user, String password) {
        return users.containsKey(user) && users.get(user).equals(password);
    }

    // Método para añadir un nuevo usuario
    public boolean addUser(String user, String password) {
        if (users.containsKey(user)) {
            // Si el usuario ya existe, retornamos falso
            return false;
        }
        // Añadimos el nuevo usuario
        users.put(user, password);
        return true;
    }

    // Método para modificar la contraseña de un usuario existente
    public boolean updatePassword(String user, String oldPassword, String newPassword) {
        if (users.containsKey(user) && users.get(user).equals(oldPassword)) {
            users.put(user, newPassword);
            return true;
        }
        return false;
    }
}
