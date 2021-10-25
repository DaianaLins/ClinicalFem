package com.projeto.clinicalfem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.projeto.clinicalfem.models.UsuarioAtendente;

import org.springframework.stereotype.Service;

@Service
public class UsuarioAtendenteService {
    Firestore conex = FirestoreClient.getFirestore();

    public boolean cadastrar(UsuarioAtendente usuarioatendente) throws InterruptedException, ExecutionException {
        
        //resgata todos os membros e verifica se há emails repetidos
        ArrayList<UsuarioAtendente> usuarioatendentes = getAllUsuarioAtendentes(); boolean emailIgual = false;
        for(UsuarioAtendente  teste : usuarioatendentes){
            if(teste.getEmail().equals(usuarioatendente.getEmail())){
                emailIgual = true;
            }
        }

        if(emailIgual){
            return false;
        }
        
        // cria um ID aleatório a partir da coleção "Membros" do banco de dados
        DocumentReference doc = conex.collection("UsuarioAtendentes").document(); 

        // bota esse ID aleatório como ID do membro
        usuarioatendente.setId(doc.getId());

        // salva os dados do membro :)
        ApiFuture<WriteResult> writeResult = doc.set(usuarioatendente); // salva os dados do membro :)

        return true;
    }
    public ArrayList<UsuarioAtendente> getAllUsuarioAtendentes() throws InterruptedException, ExecutionException {
        //gera um ArrayList para Armazenar todos os membros resgatados
        ArrayList<UsuarioAtendente> lista = new ArrayList<UsuarioAtendente>();

        //busca no Banco de dados todos os 'documentos' da coleção 'Membros' e põe em ordem alfabética
        ApiFuture<QuerySnapshot> future = conex.collection("UsuarioAtendentes").orderBy("nome").get();

        //recebe uma lista dos 'documentos' de membros resgatados 
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        //transforma cada 'documento' dessa lista em uma instância da classe Membro e adiciona á ArrayList
        for (DocumentSnapshot document : documents) {
            UsuarioAtendente adic = document.toObject(UsuarioAtendente.class);
            lista.add(adic);
        }
        return lista;
    }
    public UsuarioAtendente getUsuarioAtendenteById(String id) throws InterruptedException, ExecutionException {
        UsuarioAtendente usuarioatendente = new UsuarioAtendente();
        
        //faz referência á coleção 'Membros' do Banco de dados
        CollectionReference usuarioatendentes = conex.collection("UsuarioAtendentes");

        //pesquisa todos o membro a partir da id recebida por parâmetro
        Query query = usuarioatendentes.whereEqualTo("id", id);

        //recebe uma lista dos 'documentos' de membros resgatados (no caso só resgatou 1 membro)
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        //transforma o documento em uma instância da classe Membro
        for (DocumentSnapshot document : querySnapshot){
            usuarioatendente = document.toObject(UsuarioAtendente.class);
        }

        return usuarioatendente;
    }
    public UsuarioAtendente login(UsuarioAtendente usuarioatendente) throws InterruptedException, ExecutionException{
        
        //faz referência á coleção 'Membros'
        CollectionReference usuarioatendentes = conex.collection("UsuarioAtendentes");

        //pesquisa os membros a partir do email e senha recebidos por parâmetro
        Query query = usuarioatendentes.whereEqualTo("email", usuarioatendente.getEmail()).whereEqualTo("senha", usuarioatendente.getSenha());

        //recebe uma lista dos 'documentos' de membros resgatados
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        //transforma o documento em uma instância da classe Membro
        UsuarioAtendente resultado = null;
        for (DocumentSnapshot document : querySnapshot){
            resultado = document.toObject(UsuarioAtendente.class);
        }

        return resultado;
    }
    public UsuarioAtendente getUsuarioAtendenteByEmail(String email) throws InterruptedException, ExecutionException {
        UsuarioAtendente usuarioatendente = new UsuarioAtendente();

        usuarioatendente.setId(null);
        
        //faz referência á coleção 'Membros' do Banco de dados
        CollectionReference usuarioatendentes = conex.collection("UsuarioAtendentes");

        //pesquisa todos o membro a partir da id recebida por parâmetro
        Query query = usuarioatendentes.whereEqualTo("email", email);

        //recebe uma lista dos 'documentos' de membros resgatados (no caso só resgatou 1 membro)
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        //transforma o documento em uma instância da classe Membro
        for (DocumentSnapshot document : querySnapshot){
            usuarioatendente = document.toObject(UsuarioAtendente.class);
        }

        return usuarioatendente;
    }

}
