package com.samuca.contato.crud.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.samuca.contato.crud.R;

public class WebViewFecebookActivity extends AppCompatActivity {
   // private ProgressBar mProgresseBarFecebook;
    private WebView mWebViewFecebook;
    private ImageView mImegeviewVoltaFecebook;
    private String fecebookSelecionado ;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_fecebook);

       // mProgresseBarFecebook = (ProgressBar) findViewById(R.id.progressebarWebFecebook);
        mWebViewFecebook = (WebView) findViewById(R.id.webViewFecebook);
        mImegeviewVoltaFecebook = (ImageView) findViewById(R.id.img_voltarWebFecebook);
        mImegeviewVoltaFecebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

       progressDialog = new ProgressDialog(WebViewFecebookActivity.this);
       progressDialog.setMax(100);
       progressDialog.setMessage("Carregando dados...");
       progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
       progressDialog.setProgress(0);
       progressDialog.setCancelable(false);

        //recebendo a url fecebook
        fecebookSelecionado = getIntent().getStringExtra("uriFecebook");
          webViewFecebook(fecebookSelecionado);


    }
    // metado para navegar dentro da webview
    private void webViewFecebook( String fecebookSelecionado) {

        mWebViewFecebook.getSettings().setJavaScriptEnabled(true);// permite q algumas paginas funcione bem
        mWebViewFecebook.getSettings().setBuiltInZoomControls(true);// permite q o zoom das paginas fica reponsivo
        mWebViewFecebook.loadUrl(fecebookSelecionado);
        mWebViewFecebook.setWebViewClient(new WebViewClient(){
            public  boolean shouldOverriceUrlLoading(WebView view, String url){


                return false;//permite q a pagina se refresca

            }
        });


        mWebViewFecebook.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
               progressDialog.setProgress(newProgress);
                if (newProgress == 100){
                    progressDialog.dismiss();
                  //  mProgresseBarFecebook.setVisibility(View.INVISIBLE);

                }else {
                    progressDialog.show();
                  //  mProgresseBarFecebook.setVisibility(View.VISIBLE);
                }
            }
        });
        }
    }





