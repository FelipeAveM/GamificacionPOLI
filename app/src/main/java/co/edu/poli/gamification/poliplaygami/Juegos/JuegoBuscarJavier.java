package co.edu.poli.gamification.poliplaygami.Juegos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import co.edu.poli.gamification.poliplaygami.Modelo.Utiles;
import co.edu.poli.gamification.poliplaygami.R;
import co.edu.poli.gamification.poliplaygami.Secuencia.Login;

public class JuegoBuscarJavier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_buscar_javier);
    }
    public void toLogin (View view){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}
