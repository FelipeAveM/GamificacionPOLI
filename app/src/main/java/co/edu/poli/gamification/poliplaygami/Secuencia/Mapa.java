package co.edu.poli.gamification.poliplaygami.Secuencia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.edu.poli.gamification.poliplaygami.Juegos.JuegoAhorcado;
import co.edu.poli.gamification.poliplaygami.Juegos.JuegoCalculadora;
import co.edu.poli.gamification.poliplaygami.Juegos.JuegoCrucigrama;
import co.edu.poli.gamification.poliplaygami.Juegos.JuegoRelacionar;
import co.edu.poli.gamification.poliplaygami.Juegos.JuegoTrivia;
import co.edu.poli.gamification.poliplaygami.Modelo.TiempoConexionJuego;
import co.edu.poli.gamification.poliplaygami.Modelo.Utiles;
import co.edu.poli.gamification.poliplaygami.R;

public class Mapa extends AppCompatActivity {

    private TextView nombre, materia, rol, monedas, insignias;
    private Button btnNivel1, btnNivel2, btnNivel3, btnNivel4, btnNivel5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        Utiles.startCon = System.currentTimeMillis();
        nombre = (TextView)findViewById(R.id.nombre);
        materia = (TextView)findViewById(R.id.materia);
        rol = (TextView)findViewById(R.id.roll);
        monedas = (TextView)findViewById(R.id.monedas);
        insignias = (TextView)findViewById(R.id.insignias);
        if(Login.user.getLevel().equals("0")){
            guardarRes("1");
            Login.user.setLevel("1");
        }
        if(Login.user.getLevel().equals("FIN")){ //--TMP
            Bundle ext = getIntent().getExtras();
            String yes = ext.getString("back");
            if(!yes.equals("yes")) {
                startActivity(new Intent(getApplicationContext(), JuegoCalculadora.class));
            }
        }

        btnNivel1 = (Button)findViewById(R.id.btnNivel1);
        btnNivel2 = (Button)findViewById(R.id.btnNivel2);
        btnNivel3 = (Button)findViewById(R.id.btnNivel3);
        btnNivel4 = (Button)findViewById(R.id.btnNivel4);
        btnNivel5 = (Button)findViewById(R.id.btnNivel5);

        nombre.setText("Nombre: " + Login.user.getUsername().substring(0,1) + Login.user.getUsername().substring(1));
        materia.setText("Materia: " + Login.user.getSignature());
        rol.setText("Rol: " + Login.user.getRole());
        monedas.setText("Monedas: " + Login.user.getCoins());
        insignias.setText("Insignias: " + Login.user.getBadges());

        enableButton(Login.user.getLevel());
    }

    ////
    public void nivel1(View view){
        if(Login.user.getSignature().equals("Proceso Administrativo")){
            int aleatorio = (int)(Math.random()*2);
            if(aleatorio == 0){
                Intent i = new Intent(this, JuegoTrivia.class);
                Utiles.terminarConexion();
                startActivity(i);
            }
            else if(aleatorio == 1){
                Intent i = new Intent(this, JuegoRelacionar.class);
                Utiles.terminarConexion();
                startActivity(i);
            }
        }
        else if(Login.user.getSignature().equals("Pensamiento Algoritmico")){
            Intent i = new Intent(this, JuegoTrivia.class);
            Utiles.terminarConexion();
            startActivity(i);
        }
    }

    public void nivel2(View view){
        if(Login.user.getSignature().equals("Proceso Administrativo")){
            int aleatorio = (int)(Math.random()*2);
            if(aleatorio == 0){
                Intent i = new Intent(this, JuegoTrivia.class);
                Utiles.terminarConexion();
                startActivity(i);
            }
            else if(aleatorio == 1){
                Intent i = new Intent(this, JuegoCrucigrama.class);
                Utiles.terminarConexion();
                startActivity(i);
            }
        }
        else if(Login.user.getSignature().equals("Pensamiento Algoritmico")){
            Intent i = new Intent(this, JuegoRelacionar.class);
            Utiles.terminarConexion();
            startActivity(i);
        }
    }

    public void nivel3(View view){
        if(Login.user.getSignature().equals("Proceso Administrativo")){
            Intent i = new Intent(this, JuegoAhorcado.class);
            Utiles.terminarConexion();
            startActivity(i);
        }
        else if(Login.user.getSignature().equals("Pensamiento Algoritmico")){
            Intent i = new Intent(this, JuegoRelacionar.class);
            Utiles.terminarConexion();
            startActivity(i);
        }
    }

    public void nivel4(View view){
        Intent i = new Intent(this, JuegoAhorcado.class);
        Utiles.terminarConexion();
        startActivity(i);
    }

    public void nivel5(View view){
        if(Login.user.getSignature().equals("Proceso Administrativo")){
            Intent i = new Intent(this, JuegoAhorcado.class);
            Utiles.terminarConexion();
            startActivity(i);
        }
        else if(Login.user.getSignature().equals("Pensamiento Algoritmico")){
            Intent i = new Intent(this, JuegoCrucigrama.class);
            Utiles.terminarConexion();
            startActivity(i);
        }
    }

    public void enableButton(String nivel){
        if(nivel.equals("1")){
            btnNivel1.setEnabled(true);
            btnNivel2.setEnabled(false);
            btnNivel3.setEnabled(false);
            btnNivel4.setEnabled(false);
            btnNivel5.setEnabled(false);
            btnNivel1.setBackground(getResources().getDrawable(R.drawable.circulo_boton_claro));
            btnNivel2.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro_disable));
            btnNivel3.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro_disable));
            btnNivel4.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro_disable));
            btnNivel5.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro_disable));
        }
        else if(nivel.equals("2")){
            btnNivel1.setEnabled(false);
            btnNivel2.setEnabled(true);
            btnNivel3.setEnabled(false);
            btnNivel4.setEnabled(false);
            btnNivel5.setEnabled(false);
            btnNivel1.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro_disable));
            btnNivel2.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro));
            btnNivel3.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro_disable));
            btnNivel4.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro_disable));
            btnNivel5.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro_disable));
        }
        else if(nivel.equals("3")){
            btnNivel1.setEnabled(false);
            btnNivel2.setEnabled(false);
            btnNivel3.setEnabled(true);
            btnNivel4.setEnabled(false);
            btnNivel5.setEnabled(false);
            btnNivel1.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro_disable));
            btnNivel2.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro_disable));
            btnNivel3.setBackground(getResources().getDrawable(R.drawable.circulo_boton_claro));
            btnNivel4.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro_disable));
            btnNivel5.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro_disable));
        }
        else if(nivel.equals("4")){
            btnNivel1.setEnabled(false);
            btnNivel2.setEnabled(false);
            btnNivel3.setEnabled(false);
            btnNivel4.setEnabled(true);
            btnNivel5.setEnabled(false);
            btnNivel1.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro_disable));
            btnNivel2.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro_disable));
            btnNivel3.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro_disable));
            btnNivel4.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro));
            btnNivel5.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro_disable));
        }
        else if(nivel.equals("5")){
            btnNivel1.setEnabled(false);
            btnNivel2.setEnabled(false);
            btnNivel3.setEnabled(false);
            btnNivel4.setEnabled(false);
            btnNivel5.setEnabled(true);
            btnNivel1.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro_disable));
            btnNivel2.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro_disable));
            btnNivel3.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro_disable));
            btnNivel4.setBackground(getResources().getDrawable(R.drawable.circulo_boton_oscuro_disable));
            btnNivel5.setBackground(getResources().getDrawable(R.drawable.circulo_boton_claro));
        }
    }

    @Override
    public void onBackPressed(){
        //Se deja vacío intencional para bloquear el retorno de actividad.
    }

    public void guardarRes(String level){
        Login.user.setLevel(level);
        TiempoConexionJuego atr = new TiempoConexionJuego(
                Utiles.getFecha(),
                Login.user.getCode(),
                Login.user.getGroup(),
                "Mapa",
                String.valueOf(0),
                String.valueOf(0),
                level,
                String.valueOf(0));
        atr.execute();
    }

    public void goRanking(View view){
        Intent i = new Intent(getApplicationContext(), Ranking.class);
        startActivity(i);
    }
}
