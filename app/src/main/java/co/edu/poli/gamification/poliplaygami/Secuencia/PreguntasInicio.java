package co.edu.poli.gamification.poliplaygami.Secuencia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import co.edu.poli.gamification.poliplaygami.R;

public class PreguntasInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_inicio);
        /*if(!Login.user.getTransport().equals("vacio")){
            Intent i = new Intent(this, Mapa.class);
        }*/
    }

    public void btnAlgo(View view){
        startActivity(new Intent(this, SeleccionarTransporte.class));
    }
}
