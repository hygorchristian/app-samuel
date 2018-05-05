package com.samuca.contato.crud;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class ContatoLojaAdapter extends BaseAdapter {

    private String HOST = "http://177.128.81.242:888/contatos";
    private Context ctxloja;
    private List<ContatoLoja> listaloja;

    public ContatoLojaAdapter(Context ctxloja2, List<ContatoLoja> listaloja2) {
        ctxloja = ctxloja2;
        listaloja = listaloja2;

    }

    @Override
    public int getCount() {
        return listaloja.size();
    }

    @Override
    public ContatoLoja getItem(int position) {
        return listaloja.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View v = null;
        if (view == null) {
            LayoutInflater inflater = ((Activity) ctxloja).getLayoutInflater();
            v = inflater.inflate(R.layout.item_lista_loja, null);

        } else {
            v = view;

        }

        ContatoLoja contatoLoja = getItem(position);
        // ContatoLoja contatoLoja1 = new ContatoLoja();

        ImageView fotoContato = (ImageView) v.findViewById(R.id.imgContatoLoja);
        TextView itemnome = (TextView) v.findViewById(R.id.textnomeLoja);
        TextView itemEndereco = (TextView) v.findViewById(R.id.textEnderecoLoja);
        TextView itemtelefone = (TextView) v.findViewById(R.id.textTelefoneLoja);

/*
        TextView whatsapp = (TextView)v.findViewById(R.id.whatsapploja);
        TextView email = (TextView) v.findViewById(R.id.emailloja);
        TextView fecebook = (TextView) v.findViewById(R.id.fecebookloja);
        TextView descricao = (TextView) v.findViewById(R.id.descraoloja);
 */

        String fotoRecebida = HOST + "/" + contatoLoja.getFoto_loja();

        Picasso.get().load(fotoRecebida).into(fotoContato);
        contatoLoja.getId();

        itemnome.setText(contatoLoja.getNome_loja());
        itemEndereco.setText(contatoLoja.getEndereco_loja());
        itemtelefone.setText(contatoLoja.getTelefone_loja());
 /*
        whatsapp.setText(contatoLoja.getWhatsapp_loja());
        email.setText(contatoLoja.getEmail_loja());
        fecebook.setText(contatoLoja.getFecebook_loja());
        descricao.setText(contatoLoja.getDescricao_loja());
 */
        return v;
    }
}

