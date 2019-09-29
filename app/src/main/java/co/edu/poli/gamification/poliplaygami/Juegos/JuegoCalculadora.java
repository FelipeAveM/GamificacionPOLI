package co.edu.poli.gamification.poliplaygami.Juegos;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import co.edu.poli.gamification.poliplaygami.R;
import co.edu.poli.gamification.poliplaygami.Secuencia.Login;
import co.edu.poli.gamification.poliplaygami.Secuencia.Mapa;
import co.edu.poli.gamification.poliplaygami.Servicios.Api;
import co.edu.poli.gamification.poliplaygami.Servicios.RequestHandler;

public class JuegoCalculadora extends AppCompatActivity {

    public TextView monedasGrupo, monedasPersonal;
    public Button volverMapa;
    public ImageView transporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_calculadora);
        monedasGrupo = (TextView)findViewById(R.id.monedasGrupo);
        monedasPersonal = (TextView)findViewById(R.id.monedasPersonal);
        volverMapa = (Button)findViewById(R.id.volverMapa);
        transporte = (ImageView)findViewById(R.id.transporte);

        monedasGrupo.setText("Cargando monedas...");
        monedasPersonal.setText("Tienes " + Login.user.getCoins() + " monedas.");
        cargarMonedasGrupo();
    }

    public void cargarMonedasGrupo(){
        class ObtenerMonedas extends AsyncTask<Void, Void, String> {

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
                        //Toast.makeText(JuegoCalculadora.this, obj.getString("message"), Toast.LENGTH_SHORT).show();

                        JSONObject arrJson = obj.getJSONObject("grupos");
                        String group = Login.user.getGroup();
                        String grupo = arrJson.getString(group.substring(0, group.length()-1));
                        monedasGrupo.setText("Monedas de tu grupo: " + grupo);

                        int mon = Integer.parseInt(grupo);
                        if(mon >= 75){
                            transporte.setImageDrawable(getResources().getDrawable(R.drawable.transporte_submarino));
                        }
                        else if(mon >= 60){
                            transporte.setImageDrawable(getResources().getDrawable(R.drawable.transporte_barco));
                        }
                        else if(mon >= 45){
                            transporte.setImageDrawable(getResources().getDrawable(R.drawable.transporte_globo));
                        }
                        else if(mon >= 30){
                            transporte.setImageDrawable(getResources().getDrawable(R.drawable.transporte_balsa));
                        }
                        else if(mon >= 15){
                            transporte.setImageDrawable(getResources().getDrawable(R.drawable.transporte_tronco));
                        }
                        else if(mon >= 1){
                            transporte.setImageDrawable(getResources().getDrawable(R.drawable.transporte_alas));
                        }
                    } else {
                        Toast.makeText(JuegoCalculadora.this, "Error de parámetros", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();

                return requestHandler.sendGetRequest(Api.URL_GET_COINS_GROUP);
            }
        }
        ObtenerMonedas om = new ObtenerMonedas();
        om.execute();
    }

    public void volverMapa(View view){
        Intent i = new Intent(this, Mapa.class);
        i.putExtra("back", "yes");
        startActivity(i);
    }

}
