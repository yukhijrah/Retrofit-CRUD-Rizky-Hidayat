package rizky.app.retrofitti16.Network;


import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rizky.app.retrofitti16.entity.DaftarMahasiswa;
import rizky.app.retrofitti16.entity.Mahasiswa;


public interface Routes {


    @GET("dev/list_mahasiswa")
    Call<DaftarMahasiswa> getMahasiswa();

    @POST("dev/insert_mahasiswa")
    @FormUrlEncoded
    Call<Mahasiswa> postMahasiswa(
            @Field("name") String name,
            @Field("nim") String nim
    );

    @DELETE("mahasiswatest/{mhsId}")
    Call<Mahasiswa> deleteMahasiswa(
            @Path("mhsId") String mhsId
    );

    @PUT("mahasiswatest/{mhsId}")
    @FormUrlEncoded
    Call<Mahasiswa> updateMahasiswa(
            @Path("mhsId") String mhsId,
            @Field("name") String name,
            @Field("nim") String nim
    );
}
