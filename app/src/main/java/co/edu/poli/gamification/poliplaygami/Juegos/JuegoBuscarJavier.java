package co.edu.poli.gamification.poliplaygami.Juegos;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import co.edu.poli.gamification.poliplaygami.Modelo.TiempoConexionJuego;
import co.edu.poli.gamification.poliplaygami.Modelo.Utiles;
import co.edu.poli.gamification.poliplaygami.R;
import co.edu.poli.gamification.poliplaygami.Secuencia.Login;
import co.edu.poli.gamification.poliplaygami.Secuencia.Mapa;


public class JuegoBuscarJavier extends AppCompatActivity {

    private long start, end;

    private ImageView imagenb1, imagenb2, imagenb3, imagenb4, imagenb5, imagenb6, imagenb7, imagenb8,
            imagenb9, imagenb10, imagenb11, imagenb12, imagenb13, imagenb14, imagenb15, imagenb16;

    private ImageView imagesClues [] = {imagenb1, imagenb2, imagenb3, imagenb4, imagenb5, imagenb6, imagenb7, imagenb8,
            imagenb9, imagenb10, imagenb11, imagenb12, imagenb13, imagenb14, imagenb15, imagenb16};

    int clueSets = 0;

    private TextView clues;
    private int shower = 1;
    private int clueFinds = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_buscar_javier);
        start = System.currentTimeMillis();
        Utiles.startCon = start;
        imagenb1 = (ImageView) findViewById(R.id.imageView10);
        imagenb2 = (ImageView) findViewById(R.id.imageView11);
        imagenb3 = (ImageView) findViewById(R.id.imageView12);
        imagenb4 = (ImageView) findViewById(R.id.imageView13);
        imagenb5 = (ImageView) findViewById(R.id.imageView14);
        imagenb6 = (ImageView) findViewById(R.id.imageView15);
        imagenb7 = (ImageView) findViewById(R.id.imageView16);
        imagenb8 = (ImageView) findViewById(R.id.imageView17);
        imagenb9 = (ImageView) findViewById(R.id.imageView18);
        imagenb10 = (ImageView) findViewById(R.id.imageView19);
        imagenb11 = (ImageView) findViewById(R.id.imageView20);
        imagenb12 = (ImageView) findViewById(R.id.imageView21);
        imagenb13 = (ImageView) findViewById(R.id.imageView22);
        imagenb14 = (ImageView) findViewById(R.id.imageView23);
        imagenb15 = (ImageView) findViewById(R.id.imageView24);
        imagenb16 = (ImageView) findViewById(R.id.imageView25);
        clues = (TextView) findViewById(R.id.clues);

        imagenb2.setImageDrawable(getDrawable(R.drawable.capacitacion));
        imagenb3.setImageDrawable(getDrawable(R.drawable.competitividad));
        imagenb4.setImageDrawable(getDrawable(R.drawable.crecimiento_intensivo));
        imagenb5.setImageDrawable(getDrawable(R.drawable.desigualdad));
        imagenb6.setImageDrawable(getDrawable(R.drawable.directivos));
        imagenb7.setImageDrawable(getDrawable(R.drawable.diseno_organizacional));
        imagenb8.setImageDrawable(getDrawable(R.drawable.fluctuacion_laboral));
        imagenb9.setImageDrawable(getDrawable(R.drawable.generacion_valor));
        imagenb10.setImageDrawable(getDrawable(R.drawable.indicadores));
        imagenb11.setImageDrawable(getDrawable(R.drawable.internet));
        imagenb12.setImageDrawable(getDrawable(R.drawable.liderazgo_liberal));
        imagenb13.setImageDrawable(getDrawable(R.drawable.liderazgo_situacional));
        imagenb14.setImageDrawable(getDrawable(R.drawable.perturbaciones));
        imagenb15.setImageDrawable(getDrawable(R.drawable.poligrafo));
        imagenb16.setImageDrawable(getDrawable(R.drawable.reuniones));


        imagenb1.setVisibility(View.INVISIBLE);
        imagenb2.setVisibility(View.INVISIBLE);
        imagenb3.setVisibility(View.INVISIBLE);
        imagenb4.setVisibility(View.INVISIBLE);
        imagenb5.setVisibility(View.INVISIBLE);
        imagenb6.setVisibility(View.INVISIBLE);
        imagenb7.setVisibility(View.INVISIBLE);
        imagenb8.setVisibility(View.INVISIBLE);
        imagenb9.setVisibility(View.INVISIBLE);
        imagenb10.setVisibility(View.INVISIBLE);
        imagenb11.setVisibility(View.INVISIBLE);
        imagenb12.setVisibility(View.INVISIBLE);
        imagenb13.setVisibility(View.INVISIBLE);
        imagenb14.setVisibility(View.INVISIBLE);
        imagenb15.setVisibility(View.INVISIBLE);
        imagenb16.setVisibility(View.INVISIBLE);
        randomSet();
        randomPos();


     }
    private void randomSet(){
        Random r = new Random();
        int low = 0;
        int high = 4;
        int result = r.nextInt(high-low) + low;
        clueSets = result;
        String showClues = "";
        switch (clueSets){
            case 0:
                showClues = "1" +"\n" + "2" +"\n" + "3" +"\n" + "4";
                break;
            case 1:
                showClues = "5" +"\n" + "6" +"\n" + "7" +"\n" + "8";
                break;
            case 2:
                showClues = "9" +"\n" + "10" +"\n" + "11" +"\n" + "12";
                break;
            case 3:
                showClues = "13" +"\n" + "14" +"\n" + "15" +"\n" + "16";
                break;
            default:
                break;
        }

        String algo = "Estas son las pistas para encontrar los personajes y/o objetos:" + "\n" +
                showClues + "\n" + "¡pulsa aquí para escoder las pistas!";
        clues.setText(algo);
    }
    private void randomPos(){
        switch (clueSets){
            case 0:
                imagenb1.setVisibility(View.VISIBLE);
                imagenb2.setVisibility(View.VISIBLE);
                imagenb3.setVisibility(View.VISIBLE);
                imagenb4.setVisibility(View.VISIBLE);
                break;
            case 1:
                imagenb5.setVisibility(View.VISIBLE);
                imagenb6.setVisibility(View.VISIBLE);
                imagenb7.setVisibility(View.VISIBLE);
                imagenb8.setVisibility(View.VISIBLE);
                break;
            case 2:
                imagenb9.setVisibility(View.VISIBLE);
                imagenb10.setVisibility(View.VISIBLE);
                imagenb11.setVisibility(View.VISIBLE);
                imagenb12.setVisibility(View.VISIBLE);
                break;
            case 3:
                imagenb13.setVisibility(View.VISIBLE);
                imagenb14.setVisibility(View.VISIBLE);
                imagenb15.setVisibility(View.VISIBLE);
                imagenb16.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
    public void toLogin (View view){
        clueFinds++;
        if(clueFinds == 4){
            long end2 = System.currentTimeMillis();
            long totaltime = (end2-start)/1000;
            if(totaltime < 30000 && !Login.user.getLevel().equals("FIN")){
                guardarRes(5, "2", 1);
                Intent i = new Intent(this, Mapa.class);
                startActivity(i);
            }
            else if(Login.user.getLevel().equals("FIN")){
                guardarRes(1, "FIN", 0);
                Intent i = new Intent(this, JuegoCalculadora.class);
                startActivity(i);
            }else if(totaltime < 120000){
                guardarRes(1, "2", 0);
                Intent i = new Intent(this, Mapa.class);
                startActivity(i);
            }
        }
        view.setEnabled(false);
        view.setVisibility(View.INVISIBLE);
    }
    public void guardarRes(int res, String level, int insignia){
        end = System.currentTimeMillis();
        long totaltime = (end-start)/1000;
        int puntajes = Integer.parseInt(Login.user.getCoins());
        puntajes += res;
        int insignias = Integer.parseInt(Login.user.getBadges());
        insignias += insignia;
        Login.user.setLevel(level);
        Login.user.setCoins(String.valueOf(puntajes));
        Login.user.setBadges(String.valueOf(insignias));
        TiempoConexionJuego atr = new TiempoConexionJuego(
                Utiles.getFecha(),
                Login.user.getCode(),
                Login.user.getGroup(),
                "Encuentra a Javier",
                String.valueOf(puntajes),
                String.valueOf(totaltime),
                level,
                String.valueOf(insignias));
        atr.execute();
    }
    public void info(View view){
        TextView tx = (TextView) view;
        String showClues = "";
        switch (clueSets){
            case 0:
                showClues = "1" +"\n" + "2" +"\n" + "3" +"\n" + "4";
                break;
            case 1:
                showClues = "5" +"\n" + "6" +"\n" + "7" +"\n" + "8";
                break;
            case 2:
                showClues = "9" +"\n" + "10" +"\n" + "11" +"\n" + "12";
                break;
            case 3:
                showClues = "13" +"\n" + "14" +"\n" + "15" +"\n" + "16";
                break;
            default:
                break;
        }
        String algo = "Estas son las pistas para encontrar los personajes y/o objetos:"+ "\n"  +
                showClues + "\n" + "!pulsa aquí para esconder las pistas!";
        if(shower == 1){
            tx.setText("...");
            shower--;
        }else{
            tx.setText(algo);
            shower++;
        }
    }

}
