package com.samuca.contato.crud.activities;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.samuca.contato.crud.models.ContatoLoja;
import com.samuca.contato.crud.R;
import com.squareup.picasso.Picasso;

public class DadoContatoLojaActivity extends AppCompatActivity {

    private LinearLayout mTelefoneLayout, mEmailLayout, mWhatsappLayout, mFecebookLayout;
    private ImageView mImgemVoltar, mImageDadosEmpresa;
    private TextView mNomeEmpresa, mTelefoneDadoEmpresa, mEmailDadoEmpresa, mWhatsappDadoEmpresa, mFecebookDadoEmpresa, mFcebookClic, mIntagramClic, mInstagramDadoEmpresa, mEnderecoDadoEmpresa, mDescricaoDadoEmpresa;
    private ContatoLoja contatoLoja;

    private String HOST = "http://177.128.81.242:888/contatos";

    // private   String uriFecebook = mFecebookDadoEmpresa.getText().toString().trim();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dado_contato_loja);

        mImgemVoltar = (ImageView) findViewById(R.id.imgDadoLojaVolta);

        mImageDadosEmpresa = (ImageView) findViewById(R.id.imageDadoEempresa);
        mNomeEmpresa = (TextView) findViewById(R.id.nomeDadoEmpresa);
        mTelefoneDadoEmpresa = (TextView) findViewById(R.id.telefoneDadoEmpresa);
        mEnderecoDadoEmpresa = (TextView) findViewById(R.id.enderecoDadoEmpresa);
        mWhatsappDadoEmpresa = (TextView) findViewById(R.id.whatsappDadoEmpresa);
        mEmailDadoEmpresa = (TextView) findViewById(R.id.emailDadoEmpresa);
        mFecebookDadoEmpresa = (TextView) findViewById(R.id.fecebookDadoEmpresa);
        mInstagramDadoEmpresa = (TextView) findViewById(R.id.instagramDadoEmpresa);
        mDescricaoDadoEmpresa = (TextView) findViewById(R.id.descricaoDadoEmpresa);
        mFcebookClic = (TextView) findViewById(R.id.fecebookEmpresa);
        mIntagramClic = (TextView) findViewById(R.id.instagramEmpresa);

        mEmailLayout = (LinearLayout) findViewById(R.id.layoutEmail);
        mFecebookLayout = (LinearLayout) findViewById(R.id.layoutFecebook);
        mWhatsappLayout = (LinearLayout) findViewById(R.id.layoutWhatsapp);
        mTelefoneLayout = (LinearLayout) findViewById(R.id.layoutTelefone);

        mImgemVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // recebendo dados pela intent
        try {
            contatoLoja = (ContatoLoja) getIntent().getSerializableExtra("ContatoLoja");
            //   Toast.makeText(DadoContatoLojaActivity.this,"item  selecionado :  !" +contatoLoja.getWhatsapp_loja(),Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            fileList();
            Toast.makeText(DadoContatoLojaActivity.this, "poblema na rede !", Toast.LENGTH_LONG).show();
        }
        //setando os dado do obijeto salvo no banco de dados mysqli
        String fotoRecebidaLoja = HOST + "/" + contatoLoja.getFoto_loja();
        contatoLoja.getId();
        Picasso.get().load(fotoRecebidaLoja).into(mImageDadosEmpresa);
        mNomeEmpresa.setText(contatoLoja.getNome_loja());
        mEmailDadoEmpresa.setText(contatoLoja.getEmail_loja());
        mTelefoneDadoEmpresa.setText(contatoLoja.getTelefone_loja());
        mEnderecoDadoEmpresa.setText(contatoLoja.getEndereco_loja());
        mWhatsappDadoEmpresa.setText(contatoLoja.getWhatsapp_loja());
        mFecebookDadoEmpresa.setText(contatoLoja.getFecebook_loja());
        mInstagramDadoEmpresa.setText(contatoLoja.getInstagran_loja());
        mDescricaoDadoEmpresa.setText(contatoLoja.getDescricao_loja());

        //  mFecebookDadoEmpresa.setText(fecebook);


        //fazer ligação
        mTelefoneDadoEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numeroTel = mTelefoneDadoEmpresa.getText().toString().trim();
                Intent intentcall = new Intent();
                intentcall.setAction(Intent.ACTION_DIAL);
                intentcall.setData(Uri.parse("tel:" + numeroTel));
                try {
                    startActivity(intentcall);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(DadoContatoLojaActivity.this, "Verifique telefone...", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        //função de abrir whatsapp
        mWhatsappDadoEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numeroWhatdapp = mWhatsappDadoEmpresa.getText().toString().trim();
                Intent _intencion = new Intent("android.intent.action.MAIN");
                _intencion.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                _intencion.putExtra("jid", PhoneNumberUtils.stripSeparators("+55" + numeroWhatdapp) + "@s.whatsapp.net");
                try {
                    startActivity(_intencion);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(DadoContatoLojaActivity.this, "Verifique o whatsapp...", Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });

        // função abrir aplicativo email
        mEmailDadoEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("Send email", "");
                String[] TO = {mEmailDadoEmpresa.getText().toString()};
                String[] CC = {""};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:" + TO));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Assunto");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Envia email");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Seleciona um  mail..."));
                    finish();
                    //  Log.i("Finished sending email...", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(DadoContatoLojaActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        //abrindo rota de endereço da empresa selecinada
        mEnderecoDadoEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapa();
            }
        });

        //metado de chamar fecebook
        mFcebookClic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String uriFecebook = mFecebookDadoEmpresa.getText().toString().trim();


                Intent irfecebook = new Intent(DadoContatoLojaActivity.this, WebViewFecebookActivity.class);
                try {
                    irfecebook.putExtra("uriFecebook", uriFecebook);
                    startActivity(irfecebook);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(DadoContatoLojaActivity.this, "Fecabook temporariamente fora do ar...", Toast.LENGTH_LONG).show();
                    finish();
                }


            }
        });

        //abre url do instagram
        mIntagramClic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlinstagram = mInstagramDadoEmpresa.getText().toString().trim();
                //String urlinstagram = "https://www.instagram.com/joyceelorhana/";
                Uri uri = Uri.parse(urlinstagram);
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(urlinstagram)));
                }

            }
        });

        FloatingActionButton compartilharLooja = (FloatingActionButton) findViewById(R.id.floatCmpartilhar);
        compartilharLooja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compartilharLoja();
            }
        });
    }


    public void mapa() {

        String urlmapaEmpresa = mDescricaoDadoEmpresa.getText().toString().trim();
        Uri urlmapa = Uri.parse(urlmapaEmpresa);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, urlmapa);
        mapIntent.setPackage("com.google.android.apps.maps");
        try {
            startActivity(mapIntent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(DadoContatoLojaActivity.this, "Verifique o google maps...", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    //compatilha os dados  de comnicação da empresa e o link do aplicativo para danwload.
    public void compartilharLoja() {

        String linkAplicativo = " https://play.google.com/store/apps/details?id=samu.com.construtora";
        String nome = mNomeEmpresa.getText().toString();
        String telefone = mTelefoneDadoEmpresa.getText().toString();
        String whatsapp = mWhatsappDadoEmpresa.getText().toString();
        String email = mEmailDadoEmpresa.getText().toString();
        String fecebook = mFecebookDadoEmpresa.getText().toString();
        String instagram = "https://www.instagram.com/joyceelorhana/";
        //mDescricaoDadoEmpresa.getText().toString();
        String mapa = "https://goo.gl/maps/ZGgZHmZRXZD2";
        //  mEnderecoDadoEmpresa.getText().toString();

        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        share.putExtra(Intent.EXTRA_SUBJECT,
                " Dados de Contato da Empresa");
        share.putExtra(Intent.EXTRA_TEXT,
                " Baixe o Aplicativo Guia Comercial Vale do Aço :  " + linkAplicativo + "  - " + " Nome da Empresa :  " + nome + "  - " + " Telefone  :  " + telefone + "  - " + " Whatsapp  :   " + whatsapp + "  - " + " Email  :  " + email + "  - " + " Facebook  : " + fecebook + "  - " + " Instagram  : " + instagram + "  - " + "  Rota de Endereço : " + mapa);

        try {
            startActivity(Intent.createChooser(share, "Compartilhar"));

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(DadoContatoLojaActivity.this, "Poblema na conexão...", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}