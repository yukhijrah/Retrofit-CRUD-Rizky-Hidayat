package rizky.app.retrofitti16;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rizky.app.retrofitti16.Network.Network;
import rizky.app.retrofitti16.Network.Routes;
import rizky.app.retrofitti16.entity.Mahasiswa;
import rizky.app.retrofitti16.util.Consts;


public class DetailMahasiswaActivity extends AppCompatActivity {

    private EditText edtName, edtNim;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mahasiswa);
        //casting untuk semua view
        edtName = (EditText) findViewById(R.id.edt_name);
        edtNim = (EditText) findViewById(R.id.edt_nim);
        btnAdd = (Button) findViewById(R.id.btn_add);

        String action = getIntent().getStringExtra(Consts.KEY_ACTION_DETAIL);
        switch (action) {
            case Consts.INTENT_ADD:
                btnAdd.setText("TAMBAH MAHASISWA");
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = edtName.getText().toString();
                        String nim = edtNim.getText().toString();
                        if (!name.isEmpty() && !nim.isEmpty()) {
                            addNewMahasiswa(name, nim);
                        } else {
                            Toast.makeText(DetailMahasiswaActivity.this,
                            "Maaf nama dan nim tidak boleh kosong",
                                    Toast.LENGTH_SHORT).show();
                        
                        }
                    }
                });
                break;
            case Consts.INTENT_EDIT:
                final Mahasiswa mahasiswa = (Mahasiswa) getIntent().getSerializableExtra("mahasiswa");
                edtName.setText(mahasiswa.getName());
                edtNim.setText(mahasiswa.getNim());

                btnAdd.setText("UPDATE DATA");
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mahasiswa.setName(edtName.getText().toString());
                        mahasiswa.setNim(edtNim.getText().toString());
                        updateMahasiswa(mahasiswa);
                    }
                });
                break;
        }
    }

    private void updateMahasiswa(Mahasiswa mahasiswa) {
        Routes services = Network.request().create(Routes.class);

        String mahasiswaId = String.valueOf(mahasiswa.getId());
        String name = mahasiswa.getName();
        String nim = mahasiswa.getNim();

        services.updateMahasiswa(mahasiswaId, name, nim).enqueue(new Callback<Mahasiswa>() {
            @Override
            public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                if(response.isSuccessful()) {
                    Toast.makeText( DetailMahasiswaActivity.this,
                           "update berhasil",
                            Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    onErrorMahasiswa();
                }
            }

            @Override
            public void onFailure(Call<Mahasiswa> call, Throwable t) {
                onErrorMahasiswa();
            }
        });
    }

    private void addNewMahasiswa(String name, String nim) {
        Routes services = Network.request().create(Routes.class);

        //lalu lakukan post terhadap post pada mahasiswa baru terhadap API /add.php
        services.postMahasiswa(name, nim).enqueue(new Callback<Mahasiswa>() {
            @Override
            public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                if (response.isSuccessful()) {
                    finish();//kembalik ke aktifitas sebelumnya
                } else {
                    onErrorMahasiswa();
                }
            }

            @Override
            public void onFailure(Call<Mahasiswa> call, Throwable t)     {
                onErrorMahasiswa();
            }
        });

    }
    private void onErrorMahasiswa() {
        Toast.makeText(DetailMahasiswaActivity.this,
                "Maaf, terjadi kesalahan",
                Toast.LENGTH_SHORT).show();
    }
}
