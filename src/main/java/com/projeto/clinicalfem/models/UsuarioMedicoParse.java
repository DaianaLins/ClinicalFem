package com.projeto.clinicalfem.models;

public class UsuarioMedicoParse {
    public static UsuarioMedico toGoogle(UsuarioMedicoSpring antigo){
        UsuarioMedico novo = new UsuarioMedico(antigo.getId(), antigo.getNome(), (antigo.getImagemLocal() != null ? antigo.getImagemLocal():""), antigo.getTipo(), antigo.isAdm(), antigo.getDataNascLocal(), antigo.getEmail(), antigo.getSenha(), antigo.getNumero(), antigo.getNomeImagem(), antigo.getCrm());
        
        return novo;
    }

    public static UsuarioMedicoSpring toSpring(UsuarioMedico antigo){
        UsuarioMedicoSpring novo = new UsuarioMedicoSpring(antigo.getId(), antigo.getNome(), antigo.getImagem(), antigo.getTipo(), antigo.isAdm(), antigo.getDatanasc(), antigo.getEmail(), antigo.getSenha(), antigo.getNumero(), antigo.getNomeImagem(), antigo.getCrm());
        return novo;
}
}
