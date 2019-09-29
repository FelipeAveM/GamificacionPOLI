package co.edu.poli.gamification.poliplaygami.Secuencia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import co.edu.poli.gamification.poliplaygami.Juegos.JuegoCalculadora;
import co.edu.poli.gamification.poliplaygami.Modelo.Usuario;
import co.edu.poli.gamification.poliplaygami.R;
import co.edu.poli.gamification.poliplaygami.Servicios.Api;
import co.edu.poli.gamification.poliplaygami.Servicios.RequestHandler;

public class Login extends AppCompatActivity {

    private static long back_pressed; //Tiempo para salir de aplicacion
    private Button btnLogin; //Boton para ingresar
    private EditText usernameLogin, passwordLogin; //Campos de datos ingresados por usuario
    private TextView noReg; //Boton para registrar usuario
    private ProgressDialog loadingBar; //Barra de carga de inicio

    public static Usuario user; //Usuario que se manipulara

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameLogin = findViewById(R.id.usernameLogin);
        passwordLogin = findViewById(R.id.passwordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        noReg = findViewById(R.id.msj_no_registro2);

        loadingBar = new ProgressDialog(this, R.style.Theme_AppCompat_DayNight_Dialog);
        loadingBar.setCanceledOnTouchOutside(false);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { login(); }
        });
        noReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Registro.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void login(){
        final String username = usernameLogin.getText().toString().trim();
        final String password = passwordLogin.getText().toString().trim();
        if(username.isEmpty()){
            usernameLogin.setError("Ingrese un nombre de Usuario.");
            usernameLogin.requestFocus();
            return;
        }
        if(password.isEmpty()){
            passwordLogin.setError("Ingrese una contraseña.");
            passwordLogin.requestFocus();
            return;
        }
        loadingBar.setTitle(R.string.progress_login);
        loadingBar.setMessage(getString(R.string.progress_esperar));
        loadingBar.show();
        if(username.equals("j")){//Ingreso genérico a usuario de pruebas de juegos.
            loadingBar.cancel();
            Intent i = new Intent(Login.this, Mapa.class);
            i.putExtra("back", "no");
            startActivity(i);
            finish();
            return;
        }
        if(username.equals("t")){//Ingreso genérico a usuario de pruebas sin bd.
            loadingBar.cancel();
            Intent i = new Intent(Login.this, SeleccionarCurso.class);
            startActivity(i);
            finish();
            return;
        }

        /**
         * Clase para verficar los datos en la base de datos.
         */
        class UserLogin extends AsyncTask<Void, Void, String> {
            private ProgressBar progressBar;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.progressBarLogin);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject obj = new JSONObject(s);
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(Login.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                        //Obtener el JSON del usuario en base de datos.
                        JSONObject userJson = obj.getJSONObject("user");
                        //Crear un nuevo usuario.
                        //code mail user, pass, sign, role, trans, grou
                        user = new Usuario(
                                userJson.getString("codigo"),
                                userJson.getString("correo"),
                                userJson.getString("username"),
                                userJson.getString("contrasena"),
                                userJson.getString("materia"),
                                userJson.getString("rol"),
                                userJson.getString("grupo"),
                                userJson.getString("monedas"),
                                userJson.getString("nivel"),
                                userJson.getString("insignias")
                        );

                        finish();
                        if(!Login.user.getSignature().equals("vacio")){
                            if(!Login.user.getRole().equals("vacio")) {
                                if(!Login.user.getGroup().equals("vacio")) {
                                    if(!Login.user.getLevel().equals("0")) {
                                        if (!Login.user.getLevel().equals("FIN")) {
                                            startActivity(new Intent(getApplicationContext(), Mapa.class));
                                        } else {
                                            startActivity(new Intent(getApplicationContext(), JuegoCalculadora.class));
                                        }
                                    }
                                    else{
                                        startActivity(new Intent(getApplicationContext(), SeleccionarTransporte.class));
                                    }
                                }
                                else{
                                    startActivity(new Intent(getApplicationContext(), SeleccionarPuesto.class));
                                }
                            }
                            else{
                                startActivity(new Intent(getApplicationContext(), SeleccionarRol.class));
                            }
                        }
                        else{
                            startActivity(new Intent(getApplicationContext(), SeleccionarCurso.class));
                        }
                    } else {
                        Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();

                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("contrasena", password);

                return requestHandler.sendPostRequest(Api.URL_LOGIN_USER, params);
            }
        }
        UserLogin ul = new UserLogin();
        ul.execute();
        loadingBar.cancel();
    }

    /**
     * Cuando el usuario oprima el botón de regresar dos veces en menos de 2 segundos
     * la aplicación se cerrará, en ese lapso de tiempo envía un mensaje con la
     * advertencia
     */
    @Override
    public void onBackPressed() {
        if(back_pressed + 2000 > System.currentTimeMillis())
            System.exit(0);
        else
            Toast.makeText(getBaseContext(), R.string.toast_salir, Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }
    /*
    //Método para volver fullscreen la aplicación.
    @Override
    protected void onResume() {
        super.onResume();
        hideNavegationBar();
    }

    private void hideNavegationBar() {
        this.getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY|
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }
     */
}
