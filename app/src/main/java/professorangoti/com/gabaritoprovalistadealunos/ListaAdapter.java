package professorangoti.com.gabaritoprovalistadealunos;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import professorangoti.com.gabaritoprovalistadealunos.model.Aluno;

class ListaAdapter extends BaseAdapter {

    private Context contexto;
    private List<Aluno> lista;

    public ListaAdapter(Context contexto, List<Aluno> lista) {
        this.contexto = contexto;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //obter o objeto da lista na posição position
        Aluno aluno = lista.get(position);
        //obter a referncia da view
        View linha = LayoutInflater.from(contexto).inflate(R.layout.layout_da_linha_do_listview, null);
        //preencher a linha
        ImageView imagem = (ImageView) linha.findViewById(R.id.imageViewCirculo);
        TextView nomeAluno = (TextView) linha.findViewById(R.id.textViewNome);
        TextView notaAluno = (TextView) linha.findViewById(R.id.textViewNota);

        if (aluno.getNota() < 6)
            imagem.setImageResource(R.drawable.vermelho);
        else
            imagem.setImageResource(R.drawable.azul);
        nomeAluno.setText(aluno.getNome());
        notaAluno.setText(aluno.getNota()+"");
        Log.i("nome: ", aluno.getNome());
        return linha;
    }
}
