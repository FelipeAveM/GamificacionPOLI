package co.edu.poli.gamification.poliplaygami.Juegos;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import co.edu.poli.gamification.poliplaygami.Modelo.TiempoConexionJuego;
import co.edu.poli.gamification.poliplaygami.Modelo.Utiles;
import co.edu.poli.gamification.poliplaygami.Secuencia.Login;
import co.edu.poli.gamification.poliplaygami.Secuencia.Mapa;
import co.edu.poli.gamification.poliplaygami.R;

public class JuegoAhorcado extends AppCompatActivity {

    int index = 0;
    int intentos = 7;
    boolean cambio = false;
    private TextView intentosRes;
    private TextView hideWord;
    private TextView showWord;
    private TextView intentosRestantes;
    private TextView showEnum;
    private Random randomWords = new Random();
    private Random randomLetters = new Random();
    private String[] palabras = {

            "ADMINISTRACION", "EFECTIVO", "TACTICOS", "CIENTIFICO", "ESTRATEGICO", "EVALUACION", "DELEGAR", "ORGANIZACION", "CONTROL", "POLITICAS", "RETROALIMENTACION","CORRECCION","DESVIACION","INDICADOR","CONTROL","CONTROL","RECLUTAMIENTO","INTEGRACION","ESCRITA","HORIZONTAL","VERTICAL","COMUNICACIÓN","PLANDEACCION","IMPLEMENTACION","RETROALIMENTACION", "EMPODERAMIENTO", "LINEA", "DELEGACION", "ORGANICA"
    };

    private String[] enunciadosPalabras = {


            "Proceso que se centra en planear, integrar, dirigir y controlar con el fin de coordinar y aprovechar los recursos y las personas para lograr los objetivos de una organización con eficacia y eficiencia",
            "Al maximizar la productividad de la organización, utilizando de manera óptima los recursos tanto materiales como intelectuales, cumpliendo objetivos, se puede deducir que el administrador es",
            "Existe un tipo de plan que se utiliza para alcanzar y apoyar el plan estratégico y se establece en el mediano plazo, escriba el tipo de plan a que se refiere la frase anterior.",
            "Se dice que la administración está definida como ciencia, ya que parte de un conjunto de conocimientos sistematizados y estructurados, que generan uno principios y teorías, de lo anterior se deduce que utiliza el método",
            "Cuando se dice que este es el fundamento de la empresa; expresa las decisiones a futuro y va de 2 a 10 años. Los elementos que componen este nivel son la misión, la visión, los objetivos y los valores, los cuales deben ser robustos, pero con un grado de flexibilidad para adaptarse a las condiciones futuras, se está hablando de un plan",
            "Cuando se habla de parámetros que miden cómo va el proceso, si cada uno de los planes se ha realizado, se fijan criterios para determinar con precisión el objetivo (largo plazo) y sus metas (corto plazo) estamos hablando de",
            "Proceso de conferir la autoridad de una tarea específica o conjunto de actividades a otro miembro de la organización, junto con la responsabilidad y el poder para tomar decisiones.",
            "Relaciones entre niveles jerárquicos para alcanzar los objetivos, que se articulan mediante el proceso de división del trabajo, coordinación y delegación, se refieren a etapas del proceso de",
            "Esta función está estrechamente ligada a la función de planeación, por lo que al ejecutar el proceso se cotejan estas dos funciones para medir así el éxito empresarial, por lo tanto, se está hablando de",
            "El proceso de planeación lo conforma varios elementos entre los cuales existe uno que establece acciones orientan/facilitan las decisiones, lo anterior se refiere a",
            "Proporciona elementos para efectuar mejoras en el proceso administrativo y para prever la posible existencia de fallas y errores.",
            "Acción y aplicación de medidas para eliminar las desviaciones o no conformidades con relación a los estándares o indicadores.",
            "Diferencia entre el desempeño real y el estándar.",
            "Una unidad de medida que sirve como patrón para efectuar el control",
            "Fase del proceso administrativo a través del cual se establecen los estándares para medir los resultados obtenidos con el fin de corregir desviaciones, prevenirlas y mejorar continuamente el desempeño de la empresa.",
            "Evaluar el desempeño de la organización, sus áreas e individuos para apreciar el progreso en la dirección deseada. Esta definición se refiere a:",
            "Consiste en convocar o allegar el mayor número de candidatos de un puesto para seleccionar al idóneo.",
            "Función a través de la cual se eligen y obtienen los recursos necesarios para poner en marcha las decisiones requeridas para ejecutar los planes de la organización.",
            "La comunicación que se da a través de circulares, memorando, email y cualquier otro medio gráfico:",
            "Tipo de comunicación que prevalece en los niveles jerárquicos semejantes o entre pares: memorandos, circulares, juntas, comités.",
            "Sucede cuando la comunicación fluye de un nivel administrativo superior a uno inferior, o viceversa: quejas, reportes, sugerencias, órdenes, instrucciones, este tipo de comunicación es:",
            "Proceso a través del cual se transmite y recibe información.",
            "Una vez se ha elegido la alternativa más óptima para el proceso de toma de decisiones, el paso posterior para hacer seguimiento a los resultados es",
            "Última etapa del proceso de toma de decisiones:",
            "La cantidad de información que recibe un trabajador sobre su desempeño",
            "Sensación de motivación intrínseca en la que los trabajadores perciben que su trabajo tiene impacto y significado y ellos se sienten capaces y competentes para actuar con autodeterminación. ",
            "Autoridad de _____ El derecho de mando sobre las personas inmediatamente subordinadas en la cadena de comando",
            "La asignación de autoridad directa y responsabilidad a un subordinado para completar tareas por las que el jefe es responsable normalmente.",
            "Sensación de motivación intrínseca en la que los trabajadores perciben que su trabajo tiene impacto y significado y ellos se sienten capaces y competentes para actuar con autodeterminación"};


    private TextView[] letters = new TextView[21];
    private ImageView animador;

    private Handler waiter = new Handler();
    private Runnable runner;

    private long start, end;
    private long timestart, timend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_ahorcado);
        Utiles.startCon = System.currentTimeMillis();
        start = System.currentTimeMillis();
        timestart = System.currentTimeMillis();
        intentosRes = (TextView) findViewById(R.id.intenRes);
        intentosRestantes = (TextView) findViewById(R.id.intentosRest);
        hideWord = (TextView) findViewById(R.id.hideWord);
        showEnum = (TextView) findViewById(R.id.showEnun);
        animador = (ImageView) findViewById(R.id.animacion);
        animador.setImageDrawable(getResources().getDrawable(R.drawable.ahorcado0));

        palabraRandom();
        enunciadoRand();

        letters[0] = (TextView) findViewById(R.id.let1);
        letters[1] = (TextView) findViewById(R.id.let2);
        letters[2] = (TextView) findViewById(R.id.let3);
        letters[3] = (TextView) findViewById(R.id.let4);
        letters[4] = (TextView) findViewById(R.id.let5);
        letters[5] = (TextView) findViewById(R.id.let6);
        letters[6] = (TextView) findViewById(R.id.let7);
        letters[7] = (TextView) findViewById(R.id.let8);
        letters[8] = (TextView) findViewById(R.id.let9);
        letters[9] = (TextView) findViewById(R.id.let10);
        letters[10] = (TextView) findViewById(R.id.let11);
        letters[11] = (TextView) findViewById(R.id.let12);
        letters[12] = (TextView) findViewById(R.id.let13);
        letters[13] = (TextView) findViewById(R.id.let14);
        letters[14] = (TextView) findViewById(R.id.let15);
        letters[15] = (TextView) findViewById(R.id.let16);
        letters[16] = (TextView) findViewById(R.id.let17);
        letters[17] = (TextView) findViewById(R.id.let18);
        letters[18] = (TextView) findViewById(R.id.let19);
        letters[19] = (TextView) findViewById(R.id.let20);
        letters[20] = (TextView) findViewById(R.id.let21);
        letrasRandom();
    }

    public void comprobar(View vista) throws Exception {
        TextView t = (TextView) vista;
        String partsWord = palabras[index];
        String unlockWords = hideWord.getText().toString();
        String[] c = unlockWords.split(" +");
        String d = t.getText().toString();
        for (int i = 0; i < partsWord.length(); i++) {
            if (partsWord.charAt(i) == d.charAt(0)) {
                c[i] = d;
                cambio = true;
                t.setTextColor(getResources().getColor(R.color.colorSecondary));
                t.setEnabled(false);
            }
        }
        char[] e = new char[(c.length * 2) - 1];
        int contador = 0;
        for (int i = 0; i < e.length; i++) {
            if (i % 2 == 1) {
                e[i] = ' ';
            } else if (i % 2 == 0) {
                e[i] = c[contador].charAt(0);
                contador++;
            }
        }
        String f = String.valueOf(e).trim();
        hideWord.setText(f);
        if (!cambio) {
            intRestantes();
        }
        cambio = false;
        //Método que no deja jugar más si pierde
        gameOver();
    }

    public void gameOver() throws Exception {
        String termino = hideWord.getText().toString();
        termino.split(" +");
        int contador = 0;
        for (int i = 0; i < termino.length(); i++) {
            if (termino.charAt(i) == '_') {
                contador++;
            }
        }
        if (contador == 0) {
            for (int i = 0; i < letters.length; i++) {
                letters[i].setTextColor(getResources().getColor(R.color.colorSecondary));
                letters[i].setEnabled(false);
            }
            runner = new Runnable() {
                @Override
                public void run() {
                    alMapa();
                }
            };
            hideWord.setText("Solucionado");
            timend = System.currentTimeMillis();
            long totaltimes = (timend - timestart) / 1000;
            if(Login.user.getLevel().equals("3")) {
                if(totaltimes <= 30){
                    guardarRes(3, "4", 1);
                }
                else{
                    guardarRes(3, "4", 0);
                }
            }
            else if(Login.user.getLevel().equals("4")){
                if(totaltimes <= 30) {
                    guardarRes(4, "5", 1);
                }
                else{
                    guardarRes(4, "5", 0);
                }
            }
            else if(Login.user.getLevel().equals("5")){
                if(totaltimes <= 30) {
                    guardarRes(5, "FIN", 1);
                }
                else{
                    guardarRes(5, "FIN", 0);
                }
            }
            else if(Login.user.getLevel().equals("FIN")){
                guardarRes(1, "FIN", 0);
            }
            waiter.postDelayed(runner, 2500);
        }
        if (intentos == 0) {
            runner = new Runnable() {
                @Override
                public void run() {
                    alMapa();
                }
            };
            hideWord.setText("No solucionado");
            String res = hideWord.getText().toString();
            if(Login.user.getLevel().equals("3")) {
                guardarRes(0, "4", 0);
            }
            else if(Login.user.getLevel().equals("4")){
                guardarRes(0, "5", 0);
            }
            else if(Login.user.getLevel().equals("5")){
                guardarRes(0, "FIN", 0);
            }
            else if(Login.user.getLevel().equals("FIN")){
                guardarRes(0, "FIN", 0);
            }
            waiter.postDelayed(runner, 2500);
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
                "Ahorcado",
                String.valueOf(puntajes),
                String.valueOf(totaltime),
                level,
                String.valueOf(insignias));
        atr.execute();
    }

    public void cambioImagen() {
        switch (intentos) {
            case 6:
                animador.setImageDrawable(getResources().getDrawable(R.drawable.ahorcado1));
                break;
            case 5:
                animador.setImageDrawable(getResources().getDrawable(R.drawable.ahorcado2));
                break;
            case 4:
                animador.setImageDrawable(getResources().getDrawable(R.drawable.ahorcado3));
                break;
            case 3:
                animador.setImageDrawable(getResources().getDrawable(R.drawable.ahorcado4));
                break;
            case 2:
                animador.setImageDrawable(getResources().getDrawable(R.drawable.ahorcado5));
                break;
            case 1:
                animador.setImageDrawable(getResources().getDrawable(R.drawable.ahorcado6));
                break;
            case 0:
                animador.setImageDrawable(getResources().getDrawable(R.drawable.ahorcado7));
                break;
            default:
                animador.setImageDrawable(getResources().getDrawable(R.drawable.ahorcado0));
        }
    }

    public void intRestantes() {
        intentos--;
        intentosRestantes.setText(String.valueOf(intentos));
        cambioImagen();
        if (intentos == 0) {
            for (int i = 0; i < letters.length; i++) {
                letters[i].setEnabled(false);
            }
        }
    }

    public void palabraRandom() {
        int rand = randomWords.nextInt(palabras.length);
        hideWord.setText(palabras[rand]);
        String hideR = "";
        for (int i = 0; i < hideWord.getText().toString().length(); i++) {
            hideR += "_ ";
        }

        hideWord.setText(hideR.trim());
        index = rand;
    }

    public void enunciadoRand() {
        showEnum.setText(enunciadosPalabras[index]);
    }

    public void letrasRandom() {
        String word = palabras[index];
        char[] an_g = word.toCharArray();
        Set<Character> conjunto = new TreeSet<Character>();
        int j = 0;
        while (conjunto.size() < 21) {
            if (j < word.length()) {
                conjunto.add(an_g[j]);
                j++;
            } else {
                int rand = randomLetters.nextInt(26);
                conjunto.add((char) (rand + 65));
            }
        }
        String ale = "";
        while (conjunto.size() != 0) {
            char aux = ((TreeSet<Character>) conjunto).first();
            ale += String.valueOf(aux);
            conjunto.remove(aux);
        }
        for (int i = 0; i < 21; i++) {
            letters[i].setText(String.valueOf(ale.charAt(i)));
        }
    }

    public void alMapa() {
        Intent i = new Intent(this, Mapa.class);
        i.putExtra("back", "no");
        Utiles.terminarConexion();
        startActivity(i);
    }
}
