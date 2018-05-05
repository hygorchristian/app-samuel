package com.samuca.contato.crud;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgreListaCategoria;
    private EditText mBuscar;
    private TextView mCidadeSeleciondaBusca;
    private ListView mListaContato;
    private String HOST = "http://177.128.81.242:888/contatos/read.php?nome=";
    // private String HOST = "http://10.0.0.6/contatos/read.php?nome=";
    ContatosAdapter contatosAdapter;
    List<Contato> lista;
    List<Contato> listaBuscar;//listabusca
    private Intent it;
    String cidadeSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgreListaCategoria = (ProgressBar) findViewById(R.id.progressebarListaCategoria);
        mListaContato = (ListView) findViewById(R.id.listaContato);
        mBuscar = (EditText) findViewById(R.id.edtBusacar);
        mCidadeSeleciondaBusca = (TextView) findViewById(R.id.edtCidadeSeleciondaBusca);
        mCidadeSeleciondaBusca.requestFocus();

        lista = new ArrayList<Contato>();
        listaBuscar = new ArrayList<Contato>();//listabusca
        lista = new ArrayList<Contato>();//listabusca
        contatosAdapter = new ContatosAdapter(MainActivity.this, lista);
        mListaContato.setAdapter(contatosAdapter);

        // recebendo dados pela intent
        it = getIntent();
        Bundle extras = it.getExtras();
        cidadeSelecionada = extras.getString("nomeCidade");
        Log.d("CIDADE SELECIONADA E : ", cidadeSelecionada);
     //   Toast.makeText(MainActivity.this, cidadeSelecionada, Toast.LENGTH_LONG).show();

        listaContato();

      mCidadeSeleciondaBusca.setText(cidadeSelecionada);

        //consulta na lista
        mBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                busca("" + charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //pegando clic  de cada item da lista
        mListaContato.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contato contato = (Contato) adapterView.getAdapter().getItem(i);
                ImageView fotoContato = (ImageView) view.findViewById(R.id.imgContato);

                // enviando dados pela intent para outra tela
                Intent psaasContato = new Intent(MainActivity.this, ListaLojasActivity.class);
                String fotoRecebida = HOST + "/" + contato.getFotoContato();
                Picasso.get().load(fotoRecebida).into(fotoContato);
                psaasContato.putExtra("foto", fotoRecebida);
                psaasContato.putExtra("nome", lista.get(i).getNome());
                Log.d("CATEGORIA", lista.get(i).getNome());
                psaasContato.putExtra("email", lista.get(i).getEmail());
                psaasContato.putExtra("telefone", lista.get(i).getTelefone());
                try {
                    startActivity(psaasContato);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Poblema na conexão...", Toast.LENGTH_LONG).show();
                    // finish();
                }


            }
        });


    }

    //metado de digita e pesquisa  dados busca na listview
    public void busca(String texto) {
        lista.clear();
        for (int i = 0; i < listaBuscar.size(); i++) {
            if (listaBuscar.get(i).getNome().toLowerCase().contains(texto.toLowerCase())) {
                lista.add(listaBuscar.get(i));

            }
        }
        contatosAdapter.notifyDataSetChanged();

    }


    //lista os dados na list view
    public void listaContato() {

        ///para testa conexão com internet
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            mProgreListaCategoria.setVisibility(View.VISIBLE);

            String url = HOST;
            Log.d("RECUPERADO", url);
            if (!url.isEmpty()) {

                Ion.with(getBaseContext())
                        .load(url)
                        .asJsonArray()
                        .setCallback(new FutureCallback<JsonArray>() {
                            @Override
                            public void onCompleted(Exception e, JsonArray result) {

                                try {

                                    for (int i = 0; i < result.size(); i++) {
                                        JsonObject object = result.get(i).getAsJsonObject();
                                        Contato contato = new Contato();
                                        contato.setId(object.get("id").getAsInt());
                                        contato.setNome(object.get("nome").getAsString());
                                        contato.setEmail(object.get("email").getAsString());
                                        contato.setTelefone(object.get("telefone").getAsString());
                                        contato.setFotoContato(object.get("foto").getAsString());


                                        lista.add(contato);
                                        listaBuscar.add(contato);


                                    }
                                    contatosAdapter.notifyDataSetChanged();
                                    mProgreListaCategoria.setVisibility(View.INVISIBLE);


                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                    Toast.makeText(MainActivity.this, "Servidor temporariamente fora do ar... !", Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            }
                        });
            } else {
                Toast.makeText(MainActivity.this, "Não a dados para carregar no momento... !", Toast.LENGTH_LONG).show();
                finish();
            }


        } else {
            Toast.makeText(getApplicationContext(), "Nenhuma conexão ativa !", Toast.LENGTH_LONG).show();
        }
    }
}
