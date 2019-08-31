package co.com.k4soft.facturaciondejackeline.view.productos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.k4soft.facturaciondejackeline.R;
import co.com.k4soft.facturaciondejackeline.entity.Producto;
import co.com.k4soft.facturaciondejackeline.persistence.database.DataBase;

public class ListaProductoActivity extends AppCompatActivity {

    @BindView(R.id.listviewProductos)
    public ListView listviewProductos;
    private List<Producto> productos;
    private ArrayAdapter<String> arrayAdapter;

    public void goToInsertar(View view) {
        Intent intent = new Intent(this,RegistroProductoActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_producto);
        ButterKnife.bind(this);
        cargarProductos();
    }

    private void cargarProductos() {

        productos = DataBase.getMainThreadDB(this).getProductoDAO().listAll();
        if (productos.isEmpty()) {
            Toast.makeText(this, R.string.empty, Toast.LENGTH_SHORT).show();
        } else {
            String[] productoArray = new String[productos.size()];
            for (int i = 0; i < productos.size(); i++) {
                productoArray[i] = productos.get(i).getNombre().concat(" Precio:").concat(Double.toString(productos.get(i).getPrecio()));
            }
            arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, productoArray);
            listviewProductos.setAdapter(arrayAdapter);
        }
    }
}
