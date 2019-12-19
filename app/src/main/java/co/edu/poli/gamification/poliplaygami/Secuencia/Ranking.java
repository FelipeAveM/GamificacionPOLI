package co.edu.poli.gamification.poliplaygami.Secuencia;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import co.edu.poli.gamification.poliplaygami.R;
import co.edu.poli.gamification.poliplaygami.Servicios.Api;
import co.edu.poli.gamification.poliplaygami.Servicios.RequestHandler;

public class Ranking extends AppCompatActivity {

    public TextView monedasGrupo;
    public Button volverMapa;
    public LinearLayout contenerRanking;
    public Context context = this;
    public int [] paletaColores  = {R.color.silla1, R.color.silla2, R.color.silla3, R.color.silla4, R.color.silla5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        volverMapa = (Button)findViewById(R.id.volverMapa);
        contenerRanking = (LinearLayout)findViewById(R.id.containerRanking);
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

                        JSONArray arrJson = obj.getJSONArray("grupos");
                        int  indice = 0;
                        for (int i = 0 ; i < arrJson.length(); i++){

                            LinearLayout horizontallHelp = new LinearLayout(context);
                            horizontallHelp.setLayoutParams(new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                            horizontallHelp.setOrientation(LinearLayout.HORIZONTAL);



                            TextView grupo = new TextView(context);
                            TextView monedas = new TextView(context);
                            TextView insignias = new TextView(context);

                            setParamsTextView( grupo, arrJson.getJSONObject(i).getInt("grupo") + "", indice);
                            setParamsTextView( monedas, arrJson.getJSONObject(i).getString("monedas"), indice);
                            setParamsTextView( insignias, arrJson.getJSONObject(i).getString("insignias"), indice);

                            horizontallHelp.addView(grupo);
                            horizontallHelp.addView(monedas);
                            horizontallHelp.addView(insignias);

                            contenerRanking.addView(horizontallHelp);
                            indice ++;
                            indice%=paletaColores.length;

                        }

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

    private void setParamsTextView(TextView textView, String texto, int  indiceColor) {

        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
        textView.setTypeface(ResourcesCompat.getFont(context,R.font.open_sans_light), Typeface.BOLD);

        textView.setTextColor(getResources().getColor(paletaColores[indiceColor]));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,22);
        textView.setGravity(Gravity.CENTER);
        textView.setText(texto);
    }


    public void volverMapa(View view){
        Intent i = new Intent(this, Mapa.class);
        i.putExtra("back", "yes");
        startActivity(i);
    }
}
