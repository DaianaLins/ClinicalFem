package com.projeto.clinicalfem.models;

public class UsuarioParse {
    public static Usuarios toGoogle(UsuariosSpring antigo){
        Usuarios novo = new Usuarios(antigo.getId(), antigo.getNome(), (antigo.getImagemLocal() != null ? antigo.getImagemLocal():""), antigo.getTipo(), antigo.isAdm() ,antigo.getDataNascLocal(), antigo.getEmail(), antigo.getSenha(), antigo.getNumero(), antigo.getNomeImagem());
        
        return novo;
    }

    public static UsuariosSpring toSpring(Usuarios antigo){
        UsuariosSpring novo = new UsuariosSpring(antigo.getId(), antigo.getNome(), antigo.getImagem(), antigo.getTipo(), antigo.isAdm(), antigo.getDatanasc(), antigo.getEmail(), antigo.getSenha(), antigo.getNumero(), antigo.getNomeImagem());
        return novo;
}
}