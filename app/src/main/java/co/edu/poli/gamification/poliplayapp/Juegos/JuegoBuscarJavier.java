package co.edu.poli.gamification.poliplayapp.Juegos;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import co.edu.poli.gamification.poliplayapp.Modelo.TiempoConexionJuego;
import co.edu.poli.gamification.poliplayapp.Modelo.Utiles;
import co.edu.poli.gamification.poliplayapp.R;
import co.edu.poli.gamification.poliplayapp.Secuencia.Login;
import co.edu.poli.gamification.poliplayapp.Secuencia.Mapa;


public class JuegoBuscarJavier extends AppCompatActivity {

    private long start, end;

    private ImageView imagenb1, imagenb2, imagenb3, imagenb4, imagenb5, imagenb6, imagenb7, imagenb8,
            imagenb9, imagenb10, imagenb11, imagenb12, imagenb13, imagenb14, imagenb15, imagenb16;

    private ImageView imagesClues [] = {imagenb1, imagenb2, imagenb3, imagenb4, imagenb5, imagenb6, imagenb7, imagenb8,
            imagenb9, imagenb10, imagenb11, imagenb12, imagenb13, imagenb14, imagenb15, imagenb16};

    //15,7,3,2,1,14,4,16,5,8,9,13,12,10,6,11
    private String [] respuestas = {"Controles de recompensa", "Capacitación","Competitividad", "Crecimiento Intensivo", "Desigualdad","Directivos", "Diseño Organizacional", "Fluctuación Laboral",
            "Generación de valor", "Indicadores", "Internet", "Liderago Liberal", "Liderazgo Situacional","Perturbaciones", "Polígrafo", "Reuniones"};

    int clueSets = 0;
    private ConstraintLayout constraintLayout;
    private TextView clues, clue1, clue2, clue3, clue4, introClues, hideClues;
    private int shower = 1;
    private int clueFinds = 0;
    private Button buttonClues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_buscar_javier);
        start = System.currentTimeMillis();
        Utiles.startCon = start;
        constraintLayout = (ConstraintLayout) findViewById(R.id.clues);
        buttonClues = (Button) findViewById(R.id.bClues);
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
        clues = (TextView) findViewById(R.id.encJav);
        clue1 = (TextView) findViewById(R.id.encJ1);
        clue2 = (TextView) findViewById(R.id.encJ2);
        clue3 = (TextView) findViewById(R.id.encJ3);
        clue4 = (TextView) findViewById(R.id.encJ4);
        introClues = (TextView) findViewById(R.id.encJav);
        hideClues = (TextView) findViewById(R.id.encJav2);


        //15
        //7
        imagenb2.setImageDrawable(getDrawable(R.drawable.capacitacion));
        //3
        imagenb3.setImageDrawable(getDrawable(R.drawable.competitividad));
        //2
        imagenb4.setImageDrawable(getDrawable(R.drawable.crecimiento_intensivo));
        //1
        imagenb5.setImageDrawable(getDrawable(R.drawable.desigualdad));
        //14
        imagenb6.setImageDrawable(getDrawable(R.drawable.directivos));
        //4
        imagenb7.setImageDrawable(getDrawable(R.drawable.diseno_organizacional));
        //16
        imagenb8.setImageDrawable(getDrawable(R.drawable.fluctuacion_laboral));
        //5
        imagenb9.setImageDrawable(getDrawable(R.drawable.generacion_valor));
        //8
        imagenb10.setImageDrawable(getDrawable(R.drawable.indicadores));
        //9
        imagenb11.setImageDrawable(getDrawable(R.drawable.internet));
        //13
        imagenb12.setImageDrawable(getDrawable(R.drawable.liderazgo_liberal));
        //12
        imagenb13.setImageDrawable(getDrawable(R.drawable.liderazgo_situacional));
        //10
        imagenb14.setImageDrawable(getDrawable(R.drawable.perturbaciones));
        //6
        imagenb15.setImageDrawable(getDrawable(R.drawable.poligrafo));
        //11
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
        changeClues(clueSets);
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
        double x = view.getX();
        double y = view.getY();
        int imagen = 0;
        int enunciado = 0;
        if(x == imagenb1.getX() && y == imagenb1.getY()) {
            imagen = 1;
            enunciado = 0;
        }
        else if(x == imagenb2.getX() && y == imagenb2.getY()) {
            imagen = 2;
            enunciado = 1;
        }
        else if(x == imagenb3.getX() && y == imagenb3.getY()) {
            imagen = 3;
            enunciado = 2;
        }
        else if(x == imagenb4.getX() && y == imagenb4.getY()) {
            imagen = 4;
            enunciado = 3;
        }
        else if(x == imagenb5.getX() && y == imagenb5.getY()) {
            imagen = 1;
            enunciado = 4;
        }
        else if(x == imagenb6.getX() && y == imagenb6.getY()) {
            imagen = 2;
            enunciado = 5;
        }
        else if(x == imagenb7.getX() && y == imagenb7.getY()) {
            imagen = 3;
            enunciado = 6;
        }
        else if(x == imagenb8.getX() && y == imagenb8.getY()) {
            imagen = 4;
            enunciado = 7;
        }
        else if(x == imagenb9.getX() && y == imagenb9.getY()) {
            imagen = 1;
            enunciado = 8;
        }
        else if(x == imagenb10.getX() && y == imagenb10.getY()) {
            imagen = 2;
            enunciado = 9;
        }
        else if(x == imagenb11.getX() && y == imagenb11.getY()) {
            imagen = 3;
            enunciado = 10;
        }
        else if(x == imagenb12.getX() && y == imagenb12.getY()) {
            imagen = 4;
            enunciado = 11;
        }
        else if(x == imagenb13.getX() && y == imagenb13.getY()) {
            imagen = 1;
            enunciado = 12;
        }
        else if(x == imagenb14.getX() && y == imagenb14.getY()) {
            imagen = 2;
            enunciado = 13;
        }
        else if(x == imagenb15.getX() && y == imagenb15.getY()) {
            imagen = 3;
            enunciado = 14;
        }
        else if(x == imagenb16.getX() && y == imagenb16.getY()) {
            imagen = 4;
            enunciado = 15;
        }
        TextView [] cluesView = {clue1, clue2, clue3, clue4};
        cluesView[imagen-1].setTextColor(Color.RED);
        String pista = cluesView[imagen -1].getText().toString();
        cluesView[imagen-1].setText(pista + " R: " + respuestas[enunciado]);
        String textoAlerta = "¡Pista " + String.valueOf(imagen) + " encontrada!";
        clueFinds++;
        Toast.makeText(this, textoAlerta, Toast.LENGTH_SHORT).show();
        if(clueFinds == 4){
            long end2 = System.currentTimeMillis();
            long totaltime = (end2-start)/1000;
            if(totaltime < 30000 && !Login.user.getLevel().equals("FIN")){
                constraintLayout.setVisibility(View.VISIBLE);
                buttonClues.setVisibility(View.INVISIBLE);
                introClues.setText("Muy bien, has encontrado todas las pistas. Obtienes 5 monedas y 1 insignia.");
                hideClues.setText("¡AVANZAR!");
                shower = 4;
                guardarRes(5, "2", 1);
            }
            else if(Login.user.getLevel().equals("FIN")){
                constraintLayout.setVisibility(View.VISIBLE);
                buttonClues.setVisibility(View.INVISIBLE);
                introClues.setText("Muy bien, has encontrado todas las pistas. Obtienes 1 moneda.");
                hideClues.setText("¡AVANZAR!");
                shower = 4;
                guardarRes(1, "FIN", 0);
            }else if(totaltime < 120000){
                constraintLayout.setVisibility(View.VISIBLE);
                buttonClues.setVisibility(View.INVISIBLE);
                introClues.setText("Muy bien, has encontrado todas las pistas. Obtienes 1 moneda.");
                hideClues.setText("¡AVANZAR!");
                shower = 4;
                guardarRes(2, "2", 0);
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
    public void closeInfo(View view){
        if(shower == 4){
            Intent i = new Intent(getApplicationContext(), Mapa.class);
            startActivity(i);
        }
        else {
            constraintLayout.setVisibility(View.INVISIBLE);
            buttonClues.setVisibility(View.VISIBLE);
        }
    }
    public void showClues(View view){
            constraintLayout.setVisibility(View.VISIBLE);
            buttonClues.setVisibility(View.INVISIBLE);
    }
    public void changeClues(int i){
        switch (i){
            case 0:
                clue1.setText("1) Se implementan para motivar a los individuos a alcanzar las metas. <Ver lectura 15>");
                clue2.setText("2) Las empresas deben instar a su recurso más valioso: El talento humano, a llevar a cabo este proceso de forma permanente. <Ver lectura 7>");
                clue3.setText("3) Mayor eficiencia y eficacia frente a los competidores. <Ver lectura 3>");
                clue4.setText("4) Uso intensivo y extensivo de los factores de producción para el crecimiento de los países. <Ver lectura 2>");
                break;
            case 1:
                clue1.setText("1) Diferencia en el PIB de los países. <Ver lectura 1>");
                clue2.setText("2) Responsables de la matriz OVAR <Ver lectura 14>");
                clue3.setText("3) En Bogotá muchos de los cambios de la estructura organizaciona no obedecen a este proceso. <Ver lectura 4>");
                clue4.setText("4) Una debilidad de la empresa agropecuaria \"Camilo Cienfuegos\" a nivel gerencial. <Ver lectura 16>");
                break;
            case 2:
                clue1.setText("1) Resultado de orientar el diseño organizacional en  los procesos clave, las competencias clave, y los procesos de apoyo. <Ver lectura 5>");
                clue2.setText("2) La eficiencia en la selección del talento humano incide directamente en la competitividad.  Se evidencia que se requieren de estos instrumentos para determinarla. <Ver lectura 8>");
                clue3.setText("3) Instrumento que logra una comunicación de doble vía en las organizaciones. <Ver lectura 9>");
                clue4.setText("4) Se identifica este tipo de liderazgo en empresas con bajo éxito en su actividad exportadora <Ver lectura 13>");
                break;
            case 3:
                clue1.setText("1) Tipo de liderazgo que se adopta diferentes tipos de liderazgo de acuerdo a la situación. <Ver lectura 12>");
                clue2.setText("2) Para mejorar la comunicación interna o externa es necesario que se identifiquen se manejen o eliminen. <Ver lectura 10>");
                clue3.setText("3) Según las 'modas administrativias' se desarrollan algunas prácticas de selección que pueden producir efectos negativos en los participantes del proceso, visto de una manera crítica. <Ver lectura6>");
                clue4.setText("4) Principal medio de comunicación en la empresas del norte del Ecuador. <Ver lectura 11>");
                break;
            default:
                break;
        }
    }

}
