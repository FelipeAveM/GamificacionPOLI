<?php

class DbConnect{

    public $conn;

    public function __construct(){}

    public function connect(){
        $servername = "localhost";
        $username = "u572341919_edare";
        $password = "administrador";
        $database = "u572341919_polip";

        $this->conn = new mysqli($servername, $username, $password, $database);

        if (mysqli_connect_errno()) {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }

        return $this->conn;
    }

}
