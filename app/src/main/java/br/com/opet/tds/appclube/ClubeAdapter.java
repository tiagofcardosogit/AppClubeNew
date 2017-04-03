package br.com.opet.tds.appclube;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Diego on 03/04/2017.
 */

public class ClubeAdapter extends ArrayAdapter<Clube> {
    private List<Clube> clubes;
    private int layout;

    public ClubeAdapter(Context context, int resource, List<Clube> clubes){
        super(context,resource,clubes);
        this.clubes = clubes;
        layout = resource;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent){
        View localView = contentView;

        if(localView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            localView = inflater.inflate(layout,null);
        }

        Clube clube = clubes.get(position);

        if(clube != null){
            TextView textID = (TextView) localView.findViewById(R.id.textID);
            TextView textNome = (TextView) localView.findViewById(R.id.textNome);
            TextView textCidade = (TextView) localView.findViewById(R.id.textCidade);
            TextView textAno = (TextView) localView.findViewById(R.id.textAno);

            if(textID != null){
                textID.setText(String.valueOf(clube.getID()));
            }
            if(textNome != null){
                textNome.setText(clube.getNome());
            }
            if(textCidade != null){
                textCidade.setText(clube.getCidade());
            }
            if(textAno != null){
                textAno.setText(String.valueOf(clube.getAno()));
            }
        }
        return localView;
    }
}
