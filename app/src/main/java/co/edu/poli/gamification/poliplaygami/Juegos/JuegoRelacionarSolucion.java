package co.edu.poli.gamification.poliplaygami.Juegos;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import co.edu.poli.gamification.poliplaygami.Modelo.Utiles;
import co.edu.poli.gamification.poliplaygami.Secuencia.Mapa;
import co.edu.poli.gamification.poliplaygami.R;

public class JuegoRelacionarSolucion extends AppCompatActivity {

    private int points;
    private String ans1, ans2, ans3, ans4, ans5, ans6;
    private String sal1, sal2, sal3, sal4, sal5, sal6;
    private TextView target11, target22, target33, target44, target55, target66, resultado, bH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_relacionar_solucion);

        getPointsMain();
        target11 = (TextView) findViewById(R.id.target11);
        target22 = (TextView) findViewById(R.id.target22);
        target33 = (TextView) findViewById(R.id.target33);
        target44 = (TextView) findViewById(R.id.target44);
        target55 = (TextView) findViewById(R.id.target55);
        target66 = (TextView) findViewById(R.id.target66);
        resultado = (TextView) findViewById(R.id.resultado);
        bH = (TextView) findViewById(R.id.bienHecho);
        colorAnswer();

        switch (points){
            case 0: bH.setText("No obtuviste ningún punto."); break;
            case 1: bH.setText("Practica un poco más."); break;
            case 2: bH.setText("¡Buen intento!"); break;
            case 3: bH.setText("¡No está mal!"); break;
            case 4: bH.setText("¡Bien! ¡Obtuviste una moneda!"); break;
            case 5: bH.setText("¡Bien hecho! ¡Obtuviste una moneda!"); break;
            case 6: bH.setText("¡Excelente! ¡¡Obtuviste una moneda y una insignia!!"); break;
        }
        resultado.setText("Tuviste bien " + points + " de 6");
    }


    private void getPointsMain(){
        Bundle ext = getIntent().getExtras();
        points = ext.getInt("points");
        ans1 = ext.getCharSequence("ans1").toString();
        ans2 = ext.getCharSequence("ans2").toString();
        ans3 = ext.getCharSequence("ans3").toString();
        ans4 = ext.getCharSequence("ans4").toString();
        ans5 = ext.getCharSequence("ans5").toString();
        ans6 = ext.getCharSequence("ans6").toString();
        sal1 = ext.getCharSequence("sal1").toString();
        sal2 = ext.getCharSequence("sal2").toString();
        sal3 = ext.getCharSequence("sal3").toString();
        sal4 = ext.getCharSequence("sal4").toString();
        sal5 = ext.getCharSequence("sal5").toString();
        sal6 = ext.getCharSequence("sal6").toString();
    }

    public void colorAnswer(){
        target11.setText(ans1);
        if(!sal1.equals(ans1)) target11.setTextColor(Color.RED);
        else target11.setTextColor(Color.GREEN);

        target22.setText(ans2);
        if(!sal2.equals(ans2)) target22.setTextColor(Color.RED);
        else target22.setTextColor(Color.GREEN);

        target33.setText(ans3);
        if(!sal3.equals(ans3)) target33.setTextColor(Color.RED);
        else target33.setTextColor(Color.GREEN);

        target44.setText(ans4);
        if(!sal4.equals(ans4)) target44.setTextColor(Color.RED);
        else target44.setTextColor(Color.GREEN);

        target55.setText(ans5);
        if(!sal5.equals(ans5)) target55.setTextColor(Color.RED);
        else target55.setTextColor(Color.GREEN);

        target66.setText(ans6);
        if(!sal6.equals(ans6)) target66.setTextColor(Color.RED);
        else target66.setTextColor(Color.GREEN);
    }

    public void contBtn(View view){
        Intent i = new Intent(this, Mapa.class);
        i.putExtra("back", "no");
        Utiles.terminarConexion();
        startActivity(i);
    }
}
