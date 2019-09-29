package co.edu.poli.gamification.poliplaygami.Modelo;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import co.edu.poli.gamification.poliplaygami.Servicios.Api;
import co.edu.poli.gamification.poliplaygami.Servicios.RequestHandler;

public class TiempoConexionJuego extends AsyncTask<Void, Void, String> {
    private String fecha;
    private String codigo_usuario;
    private String grupo_usuario;
    private String nombre_juego;
    private String puntaje;
    private String tiempo;
    private String nivel;
    private String insignias;

    public TiempoConexionJuego(
            String fecha,
            String codigo_usuario,
            String grupo_usuario,
            String nombre_juego,
            String puntaje,
            String tiempo,
            String nivel,
            String insignias){
        this.fecha = fecha;
        this.codigo_usuario = codigo_usuario;
        this.grupo_usuario = grupo_usuario;
        this.nombre_juego = nombre_juego;
        this.puntaje = puntaje;
        this.tiempo = tiempo;
        this.nivel = nivel;
        this.insignias = insignias;
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
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(Void... voids) {
        RequestHandler requestHandler = new RequestHandler();

        HashMap<String, String> params1 = new HashMap<>();
        params1.put("fecha", fecha);
        params1.put("codigo_usuario", codigo_usuario);
        params1.put("grupo_usuario", grupo_usuario);
        params1.put("nombre_juego", nombre_juego);
        params1.put("monedas", puntaje);
        params1.put("tiempo", tiempo);
        requestHandler.sendPostRequest(Api.URL_ADD_GAME_TIME, params1);

        HashMap<String, String> params2 = new HashMap<>();
        params2.put("codigo", codigo_usuario);
        params2.put("monedas", puntaje);
        requestHandler.sendPostRequest(Api.URL_ADD_COINS, params2);

        HashMap<String, String> params3 = new HashMap<>();
        params3.put("codigo", codigo_usuario);
        params3.put("nivel", nivel);
        requestHandler.sendPostRequest(Api.URL_ADD_LEVEL, params3);

        HashMap<String, String> params4 = new HashMap<>();
        params4.put("codigo", codigo_usuario);
        params4.put("insignias", insignias);
        requestHandler.sendPostRequest(Api.URL_ADD_BADGES, params4);

        return "Cargado a DB";
    }
}
