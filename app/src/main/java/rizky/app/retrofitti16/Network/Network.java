package rizky.app.retrofitti16.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class Network {

    public static Retrofit request() {
        //instance interceptor dengan cara
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        //setiap ada request ke network, kita monitoring body nya, dengan cara
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //bikin client agar bisa menggunakan interceptor
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        return new Retrofit.Builder()
                //ini main url dari web services yang tersedia
                .baseUrl("http://35.186.145.167:1337/")
                //tambahkan client okhttp
                .client(client)
                //ini melakukan konversi dari json string ke java object
                .addConverterFactory(GsonConverterFactory.create())
                //build it!
                .build();
    }

}
