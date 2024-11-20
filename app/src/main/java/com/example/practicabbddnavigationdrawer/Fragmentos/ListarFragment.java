package com.example.practicabbddnavigationdrawer.Fragmentos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.practicabbddnavigationdrawer.Alumno;
import com.example.practicabbddnavigationdrawer.AlumnoAdapter;
import com.example.practicabbddnavigationdrawer.R;
import com.example.practicabbddnavigationdrawer.databinding.FragmentListarBinding;
import com.example.practicabbddnavigationdrawer.db.DataBaseManager;

import java.util.List;


public class ListarFragment extends Fragment {
    private FragmentListarBinding binding;
    private DataBaseManager dataBaseManager;

    public ListarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataBaseManager = new DataBaseManager(requireContext());

        List<Alumno> listaAlumnos = dataBaseManager.listarAlumnos();

        if (listaAlumnos.isEmpty()) {
            System.out.println("No hay datos en la base de datos.");
        } else {
            for (Alumno alumno : listaAlumnos) {
                System.out.println("Alumno: " + alumno.getNombre() + " " + alumno.getApellidos());
            }
        }

        AlumnoAdapter adapter = new AlumnoAdapter(requireContext(), R.layout.alumnos_item, listaAlumnos);
        binding.listView.setAdapter(adapter);
    }
}