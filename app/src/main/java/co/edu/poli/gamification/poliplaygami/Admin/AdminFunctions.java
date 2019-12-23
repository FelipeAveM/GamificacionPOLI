package co.edu.poli.gamification.poliplaygami.Admin;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

import co.edu.poli.gamification.poliplaygami.Modelo.Utiles;
import co.edu.poli.gamification.poliplaygami.Narrativa.IntroTrans;
import co.edu.poli.gamification.poliplaygami.R;
import co.edu.poli.gamification.poliplaygami.Secuencia.Login;
import co.edu.poli.gamification.poliplaygami.Secuencia.Registro;
import co.edu.poli.gamification.poliplaygami.Servicios.Api;
import co.edu.poli.gamification.poliplaygami.Servicios.RequestHandler;

public class AdminFunctions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_functions);
    }

    public void toLogin(View view){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    public void resetDB(View view){
        Reset reset = new Reset();
        reset.execute();
        Toast.makeText(this, "...Borrado..", Toast.LENGTH_SHORT).show();
    }

    class Reset extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();
            return requestHandler.sendGetRequest(Api.URL_RESET);
        }
    }
}
