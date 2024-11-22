package com.example.practicabbddnavigationdrawer.Fragmentos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.practicabbddnavigationdrawer.Alumno;
import com.example.practicabbddnavigationdrawer.R;
import com.example.practicabbddnavigationdrawer.databinding.FragmentBorrarBinding;
import com.example.practicabbddnavigationdrawer.db.DataBaseHelper;


public class BorrarFragment extends Fragment {

    private FragmentBorrarBinding binding;
    private DataBaseHelper dataBaseHelper;

    public BorrarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBorrarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataBaseHelper = new DataBaseHelper(getContext());

        binding.buttonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dni = binding.editTextDNI.getText().toString();
                Alumno alumno = new Alumno(dni);

                int filas = dataBaseHelper.borrarAlumnos(alumno);

                if (filas > 0) {
                    Toast.makeText(getContext(), "Alumnos eliminados", Toast.LENGTH_LONG).show();
                } else if (filas == 0) {
                    Toast.makeText(getContext(), "Alumnos no eliminados", Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.action_matricularFragment_to_nav_home);
            }
        });
    }
}