<?php 

require_once 'DbConnect.php';

function isTheseParametersAvailable($params){
    foreach($params as $param){
        if(!isset($_REQUEST[$param])) return false; 
    }
    return true; 
}

$db = new DbConnect;
$conn = $db->connect();

$response = array();

if(isset($_GET['apicall'])){
    switch($_GET['apicall']){

       case 'agregarSillas':
            if(isTheseParametersAvailable(array('grupo'))){
                $grupo = $_REQUEST['grupo'];
                $stmt = $conn->prepare("UPDATE usuario SET grupo = ? WHERE correo = 'poli@poligran.edu.co'");
                $stmt->bind_param("s", $grupo);
                if($stmt->execute()){
                    $stmt->execute();
                    $stmt->bind_result($grupo);
                    $stmt->fetch();
                    $stmt->close();
                    $response['error'] = false;
                    $response['message'] = 'Grupo agregado satisfactoriamente.';
                }
                else{
                    $response['error'] = true;
                    $response['message'] = 'Error.';
                }
            }
            else{
                $response['error'] = true;
                $response['message'] = 'Los datos ingresados no son correctos.';
            }
        break;

         case 'getSillas':
            $stmt = $conn->prepare("SELECT grupo FROM usuario where username = 'administrador'");
            $stmt->execute();
            $stmt->store_result();
            $grupo = $stmt;
            $stmt->bind_result($grupo);
            $stmt->fetch();
            $response = $grupo;
        break;
        
        case 'resetdb':
            $stmt = $conn->prepare("DELETE FROM tiempos_conexion");
            $stmt->execute();
            $stmt->fetch();
            $stmt = $conn->prepare("DELETE FROM tiempos_juegos");
            $stmt->execute();
            $stmt->fetch();
            $stmt = $conn->prepare("DELETE FROM usuario");
            $stmt->execute();
            $stmt->fetch();
            $stmt = $conn->prepare("INSERT INTO usuario (codigo, correo, username, contrasena, materia, rol, grupo, monedas, nivel, insignias, gruporeal) VALUES ('000', 'poli@poligran.edu.co', 'administrador', 'politecnico123', 'Admin', 'Administrador', '250', '0', 'FIN', '1', null)");
            $stmt->execute();
            $stmt->fetch();
        break;
        case 'registrousuario':
        if(isTheseParametersAvailable(array('codigo','correo','username','contrasena', 'materia','rol', 'grupo', 'monedas', 'nivel', 'insignias'))){
            $codigo = $_POST['codigo']; 
            $correo = $_POST['correo']; 
            $username = $_POST['username']; 
            $contrasena = md5($_POST['contrasena']);
            $materia = $_POST['materia']; 
            $rol = $_POST['rol']; 
            $grupo = $_POST['grupo'];
            $monedas = $_POST['monedas'];
            $nivel = $_POST['nivel'];
            $insignias = $_POST['insignias'];

            $stmt = $conn->prepare("SELECT codigo FROM usuario WHERE username = ? OR correo = ? OR codigo = ?");
            $stmt->bind_param("sss", $username, $coreo, $codigo);
            $stmt->execute();
            $stmt->store_result();
            $response = $stmt;

            if($stmt->num_rows > 0){
                $response['error'] = true;
                $response['message'] = 'El usuario ya se encuentra registrado.';
                $stmt->close();
            }
            else{
                $stmt = $conn->prepare("INSERT INTO usuario (codigo, correo, username, contrasena, materia, rol, grupo, monedas, nivel, insignias) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                $stmt->bind_param("ssssssssss", $codigo, $correo, $username, $contrasena, $materia, $rol, $grupo, $monedas, $nivel, $insignias);

                if($stmt->execute()){
                    $stmt = $conn->prepare("SELECT codigo, correo, username FROM usuario WHERE username = ?"); 
                    $stmt->bind_param("s",$username);
                    $stmt->execute();
                    $stmt->bind_result($codigo, $correo, $username);
                    $stmt->fetch();

                    $user = array(
                        'codigo'=>$codigo, 
                        'correo'=>$correo, 
                        'username'=>$username);

                    $stmt->close();
                    $response['error'] = false; 
                    $response['message'] = 'Usuario registrado satisfactoriamente.'; 
                    $response['user'] = $user; 
                }
            }
        }
        else{
            $response['error'] = true; 
            $response['message'] = 'Los datos ingresados no son correctos.'; 
        }
        break; 
        
        case 'loginusuario':
        if(isTheseParametersAvailable(array('username', 'contrasena'))){
            $username = $_POST['username'];
            $contrasena = md5($_POST['contrasena']); 

            $stmt = $conn->prepare("SELECT codigo, username, contrasena, correo, materia, rol, grupo, monedas, nivel, insignias FROM usuario WHERE correo = ? AND contrasena = ?");
            $stmt->bind_param("ss",$username, $contrasena);
            $stmt->execute();
            $stmt->store_result();

            if($stmt->num_rows > 0){
                $stmt->bind_result($codigo, $username, $contrasena, $correo, $materia, $rol, $grupo, $monedas, $nivel, $insignias);
                $stmt->fetch();

                $user = array(
                    'codigo'=>$codigo, 
                    'username'=>$username,
                    'contrasena'=>$contrasena, 
                    'correo'=>$correo,
                    'materia'=>$materia,
                    'rol'=>$rol,
                    'grupo'=>$grupo,
                    'monedas'=>$monedas,
                    'nivel'=>$nivel,
                    'insignias'=>$insignias);

                $response['error'] = false; 
                $response['message'] = 'Ingreso exitoso.'; 
                $response['user'] = $user; 
            }
            else{
                $response['error'] = true; 
                $response['message'] = 'Usuario o contrasena invalidos';
            }
        }
        break;

        case 'agregarmateria':
        if(isTheseParametersAvailable(array('codigo', 'materia'))){
            $codigo = $_POST['codigo'];
            $materia = $_POST['materia'];

            $stmt = $conn->prepare("UPDATE usuario SET materia = ? WHERE codigo = ?");
            $stmt->bind_param("ss", $materia, $codigo);

            if($stmt->execute()){
                $stmt = $conn->prepare("SELECT codigo, materia FROM usuario WHERE codigo = ?");
                $stmt->bind_param("s", $codigo);
                $stmt->execute();
                $stmt->bind_result($codigo, $materia);
                $stmt->fetch();

                $user = array(
                    'codigo'=>$codigo,
                    'materia'=>$materia);

                $stmt->close();
                $response['error'] = false;
                $response['message'] = 'Materia agregada satisfactoriamente.';
                $response['user'] = $user;
            }
            else{
                $response['error'] = true;
                $response['message'] = 'Error al agregar la materia.';
            }
        }
        else{
            $response['error'] = true;
            $response['message'] = 'Los datos ingresados no son correctos.';
        }
        break;

        case 'agregarrol':
        if(isTheseParametersAvailable(array('codigo', 'rol'))){
            $codigo = $_POST['codigo'];
            $rol = $_POST['rol'];

            $stmt = $conn->prepare("UPDATE usuario SET rol = ? WHERE codigo = ?");
            $stmt->bind_param("ss", $rol, $codigo);

            if($stmt->execute()){
                $stmt = $conn->prepare("SELECT codigo, rol FROM usuario WHERE codigo = ?");
                $stmt->bind_param("s", $codigo);
                $stmt->execute();
                $stmt->bind_result($codigo, $rol);
                $stmt->fetch();

                $user = array(
                    'codigo'=>$codigo,
                    'rol'=>$rol);

                $stmt->close();
                $response['error'] = false;
                $response['message'] = 'Rol agregado satisfactoriamente.';
                $response['user'] = $user;
            }
            else{
                $response['error'] = true;
                $response['message'] = 'Error al agregar el rol.';
            }
        }
        else{
            $response['error'] = true;
            $response['message'] = 'Los datos ingresados no son correctos.';
        }
        break;

        case 'agregargrupo':
        if(isTheseParametersAvailable(array('codigo', 'grupo', 'gruporeal'))){
            $codigo = $_REQUEST['codigo'];
            $grupo = $_REQUEST['grupo'];
            $gruporeal = $_REQUEST['gruporeal'];

            $stmt = $conn->prepare("UPDATE usuario SET grupo = ?, gruporeal = ? WHERE codigo = ?");
            $stmt->bind_param("sss", $grupo, $gruporeal, $codigo );
            
            if($stmt->execute()){
                $stmt = $conn->prepare("SELECT codigo, grupo FROM usuario WHERE codigo = ?");
                $stmt->bind_param("s", $codigo);
                $stmt->execute();
                $stmt->bind_result($codigo, $grupo);
                $stmt->fetch();

                $user = array(
                    'codigo'=>$codigo,
                    'grupo'=>$grupo);

                $stmt->close();
                $response['error'] = false;
                $response['message'] = 'Grupo agregado satisfactoriamente.';
                $response['user'] = $user;
            }
            else{
                $response['error'] = true;
                $response['message'] = 'Error.';
            }
        }
        else{
            $response['error'] = true;
            $response['message'] = 'Los datos ingresados no son correctos.';
        }
        break;

        case 'agregarmonedas':
        if(isTheseParametersAvailable(array('codigo', 'monedas'))){
            $codigo = $_POST['codigo'];
            $monedas = $_POST['monedas'];

            $stmt = $conn->prepare("UPDATE usuario SET monedas = ? WHERE codigo = ?");
            $stmt->bind_param("ss", $monedas, $codigo);

            if($stmt->execute()){
                $stmt = $conn->prepare("SELECT codigo, monedas FROM usuario WHERE codigo = ?");
                $stmt->bind_param("s", $codigo);
                $stmt->execute();
                $stmt->bind_result($codigo, $monedas);
                $stmt->fetch();

                $user = array(
                    'codigo'=>$codigo,
                    'monedas'=>$monedas);

                $stmt->close();
                $response['error'] = false;
                $response['message'] = 'Monedas agregadas.';
                $response['user'] = $user;
            }
            else{
                $response['error'] = true;
                $response['message'] = 'Error al agregar las monedas.';
            }
        }
        else{
            $response['error'] = true;
            $response['message'] = 'Los datos ingresados no son correctos.';
        }
        break;

        case 'agregarnivel':
        if(isTheseParametersAvailable(array('codigo', 'nivel'))){
            $codigo = $_POST['codigo'];
            $nivel = $_POST['nivel'];

            $stmt = $conn->prepare("UPDATE usuario SET nivel = ? WHERE codigo = ?");
            $stmt->bind_param("ss", $nivel, $codigo);

            if($stmt->execute()){
                $stmt = $conn->prepare("SELECT codigo, nivel FROM usuario WHERE codigo = ?");
                $stmt->bind_param("s", $codigo);
                $stmt->execute();
                $stmt->bind_result($codigo, $nivelnivel);
                $stmt->fetch();

                $user = array(
                    'codigo'=>$codigo,
                    'nivel'=>$nivel);

                $stmt->close();
                $response['error'] = false;
                $response['message'] = 'Nivel agregado.';
                $response['user'] = $user;
            }
            else{
                $response['error'] = true;
                $response['message'] = 'Error al agregar el nivel.';
            }
        }
        else{
            $response['error'] = true;
            $response['message'] = 'Los datos ingresados no son correctos.';
        }
        break;

        case 'agregarinsignias':
        if(isTheseParametersAvailable(array('codigo', 'insignias'))){
            $codigo = $_POST['codigo'];
            $insignias = $_POST['insignias'];

            $stmt = $conn->prepare("UPDATE usuario SET insignias = ? WHERE codigo = ?");
            $stmt->bind_param("ss", $insignias, $codigo);

            if($stmt->execute()){
                $stmt = $conn->prepare("SELECT codigo, insignias FROM usuario WHERE codigo = ?");
                $stmt->bind_param("s", $codigo);
                $stmt->execute();
                $stmt->bind_result($codigo, $nivelnivel);
                $stmt->fetch();

                $user = array(
                    'codigo'=>$codigo,
                    'insignias'=>$insignias);

                $stmt->close();
                $response['error'] = false;
                $response['message'] = 'Insignia agregada.';
                $response['user'] = $user;
            }
            else{
                $response['error'] = true;
                $response['message'] = 'Error al agregar la insignia.';
            }
        }
        else{
            $response['error'] = true;
            $response['message'] = 'Los datos ingresados no son correctos.';
        }
        break;

        case 'obtenermonedasgrupo':
        if(isTheseParametersAvailable(array('grupo'))){
            $grupo = $_POST['grupo'];

            $stmt = $conn->prepare("SELECT monedas FROM usuario WHERE gruporeal = ?");
            $stmt->bind_param("i", $grupo);
            $stmt->execute();
            $stmt->store_result();

            if($stmt->num_rows > 0){
                $monedasGrupales = 0;
                $fila = 0;
                while($fila < $stmt->num_rows){
                    $stmt->bind_result($monedas);
                    $stmt->fetch();
                    $moneda = $monedas;
                    $monedasGrupales = $monedasGrupales + (int)$moneda;
                    $fila = $fila + 1;
                }
                $response['error'] = false;
                $response['message'] = "Monedas obtenidas.";
                $response['monedas'] = $monedasGrupales;
            }
            else{
                $response['error'] = true;
                $response['message'] = 'Grupo no existente.';
            }
        }
        break;

        case 'obtenerlistagrupos':
        //$stmt = $conn->prepare("SELECT DISTINCT gruporeal FROM usuario");
        $stmt = $conn->prepare("SELECT DISTINCT gruporeal FROM usuario where gruporeal <> 'vacio'");
        $stmt->execute();
        $stmt->store_result();
        $cantGrupos = $stmt->num_rows;
        if($cantGrupos > 0){
            $arrayGrupos = array();
            $salida = [];
            $fila = 0;
            while($fila < $cantGrupos){
                $stmt->bind_result($grupo);
                $stmt->fetch();
                $arrayGrupos[$fila] = $grupo;

                $fila = $fila + 1;
            }
         
            $cont = 0;
            while($cont < $cantGrupos){
                $stmt = $conn->prepare("SELECT monedas, insignias FROM usuario WHERE gruporeal = ? ");
                //$stmt = $conn->prepare("SELECT monedas FROM usuario WHERE gruporeal = ? ORDER BY CAST(monedas as SIGNED) DESC");
                $stmt->bind_param("i", $arrayGrupos[$cont]);
                $stmt->execute();
                $stmt->store_result();
                $cantMonedas = $stmt->num_rows;
                if($cantMonedas > 0){
                    $monedasGrupales = 0;
                    $insigniasGrupales = 0;
                    $col = 0;
                    while($col < $cantMonedas){
                        $stmt->bind_result($monedas, $insignias);
                        $stmt->fetch();                                           
                        $moneda = $monedas;
                        $monedasGrupales = $monedasGrupales + (int)$moneda;
                        $insigniasGrupales = $insigniasGrupales + (int)$insignias;
                        $col = $col + 1;
                    }

                    $salida[] = ['grupo' => $arrayGrupos[$cont] , 'monedas' => $monedasGrupales, 'insignias' => $insigniasGrupales];
                }
                $cont = $cont + 1;
            }
            
            function sortCoins ($a, $b){
            	if ($a['monedas'] == $b['monedas']) return 0;
				return ( $a['monedas'] < $b['monedas'] ) ? 1 : -1;
            }
            
            usort($salida, "sortCoins");
            $response['error'] = false;
            $response['message'] = "Grupos obtenidos.";
            $response['grupos'] = $salida;
        }
        break;
        
        
        case 'traerpuestosprad':
        $sql = "SELECT grupo from usuario where materia = 'Proceso Administrativo'";
        $result = mysqli_query($conn,$sql);
        $json_array = array();
        while($row = mysqli_fetch_assoc($result)){
            $json_array[] = $row;
        }
        echo json_encode($json_array);
        break;

        case 'traerpuestospeal':
        $sql = "SELECT grupo from usuario where materia = 'Pensamiento Algoritmico'";
        $result = mysqli_query($conn,$sql);
        $json_array = array();
        while($row = mysqli_fetch_assoc($result)){
            $json_array[] = $row;
        }
        echo json_encode($json_array);
        break;


        //TIEMPOS DE JUEGO!------------------------------------------------------------------------------------------------------------------------------------

        case 'agregartiempojuego': 
        if(isTheseParametersAvailable(array('fecha', 'codigo_usuario', 'grupo_usuario', 'nombre_juego', 'monedas', 'tiempo'))){
            $fecha = $_POST['fecha'];
            $codigo_usuario = $_POST['codigo_usuario'];
            $grupo_usuario = $_POST['grupo_usuario'];
            $nombre_juego = $_POST['nombre_juego'];
            $monedas = $_POST['monedas'];
            $tiempo = $_POST['tiempo'];

            $stmt = $conn->prepare("SELECT fecha FROM tiempos_juegos WHERE fecha = ?");
            $stmt->bind_param("s", $fecha);
            $stmt->execute();
            $stmt->store_result();

            if($stmt->num_rows > 0){
                $response['error'] = true;
                $response['message'] = 'El dato ya se encuenta registrado.';
            }
            else{
                $stmt = $conn->prepare("INSERT INTO tiempos_juegos (fecha, codigo_usuario, grupo_usuario, nombre_juego, monedas, tiempo) VALUES (?, ?, ?, ?, ?, ?)");
                $stmt->bind_param("ssssss", $fecha, $codigo_usuario, $grupo_usuario, $nombre_juego, $monedas, $tiempo);
                
                if($stmt->execute()){
                    $stmt = $conn->prepare("SELECT fecha, codigo_usuario, grupo_usuario, nombre_juego, monedas, tiempo FROM tiempos_juegos WHERE fecha = ?");
                    $stmt->bind_param("s", $fecha);
                    $stmt->execute();
                    $stmt->bind_result($fecha, $codigo_usuario, $grupo_usuario, $nombre_juego, $monedas, $tiempo);
                    $stmt->fetch();

                    $time = array(
                        'fecha'=>$fecha,
                        'codigo_usuario'=>$codigo_usuario,
                        'grupo_usuario'=>$grupo_usuario,
                        'nombre_juego'=>$nombre_juego,
                        'monedas'=>$monedas,
                        'tiempo'=>$tiempo);

                    $stmt->close();
                    $response['error'] = false;
                    $response['message'] = 'Tiempo agregado satisfactoriamente';
                    $response['time'] = $time;
                }
            }
        }
        else{
            $response['error'] = true;
            $response['message'] = 'Los datos ingresados no son correctos';
        }
        break;

        case 'agregartiempoconexion':
        if(isTheseParametersAvailable(array('fecha', 'codigo_usuario', 'grupo_usuario', 'tiempo'))){
            $fecha = $_POST['fecha'];
            $codigo_usuario = $_POST['codigo_usuario'];
            $grupo_usuario = $_POST['grupo_usuario'];
            $tiempo = $_POST['tiempo'];

            $stmt = $conn->prepare("SELECT fecha FROM tiempos_conexion WHERE fecha = ?");
            $stmt->bind_param("s", $fecha);
            $stmt->execute();
            $stmt->store_result();

            if($stmt->num_rows > 0){
                $response['error'] = false;
                $response['message'] = "El dato ya se encuenta registrado";
            }
            else{
                $stmt = $conn->prepare("INSERT INTO tiempos_conexion (fecha, codigo_usuario, grupo_usuario, tiempo) VALUES (?, ?, ?, ?)"); 
                $stmt->bind_param("ssss", $fecha, $codigo_usuario, $grupo_usuario, $tiempo);

                if($stmt->execute()){
                    $stmt = $conn->prepare("SELECT fecha, codigo_usuario, grupo_usuario, tiempo FROM tiempos_conexion WHERE fecha = ?");
                    $stmt->bind_param("s", $fecha);
                    $stmt->execute();
                    $stmt->bind_result($fecha, $codigo_usuario, $grupo_usuario, $tiempo);
                    $stmt->fetch();

                    $time = array(
                        'fecha'=>$fecha,
                        'codigo_usuario'=>$codigo_usuario,
                        'grupo_usuario'=>$grupo_usuario,
                        'tiempo'=>$tiempo);

                    $stmt->close();
                    $response['error'] = false;
                    $response['message'] = 'Tiempo agregado satisfactoriamente.';
                    $response['time'] = $time;
                }
            }
        }
        else{
            $response['error'] = true;
            $response['message'] = 'Los datos ingresados no son correctos.';
        }
        break;
        case 'generarreporte':
            include('Reporte.php');
            break;
        default:
        $response['error'] = true;
        $response['message'] = 'Invalid Operation Called';
    }

}
else{
    $response['error'] = true; 
    $response['message'] = 'Invalid API Call';
}

echo json_encode($response);    