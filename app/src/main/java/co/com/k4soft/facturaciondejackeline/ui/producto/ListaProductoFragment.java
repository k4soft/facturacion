package co.com.k4soft.facturaciondejackeline.ui.producto;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.k4soft.facturaciondejackeline.R;
import co.com.k4soft.facturaciondejackeline.entity.Producto;
import co.com.k4soft.facturaciondejackeline.persistence.database.DataBase;
import co.com.k4soft.facturaciondejackeline.view.productos.RegistroProductoActivity;

public class ListaProductoFragment extends Fragment {

    @BindView(R.id.btnCrearProducto)
    public FloatingActionButton btnCrearProducto;
    @BindView(R.id.listviewProductos)
    public ListView listviewProductos;
    private List<Producto> productos;
    private ArrayAdapter<String> arrayAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lista_producto, container, false);
        ButterKnife.bind(this, root);
        onClickBtnCrearProducto();
        cargarProductos();
        return root;
    }

    private void cargarProductos() {

        productos = DataBase.getMainThreadDB(getActivity()).getProductoDAO().listAll();
        if (productos.isEmpty()) {
            Toast.makeText(getActivity(), R.string.empty, Toast.LENGTH_SHORT).show();
        } else {
            String[] productoArray = new String[productos.size()];
            for (int i = 0; i < productos.size(); i++) {
                productoArray[i] = productos.get(i).getNombre().concat(" Precio:").concat(Double.toString(productos.get(i).getPrecio()));
            }
            arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, productoArray);
            listviewProductos.setAdapter(arrayAdapter);
        }
    }

    private void onClickBtnCrearProducto() {
        btnCrearProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegistroProductoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        cargarProductos();
    }


}