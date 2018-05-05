package com.samuca.contato.crud;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by samuca on 07/04/2018.
 */
//805 984 605
    // 4970
public class ContatosAdapter extends BaseAdapter {
    private String HOST = "http://177.128.81.242:888/contatos";
    private Context ctx;
    private List<Contato> lista;
    public ContatosAdapter (Context ctx2, List<Contato> lista2){
         ctx = ctx2;
         lista = lista2;

     }
    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Contato getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

         View v = null;
         if (view == null) {
             LayoutInflater inflater = ((Activity) ctx).getLayoutInflater();
             v = inflater.inflate(R.layout.item_lista, null);

         }else{
             v = view;

         }

         Contato contato = getItem(position);

        ImageView fotoContato = (ImageView)v.findViewById(R.id.imgContato);
        TextView itemnome = (TextView) v.findViewById(R.id.textVnome);
       // TextView itememail = (TextView) v.findViewById(R.id.textVemail);
      //  TextView itemtelefone = (TextView) v.findViewById(R.id.textVtelefone);

        String fotoRecebida = HOST + "/" + contato.getFotoContato();

        Picasso.get().load(fotoRecebida).into(fotoContato);
        itemnome.setText(contato.getNome());
      //  itememail.setText(contato.getEmail());
     //   itemtelefone.setText(contato.getTelefone());



        return v;
    }
}
