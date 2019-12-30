<?php

	$handle = fopen("file.txt", "w");


	fwrite($handle, "//////////////////////////////////////////////////////////////////////\n");
    fwrite($handle, "USUARIO\n");
    fwrite($handle, "//////////////////////////////////////////////////////////////////////\n\n");

    foreach ($arrayUsuarios as $key => $usuario) {
    	foreach ($usuario as $usuarioKey => $value) {
    		fwrite($handle, $value . ";");
    	}
    	fwrite($handle, $value . "\n");
    }
	fwrite($handle, "\n//////////////////////////////////////////////////////////////////////\n");
    fwrite($handle, "TIEMPOS CONEXION\n");
    fwrite($handle, "//////////////////////////////////////////////////////////////////////\n\n");
    foreach ($arrayTiemposConexion as $key => $tiempoConexion) {
    	foreach ($tiempoConexion as $tiempoConexionKey => $value) {
    		fwrite($handle, $value . ";");
    	}
    	fwrite($handle, $value . "\n");
    }
	fwrite($handle, "\n//////////////////////////////////////////////////////////////////////\n");
    fwrite($handle, "TIEMPOS JUEGO\n");
    fwrite($handle, "//////////////////////////////////////////////////////////////////////\n\n");
    foreach ($arrayTiemposJuegos as $key => $tiemposJuegos) {
    	foreach ($tiemposJuegos as $tiemposJuegosKey => $value) {
    		fwrite($handle, $value . ";");
    	}
    	fwrite($handle, $value . "\n");
    }
    fclose($handle);
    header('Content-Type: application/octet-stream');
    header('Content-Disposition: attachment; filename='.basename('file.txt'));
    header('Expires: 0');
    header('Cache-Control: must-revalidate');
    header('Pragma: public');
    header('Content-Length: ' . filesize('file.txt'));
    readfile('file.txt');
    exit();
?>