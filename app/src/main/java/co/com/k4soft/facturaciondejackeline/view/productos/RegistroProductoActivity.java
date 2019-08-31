package co.com.k4soft.facturaciondejackeline.view.productos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.k4soft.facturaciondejackeline.R;
import co.com.k4soft.facturaciondejackeline.entity.Producto;
import co.com.k4soft.facturaciondejackeline.persistence.database.DataBase;

public class RegistroProductoActivity extends AppCompatActivity {

    @BindView(R.id.txtNombre)
    public EditText txtNombre;
    @BindView(R.id.txtPrecio)
    public EditText txtPrecio;

    private Producto producto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_producto);
        ButterKnife.bind(this);
        producto = new Producto();
    }

    public void guardar(View view) {
        String nombre = txtNombre.getText().toString();
        double precio = Double.parseDouble(txtPrecio.getText().toString());
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        Insertar insertar = new Insertar();
        insertar.execute();

    }

    private class Insertar extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            DataBase.getdDB(getApplicationContext()).getProductoDAO().insert(producto);
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(),R.string.success,Toast.LENGTH_SHORT).show();
            finish();

        }
    }

}
