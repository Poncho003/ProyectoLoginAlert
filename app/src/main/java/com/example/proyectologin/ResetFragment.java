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
 * Use the {@link ResetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResetFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private UserManager userManager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ResetFragment() {
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
    public static ResetFragment newInstance(String param1, String param2) {
        ResetFragment fragment = new ResetFragment();
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

        // Referencias a los EditText del layout
        EditText user = view.findViewById(R.id.etxtUsernameReset);
        EditText oldPasswordEditText = view.findViewById(R.id.etxtPasswordReset);
        EditText newPasswordEditText = view.findViewById(R.id.etxtNewPasswordReset);
        Button resetButton = view.findViewById(R.id.btnRegisterReset);

        // Al hacer clic en el botón de Reset
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user1 = user.getText().toString();
                String oldPassword = oldPasswordEditText.getText().toString();
                String newPassword = newPasswordEditText.getText().toString();

                // Validar que los campos no estén vacíos
                if (user1.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty()) {
                    Toast.makeText(getActivity(), "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Intentar actualizar la contraseña
                userManager = UserManager.getInstance();  // Usar la instancia Singleton de UserManager
                if (userManager.updatePassword(user1, oldPassword, newPassword)) {
                    Toast.makeText(getActivity(), "Contraseña actualizada correctamente.", Toast.LENGTH_SHORT).show();

                    // Limpiar los campos
                    user.setText("");
                    oldPasswordEditText.setText("");
                    newPasswordEditText.setText("");

                    // Volver al LoginFragment
                    final NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.loginFragment);

                } else {
                    Toast.makeText(getActivity(), "Correo o contraseña incorrectos.", Toast.LENGTH_SHORT).show();
                }
            }
        }); // Cierre de setOnClickListener
    } // Cierre de onViewCreated

}