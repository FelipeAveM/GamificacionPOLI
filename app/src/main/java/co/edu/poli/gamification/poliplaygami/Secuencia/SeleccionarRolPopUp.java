package co.edu.poli.gamification.poliplaygami.Secuencia;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import co.edu.poli.gamification.poliplaygami.Modelo.Utiles;
import co.edu.poli.gamification.poliplaygami.R;
import co.edu.poli.gamification.poliplaygami.Servicios.Api;
import co.edu.poli.gamification.poliplaygami.Servicios.RequestHandler;

public class SeleccionarRolPopUp extends Activity {

    private ImageView rol;
    private String rolSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_rol_pop_up);

        rol = (ImageView)findViewById(R.id.rolPrin);
        rolSelected = Login.user.getTempRole();

        //EscalarPantalla
        DisplayMetrics md = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(md);
        int width = md.widthPixels;
        int heigth = md.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (heigth * .6));

        escogerRolPop();
    }

    public void escogerRolPop() {
        if (Login.user.getSignature().equals("Proceso Administrativo") || Login.user.getSignature().equals("Pensamiento Algoritmico")) { //Pensamiento Algoritmico
            if (rolSelected.equals("Rol 1"))    {
                rol.setImageDrawable(getDrawable(R.drawable.personaje_h_explorador));
            }
            else if (rolSelected.equals("Rol 1f"))    {
                rol.setImageDrawable(getDrawable(R.drawable.personaje_m_explorador));
            }
            else if (rolSelected.equals("Rol 2")) {
                rol.setImageDrawable(getDrawable(R.drawable.personaje_h_filantropo));
            }
            else if (rolSelected.equals("Rol 2f")) {
                rol.setImageDrawable(getDrawable(R.drawable.personaje_m_filantropa));
            }
            else if (rolSelected.equals("Rol 3")) {
                rol.setImageDrawable(getDrawable(R.drawable.personaje_h_triunfador));
            }
            else if (rolSelected.equals("Rol 3f")) {
                rol.setImageDrawable(getDrawable(R.drawable.personaje_m_triunfadora));
            }
            else if (rolSelected.equals("Rol 4")) {
                rol.setImageDrawable(getDrawable(R.drawable.personaje_h_pensador));
            }
            else if (rolSelected.equals("Rol 4f")) {
                rol.setImageDrawable(getDrawable(R.drawable.personaje_m_pensadora));
            }
            else if (rolSelected.equals("Rol 5")) {
                rol.setImageDrawable(getDrawable(R.drawable.personaje_h_revolucionario));
            }
            else if (rolSelected.equals("Rol 5f")) {
                rol.setImageDrawable(getDrawable(R.drawable.personaje_m_revolucionaria));
            }
            else if(rolSelected.equals("Rol 6")){
                rol.setImageDrawable(getDrawable(R.drawable.personaje_h_socializador));
            }
            else if(rolSelected.equals("Rol 6f")){
                rol.setImageDrawable(getDrawable(R.drawable.personaje_m_socializador));
            }
        }
        /*else if (Login.user.getSignature().equals("Cultura Ambiental")) {
            if (rolSelected.equals("Rol 1")) {
                rol.setImageDrawable(getDrawable(R.drawable.personaje_ambiental_coati_completo));
            }
            else if (rolSelected.equals("Rol 2")) {
                rol.setImageDrawable(getDrawable(R.drawable.personaje_ambiental_tucan_completo));
            }
            else if (rolSelected.equals("Rol 3")) {
                rol.setImageDrawable(getDrawable(R.drawable.personaje_ambiental_aguila_completo));
            }
            else if (rolSelected.equals("Rol 4")) {
                rol.setImageDrawable(getDrawable(R.drawable.personaje_ambiental_carpintero_completo));
            }
            else if (rolSelected.equals("Rol 5")) {
                rol.setImageDrawable(getDrawable(R.drawable.personaje_ambiental_tingua_completo));
            }
            else{
                rol.setImageDrawable(getDrawable(R.drawable.personaje_ambiental_rana_completo));
            }
        }*/
    }

    public void btnSelRol(View view) {
        Bundle ext = getIntent().getExtras();
        String r = ext.getCharSequence("role").toString();
        AddRole ar = new AddRole(r);
        ar.execute();
    }

    class AddRole extends AsyncTask<Void, Void, String> {
        private String roleAdd;
        public AddRole(String roleAdd){
            this.roleAdd = roleAdd;
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
                    Toast.makeText(SeleccionarRolPopUp.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                    JSONObject userJson = obj.getJSONObject("user");
                    //Agregar la materia al usuario.
                    Login.user.setRole(roleAdd);

                    finish();
                    Utiles.terminarConexion();
                    startActivity(new Intent(getApplicationContext(), SeleccionarPuesto.class));
                } else {
                    Toast.makeText(SeleccionarRolPopUp.this, "Invalid data", Toast.LENGTH_SHORT).show();
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
            params.put("rol", roleAdd);

            return requestHandler.sendPostRequest(Api.URL_ADD_ROLE, params);
        }
    }
}
