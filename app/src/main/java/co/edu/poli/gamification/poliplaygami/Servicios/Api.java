package co.edu.poli.gamification.poliplaygami.Servicios;

public class Api {
    //http://poliplay.gq/PoliPlay/SV1/Api.php?apicall=loginusuario
    /** La URL de este parámetro se modifica cada vez que sea necesario o cada que se cambie de "servidor"*/

    //private static final String SERVER_URL = "192.168.0.9";
    private static final String SERVER_URL = "poliplay.gq";
    public static final String ROOT_URL = "https://"+SERVER_URL+"/PoliPlayApi/Api.php?apicall=";

    public static final String URL_REGISTER_USER = ROOT_URL + "registrousuario";
    public static final String URL_LOGIN_USER = ROOT_URL + "loginusuario";

    public static final String URL_ADD_SIGNATURE = ROOT_URL + "agregarmateria";
    public static final String URL_ADD_ROLE = ROOT_URL + "agregarrol";
    public static final String URL_ADD_COINS = ROOT_URL + "agregarmonedas";
    public static final String URL_ADD_LEVEL = ROOT_URL + "agregarnivel";
    public static final String URL_ADD_BADGES = ROOT_URL + "agregarinsignias";

    public static final String URL_ADD_GAME_TIME = ROOT_URL + "agregartiempojuego";
    public static final String URL_ADD_CONN_TIME = ROOT_URL + "agregartiempoconexion";

    public static final String URL_ADD_GROUP = ROOT_URL + "agregargrupo";
    public static final String URL_GET_CHECKIN_PRAD = ROOT_URL + "traerpuestosprad";
    public static final String URL_GET_CHECKIN_PEAL = ROOT_URL + "traerpuestospeal";
    public static final String URL_GET_CHECKIN = ROOT_URL + "traerpuestos";
    public static final String URL_GET_COINS_GROUP = ROOT_URL + "obtenerlistagrupos";


}
