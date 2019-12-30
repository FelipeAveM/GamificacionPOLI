package co.edu.poli.gamification.poliplaygami.Juegos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.io.*;
import java.util.*;

import co.edu.poli.gamification.poliplaygami.Modelo.Utiles;
import co.edu.poli.gamification.poliplaygami.R;

public class JuegoCrucigrama extends AppCompatActivity {

    private int[][] screenMat;
    private char[][] solved = new char[17][14];
    private final char resuelto[][] = new char[17][14];   //Matriz leida desde archivo plano con el crucigrama resuelto
    private final byte posValidas[][] = new byte[17][14]; //Matriz de bits de posiciones donde es válido colocar carcateres
    private final TreeMap<Integer, int[]> formPalabra = new TreeMap<>();
    private final TreeMap<String, String> enunciados = new TreeMap<>();
    private final int posAct[] = new int[2];

    private TextView enun;
    private EditText resp;
    private long start, end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_crucigrama2);
        start = System.currentTimeMillis();
        Utiles.startCon = start;
        llenarScreenMat();
        try {
            lecturaResuelto();
        } catch (Exception e) {
            e.printStackTrace();
        }
        llenarPosicionesValidas();
        pintarCuadricula();
        enun = (TextView) findViewById(R.id.enunciado);
        resp = (EditText) findViewById(R.id.respuesta);
    }

    public void btnTerminar(View view){
        Intent i = new Intent(this, JuegoCrucigramaSolucion.class);
        for(int j = 0; j< solved.length; j++){
            i.putExtra(String.valueOf(j), solved[j]);
        }
        end = System.currentTimeMillis();
        long totaltime = (end-start)/1000;
        i.putExtra("time", totaltime);
        startActivity(i);
    }

    public void tocar(View view) {
        pintarCuadricula();
        TextView a = (TextView) view;
        int pos[] = formPalabra.get(a.getId());
        posAct[0] = pos[0];
        posAct[1] = pos[1];
        if (formPalabra.containsKey(a.getId())) {
            String palabra = getPalabra(pos);
            if (enunciados.containsKey(palabra))
                enun.setText(enunciados.get(palabra));
            else
                enun.setText("Selecciona otra posición");
        }
        resp.setText("");
    }

    public void sendAnswer(View view) {
        String palabra = getPalabra(posAct);
        String respuesta = resp.getText().toString();
        int x = posAct[0];
        int y = posAct[1];
        if (enunciados.containsKey(palabra)) {
            if (enunciados.get(palabra).charAt(0) == 'H') {
                int posInicio = 0;
                loop:
                for (int i = y; i >= 0; i--) {
                    if (posValidas[x][i] == 0) {
                        posInicio = i + 1;
                        break loop;
                    }
                }
                for (int i = posInicio, j = 0; j < respuesta.length(); i++, j++) {
                    if (j == palabra.length()) break;
                    TextView tx = (TextView) findViewById(screenMat[x][i]);
                    tx.setText(String.valueOf(respuesta.charAt(j)).toUpperCase());
                    solved[x][i] = String.valueOf(respuesta.charAt(j)).toUpperCase().charAt(0);
                }
            } else if (enunciados.get(palabra).charAt(0) == 'V') {
                int posInicio = 0;
                loop:
                for (int i = x; i >= 0; i--) {
                    if (posValidas[i][y] == 0) {
                        posInicio = i + 1;
                        break loop;
                    }
                }
                for (int i = posInicio, j = 0; j < respuesta.length(); i++, j++) {
                    if (j == palabra.length()) break;
                    TextView ty = (TextView) findViewById(screenMat[i][y]);
                    ty.setText(String.valueOf(respuesta.charAt(j)).toUpperCase());
                    solved[i][y] = String.valueOf(respuesta.charAt(j)).toUpperCase().charAt(0);
                }
            }
        }
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private String getPalabra(int[] pos) {
        boolean horizontal = true;
        boolean vertical = true;
        int x = pos[0];
        int y = pos[1];
        if (x > -1 && y > -1 && x < 14 && y < 17) {
            if (posValidas[x + 1][y] == 0 || (x - 1 >= 0 ? posValidas[x - 1][y] == 0 : posValidas[x][y] == 0))
                vertical = false;
            else if (posValidas[x][y + 1] == 0 || (y - 1 >= 0 ? posValidas[x][y - 1] == 0 : posValidas[x][y] == 0))
                horizontal = false;
        }
        String palabra = "";
        if (horizontal) {
            for (int i = y; i < posValidas[0].length; i++) {
                if (posValidas[x][i] == 1) {
                    palabra = palabra + String.valueOf(resuelto[x][i]);
                    TextView p = (TextView) findViewById(screenMat[x][i]);
                    p.setBackground(getResources().getDrawable(R.drawable.rectangulo_letra_sel));
                } else break;
            }
            for (int i = y - 1; i >= 0; i--) {
                if (posValidas[x][i] == 1) {
                    String aux = palabra;
                    palabra = String.valueOf(resuelto[x][i]) + aux;
                    TextView p = (TextView) findViewById(screenMat[x][i]);
                    p.setBackground(getResources().getDrawable(R.drawable.rectangulo_letra_sel));
                } else break;
            }
        } else if (vertical) {
            for (int i = x; i < posValidas.length; i++) {
                if (posValidas[i][y] == 1) {
                    palabra = palabra + String.valueOf(resuelto[i][y]);
                    TextView p = (TextView) findViewById(screenMat[i][y]);
                    p.setBackground(getResources().getDrawable(R.drawable.rectangulo_letra_sel));
                } else break;
            }
            for (int i = x - 1; i >= 0; i--) {
                if (posValidas[i][y] == 1) {
                    String aux = palabra;
                    palabra = String.valueOf(resuelto[i][y]) + aux;
                    TextView p = (TextView) findViewById(screenMat[i][y]);
                    p.setBackground(getResources().getDrawable(R.drawable.rectangulo_letra_sel));
                } else break;
            }
        }
        palabra = palabra.replaceAll("\\s", "");
        if (palabra.length() <= 2) {
            palabra = "";
        }
        return palabra;
    }

    private void pintarCuadricula() {
        for (int i = 0; i < posValidas.length; i++) {
            for (int j = 0; j < posValidas[0].length; j++) {
                if (posValidas[i][j] == 1) {
                    TextView pos = (TextView) findViewById(screenMat[i][j]);
                    pos.setBackground(getResources().getDrawable(R.drawable.rectangulo_letra));
                    formPalabra.put(screenMat[i][j], new int[]{i, j});
                } else {
                    TextView pos = (TextView) findViewById(screenMat[i][j]);
                    pos.setEnabled(false);
                }
            }
        }
    }

    private void lecturaResuelto() throws Exception {

        Random random = new Random();
        int crus = random.nextInt(3);
        JuegoCrucigramaSolucion.setNumCrus(crus);
        InputStream archivo;
        switch (crus){
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
        for (int i = 0; i < resuelto.length; i++) {
            String line = llenar.readLine();
            resuelto[i] = line.toCharArray();
        }
        int s = Integer.parseInt(llenar.readLine().trim());
        for (int i = 0; i < s; i++) {
            String line = llenar.readLine();
            StringTokenizer tok = new StringTokenizer(line, ";");
            String key = tok.nextToken();
            String value = tok.nextToken();
            enunciados.put(key, value);
        }
    }

    private void llenarPosicionesValidas() {
        for (int i = 0; i < posValidas.length; i++) {
            for (int j = 0; j < posValidas[0].length; j++) {
                if (resuelto[i][j] == '.')
                    posValidas[i][j] = 0; //Posición vacía (no válida)
                else
                    posValidas[i][j] = 1;//Posicion válida
            }
        }
    }

    private void llenarScreenMat() {
        screenMat = new int[][]{{R.id.l, R.id.l2, R.id.l3, R.id.l4, R.id.l5, R.id.l6, R.id.l7, R.id.l8, R.id.l9, R.id.l10, R.id.l11, R.id.l12, R.id.l13, R.id.l14},
                {R.id.l15, R.id.l16, R.id.l17, R.id.l18, R.id.l19, R.id.l20, R.id.l21, R.id.l22, R.id.l23, R.id.l24, R.id.l25, R.id.l26, R.id.l27, R.id.l28},
                {R.id.l29, R.id.l30, R.id.l31, R.id.l32, R.id.l33, R.id.l34, R.id.l35, R.id.l36, R.id.l37, R.id.l38, R.id.l39, R.id.l40, R.id.l41, R.id.l42},
                {R.id.l43, R.id.l44, R.id.l45, R.id.l46, R.id.l47, R.id.l48, R.id.l49, R.id.l50, R.id.l51, R.id.l52, R.id.l53, R.id.l54, R.id.l55, R.id.l56},
                {R.id.l57, R.id.l58, R.id.l59, R.id.l60, R.id.l61, R.id.l62, R.id.l63, R.id.l64, R.id.l65, R.id.l66, R.id.l67, R.id.l68, R.id.l69, R.id.l70},
                {R.id.l71, R.id.l72, R.id.l73, R.id.l74, R.id.l75, R.id.l76, R.id.l77, R.id.l78, R.id.l79, R.id.l80, R.id.l81, R.id.l82, R.id.l83, R.id.l84},
                {R.id.l85, R.id.l86, R.id.l87, R.id.l88, R.id.l89, R.id.l90, R.id.l91, R.id.l92, R.id.l93, R.id.l94, R.id.l95, R.id.l96, R.id.l97, R.id.l98},
                {R.id.l99, R.id.l100, R.id.l101, R.id.l102, R.id.l103, R.id.l104, R.id.l105, R.id.l106, R.id.l107, R.id.l108, R.id.l109, R.id.l110, R.id.l111, R.id.l112},
                {R.id.l113, R.id.l114, R.id.l115, R.id.l116, R.id.l117, R.id.l118, R.id.l119, R.id.l120, R.id.l121, R.id.l122, R.id.l123, R.id.l124, R.id.l125, R.id.l126},
                {R.id.l127, R.id.l128, R.id.l129, R.id.l130, R.id.l131, R.id.l132, R.id.l133, R.id.l134, R.id.l135, R.id.l136, R.id.l137, R.id.l138, R.id.l139, R.id.l140},
                {R.id.l141, R.id.l142, R.id.l143, R.id.l144, R.id.l145, R.id.l146, R.id.l147, R.id.l148, R.id.l149, R.id.l150, R.id.l151, R.id.l152, R.id.l153, R.id.l154},
                {R.id.l155, R.id.l156, R.id.l157, R.id.l158, R.id.l159, R.id.l160, R.id.l161, R.id.l162, R.id.l163, R.id.l164, R.id.l165, R.id.l166, R.id.l167, R.id.l168},
                {R.id.l169, R.id.l170, R.id.l171, R.id.l172, R.id.l173, R.id.l174, R.id.l175, R.id.l176, R.id.l177, R.id.l178, R.id.l179, R.id.l180, R.id.l181, R.id.l182},
                {R.id.l183, R.id.l184, R.id.l185, R.id.l186, R.id.l187, R.id.l188, R.id.l189, R.id.l190, R.id.l191, R.id.l192, R.id.l193, R.id.l194, R.id.l195, R.id.l196},
                {R.id.l197, R.id.l198, R.id.l199, R.id.l200, R.id.l201, R.id.l202, R.id.l203, R.id.l204, R.id.l205, R.id.l206, R.id.l207, R.id.l208, R.id.l209, R.id.l210},
                {R.id.l211, R.id.l212, R.id.l213, R.id.l214, R.id.l215, R.id.l216, R.id.l217, R.id.l218, R.id.l219, R.id.l220, R.id.l221, R.id.l222, R.id.l223, R.id.l224},
                {R.id.l225, R.id.l226, R.id.l227, R.id.l228, R.id.l229, R.id.l230, R.id.l231, R.id.l232, R.id.l233, R.id.l234, R.id.l235, R.id.l236, R.id.l237, R.id.l238}};
    }
}
