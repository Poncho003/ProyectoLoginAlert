package com.example.proyectologin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StartFragment newInstance(String param1, String param2) {
        StartFragment fragment = new StartFragment();
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
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Asignación de botones y texto desde el layout a variables de código
        Button btn1 = view.findViewById(R.id.btnLogin);     // Botón de inicio de sesión
        Button btn2 = view.findViewById(R.id.btnRegister);  // Botón de registro
        TextView forgotPass = view.findViewById(R.id.tvGuest);  // Texto para el inicio de sesión como invitado

        // Listener para el botón de inicio de sesión
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegación al fragmento de inicio de sesión
                Navigation.findNavController(v).navigate(R.id.loginFragment);
            }
        });

        // Listener para el botón de registro
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegación al fragmento de registro
                Navigation.findNavController(v).navigate(R.id.registerFragment);
            }
        });

        // Listener para el texto de inicio de sesión como invitado
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra un mensaje temporal (Toast) indicando que se ha iniciado sesión como invitado
                Toast.makeText(getActivity(), "Inicio de sesión Invitado", Toast.LENGTH_SHORT).show();

                // Inicia una nueva actividad (HomeActivity) al hacer clic en el texto de inicio de sesión como invitado
                Intent intent = new Intent(getActivity(), HomeActivity.class);  // Corregido: Usar getActivity() como contexto
                startActivity(intent);
            }
        });
    }

}