package co.edu.poli.gamification.poliplayapp.Narrativa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import co.edu.poli.gamification.poliplayapp.R;
import co.edu.poli.gamification.poliplayapp.Secuencia.Mapa;

public class IntroMapa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_mapa);
    }
    public void toMap(View view){
        Intent i = new Intent(this, Mapa.class);
        startActivity(i);
    }
}
