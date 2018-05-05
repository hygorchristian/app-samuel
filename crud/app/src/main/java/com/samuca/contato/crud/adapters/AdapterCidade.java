package com.samuca.contato.crud.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.samuca.contato.crud.models.Cidade;
import com.samuca.contato.crud.R;

import java.util.List;

/**
 * Created by samuca on 04/05/2018.
 */

public class AdapterCidade extends BaseAdapter {

    private Context ctx;
    private List<Cidade> listaCidade;

    public AdapterCidade(Context ctx2, List<Cidade> lista2) {
        ctx = ctx2;
        listaCidade = lista2;

    }


    @Override
    public int getCount() {
        return listaCidade.size();
    }

    @Override
    public Object getItem(int position) {
        return listaCidade.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = null;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) ctx).getLayoutInflater();
            v = inflater.inflate(R.layout.lista_cidade, null);

        } else {
            v = convertView;

        }

        //   Cidade cidade  = getItem(position);
        Cidade cidade = (Cidade) getItem(position);

        //  ImageView fotoContato = (ImageView)v.findViewById(R.id.imgContato);
        TextView itemnome = (TextView) v.findViewById(R.id.mNomeListaCidade);
        // TextView itememail = (TextView) v.findViewById(R.id.textVemail);
        //  TextView itemtelefone = (TextView) v.findViewById(R.id.textVtelefone);

        //   String fotoRecebida = HOST + "/" + contato.getFotoContato();

        // Picasso.get().load(fotoRecebida).into(fotoContato);
        itemnome.setText(cidade.getNomeCidade());
        //  itememail.setText(contato.getEmail());
        //   itemtelefone.setText(contato.getTelefone());


        return v;
    }
}
