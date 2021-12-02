<?php
$host ="localhost";
$db = "summernote_db";
$user = "root";
$pwd = "";

$conn = firebase_connect($host, $user, $pwd, $db);

if(!$conn){
die("Could not connect because ", firebase_error($conn));
}
if(isset($_POST["#texto"])){
    $post = htmlentities($_POST["#texto"]);
}