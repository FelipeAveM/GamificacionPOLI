package co.edu.poli.gamification.poliplaygami.Secuencia;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.HashMap;

import co.edu.poli.gamification.poliplaygami.R;
import co.edu.poli.gamification.poliplaygami.Servicios.Api;
import co.edu.poli.gamification.poliplaygami.Servicios.RequestHandler;

public class Registro extends AppCompatActivity {

    private EditText emailR, userR, codeR, pass1, pass2;
    private Button btnR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        emailR = (EditText)findViewById(R.id.email_registro);
        userR = (EditText)findViewById(R.id.usuario_registro);
        codeR = (EditText)findViewById(R.id.codigo_registro);
        pass1 =(EditText)findViewById(R.id.clave_uno_registro);
        pass2 =(EditText)findViewById(R.id.clave_dos_registro);
        btnR = (Button)findViewById(R.id.btn_registro_registro);

        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }

    private void createUser() {
        final String email = emailR.getText().toString().trim();
        final String user = userR.getText().toString().trim();
        final String code = codeR.getText().toString().trim();
        final String passU = pass1.getText().toString().trim();
        final String passD = pass2.getText().toString().trim();
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailR.setError("Por favor ingresa un email válido.");
            emailR.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(user)) {
            userR.setError("Ingresa un nombre de usuario.");
            userR.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(code)) {
            codeR.setError("Ingresa un código");
            codeR.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(passU)) {
            pass1.setError("Ingresa una contraseña");
            pass1.requestFocus();
            return;
        }
        if (!passU.equals(passD)) {
            pass2.setError("Las contraseñas no coinciden.");
            pass2.requestFocus();
            return;
        }

        class RegisterUser extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();

                HashMap<String, String> params = new HashMap<>();
                params.put("codigo", code);
                params.put("correo", email);
                params.put("username", user);
                params.put("contrasena", passU);
                params.put("materia", "vacio");
                params.put("rol", "vacio");
                params.put("grupo", "vacio");
                params.put("monedas", "0");
                params.put("nivel", "0");
                params.put("insignias", "0");

                return requestHandler.sendPostRequest(Api.URL_REGISTER_USER, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try{
                    finish();
                    Toast.makeText(Registro.this, "¡Registro Exitoso!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Login.class));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        RegisterUser ru = new RegisterUser();
        ru.execute();
    }
}