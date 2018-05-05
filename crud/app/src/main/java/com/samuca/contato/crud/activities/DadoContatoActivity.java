package com.samuca.contato.crud.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.samuca.contato.crud.R;
import com.squareup.picasso.Picasso;

public class DadoContatoActivity extends AppCompatActivity {

    private TextView mNome, mEmail, mTelefone;
    private EditText mBuscar;
    private ImageView imFoto;
    private ImageView mImgVoltar, mImgEditar, mImgExcluir;
    private ListView mListaContato;
    private String HOST = "http://177.128.81.242:888/contatos";

    // AlertDialog
    private AlertDialog.Builder build;
    private AlertDialog alert;
    private Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dado_contatoctivity);


        mImgVoltar = (ImageView) findViewById(R.id.img_voltar);
        mImgEditar = (ImageView) findViewById(R.id.img_editar);
        mImgExcluir = (ImageView) findViewById(R.id.img_excluir);
        mListaContato = (ListView) findViewById(R.id.listaContato);
        mBuscar = (EditText) findViewById(R.id.edtBusacar);
        imFoto = (ImageView) findViewById(R.id.imgDadoContato);
        mNome = (TextView) findViewById(R.id.edtNome);
        mEmail = (TextView) findViewById(R.id.edtEmail);
        mTelefone = (TextView) findViewById(R.id.edtTelefone);

        // recebendo dados pela intent
        it = getIntent();
        String fotoRecebida = it.getStringExtra("foto");
        String nome = it.getStringExtra("nome");
        String email = it.getStringExtra("email");
        String telefone = it.getStringExtra("telefone");

        Picasso.get().load(fotoRecebida).into(imFoto);
        mNome.setText(nome);
        mEmail.setText(email);
        mTelefone.setText(telefone);


        mNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DadoContatoActivity.this, ListaLojasActivity.class);
                startActivity(intent);
            }
        });

        // função voltar tela anterior
        mImgVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //função editar contato
        mImgEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(getBaseContext(), "Editar", Toast.LENGTH_LONG).show();
                //   editarContato();
                View alertView = view.inflate(DadoContatoActivity.this, R.layout.alert_dialog_editar_listview, null);

                String nome = it.getStringExtra("nome");
                String email = it.getStringExtra("email");
                String telefone = it.getStringExtra("telefone");


                final EditText editMateria = alertView.findViewById(R.id.editMateria);
                final EditText editNotaTrabalho = alertView.findViewById(R.id.editNotaTrabalho);
                final EditText editNotaProva = alertView.findViewById(R.id.editNotaProva);


                //  mId.setText(String.valueOf(id));
                editMateria.setText(nome);
                editNotaTrabalho.setText(email);
                editNotaProva.setText(telefone);

                AlertDialog.Builder alertDalog = new AlertDialog.Builder(DadoContatoActivity.this);
                alertDalog.setIcon(R.drawable.ic_editar);
                alertDalog.setTitle("Editar Contato");
                alertDalog.setMessage("Deseja mesmo editar !");

                alertDalog.setView(alertView);


                alertDalog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //   editarContato();
                    }
                });

                alertDalog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();

                    }
                });

                alertDalog.show();
            }
        });
        // função abrir aplicativo email
        mEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("Seleciona um  email", "");
                String[] TO = {mEmail.getText().toString()};
                String[] CC = {""};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:" + TO));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Assunto");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Envia email");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Seleciona um  email..."));
                    finish();
                    //  Log.i("Finished sending email...", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(DadoContatoActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }

        });
        //fazer ligação para numero de celular
        mTelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeroTel = mTelefone.getText().toString().trim();
                Intent intentcall = new Intent();
                intentcall.setAction(Intent.ACTION_DIAL);
                intentcall.setData(Uri.parse("tel:" + numeroTel));
                startActivity(intentcall);

            }

        });

        // função excluir contato
        mImgExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(getBaseContext(), "Excluir", Toast.LENGTH_LONG).show();
                //  excluirContato();

                AlertDialog.Builder alertDalog = new AlertDialog.Builder(DadoContatoActivity.this);
                alertDalog.setIcon(R.drawable.ic_excluir);
                alertDalog.setTitle("Excluir Contato");
                alertDalog.setMessage("Deseja mesmo Excluir !");

                alertDalog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alertDalog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();

                    }
                });

                alertDalog.show();
            }

        });


    }


}
    /*

    public void editarContato() {
        String nome = it.getStringExtra("nome");
        String email = it.getStringExtra("email");
        String telefone = it.getStringExtra("telefone");

        String url = HOST + "/update.php";
        Ion.with(DadoContatoActivity.this)
                .load(url)
                .setBodyParameter("nome", nome)
                .setBodyParameter("telefone", telefone)
                .setBodyParameter("email", email)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        if (result.get("UPDADE").getAsString().equals("OK")) {

                            //    int idRetornado = Integer.parseInt(result.get("ID").getAsString());
                            limparCampos();

                            Toast.makeText(DadoContatoActivity.this, "Atualizado com sucesso ! ", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(DadoContatoActivity.this, "Erro ao efetuar atualização ! ", Toast.LENGTH_LONG).show();
                        }
                    }
                });



    }

    public void limparCampos() {
        mNome.setText("");
        mEmail.setText("");
        mTelefone.setText("");
    }

} */

/*
   public  void excluirContato(){
       final Contato contato = new Contato();

       //  String id = String.valueOf(it.getStringExtra("id"));
       String nome = it.getStringExtra("nome");
       String email = it.getStringExtra("email");
       String telefone = it.getStringExtra("telefone");

       //  mId.setText(String.valueOf(id));
       mNome.setText(nome);
       mEmail.setText(email);
       mTelefone.setText(telefone);

      final String id = mId.getText().toString();
       if (id.isEmpty()){
           Toast.makeText(DadoContatoActivity.this, "Nenhum contato selecionado ! ", Toast.LENGTH_LONG).show();
       }else{
           String url = HOST + "/delete.php";


           Ion.with(DadoContatoActivity.this)
                   .load(url)
                   .setBodyParameter("id", id)
                   .asJsonObject()
                   .setCallback(new FutureCallback<JsonObject>() {
                       @Override
                       public void onCompleted(Exception e, JsonObject result) {
                           // do stuff with the result or error
                           if (result.get("DELETE").getAsString().equals("OK")) {


                              lista.remove(it);
                               listaBuscar.remove(it);
                               contatosAdapter.notifyDataSetChanged();

                             //  limparCampos();
                               Toast.makeText(DadoContatoActivity.this, "Excluido   com sucesso ", Toast.LENGTH_LONG).show();

                           } else {
                               Toast.makeText(DadoContatoActivity.this, "Erro ao excluir ! ", Toast.LENGTH_LONG).show();
                           }
                       }
                   });
       }


   }
   */
