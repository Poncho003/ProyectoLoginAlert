package com.example.proyectologin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

public class LoginFragment extends Fragment {

    private UserManager userManager;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Instanciar UserManager para manejar la validación
        userManager = UserManager.getInstance();
        final NavController navController = Navigation.findNavController(view);

        // Referencias a los campos de texto y botones
        Button btn1 = view.findViewById(R.id.btnRegisterLogin);
        TextView forgotPass = view.findViewById(R.id.forgotPass);
        Button btn2 = view.findViewById(R.id.btnSiginLogin);
        EditText user = view.findViewById(R.id.etxtUsernamelogin);
        EditText passwordEditText = view.findViewById(R.id.etxtPasswordLogin);

        // Navegar al fragmento de registro
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.registerFragment);
            }
        });

        // Navegar al fragmento de "Olvidé mi contraseña"
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.resetFragment);
            }
        });

        // Manejo del evento de inicio de sesión
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user1 = user.getText().toString();
                String password = passwordEditText.getText().toString();

                // Validar credenciales usando UserManager
                if (userManager.validateLogin(user1, password)) {
                    // Mostrar el diálogo de éxito
                    int clave = 1;
                    showSuccessDialog(clave);
                    user.setText("");
                    passwordEditText.setText("");
                } else {
                    // Mostrar error si las credenciales son incorrectas
                    int clave=  2;
                    showSuccessDialog(clave);
                }
            }
        });
    }

    private void showSuccessDialog(int clave) {
        // Inflar el layout personalizado de éxito o denegación según el valor de clave
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout;

        // Seleccionar el layout en función de la clave
        if (clave == 1) {
            dialogLayout = inflater.inflate(R.layout.succes_dialog, null);  // Éxito
        } else {
            dialogLayout = inflater.inflate(R.layout.deny_dialog, null);  // Denegación
        }

        Button successDone = dialogLayout.findViewById(R.id.doneButton);

        // Crear el diálogo de alerta usando el layout seleccionado
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogLayout);
        final AlertDialog alertDialog = builder.create();

        // Configurar la acción del botón para cerrar el diálogo
        successDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                if (clave == 1) {
                    // Solo navegar a HomeActivity si es éxito (clave = 1)
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                }
            }
        });

        // Mostrar el diálogo y configurar el fondo transparente
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
        alertDialog.show();
    }

}
