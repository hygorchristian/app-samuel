package com.samuca.contato.crud.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.samuca.contato.crud.models.ContatoLoja;
import com.samuca.contato.crud.adapters.ContatoLojaAdapter;
import com.samuca.contato.crud.R;

import java.util.ArrayList;
import java.util.List;

public class ListaLojasActivity extends AppCompatActivity {
    private ProgressBar mProssegreBarListaLoja;
    private ImageView mImagemVoltar;
    private EditText mBuscarLoja;
    private ListView mListaContatoLoja;
 //   private String HOST = "http://177.128.81.242:888/contatos";
    private String HOST = "http://177.128.81.242:888/contatos/read_contato_loja.php?categoria=";

    ContatoLojaAdapter contatoLojaAdapter;
    List<ContatoLoja> listaloja;
    List<ContatoLoja> listaBuscar;//listabusca filtro

    private Intent it;
    String categoriaSeleconada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_lojas);
        mProssegreBarListaLoja = (ProgressBar)findViewById(R.id.progressebarListaLoja);
        mImagemVoltar = (ImageView)findViewById(R.id.img_voltarLitaLoja);
        mListaContatoLoja = (ListView) findViewById(R.id.listaContatoLoja);
        mBuscarLoja = (EditText) findViewById(R.id.edtBusacarListaEmpresa);

        listaloja = new ArrayList<ContatoLoja>();
        listaBuscar = new ArrayList<ContatoLoja>();//listabusca
        contatoLojaAdapter = new ContatoLojaAdapter(ListaLojasActivity.this, listaloja);
        mListaContatoLoja.setAdapter(contatoLojaAdapter);



        // recebendo dados pela intent
        it = getIntent();
        Bundle extras = it.getExtras();
        categoriaSeleconada = extras.getString("nome");
        Log.d("CATEGORIA SELECIONADA", categoriaSeleconada);
        listaContatoLoja("");

        mImagemVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


       //pegando click  de cada item da lista
        mListaContatoLoja.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ContatoLoja contatoLoja = (ContatoLoja) adapterView.getAdapter().getItem(i);

                ContatoLoja contatoLoja1 = new ContatoLoja();

                Intent passaDadoLoja = new Intent(ListaLojasActivity.this, DadoContatoLojaActivity.class);
                contatoLoja1.setId(contatoLoja.getId());

                contatoLoja1.setFoto_loja(contatoLoja.getFoto_loja());
                contatoLoja1.setNome_loja(contatoLoja.getNome_loja());
                contatoLoja1.setTelefone_loja(contatoLoja.getTelefone_loja());
                contatoLoja1.setEndereco_loja(contatoLoja.getEndereco_loja());
                contatoLoja1.setWhatsapp_loja(contatoLoja.getWhatsapp_loja());
                contatoLoja1.setFecebook_loja(contatoLoja.getFecebook_loja());
                contatoLoja1.setInstagran_loja(contatoLoja.getInstagran_loja());
                contatoLoja1.setEmail_loja(contatoLoja.getEmail_loja());
                contatoLoja1.setDescricao_loja(contatoLoja.getDescricao_loja());

                passaDadoLoja.putExtra("ContatoLoja",contatoLoja1);
                 try {
                startActivity(passaDadoLoja);
                 }catch (Exception e){
                     e.printStackTrace();
                     Toast.makeText(ListaLojasActivity.this,"Poblema na conexão...",Toast.LENGTH_LONG).show();
                     // finish();
                 }


            }
        });


        //consulta na lista
        mBuscarLoja.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                buscaLoja("" + charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    //metado de digita e pesquisa  dados busca na listview
    public void buscaLoja(String texto){
        listaloja.clear();
        for (int i = 0; i< listaBuscar.size();i++){
            if (listaBuscar.get(i).getNome_loja().toLowerCase().contains(texto.toLowerCase())){

                listaloja.add(listaBuscar.get(i));
            }
        }
        contatoLojaAdapter.notifyDataSetChanged();

    }


    //lista os dados na list view
    public void listaContatoLoja(final String str) {

        ///para testa conexão com internet
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){
        mProssegreBarListaLoja.setVisibility(View.VISIBLE);

            String url = HOST + categoriaSeleconada;
            Log.d("RECUPERADO", url);
            if ( ! url.isEmpty() ) {

            Ion.with(getBaseContext())
                    .load(url)
                    .asJsonArray()
                    .setCallback(new FutureCallback<JsonArray>() {
                        @Override
                        public void onCompleted(Exception e, JsonArray result) {

                            try {


                                for (int i = 0; i < result.size(); i++) {
                                    JsonObject object = result.get(i).getAsJsonObject();
                                    ContatoLoja contatoLoja = new ContatoLoja();
                                    contatoLoja.setId(object.get("id").getAsInt());
                                    contatoLoja.setNome_loja(object.get("nome").getAsString());
                                    contatoLoja.setEndereco_loja(object.get("endereco").getAsString());
                                    contatoLoja.setTelefone_loja(object.get("telefone").getAsString());
                                    contatoLoja.setEmail_loja(object.get("email").getAsString());
                                    contatoLoja.setWhatsapp_loja(object.get("whatsapp").getAsString());
                                    contatoLoja.setFecebook_loja(object.get("fecebook").getAsString());
                                    contatoLoja.setInstagran_loja(object.get("instagran").getAsString());
                                    contatoLoja.setDescricao_loja(object.get("descricao").getAsString());
                                    contatoLoja.setFoto_loja(object.get("foto").getAsString());
                                    listaloja.add(contatoLoja);
                                    listaBuscar.add(contatoLoja);

                                }
                                contatoLojaAdapter.notifyDataSetChanged();
                                mProssegreBarListaLoja.setVisibility(View.INVISIBLE);
                            } catch (Exception e1) {
                                e1.printStackTrace();
                                Toast.makeText(ListaLojasActivity.this, "Servidor temporariamente fora do ar... !", Toast.LENGTH_LONG).show();
                                finish();
                            }

                        }
                    });
            } else {
                Toast.makeText(ListaLojasActivity.this, "Não a dados para carregar no momento... !", Toast.LENGTH_LONG).show();
                finish();
            }

        }else{
            Toast.makeText(ListaLojasActivity.this,"Nenhuma conexão ativa !",Toast.LENGTH_LONG).show();
            //  finish();
        }
    }
}
