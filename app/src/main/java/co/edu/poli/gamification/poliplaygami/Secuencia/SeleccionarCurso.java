package co.edu.poli.gamification.poliplaygami.Secuencia;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import co.edu.poli.gamification.poliplaygami.Modelo.Utiles;
import co.edu.poli.gamification.poliplaygami.Narrativa.IntroduccionCurso;
import co.edu.poli.gamification.poliplaygami.R;
import co.edu.poli.gamification.poliplaygami.Servicios.Api;
import co.edu.poli.gamification.poliplaygami.Servicios.RequestHandler;

public class SeleccionarCurso extends AppCompatActivity {

    private long back_pressed;
    private ImageView pensamiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_curso);
        Utiles.startCon = System.currentTimeMillis();

        if(!Login.user.getSignature().equals("vacio")){
            startActivity(new Intent(this, IntroduccionCurso.class));
        }
        pensamiento = (ImageView) findViewById(R.id.btn_ambiental);
        pensamiento.setEnabled(false);


    }

    public void selPensamiento(View view) {
        String signature = Login.user.getSignature();
        if(signature.equals("vacio")){
            AddSignature as = new AddSignature("Pensamiento Algoritmico");
            as.execute();
        } else{
            startActivity(new Intent(this, IntroduccionCurso.class));
        }
    }

    public void selAdmin(View view) {
        String signature = Login.user.getSignature();
        if (signature.equals("vacio")) {
            AddSignature as = new AddSignature("Proceso Administrativo");
            as.execute();
        } else {
            startActivity(new Intent(this, IntroduccionCurso.class));
        }
    }

    public void btnVolver(View view){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        if(back_pressed + 2000 > System.currentTimeMillis())
            System.exit(0);
        else
            Toast.makeText(getBaseContext(), R.string.toast_salir, Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }

    class AddSignature extends AsyncTask<Void, Void, String> {
        private String signatureAdd;

        public AddSignature(String signatureAdd){
            this.signatureAdd = signatureAdd;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject obj = new JSONObject(s);
                if (!obj.getBoolean("error")) {
                    Toast.makeText(SeleccionarCurso.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                    JSONObject userJson = obj.getJSONObject("user");
                    Login.user.setSignature(signatureAdd);

                    finish();
                    Utiles.terminarConexion();
                    startActivity(new Intent(getApplicationContext(), IntroduccionCurso.class));
                } else {
                    Toast.makeText(SeleccionarCurso.this, "Invalid data", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();

            HashMap<String, String> params = new HashMap<>();
            params.put("codigo", Login.user.getCode());
            params.put("materia", signatureAdd);

            return requestHandler.sendPostRequest(Api.URL_ADD_SIGNATURE, params);
        }
    }
}
