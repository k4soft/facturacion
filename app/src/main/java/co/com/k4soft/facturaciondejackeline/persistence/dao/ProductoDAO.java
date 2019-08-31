package co.com.k4soft.facturaciondejackeline.persistence.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import co.com.k4soft.facturaciondejackeline.entity.Producto;
import co.com.k4soft.facturaciondejackeline.persistence.database.Table;

@Dao
public interface ProductoDAO {

    @Insert
    void insert(Producto producto);

    @Query("SELECT * FROM "+Table.PRODUCTO)
    List<Producto> listAll();

    @Update
    void update(Producto producto);
}
