package co.edu.poli.gamification.poliplaygami.Juegos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.*;
import java.util.*;

import co.edu.poli.gamification.poliplaygami.Modelo.TiempoConexionJuego;
import co.edu.poli.gamification.poliplaygami.Modelo.Utiles;
import co.edu.poli.gamification.poliplaygami.Secuencia.Login;
import co.edu.poli.gamification.poliplaygami.Secuencia.Mapa;
import co.edu.poli.gamification.poliplaygami.R;

public class JuegoCrucigramaSolucion extends AppCompatActivity {

    private int[][] screenMatSol;
    private char[][] screenMatRes = new char[17][14];
    private final char resueltoSol[][] = new char[17][14];   //Matriz leida desde archivo plano con el crucigrama resueltoSol
    private final byte posValidasSol[][] = new byte[17][14]; //Matriz de bits de posiciones donde es válido colocar carcateres
    private final TreeMap<Integer, int[]> formPalabraSol = new TreeMap<>();
    private final TreeMap<String, String> enunciadosSol = new TreeMap<>();

    public static void setNumCrus(int numCrus) {
        JuegoCrucigramaSolucion.numCrus = numCrus;
    }

    public static int numCrus;

    private TextView enunciadoSol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_crucigrama_solucion);
        getScreenMatRes();
        llenarScreenMat();
        try {
            lecturaResuelto();
        } catch (Exception e) {
            e.printStackTrace();
        }
        llenarPosicionesValidas();
        pintarCuadricula();

        enunciadoSol = (TextView)findViewById(R.id.enunciadoSol);
        enunciadoSol.setText("Bien hecho");

        onBackPressed();
    }

    public void continuarBtn(View view){
        Intent i = new Intent(this, Mapa.class);
        i.putExtra("back", "no");
        Utiles.terminarConexion();
        startActivity(i);
    }

    private void getScreenMatRes(){
        Bundle ext = getIntent().getExtras();
        for(int i = 0; i < screenMatRes.length; i++){
            screenMatRes[i] = ext.getCharArray(String.valueOf(i));
        }

    }

    private void pintarCuadricula() {
        int correct = 0;
        int total = 0;
        for (int i = 0; i < posValidasSol.length; i++) {
            for (int j = 0; j < posValidasSol[0].length; j++) {
                if (posValidasSol[i][j] == 1) {
                    total ++;
                    TextView pos = (TextView) findViewById(screenMatSol[i][j]);
                    pos.setBackground(getResources().getDrawable(R.drawable.rectangulo_letra));
                    pos.setText(String.valueOf(resueltoSol[i][j]));
                    formPalabraSol.put(screenMatSol[i][j], new int[]{i, j});

                    char letraSol = screenMatRes[i][j];
                    if(resueltoSol[i][j] == letraSol){
                        pos.setTextColor(getResources().getColor(R.color.colorWhite));
                        correct ++;
                    }
                    else{
                        pos.setTextColor(getResources().getColor(R.color.colorAccent));
                    }
                } else {
                    TextView pos = (TextView) findViewById(screenMatSol[i][j]);
                    pos.setEnabled(false);
                }
            }
        }
        int prom = (total * 60) / 100;
        String time = String.valueOf(getIntent().getExtras().getLong("time"));
        if(correct >= prom && Login.user.getLevel().equals("2")){
            if(total == correct) {
                guardarRes(2, "3", time, 1);
            }
            else{
                guardarRes(2, "3", time, 0);
            }
        }
        else if(correct >= prom && Login.user.getLevel().equals("5")){
            if(total == correct) {
                guardarRes(5, "FIN", time, 1);
            }
            else{
                guardarRes(5, "FIN", time, 0);
            }
        }
        else if(correct < prom && Login.user.getLevel().equals("2")){
            guardarRes(0, "3", time, 0);
        }
        else if(correct < prom && Login.user.getLevel().equals("5")){
            guardarRes(0, "FIN", time, 0);
        }
        else if(Login.user.getLevel().equals("FIN")){
            if(correct < prom){
                guardarRes(0, "FIN", time, 0);
            }
            else if(correct >= prom){
                guardarRes(1, "FIN", time, 0);
            }
            else guardarRes(0, "FIN", time, 0);;

        }
    }

    public void guardarRes(int res, String level, String time, int insignia){
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
                "Crucigrama",
                String.valueOf(puntajes),
                String.valueOf(time),
                level,
                String.valueOf(insignias));
        atr.execute();
    }

    private void lecturaResuelto() throws Exception {
        InputStream archivo;
        switch (numCrus){
            case 1:
                archivo = getResources().openRawResource(R.raw.field);
                break;
            case 2:
                archivo = getResources().openRawResource(R.raw.field);
                break;
            default:
                archivo = getResources().openRawResource(R.raw.field);
                break;
        }
        BufferedReader llenar = new BufferedReader(new InputStreamReader(archivo));
        for (int i = 0; i < resueltoSol.length; i++) {
            String line = llenar.readLine();
            resueltoSol[i] = line.toCharArray();
        }
        int s = Integer.parseInt(llenar.readLine().trim());
        for (int i = 0; i < s; i++) {
            String line = llenar.readLine();
            StringTokenizer tok = new StringTokenizer(line, ";");
            String key = tok.nextToken();
            String value = tok.nextToken();
            enunciadosSol.put(key, value);
        }
    }

    private void llenarPosicionesValidas() {
        for (int i = 0; i < posValidasSol.length; i++) {
            for (int j = 0; j < posValidasSol[0].length; j++) {
                if (resueltoSol[i][j] == '.')
                    posValidasSol[i][j] = 0; //Posición vacía (no válida)
                else
                    posValidasSol[i][j] = 1;//Posicion válida
            }
        }
    }

    @Override
    public void onBackPressed(){
        //Se deja vacío intencional para bloquear el retorno de actividad.
    }

    private void llenarScreenMat() {
        screenMatSol = new int[][]{{R.id.s, R.id.s2, R.id.s3, R.id.s4, R.id.s5, R.id.s6, R.id.s7, R.id.s8, R.id.s9, R.id.s10, R.id.s11, R.id.s12, R.id.s13, R.id.s14},
                {R.id.s15, R.id.s16, R.id.s17, R.id.s18, R.id.s19, R.id.s20, R.id.s21, R.id.s22, R.id.s23, R.id.s24, R.id.s25, R.id.s26, R.id.s27, R.id.s28},
                {R.id.s29, R.id.s30, R.id.s31, R.id.s32, R.id.s33, R.id.s34, R.id.s35, R.id.s36, R.id.s37, R.id.s38, R.id.s39, R.id.s40, R.id.s41, R.id.s42},
                {R.id.s43, R.id.s44, R.id.s45, R.id.s46, R.id.s47, R.id.s48, R.id.s49, R.id.s50, R.id.s51, R.id.s52, R.id.s53, R.id.s54, R.id.s55, R.id.s56},
                {R.id.s57, R.id.s58, R.id.s59, R.id.s60, R.id.s61, R.id.s62, R.id.s63, R.id.s64, R.id.s65, R.id.s66, R.id.s67, R.id.s68, R.id.s69, R.id.s70},
                {R.id.s71, R.id.s72, R.id.s73, R.id.s74, R.id.s75, R.id.s76, R.id.s77, R.id.s78, R.id.s79, R.id.s80, R.id.s81, R.id.s82, R.id.s83, R.id.s84},
                {R.id.s85, R.id.s86, R.id.s87, R.id.s88, R.id.s89, R.id.s90, R.id.s91, R.id.s92, R.id.s93, R.id.s94, R.id.s95, R.id.s96, R.id.s97, R.id.s98},
                {R.id.s99, R.id.s100, R.id.s101, R.id.s102, R.id.s103, R.id.s104, R.id.s105, R.id.s106, R.id.s107, R.id.s108, R.id.s109, R.id.s110, R.id.s111, R.id.s112},
                {R.id.s113, R.id.s114, R.id.s115, R.id.s116, R.id.s117, R.id.s118, R.id.s119, R.id.s120, R.id.s121, R.id.s122, R.id.s123, R.id.s124, R.id.s125, R.id.s126},
                {R.id.s127, R.id.s128, R.id.s129, R.id.s130, R.id.s131, R.id.s132, R.id.s133, R.id.s134, R.id.s135, R.id.s136, R.id.s137, R.id.s138, R.id.s139, R.id.s140},
                {R.id.s141, R.id.s142, R.id.s143, R.id.s144, R.id.s145, R.id.s146, R.id.s147, R.id.s148, R.id.s149, R.id.s150, R.id.s151, R.id.s152, R.id.s153, R.id.s154},
                {R.id.s155, R.id.s156, R.id.s157, R.id.s158, R.id.s159, R.id.s160, R.id.s161, R.id.s162, R.id.s163, R.id.s164, R.id.s165, R.id.s166, R.id.s167, R.id.s168},
                {R.id.s169, R.id.s170, R.id.s171, R.id.s172, R.id.s173, R.id.s174, R.id.s175, R.id.s176, R.id.s177, R.id.s178, R.id.s179, R.id.s180, R.id.s181, R.id.s182},
                {R.id.s183, R.id.s184, R.id.s185, R.id.s186, R.id.s187, R.id.s188, R.id.s189, R.id.s190, R.id.s191, R.id.s192, R.id.s193, R.id.s194, R.id.s195, R.id.s196},
                {R.id.s197, R.id.s198, R.id.s199, R.id.s200, R.id.s201, R.id.s202, R.id.s203, R.id.s204, R.id.s205, R.id.s206, R.id.s207, R.id.s208, R.id.s209, R.id.s210},
                {R.id.s211, R.id.s212, R.id.s213, R.id.s214, R.id.s215, R.id.s216, R.id.s217, R.id.s218, R.id.s219, R.id.s220, R.id.s221, R.id.s222, R.id.s223, R.id.s224},
                {R.id.s225, R.id.s226, R.id.s227, R.id.s228, R.id.s229, R.id.s230, R.id.s231, R.id.s232, R.id.s233, R.id.s234, R.id.s235, R.id.s236, R.id.s237, R.id.s238}};
    }
}
