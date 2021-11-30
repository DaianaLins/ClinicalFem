<?php
//Caminho absoluto do local que vai salvar as fotos
$pasta    = 'gs://clinicalfem-827d7.appspot.com/';

//URL que vai ser gerada
$pastaurl = 'http://meusite.com/storage/upload/';

$tmp_name = $_FILES['arquivo']['tmp_name'];

//Pega o mimetype
$finfo = finfo_open(FILEINFO_MIME_TYPE);
$mime = finfo_file($finfo, $tmp_name);
finfo_close($finfo);

//Só permite upload de imagens
if (strpos($mime, 'image/') === 0) {

   //Gera um nome que não se repete para imagem e adiciona a extensão conforme o mimetype
   $file = time() . '.' . str_replace('image/', '', $mime);

   if (move_uploaded_file($tmp_name, $pasta . '/' . $file)) {
       return $pastaurl . $file;
   }
}