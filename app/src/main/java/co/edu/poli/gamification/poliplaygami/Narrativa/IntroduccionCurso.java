package co.edu.poli.gamification.poliplaygami.Narrativa;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import co.edu.poli.gamification.poliplaygami.Modelo.Utiles;
import co.edu.poli.gamification.poliplaygami.Secuencia.Login;
import co.edu.poli.gamification.poliplaygami.R;
import co.edu.poli.gamification.poliplaygami.Secuencia.SeleccionarRol;

public class IntroduccionCurso extends AppCompatActivity {

    private ConstraintLayout layout_intro_curso;
    private int cont = 0;
    private boolean curso; //true: Administrativo   ----   false: Ambiental

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduccion_curso);
        if(!Login.user.getRole().equals("vacio")){
            Intent i = new Intent(this, SeleccionarRol.class);
            startActivity(i);
        }
        Utiles.startCon = System.currentTimeMillis();
        layout_intro_curso = (ConstraintLayout)findViewById(R.id.layout_intro_curso);
        setImages();
    }

    public void goForward(View view){
        if(cont == 0 && curso){
            layout_intro_curso.setBackground(getResources().getDrawable(R.drawable.descripcion_intro_proceso_dos));
            cont++;
            return;
        }
        else if(cont == 1 && curso){
            layout_intro_curso.setBackground(getResources().getDrawable(R.drawable.descipcion_intro_proceso_tres));
            cont++;
            return;
        }
        else if(cont == 2 && curso){
            Intent i = new Intent(this, SeleccionarRol.class);
            Utiles.terminarConexion();
            startActivity(i);
        }
        else if(!curso){
            Intent i = new Intent(this,SeleccionarRol.class);
            Utiles.terminarConexion();
            startActivity(i);
        }
    }

    private void setImages(){
        if(Login.user.getSignature().equals("Proceso Administrativo")){
            layout_intro_curso.setBackground(getResources().getDrawable(R.drawable.descripcion_intro_proceso_uno));
            curso = true;
        }
        else if(Login.user.getSignature().equals("Pensamiento Algoritmico")){
            layout_intro_curso.setBackground(getResources().getDrawable(R.drawable.descripcion_intro_proceso_uno));
            curso = true;
        }
    }
}
