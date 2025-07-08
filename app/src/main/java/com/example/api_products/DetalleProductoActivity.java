package com.example.api_products;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetalleProductoActivity extends AppCompatActivity {

    TextView tvCategoria, tvTitulo, tvPrecio;
    ImageView ivImagen;
    Button btnPagar, btnRegresar; // <-- agregamos el botón

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        // Referencias de los views
        tvCategoria = findViewById(R.id.tvCategoria);
        tvTitulo = findViewById(R.id.tvTitulo);
        tvPrecio = findViewById(R.id.tvPrecio);
        ivImagen = findViewById(R.id.ivImagen);
        btnPagar = findViewById(R.id.btnPagar);
        btnRegresar = findViewById(R.id.btnRegresar); // <-- referencia

        // Obtener datos enviados desde el intent
        String categoria = getIntent().getStringExtra("categoria");
        String titulo = getIntent().getStringExtra("titulo");
        String precio = getIntent().getStringExtra("precio");
        String urlimage = getIntent().getStringExtra("urlimage");

        // "Category:" en rojo, valor en negro
        String categoriaText = "Category: " + categoria;
        SpannableString spannableCategoria = new SpannableString(categoriaText);
        spannableCategoria.setSpan(
                new ForegroundColorSpan(0xFFD32F2F),
                0, 9,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvCategoria.setText(spannableCategoria);

        // Título normal
        tvTitulo.setText(titulo);

        // "Total a pagar:" en rojo, precio en negro
        String totalText = "Total a pagar: $" + precio;
        SpannableString spannableTotal = new SpannableString(totalText);
        spannableTotal.setSpan(
                new ForegroundColorSpan(0xFFD32F2F),
                0, 14,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvPrecio.setText(spannableTotal);

        // Cargar imagen con Glide
        Glide.with(this).load(urlimage).into(ivImagen);

        // Acción del botón pagar
        btnPagar.setOnClickListener(v ->
                Toast.makeText(this, "Pago iniciado para " + titulo, Toast.LENGTH_SHORT).show()
        );

        // Acción del botón regresar
        btnRegresar.setOnClickListener(v -> finish());
    }
}
