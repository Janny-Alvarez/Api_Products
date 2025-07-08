package com.example.api_products;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdaptadorUsuario extends ArrayAdapter<Usuario> {

    public AdaptadorUsuario(Context context, ArrayList<Usuario> datos) {
        super(context, R.layout.lyitem, datos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.lyitem, null);

        Usuario usuario = getItem(position);

        // Category: en rojo + categoría en negro
        String categoriaText = "Category: " + usuario.getCategoria();
        SpannableString spannableCategoria = new SpannableString(categoriaText);
        spannableCategoria.setSpan(
                new ForegroundColorSpan(0xFFD32F2F),
                0, 9,  // "Category:" tiene 9 caracteres (incluye el :)
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView lblNombre = item.findViewById(R.id.lblNombre);
        lblNombre.setText(spannableCategoria);

        // Título
        TextView lblemail = item.findViewById(R.id.lblEmail);
        lblemail.setText(usuario.getTitulo());

        // Price: en rojo + precio en negro
        String precioText = "Price: " + usuario.getPrecio();
        SpannableString spannablePrecio = new SpannableString(precioText);
        spannablePrecio.setSpan(
                new ForegroundColorSpan(0xFFD32F2F),
                0, 6,  // "Price:" tiene 6 caracteres (incluye el :)
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView lblweb = item.findViewById(R.id.lblweb);
        lblweb.setText(spannablePrecio);

        // Descripción
        TextView lblDescripcion = item.findViewById(R.id.lblDescripcion);
        lblDescripcion.setText(usuario.getDescripcion());

        // Imagen
        ImageView img = item.findViewById(R.id.imgUsr);
        Glide.with(this.getContext())
                .load(usuario.getUrlimage())
                .into(img);

        // Agregar click para abrir detalle
        item.setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(getContext(), DetalleProductoActivity.class);
            intent.putExtra("categoria", usuario.getCategoria());
            intent.putExtra("titulo", usuario.getTitulo());
            intent.putExtra("precio", usuario.getPrecio());
            intent.putExtra("descripcion", usuario.getDescripcion());
            intent.putExtra("urlimage", usuario.getUrlimage());
            getContext().startActivity(intent);
        });


        return item;
    }
}
