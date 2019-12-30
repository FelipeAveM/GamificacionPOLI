package co.edu.poli.gamification.poliplayapp.Secuencia;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import co.edu.poli.gamification.poliplayapp.R;

public class SeleccionarTransportePopUp extends Activity {

    private ImageView transporte;
    private String transporteSeleccionado;
    private TextView descTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_transporte_pop_up);

        transporte = (ImageView)findViewById(R.id.transportePrincipal);
        transporteSeleccionado = Login.user.getTempTransport();
        descTrans = (TextView) findViewById(R.id.desctransport);

        //EscalarPantalla
        /*
        DisplayMetrics md = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(md);
        int width = md.widthPixels;
        int heigth = md.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (heigth * .6));
         */

        escogerTransportePopUp();
    }

    public void escogerTransportePopUp() {

        if (Login.user.getSignature().equals("Proceso Administrativo") || Login.user.getSignature().equals("Pensamiento Algoritmico")) {
            if (transporteSeleccionado.equals("Transporte 1"))    {
                transporte.setImageDrawable(getDrawable(R.drawable.fondo_alas));
                descTrans.setText("Este transporte tiene un costo de 1 moneda, pero solamente puede viajar una persona");
            }
            else if (transporteSeleccionado.equals("Transporte 2"))    {
                transporte.setImageDrawable(getDrawable(R.drawable.fondo_barco));
                descTrans.setText("Este transporte tiene un costo de 60 monedas, pero solamente pueden viajar cinco personas");
            }
            else if (transporteSeleccionado.equals("Transporte 3")) {
                transporte.setImageDrawable(getDrawable(R.drawable.fondo_globo));
                descTrans.setText("Este transporte tiene un costo de 45 monedas, pero solamente pueden viajar cuatro personas");
            }
            else if (transporteSeleccionado.equals("Transporte 4")) {
                transporte.setImageDrawable(getDrawable(R.drawable.fondo_balsa));
                descTrans.setText("Este transporte tiene un costo de 30 monedas, pero solamente pueden viajar tres personas");
            }
            else if (transporteSeleccionado.equals("Transporte 5")) {
                transporte.setImageDrawable(getDrawable(R.drawable.fondo_submarino));
                descTrans.setText("Este transporte tiene un costo de 75 monedas, pero pueden viajar seis personas");
            }
            else if (transporteSeleccionado.equals("Transporte 6")) {
                transporte.setImageDrawable(getDrawable(R.drawable.fondo_tronco));
                descTrans.setText("Este transporte tiene un costo de 15 monedas, pero solamente pueden viajar dos personas");
            }
        }
    }
}
