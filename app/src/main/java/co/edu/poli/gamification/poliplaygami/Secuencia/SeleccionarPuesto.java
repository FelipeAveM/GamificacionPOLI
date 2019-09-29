package co.edu.poli.gamification.poliplaygami.Secuencia;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import co.edu.poli.gamification.poliplaygami.Modelo.Utiles;
import co.edu.poli.gamification.poliplaygami.Narrativa.InicioViaje;
import co.edu.poli.gamification.poliplaygami.R;
import co.edu.poli.gamification.poliplaygami.Servicios.Api;
import co.edu.poli.gamification.poliplaygami.Servicios.RequestHandler;

import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class SeleccionarPuesto extends AppCompatActivity {

    private LinearLayout verticalLay;
    private Button buttons[] = new Button[506];
    String numToList="";
    ArrayList <Integer> toDisable = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_puesto);
        if(!Login.user.getGroup().equals("vacio")){
            startActivity(new Intent(getApplicationContext(), SeleccionarTransporte.class));
        }
        verticalLay = (LinearLayout) findViewById(R.id.verticalLayOut);
        for (int i = 1; i < 12; i++) {
            LinearLayout hl = new LinearLayout(this);
            hl.setOrientation(LinearLayout.HORIZONTAL);
            agregarBotones(hl, i);
            verticalLay.addView(hl);
        }
        checkAndFillPuestos();
    }

    public void agregarBotones(LinearLayout hl, int l){
        for (int i = 1; i < 6; i++) {
            Button tv = new Button(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f);
            tv.setBackground(getResources().getDrawable(R.drawable.boton_sillas_en));
            tv.setLayoutParams(lp);
            tv.setId((l*10)+i);
            tv.setText(l+"-"+String.valueOf((char)(i+64)));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAndFillPuestos();
                    if(!toDisable.contains(v.getId())){
                        String r = String.valueOf(v.getId());
                        AddGroup ar = new AddGroup(r);
                        ar.execute();
                        v.setEnabled(false);
                        v.setBackground(getResources().getDrawable(R.drawable.boton_sillas_dis));
                        startActivity(new Intent(getApplicationContext(), InicioViaje.class));
                    }
                    else{
                        Toast.makeText(SeleccionarPuesto.this, "¡Te ganaron esta silla, intenta con otra!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            hl.addView(tv);
            buttons[(l*10)+i] = tv;
        }
    }
    public void checkAndFillPuestos(){
        toDisable.clear();
        String algo = null;
        try {
            if(Login.user.getSignature().equals("Proceso Administrativo")) algo = new CheckinSeleccionarPRAD().execute().get();
            else if(Login.user.getSignature().equals("Pensamiento Algoritmico")) algo = new CheckinSeleccionarPEAL().execute().get();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < algo.length(); i++) {
            if(Character.isDigit(algo.charAt(i))){
                numToList += algo.charAt(i);
            }
            else if(numToList != ""){
                toDisable.add(Integer.parseInt(numToList));
                numToList="";
            }
            else continue;
        }
        for (int i = 0; i < toDisable.size(); i++) {
            buttons[toDisable.get(i)].setEnabled(false);
            buttons[toDisable.get(i)].setBackground(getResources().getDrawable(R.drawable.boton_sillas_dis));
        }
    }

    class AddGroup extends AsyncTask<Void, Void, String> {
        private String groupAdd;
        public AddGroup(String groupAdd){
            this.groupAdd = groupAdd;
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
                    Toast.makeText(SeleccionarPuesto.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                    JSONObject userJson = obj.getJSONObject("user");
                    Login.user.setGroup(groupAdd);
                    finish();
                    Utiles.terminarConexion();
                    startActivity(new Intent(getApplicationContext(), InicioViaje.class));
                } else {
                    Toast.makeText(SeleccionarPuesto.this, "Invalid data", Toast.LENGTH_SHORT).show();
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
            params.put("grupo", groupAdd);
            return requestHandler.sendPostRequest(Api.URL_ADD_GROUP, params);
        }
    }

    class CheckinSeleccionarPRAD extends AsyncTask<Void, Void, String>{
        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();
            return requestHandler.sendGetRequest(Api.URL_GET_CHECKIN_PRAD);
        }
    }
    class CheckinSeleccionarPEAL extends AsyncTask<Void, Void, String>{
        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();
            return requestHandler.sendGetRequest(Api.URL_GET_CHECKIN_PEAL);
        }
    }

}