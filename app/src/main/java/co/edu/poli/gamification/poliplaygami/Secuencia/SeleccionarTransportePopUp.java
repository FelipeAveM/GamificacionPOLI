package co.edu.poli.gamification.poliplaygami.Secuencia;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import co.edu.poli.gamification.poliplaygami.R;

public class SeleccionarTransportePopUp extends Activity {

    private ImageView transporte;
    private String transporteSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_transporte_pop_up);

        transporte = (ImageView)findViewById(R.id.transportePrincipal);
        transporteSeleccionado = Login.user.getTempTransport();

        //EscalarPantalla
        DisplayMetrics md = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(md);
        int width = md.widthPixels;
        int heigth = md.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (heigth * .6));

        escogerTransportePopUp();
    }

    public void escogerTransportePopUp() {

        if (Login.user.getSignature().equals("Proceso Administrativo") || Login.user.getSignature().equals("Pensamiento Algoritmico")) {
            if (transporteSeleccionado.equals("Transporte 1"))    {
                transporte.setImageDrawable(getDrawable(R.drawable.transporte_alas));
            }
            else if (transporteSeleccionado.equals("Transporte 2"))    {
                transporte.setImageDrawable(getDrawable(R.drawable.transporte_barco));
            }
            else if (transporteSeleccionado.equals("Transporte 3")) {
                transporte.setImageDrawable(getDrawable(R.drawable.transporte_globo));
            }
            else if (transporteSeleccionado.equals("Transporte 4")) {
                transporte.setImageDrawable(getDrawable(R.drawable.transporte_balsa));
            }
            else if (transporteSeleccionado.equals("Transporte 5")) {
                transporte.setImageDrawable(getDrawable(R.drawable.transporte_submarino));
            }
            else if (transporteSeleccionado.equals("Transporte 6")) {
                transporte.setImageDrawable(getDrawable(R.drawable.transporte_tronco));
            }
        }
        else if (Login.user.getSignature().equals("Pensamiento Algoritmico")) {
            if (transporteSeleccionado.equals("Transporte 1"))    {
                transporte.setImageDrawable(getDrawable(R.drawable.transporte_alas));
            }
            else if (transporteSeleccionado.equals("Transporte 2"))    {
                transporte.setImageDrawable(getDrawable(R.drawable.transporte_barco));
            }
            else if (transporteSeleccionado.equals("Transporte 3")) {
                transporte.setImageDrawable(getDrawable(R.drawable.transporte_globo));
            }
            else if (transporteSeleccionado.equals("Transporte 4")) {
                transporte.setImageDrawable(getDrawable(R.drawable.transporte_balsa));
            }
            else if (transporteSeleccionado.equals("Transporte 5")) {
                transporte.setImageDrawable(getDrawable(R.drawable.transporte_submarino));
            }
            else if (transporteSeleccionado.equals("Transporte 6")) {
                transporte.setImageDrawable(getDrawable(R.drawable.transporte_tronco));
            }
        }
    }
}
