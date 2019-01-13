package rizky.app.retrofitti16.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import rizky.app.retrofitti16.entity.Mahasiswa;


@Database(entities = {Mahasiswa.class}, version = 1, exportSchema = false)
public abstract class MahasiswaDatabase extends RoomDatabase {
    public abstract MahasiswaDao mahasiswaDao();
}
