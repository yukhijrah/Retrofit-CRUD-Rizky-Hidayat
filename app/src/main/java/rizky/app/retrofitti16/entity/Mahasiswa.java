package rizky.app.retrofitti16.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "mahasiswa")
public class Mahasiswa implements Serializable{

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String nim;

    public String getName() {
        return name;
    }

    public String getNim() {
        return nim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }
}
