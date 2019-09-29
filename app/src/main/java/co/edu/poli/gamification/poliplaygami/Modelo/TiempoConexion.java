package co.edu.poli.gamification.poliplaygami.Modelo;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import co.edu.poli.gamification.poliplaygami.Servicios.Api;
import co.edu.poli.gamification.poliplaygami.Servicios.RequestHandler;

public class TiempoConexion extends AsyncTask<Void, Void, String> {
    private String fecha;
    private String codigo_usuario;
    private String grupo_usuario;
    private String tiempo;

    public TiempoConexion(String fecha, String codigo_usuario, String grupo_usuario, String tiempo){
        this.fecha = fecha;
        this.codigo_usuario = codigo_usuario;
        this.grupo_usuario = grupo_usuario;
        this.tiempo = tiempo;
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
                JSONObject userJson = obj.getJSONObject("time");
                //finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(Void... voids) {
        RequestHandler requestHandler = new RequestHandler();

        HashMap<String, String> params = new HashMap<>();
        params.put("fecha", fecha);
        params.put("codigo_usuario", codigo_usuario);
        params.put("grupo_usuario", grupo_usuario);
        params.put("tiempo", tiempo);

        return requestHandler.sendPostRequest(Api.URL_ADD_CONN_TIME, params);
    }
}