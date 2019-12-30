package co.edu.poli.gamification.poliplaygami.Admin;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

    private EditText sillasEstudiantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_functions);
        sillasEstudiantes = findViewById(R.id.sillasEst);
    }

    public void toLogin(View view){
        Intent i = new Intent(this, Login.class);
        Login.user = null;
        startActivity(i);
    }

    public void resetDB(View view){
        Reset reset = new Reset();
        reset.execute();
        Toast.makeText(this, "¡Borrado!", Toast.LENGTH_SHORT).show();
    }

    public void setChairs(View view){
        String sillas = sillasEstudiantes.getText().toString().trim();
        ChairsDB chairsDB = new ChairsDB(sillas);
        chairsDB.execute();
        Toast.makeText(this, "¡...Cambiando!", Toast.LENGTH_SHORT).show();
        sillasEstudiantes.setText("");
    }
    class ChairsDB extends AsyncTask<Void, Void, String> {
        private String sillas;
        public ChairsDB(String sillas){
            this.sillas = sillas;
        }
        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();
            HashMap<String, String> params = new HashMap<>();
            params.put("grupo", sillas);
            return requestHandler.sendPostRequest(Api.URL_SET_CHAIRS, params);
        }

    }

    class Reset extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();
            return requestHandler.sendGetRequest(Api.URL_RESET);
        }
    }
}
