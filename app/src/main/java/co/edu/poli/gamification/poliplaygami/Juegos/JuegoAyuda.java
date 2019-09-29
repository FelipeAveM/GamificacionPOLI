package co.edu.poli.gamification.poliplaygami.Juegos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import co.edu.poli.gamification.poliplaygami.R;

public class JuegoAyuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_ayuda);


        //EscalarPantalla
        DisplayMetrics md = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(md);
        int width = md.widthPixels;
        int heigth = md.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (heigth * .6));
    }
}
