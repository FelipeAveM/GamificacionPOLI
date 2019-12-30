package co.edu.poli.gamification.poliplaygami.Narrativa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import co.edu.poli.gamification.poliplaygami.R;
import co.edu.poli.gamification.poliplaygami.Secuencia.Mapa;
import co.edu.poli.gamification.poliplaygami.Secuencia.SeleccionarTransporte;

public class IntroTrans extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_trans);

    }

    public void toTrans(View view){
        Intent i = new Intent(this, SeleccionarTransporte.class);
        startActivity(i);
    }
}
