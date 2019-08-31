package co.com.k4soft.facturaciondejackeline.persistence.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import co.com.k4soft.facturaciondejackeline.entity.Producto;
import co.com.k4soft.facturaciondejackeline.persistence.dao.ProductoDAO;
import co.com.k4soft.facturaciondejackeline.util.Parametro;

@Database(entities = {
        Producto.class

}, version = Parametro.DATABASE_VERSION, exportSchema = false)
public abstract class DataBase extends RoomDatabase {

    private static DataBase INSTANCE;

    public static DataBase getdDB(Context context) {
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, DataBase.class, Parametro.DATABASE_NAME).build();
        }
        return INSTANCE;
    }

    public static DataBase getMainThreadDB(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, DataBase.class, Parametro.DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }

    public abstract ProductoDAO getProductoDAO();

}

