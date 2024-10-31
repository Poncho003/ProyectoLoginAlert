package com.example.proyectologin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private UserManager userManager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reset, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener la instancia de UserManager usando el Singleton
        userManager = UserManager.getInstance();

        // Referencias a los campos de texto y el botón de registro
        EditText user = view.findViewById(R.id.etxtUsernameRegis);
        EditText gmailText = view.findViewById(R.id.etxtGmailRegis);
        EditText passwordEditText = view.findViewById(R.id.etxtPasswordRegis);
        EditText rePasswordEditText = view.findViewById(R.id.etxtRePasswordRegis);
        Button registerButton = view.findViewById(R.id.btnRegisterRegis);

        // Evento del botón de registro
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString();
                String password = passwordEditText.getText().toString();
                String rePassword = rePasswordEditText.getText().toString();

                // Validar que los campos no estén vacíos
                if (username.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                    Toast.makeText(getActivity(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar que las contraseñas coincidan
                if (!password.equals(rePassword)) {
                    Toast.makeText(getActivity(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Añadir el nuevo usuario usando el UserManager
                if (userManager.addUser(username, password)) {
                    Toast.makeText(getActivity(), "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();

                    // Limpiar los campos de texto
                    user.setText("");
                    gmailText.setText("");
                    passwordEditText.setText("");
                    rePasswordEditText.setText("");

                    // Volver al LoginFragment
                    final NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.loginFragment);

                } else {
                    Toast.makeText(getActivity(), "Este usuario ya existe", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
