/*
Esta Activity demonstra a utilização de um ListView extendendo a classe Activity.
Desta forma o id da ListView pode ser qualquer id que você quiser nomear.
No arquivo padrao.xml temos a definição do id assim:

<ListView
        android:id="@+id/listview_listadealunos"

 */

package professorangoti.com.gabaritoprovalistadealunos;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.widget.ListView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import professorangoti.com.gabaritoprovalistadealunos.model.Aluno;
import professorangoti.com.gabaritoprovalistadealunos.services.InterfaceDeServicos;
import professorangoti.com.gabaritoprovalistadealunos.services.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPadrao extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    ListView lista;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.padrao);
        dl = (DrawerLayout) findViewById(R.id.padrao);
        t = new ActionBarDrawerToggle(this, dl, R.string.abrir, R.string.fechar);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(this);
        lista = (ListView) findViewById(R.id.listview_listadealunos);
        imprimeLista();
    }

    // Responde a eventos do hamburger
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    // Responde a eventos do menu da gaveta de navegação
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.conta:
                Toast.makeText(ActivityPadrao.this, "Minha conta", Toast.LENGTH_SHORT).show();
                break;
            case R.id.config:
                Toast.makeText(ActivityPadrao.this, "Configuração", Toast.LENGTH_SHORT).show();
                break;
            case R.id.carrinho:
                Toast.makeText(ActivityPadrao.this, "Meu carrinho de compras", Toast.LENGTH_SHORT).show();
                break;
            default:
                return true;
        }
        return false;
    }

    private void imprimeLista() {

        InterfaceDeServicos services = RetrofitService.getServico();
        Call<List<Aluno>> call = services.webserviceNotasDeAlunos();

        call.enqueue(new Callback<List<Aluno>>() {
            @Override
            public void onResponse(Call<List<Aluno>> call, Response<List<Aluno>> response) {
                List<Aluno> listaAlunosNotas = response.body();
                lista.setAdapter(new ListaAdapter(ActivityPadrao.this, listaAlunosNotas));
            }

            @Override
            public void onFailure(Call<List<Aluno>> call, Throwable t) {
                Log.i("debug", t.getMessage());
            }
        });
    }
}
