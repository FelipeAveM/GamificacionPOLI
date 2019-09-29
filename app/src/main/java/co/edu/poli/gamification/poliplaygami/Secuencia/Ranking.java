package co.edu.poli.gamification.poliplaygami.Secuencia;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
                        JSONObject arrJson = obj.getJSONObject("grupos");
                        String listaGrupos = arrJson.toString();
                        StringTokenizer tok = new StringTokenizer(listaGrupos.substring(1, listaGrupos.length()-1) ,",");
                        String something = "GRUPOS\n\n";
                        while(tok.hasMoreElements()){
                            String token = "GRUPO " + tok.nextToken().replaceAll(String.valueOf((char)34), "");
                            StringTokenizer tok2 = new StringTokenizer(token, ":");

                            String st1 = tok2.nextToken();

                            String st2 = tok2.nextToken() + " monedas";
                            if(st1.length() == 7){
                                something += st1 + ":     " + st2+"\n";
                            }
                            else if(st1.length() == 8){
                                something += st1 + ":    " + st2+"\n";
                            }
                            else if(st1.length() == 9){
                                something += st1 + ":   " + st2+"\n";
                            }
                            else{
                                something += st1 + ":  " + st2+"\n";
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
