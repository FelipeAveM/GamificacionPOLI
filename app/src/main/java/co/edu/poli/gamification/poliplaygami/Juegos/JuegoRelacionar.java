package co.edu.poli.gamification.poliplaygami.Juegos;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import co.edu.poli.gamification.poliplaygami.Modelo.TiempoConexionJuego;
import co.edu.poli.gamification.poliplaygami.Modelo.Utiles;
import co.edu.poli.gamification.poliplaygami.R;
import co.edu.poli.gamification.poliplaygami.Secuencia.Login;

public class JuegoRelacionar extends AppCompatActivity {

    private  TextView
            enun1, enun2, enun3, enun4, enun5, enun6,
            txt1, txt2, txt3, txt4, txt5, txt6,
            target1, target2, target3, target4, target5, target6;

    private TextView enunciadosView[] = {enun1, enun2, enun3, enun4, enun5, enun6};

    private TextView respustasView[] = {txt1, txt2, txt3, txt4, txt5, txt6};

    private long start, end;
    private Button validar;
    private int contador = 0;
    private Random randomEnunciados = new Random();

    private ArrayList<Integer> enunciadosAgregados = new ArrayList<Integer>();
    private String enunciados[] = {


            //"Aqui",
            "Fase del proceso administrativo en la cual se establecen estándares para evaluar los resultados obtenidos con el objetivo de corregir desviaciones y mejorar las operaciones.",
            "Consiste en la ejecución de todas las fases del proceso administrativo mediante la conducción y orientación de los recursos, y el ejercicio del liderazgo.",
            "Es la función a través de la cual se eligen y obtienen los recursos necesarios para poner en marcha las operaciones.",
            "Diseño y determinación de las estructuras, procesos, funciones y responsabilidades, así como el establecimiento de métodos.",
            "Es la determinación del rumbo hacia el que se dirige la organización y los resultados que se pretende obtener mediante el análisis del entorno y la definición de estrategia",
            "Una de las actividades más importantes del directivo es la toma de decisiones, por lo consiguiente después de definir el problema la etapa que le sigue es:",
            "Una vez se ha elegido la alternativa más óptima para el proceso de toma de decisiones, el paso posterior para hacer seguimiento a los resultados es",
            "Última etapa del proceso de toma de decisiones",
            "Técnica para identificar y definir el problema correctamente en el proceso de Toma de decisione",
            "Proceso sistemático y racional a través del cual se selecciona entre varias alternativas el curso de acción óptimo.",
            "Conjunto de acciones que deben realizarse de forma secuencial para obtener un resultado en las mismas condiciones",
            "A través de éstos se proyectan, en forma cuantificada, los recursos que requiere la organización para cumplir con sus objetivos.",
            "Reglas administrativas de observancia obligatoria para la realización de una operación, procedimiento, programa o presupuesto.",
            "Enunciado del estado deseado en el futuro para la organización. Provee dirección y forja el futuro de la empresa estimulando acciones concretas en el presente.",
            "Son los resultados específicos que se desea alcanzar, medibles y cuantificables a un tiempo, para lograr la misión.",
            "En él se plasman los elementos del proceso de planeación.",
            "Son las guías para orientar la acción gerencial para lograr la estrategia.",
            "Definición amplia del propósito de la empresa y la descripción del negocio o giro al que se dedica.",
            "Son los cursos de acción que muestran la dirección y el empleo general de los recursos para lograr los objetivos.",
            "Reglas administrativas de observancia obligatoria para la realización de una operación, procedimiento, programa o presupuesto.",
            "En ellos se detallan el conjunto de actividades, responsables y tiempos necesarios para llevar a cabo las estrategias."
    };

    private String respuestas[] = {

            
            //"Esta va",
            "Control",
            "Dirección",
            "Integración",
            "Organización",
            "Planeación",
            "Plantear alternativas",
            "Plan de acción",
            "Implementación",
            "Tormenta ideas",
            "Toma desiciones",
            "Planeación",
            "Presupuestos",
            "Normas",
            "Visión",
            "Obj. estratégicos",
            "Plan Maestro",
            "Políticas",
            "Misión",
            "Estrategias",
            "Normas",
            "Cronogramas"
    };

    private int numsRandom [] = new int[respuestas.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_relacionar);
        start = System.currentTimeMillis();
        validar = (Button) findViewById(R.id.aceptar);
        Utiles.startCon = start;

        /*
        enunciadosView[0] = (TextView) findViewById(R.id.enunciado1);
        enunciadosView[1] = (TextView) findViewById(R.id.enunciado2);
        enunciadosView[2] = (TextView) findViewById(R.id.enunciado3);
        enunciadosView[3] = (TextView) findViewById(R.id.enunciado4);
        enunciadosView[4] = (TextView) findViewById(R.id.enunciado5);
        enunciadosView[5] = (TextView) findViewById(R.id.enunciado6);
        respustasView[0] = (TextView) findViewById(R.id.txt1);
        respustasView[1] = (TextView) findViewById(R.id.txt2);
        respustasView[2] = (TextView) findViewById(R.id.txt3);
        respustasView[3] = (TextView) findViewById(R.id.txt4);
        respustasView[4] = (TextView) findViewById(R.id.txt5);
        respustasView[5] = (TextView) findViewById(R.id.txt6);
        */

        enun1 = (TextView) findViewById(R.id.enunciado1);
        enun2 = (TextView) findViewById(R.id.enunciado2);
        enun3 = (TextView) findViewById(R.id.enunciado3);
        enun4 = (TextView) findViewById(R.id.enunciado4);
        enun5 = (TextView) findViewById(R.id.enunciado5);
        enun6 = (TextView) findViewById(R.id.enunciado6);


        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);
        txt4 = (TextView) findViewById(R.id.txt4);
        txt5 = (TextView) findViewById(R.id.txt5);
        txt6 = (TextView) findViewById(R.id.txt6);

        target1 = (TextView) findViewById(R.id.target1);
        target2 = (TextView) findViewById(R.id.target2);
        target3 = (TextView) findViewById(R.id.target3);
        target4 = (TextView) findViewById(R.id.target4);
        target5 = (TextView) findViewById(R.id.target5);
        target6 = (TextView) findViewById(R.id.target6);

        asignarEnunciados();

        txt1.setOnLongClickListener(longClickListener);
        txt2.setOnLongClickListener(longClickListener);
        txt3.setOnLongClickListener(longClickListener);
        txt4.setOnLongClickListener(longClickListener);
        txt5.setOnLongClickListener(longClickListener);
        txt6.setOnLongClickListener(longClickListener);

        target1.setOnDragListener(dragListener1);
        target2.setOnDragListener(dragListener2);
        target3.setOnDragListener(dragListener3);
        target4.setOnDragListener(dragListener4);
        target5.setOnDragListener(dragListener5);
        target6.setOnDragListener(dragListener6);
    }

    public void asignarEnunciados(){


        for (int i = 0; i < respuestas.length; i++) {
            enunciadosAgregados.add(i);
        }
        Collections.shuffle(enunciadosAgregados);

        enun1.setText(enunciados[enunciadosAgregados.get(0)]);
        enun2.setText(enunciados[enunciadosAgregados.get(1)]);
        enun3.setText(enunciados[enunciadosAgregados.get(2)]);
        enun4.setText(enunciados[enunciadosAgregados.get(3)]);
        enun5.setText(enunciados[enunciadosAgregados.get(4)]);
        enun6.setText(enunciados[enunciadosAgregados.get(5)]);

        txt1.setText(respuestas[enunciadosAgregados.get(0)]);
        txt2.setText(respuestas[enunciadosAgregados.get(1)]);
        txt3.setText(respuestas[enunciadosAgregados.get(2)]);
        txt4.setText(respuestas[enunciadosAgregados.get(3)]);
        txt5.setText(respuestas[enunciadosAgregados.get(4)]);
        txt6.setText(respuestas[enunciadosAgregados.get(5)]);

    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, myShadowBuilder, v, 0);
            return true;
        }
    };

    View.OnDragListener dragListener1 = new View.OnDragListener(){
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            switch (dragEvent){
                case DragEvent.ACTION_DROP:
                    final View view = (View) event.getLocalState();
                    if(view.getId() == R.id.txt1) target1.setText(txt1.getText());
                    else if(view.getId() == R.id.txt2) target1.setText(txt2.getText());
                    else if(view.getId() == R.id.txt3) target1.setText(txt3.getText());
                    else if(view.getId() == R.id.txt4) target1.setText(txt4.getText());
                    else if(view.getId() == R.id.txt5) target1.setText(txt5.getText());
                    else if(view.getId() == R.id.txt6) target1.setText(txt6.getText());
                    break;
            }
            contador++;
            return true;
        }
    };

    View.OnDragListener dragListener2 = new View.OnDragListener(){
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            switch (dragEvent){
                case DragEvent.ACTION_DROP:
                    final View view = (View) event.getLocalState();
                    if(view.getId() == R.id.txt1) target2.setText(txt1.getText());
                    else if(view.getId() == R.id.txt2) target2.setText(txt2.getText());
                    else if(view.getId() == R.id.txt3) target2.setText(txt3.getText());
                    else if(view.getId() == R.id.txt4) target2.setText(txt4.getText());
                    else if(view.getId() == R.id.txt5) target2.setText(txt5.getText());
                    else if(view.getId() == R.id.txt6) target2.setText(txt6.getText());
                    break;
            }
            contador++;
            return true;
        }
    };

    View.OnDragListener dragListener3 = new View.OnDragListener(){
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            switch (dragEvent){
                case DragEvent.ACTION_DROP:
                    final View view = (View) event.getLocalState();
                    if(view.getId() == R.id.txt1) target3.setText(txt1.getText());
                    else if(view.getId() == R.id.txt2) target3.setText(txt2.getText());
                    else if(view.getId() == R.id.txt3) target3.setText(txt3.getText());
                    else if(view.getId() == R.id.txt4) target3.setText(txt4.getText());
                    else if(view.getId() == R.id.txt5) target3.setText(txt5.getText());
                    else if(view.getId() == R.id.txt6) target3.setText(txt6.getText());
                    break;
            }
            contador++;
            return true;
        }
    };

    View.OnDragListener dragListener4 = new View.OnDragListener(){
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            switch (dragEvent){
                case DragEvent.ACTION_DROP:
                    final View view = (View) event.getLocalState();
                    if(view.getId() == R.id.txt1) target4.setText(txt1.getText());
                    else if(view.getId() == R.id.txt2) target4.setText(txt2.getText());
                    else if(view.getId() == R.id.txt3) target4.setText(txt3.getText());
                    else if(view.getId() == R.id.txt4) target4.setText(txt4.getText());
                    else if(view.getId() == R.id.txt5) target4.setText(txt5.getText());
                    else if(view.getId() == R.id.txt6) target4.setText(txt6.getText());
                    break;
            }
            return true;
        }
    };

    View.OnDragListener dragListener5 = new View.OnDragListener(){
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            switch (dragEvent){
                case DragEvent.ACTION_DROP:
                    final View view = (View) event.getLocalState();
                    if(view.getId() == R.id.txt1) target5.setText(txt1.getText());
                    else if(view.getId() == R.id.txt2) target5.setText(txt2.getText());
                    else if(view.getId() == R.id.txt3) target5.setText(txt3.getText());
                    else if(view.getId() == R.id.txt4) target5.setText(txt4.getText());
                    else if(view.getId() == R.id.txt5) target5.setText(txt5.getText());
                    else if(view.getId() == R.id.txt6) target5.setText(txt6.getText());
                    break;
            }
            contador++;
            return true;
        }
    };

    View.OnDragListener dragListener6 = new View.OnDragListener(){
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            switch (dragEvent){
                case DragEvent.ACTION_DROP:
                    final View view = (View) event.getLocalState();
                    if(view.getId() == R.id.txt1) target6.setText(txt1.getText());
                    else if(view.getId() == R.id.txt2) target6.setText(txt2.getText());
                    else if(view.getId() == R.id.txt3) target6.setText(txt3.getText());
                    else if(view.getId() == R.id.txt4) target6.setText(txt4.getText());
                    else if(view.getId() == R.id.txt5) target6.setText(txt5.getText());
                    else if(view.getId() == R.id.txt6) target6.setText(txt6.getText());
                    break;
            }
            contador++;
            return true;
        }
    };

    public void respuestas(View view){
        if(contador > 5) {
            int points = getPoints();
            Intent intent = new Intent(this, JuegoRelacionarSolucion.class);
            intent.putExtra("points", points);
            intent.putExtra("ans1", txt1.getText());
            intent.putExtra("ans2", txt2.getText());
            intent.putExtra("ans3", txt3.getText());
            intent.putExtra("ans4", txt4.getText());
            intent.putExtra("ans5", txt5.getText());
            intent.putExtra("ans6", txt6.getText());
            intent.putExtra("sal1", target1.getText());
            intent.putExtra("sal2", target2.getText());
            intent.putExtra("sal3", target3.getText());
            intent.putExtra("sal4", target4.getText());
            intent.putExtra("sal5", target5.getText());
            intent.putExtra("sal6", target6.getText());
            if (points >= 4 && Login.user.getLevel().equals("1")) {
                if (points == 6) {
                    guardarRes(1, "2", 1);
                } else {
                    guardarRes(1, "2", 0);
                }
            } else if (points >= 4 && Login.user.getLevel().equals("2")) {
                if (points == 6) {
                    guardarRes(2, "3", 1);
                } else {
                    guardarRes(2, "3", 1);
                }
            } else if (points >= 4 && Login.user.getLevel().equals("3")) {
                if (points == 6) {
                    guardarRes(3, "4", 1);
                } else {
                    guardarRes(3, "4", 0);
                }
            } else if (points < 4 && Login.user.getLevel().equals("1")) {
                guardarRes(0, "2", 0);
            } else if (points < 4 && Login.user.getLevel().equals("2")) {
                guardarRes(0, "3", 0);
            } else if (points < 4 && Login.user.getLevel().equals("3")) {
                guardarRes(0, "4", 0);
            } else if (Login.user.getLevel().equals("FIN")) {
                if(points >= 4) {
                    guardarRes(1, "FIN", 0);
                }
                else{
                    guardarRes(0, "FIN", 0);
                }
            }
            //enunciadosAgregados.clear();
            startActivity(intent);
        }
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
                "Relacionar",
                String.valueOf(puntajes),
                String.valueOf(totaltime),
                level,
                String.valueOf(insignias));
        atr.execute();
    }

    public int getPoints(){
        int cont = 0;
        if(target1.getText() == txt1.getText()) cont++;
        if(target2.getText() == txt2.getText()) cont++;
        if(target3.getText() == txt3.getText()) cont++;
        if(target4.getText() == txt4.getText()) cont++;
        if(target5.getText() == txt5.getText()) cont++;
        if(target6.getText() == txt6.getText()) cont++;
        return cont;
    }

    public void toHelp(View view){
        startActivity(new Intent(getApplicationContext(), JuegoAyuda.class));
    }
}
