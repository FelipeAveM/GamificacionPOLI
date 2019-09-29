package co.edu.poli.gamification.poliplaygami.Narrativa;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import co.edu.poli.gamification.poliplaygami.Modelo.Utiles;
import co.edu.poli.gamification.poliplaygami.R;
import co.edu.poli.gamification.poliplaygami.Secuencia.SeleccionarTransporte;

public class InicioViaje extends AppCompatActivity {

    private ConstraintLayout layout_inicio_viaje;
    private int cont = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_viaje);
        Utiles.startCon = System.currentTimeMillis();
        /*if(!Login.user.getTransport().equals("vacio")){
            Intent i = new Intent(this, Mapa.class);
            startActivity(i);
        }*/

        layout_inicio_viaje = (ConstraintLayout)findViewById(R.id.layout_inicio_viaje);
        layout_inicio_viaje.setBackground(getResources().getDrawable(R.drawable.inicio_viaje_uno)); //temporal
    }

    public void goForward(View view){
        if(cont == 1){
            layout_inicio_viaje.setBackground(getResources().getDrawable(R.drawable.inicio_viaje_dos));
            cont++;
            return;
        }
        else if(cont == 2){
            layout_inicio_viaje.setBackground(getResources().getDrawable(R.drawable.inicio_viaje_tres));
            cont++;
            return;
        }
        else if(cont == 3){
            layout_inicio_viaje.setBackground(getResources().getDrawable(R.drawable.inicio_viaje_cuatro));
            cont++;
            return;
        }
        else if(cont == 4){
            Intent i = new Intent(this, SeleccionarTransporte.class);
            Utiles.terminarConexion();
            startActivity(i);
        }
    }
}
