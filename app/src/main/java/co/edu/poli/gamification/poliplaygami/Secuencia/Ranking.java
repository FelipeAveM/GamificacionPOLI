package co.edu.poli.gamification.poliplaygami.Secuencia;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.StringTokenizer;

import co.edu.poli.gamification.poliplaygami.R;
import co.edu.poli.gamification.poliplaygami.Servicios.Api;
import co.edu.poli.gamification.poliplaygami.Servicios.RequestHandler;

public class Ranking extends AppCompatActivity {

    public TextView monedasGrupo;
    public Button volverMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        monedasGrupo = (TextView)findViewById(R.id.monedasGrupo);
        volverMapa = (Button)findViewById(R.id.volverMapa);

        monedasGrupo.setText("Cargando Ranking...");
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
                        Log.d("sdfs", "asdas");
                        JSONArray arrJson = obj.getJSONArray("grupos");
                        Log.d("sdfs", arrJson.toString());
                        String listaGrupos = arrJson.toString();
                        StringTokenizer tok = new StringTokenizer(listaGrupos.substring(1, listaGrupos.length()-1) ,",");
                        String something = "GRUPOS\n\n";
                        for (int i = 0 ; i < arrJson.length(); i++){
                            String token = "GRUPO " + arrJson.getJSONObject(i).getInt("grupo");
                            String monedas = arrJson.getJSONObject(i).getString("monedas") + " monedas";

                            if(token.length() == 7){
                                something += token + ":     " + monedas+"\n";
                            }
                            else if(token.length() == 8){
                                something += token + ":    " + monedas+"\n";
                            }
                            else if(token.length() == 9){
                                something += token + ":   " + monedas+"\n";
                            }
                            else{
                                something += token + ":  " + monedas+"\n";
                            }
                        }

                        monedasGrupo.setText(something);
                        //JSONObject arrJson = obj.getJSONObject("grupos");
                        //String group = Login.user.getGroup();
                        //String grupo = arrJson.getString(group.substring(0, group.length()-1));
                        //monedasGrupo.setText(grupo);
                    } else {
                        Toast.makeText(Ranking.this, "Error de parámetros", Toast.LENGTH_SHORT).show();
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
