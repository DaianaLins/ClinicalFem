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
    Firestore conex = FirestoreClient.getFirestore(); // gera uma conexão a qual irá fazer todo o CRUD

    public boolean cadastrar(UsuarioAtendente atendente) throws InterruptedException, ExecutionException {
        
        //resgata todos os membros e verifica se há emails repetidos
        /*ArrayList<UsuarioAtendente> atendentes = getAllAtendentes(); boolean emailIgual = false;
        for(UsuarioAtendente teste : atendentes){
            if(teste.getEmail().equals(atendente.getEmail())){
                emailIgual = true;
            }
        }

        if(emailIgual){
            return false;
        }*/
        
        // cria um ID aleatório a partir da coleção "Membros" do banco de dados
        DocumentReference doc = conex.collection("UsuarioAtendentes").document(); 

        // bota esse ID aleatório como ID do membro
        atendente.setId(doc.getId());

        // salva os dados do membro :)
        ApiFuture<WriteResult> writeResult = doc.set(atendente); // salva os dados do membro :)

        return true;
    }

    public ArrayList<UsuarioAtendente> getAllAtendentes() throws InterruptedException, ExecutionException {
        //gera um ArrayList para Armazenar todos os membros resgatados
        ArrayList<UsuarioAtendente> lista = new ArrayList<>();

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

    public UsuarioAtendente getMembroById(String id) throws InterruptedException, ExecutionException {
        UsuarioAtendente membro = new UsuarioAtendente();
        
        //faz referência á coleção 'Membros' do Banco de dados
        CollectionReference membros = conex.collection("UsuarioAtendentes");

        //pesquisa todos o membro a partir da id recebida por parâmetro
        Query query = membros.whereEqualTo("id", id);

        //recebe uma lista dos 'documentos' de membros resgatados (no caso só resgatou 1 membro)
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        //transforma o documento em uma instância da classe Membro
        for (DocumentSnapshot document : querySnapshot){
            membro = document.toObject(UsuarioAtendente.class);
        }

        return membro;
    }

    public boolean editar(UsuarioAtendente membro) throws InterruptedException, ExecutionException {
        
        //resgata todos os membros e verifica se há emails repetidos
        ArrayList<UsuarioAtendente> membros = getAllAtendentes(); boolean emailIgual = false;
        for(UsuarioAtendente teste : membros){
            if(teste.getEmail().equals(membro.getEmail()) && !teste.getId().equals(membro.getId())){
                emailIgual = true;
            }
        }

        if(emailIgual){
            return false;
        }

        //faz referência á coleção 'Membros' e resgata o 'documento' a partir da Id do membro
        DocumentReference doc = conex.collection("UsuarioAtendentes").document(membro.getId()); // resgata o doc pelo ID

        //substitui os dados antigos pelos novos registrados na instância recebida por parâmetro
        ApiFuture<WriteResult> writeResult = doc.set(membro); // salva os dados do membro :)

        return true;
    }

    public void apagar(String id){
        //Faz referência à coleção 'Membros', resgata o 'documento' pelo Id e apaga ele
        ApiFuture<WriteResult> writeResult = conex.collection("UsuarioAtendentes").document(id).delete();
    }

    public UsuarioAtendente login(UsuarioAtendente membro) throws InterruptedException, ExecutionException{
        
        //faz referência á coleção 'Membros'
        CollectionReference membros = conex.collection("UsuarioAtendentes");

        //pesquisa os membros a partir do email e senha recebidos por parâmetro
        Query query = membros.whereEqualTo("email", membro.getEmail()).whereEqualTo("senha", membro.getSenha());

        //recebe uma lista dos 'documentos' de membros resgatados
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        //transforma o documento em uma instância da classe Membro
        UsuarioAtendente resultado = null;
        for (DocumentSnapshot document : querySnapshot){
            resultado = document.toObject(UsuarioAtendente.class);
        }

        return resultado;
    }

    public UsuarioAtendente getMembroByEmail(String email) throws InterruptedException, ExecutionException {
        UsuarioAtendente membro = new UsuarioAtendente();

        membro.setId(null);
        
        //faz referência á coleção 'Membros' do Banco de dados
        CollectionReference membros = conex.collection("UsuarioAtendentes");

        //pesquisa todos o membro a partir da id recebida por parâmetro
        Query query = membros.whereEqualTo("email", email);

        //recebe uma lista dos 'documentos' de membros resgatados (no caso só resgatou 1 membro)
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        //transforma o documento em uma instância da classe Membro
        for (DocumentSnapshot document : querySnapshot){
            membro = document.toObject(UsuarioAtendente.class);
        }

        return membro;
    }
}