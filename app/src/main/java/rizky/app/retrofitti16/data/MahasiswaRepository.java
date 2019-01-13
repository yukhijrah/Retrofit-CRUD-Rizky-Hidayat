package rizky.app.retrofitti16.data;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;


import rizky.app.retrofitti16.entity.Mahasiswa;


public class MahasiswaRepository {

    private final static String DB_NAME = "datalokal";
    private MahasiswaDatabase mahasiswaDatabase;

    public MahasiswaRepository(Context context) {
        mahasiswaDatabase = Room.databaseBuilder(
                context,
                MahasiswaDatabase.class,
                DB_NAME
        ).build();
    }

    public void insertMahasiswa(final String name, final String nim) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setName(name);
                mahasiswa.setNim(nim);
                mahasiswaDatabase.mahasiswaDao().insert(mahasiswa);
                return null;
            }
        }.execute();
    }

    public List<Mahasiswa> getMahasiswas() {
        return mahasiswaDatabase.mahasiswaDao().getMahasiswa();
    }

}
