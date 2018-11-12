package professorangoti.com.gabaritoprovalistadealunos.services;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by angoti on 31/10/2017.
 */

public class RetrofitService {

    private String baseUrl = "http://angoti.atwebpages.com/";
    private InterfaceDeServicos api;
    private static RetrofitService instancia;

    private RetrofitService() {
        api = criaRetrofit().create(InterfaceDeServicos.class);
    }

    private Retrofit criaRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    public static InterfaceDeServicos getServico() {
        if (instancia == null)
            instancia = new RetrofitService();
        return instancia.api;
    }

}
