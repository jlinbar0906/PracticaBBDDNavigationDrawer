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
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.practicabbddnavigationdrawer.Alumno;
import com.example.practicabbddnavigationdrawer.R;
import com.example.practicabbddnavigationdrawer.databinding.FragmentModificarBinding;
import com.example.practicabbddnavigationdrawer.db.DataBaseHelper;


public class ModificarFragment extends Fragment {

    private FragmentModificarBinding binding;
    private DataBaseHelper dataBaseHelper;

    public ModificarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentModificarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] opciones= {"Hombre","Mujer"};
        ArrayAdapter<String> adapter= new ArrayAdapter<>(binding.getRoot().getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.addAll(opciones);
        binding.spinner.setAdapter(adapter);
        dataBaseHelper= new DataBaseHelper(getContext());

        binding.buttonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dni= binding.editTextDNI.getText().toString();
                String nombre= binding.editTextNombre.getText().toString();
                String apellidos=binding.editTextApellidos.getText().toString();
                String sexo= binding.spinner.getSelectedItem().toString();

                Alumno alumno= new Alumno(dni,nombre,apellidos,sexo);

                int filas=dataBaseHelper.actualizarAlumnos(alumno);

                if (filas>0){
                    Toast.makeText(getContext(),"Alumnos modificados",Toast.LENGTH_LONG).show();
                }else if (filas==0){
                    Toast.makeText(getContext(),"Alumnos no modificados",Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController= Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.action_matricularFragment_to_nav_home);
            }
        });
    }
}