package co.edu.poli.gamification.poliplaygami.Modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

import co.edu.poli.gamification.poliplaygami.Secuencia.Login;

public class Utiles {

    public static long startCon, endCon;

    public static String getFecha(){
        String res = "";
        Calendar fecha = new GregorianCalendar();
        int ano = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH)+1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);

        res += ano + "/" + mes + "/" + dia + "-" + hora + ":" + minuto + ":" + segundo;
        return res;
    }

    public static void terminarConexion(){
        endCon = System.currentTimeMillis();
        long totaltime = (endCon-startCon)/1000;
        TiempoConexion tc = new TiempoConexion(Utiles.getFecha(), Login.user.getCode(), Login.user.getGroup(), ""+totaltime);
        tc.execute();
    }

}
