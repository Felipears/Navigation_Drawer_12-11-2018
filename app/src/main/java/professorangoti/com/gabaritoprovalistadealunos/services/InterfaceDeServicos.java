package professorangoti.com.gabaritoprovalistadealunos.services;


import java.util.List;

import professorangoti.com.gabaritoprovalistadealunos.model.Aluno;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface InterfaceDeServicos {
    @Headers("Content-Type: application/json; charset=utf-8")
    @GET("/lista_de_alunos")
    Call<List<Aluno>> webserviceNotasDeAlunos();

}

