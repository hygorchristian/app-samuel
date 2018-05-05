package com.samuca.contato.crud.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.samuca.contato.crud.models.Cidade;
import com.samuca.contato.crud.R;
import com.samuca.contato.crud.adapters.AdapterCidade;

import java.util.ArrayList;
import java.util.List;

public class TelaAberturaActivity extends AppCompatActivity {
    private ListView mLisataCidade;
    private Button mBtnEntra;
    private String itemSelecionadoCidade;
    private String HOST = "http://177.128.81.242:888/contatos/read_cidade.php";
    //private String HOST = "http://10.0.0.6/contatos/read_cidade.php?";
    List<Cidade> listaCidade;
    AdapterCidade adapterCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_abertura);

        mLisataCidade = (ListView) findViewById(R.id.listaCidades);
        listaCidade = new ArrayList<Cidade>();
        adapterCidade = new AdapterCidade(TelaAberturaActivity.this, listaCidade);
        mLisataCidade.setAdapter(adapterCidade);

        carregarSpinerCidades();
        mLisataCidade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Cidade cidade = (Cidade) adapterView.getAdapter().getItem(i);

                // enviando dados pela intent para outra tela
                Intent intent = new Intent(TelaAberturaActivity.this, MainActivity.class);
                intent.putExtra("cidade", cidade);
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(TelaAberturaActivity.this, "Poblema na conexão...", Toast.LENGTH_LONG).show();
                    // finish();
                }
            }
        });
    }

    public void carregarSpinerCidades() {

        // mLisataCidade.setVisibility(View.VISIBLE);

        String url = HOST;
        Log.d("RECUPERADO", url);
        if (!url.isEmpty()) {

            Ion.with(getBaseContext()).load(url).asJsonArray().setCallback(new FutureCallback<JsonArray>() {

                @Override
                public void onCompleted(Exception e, JsonArray result) {
                    try {

                        for (int i = 0; i < result.size(); i++) {
                            JsonObject object = result.get(i).getAsJsonObject();
                            Cidade cidade = new Cidade();
                            cidade.setId(object.get("id").getAsInt());
                            cidade.setNomeCidade(object.get("nomeCidade").getAsString());

                            listaCidade.add(cidade);

                        }
                        adapterCidade.notifyDataSetChanged();
                        //   mLisataCidade.setVisibility(View.INVISIBLE);

                    } catch (Exception e2) {
                        e2.printStackTrace();
                        Toast.makeText(TelaAberturaActivity.this, "Servidor temporariamente fora do ar... !", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            });
        } else {
            Toast.makeText(TelaAberturaActivity.this, "Não há dados para carregar no momento... !", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
