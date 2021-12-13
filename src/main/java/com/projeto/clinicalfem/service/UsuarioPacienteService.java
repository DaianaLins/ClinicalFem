package com.projeto.clinicalfem.service;

import java.util.concurrent.ExecutionException;
import java.util.ArrayList;
import java.util.List;


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
import com.projeto.clinicalfem.models.Usuarios;

import org.springframework.stereotype.Service;

@Service
public class UsuarioPacienteService {
    Firestore conex = FirestoreClient.getFirestore(); // gera uma conexão a qual irá fazer todo o CRUD

    public boolean cadastrar(Usuarios usuario) throws InterruptedException, ExecutionException {
        
        //resgata todos os membros e verifica se há emails repetidos
        ArrayList<Usuarios> usuarios = getAllUsuarios(); boolean emailIgual = false;
        for(Usuarios teste : usuarios){
            if(teste.getEmail().equals(usuario.getEmail())){
                emailIgual = true;
            }
        }

        if(emailIgual){
            return false;
        }
        
        // cria um ID aleatório a partir da coleção "Membros" do banco de dados
        DocumentReference doc = conex.collection("UsuarioPacientes").document(); 

        // bota esse ID aleatório como ID do membro
        usuario.setId(doc.getId());

        // salva os dados do membro :)
        ApiFuture<WriteResult> writeResult = doc.set(usuario); // salva os dados do membro :)

        return true;
    }

    public ArrayList<Usuarios> getAllUsuarios() throws InterruptedException, ExecutionException {
        //gera um ArrayList para Armazenar todos os membros resgatados
        ArrayList<Usuarios> lista = new ArrayList<>();

        //busca no Banco de dados todos os 'documentos' da coleção 'Membros' e põe em ordem alfabética
        ApiFuture<QuerySnapshot> future = conex.collection("UsuarioPacientes").orderBy("nome").get();

        //recebe uma lista dos 'documentos' de membros resgatados 
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        //transforma cada 'documento' dessa lista em uma instância da classe Membro e adiciona á ArrayList
        for (DocumentSnapshot document : documents) {
            Usuarios adic = document.toObject(Usuarios.class);
            lista.add(adic);
        }
        return lista;
    }

    public Usuarios getMembroById(String id) throws InterruptedException, ExecutionException {
        Usuarios membro = new Usuarios();
        
        //faz referência á coleção 'Membros' do Banco de dados
        CollectionReference membros = conex.collection("UsuarioPacientes");

        //pesquisa todos o membro a partir da id recebida por parâmetro
        Query query = membros.whereEqualTo("id", id);

        //recebe uma lista dos 'documentos' de membros resgatados (no caso só resgatou 1 membro)
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        //transforma o documento em uma instância da classe Membro
        for (DocumentSnapshot document : querySnapshot){
            membro = document.toObject(Usuarios.class);
        }

        return membro;
    }

    public boolean editar(Usuarios membro) throws InterruptedException, ExecutionException {
        
        //resgata todos os membros e verifica se há emails repetidos
        ArrayList<Usuarios> membros = getAllUsuarios(); boolean emailIgual = false;
        for(Usuarios teste : membros){
            if(teste.getEmail().equals(membro.getEmail()) && !teste.getId().equals(membro.getId())){
                emailIgual = true;
            }
        }

        if(emailIgual){
            return false;
        }

        //faz referência á coleção 'Membros' e resgata o 'documento' a partir da Id do membro
        DocumentReference doc = conex.collection("UsuarioPacientes").document(membro.getId()); // resgata o doc pelo ID

        //substitui os dados antigos pelos novos registrados na instância recebida por parâmetro
        ApiFuture<WriteResult> writeResult = doc.set(membro); // salva os dados do membro :)

        return true;
    }

    public void apagar(String id){
        //Faz referência à coleção 'Membros', resgata o 'documento' pelo Id e apaga ele
        ApiFuture<WriteResult> writeResult = conex.collection("UsuarioPacientes").document(id).delete();
    }

    public Usuarios login(Usuarios membro) throws InterruptedException, ExecutionException{
        
        //faz referência á coleção 'Membros'
        CollectionReference membros = conex.collection("UsuarioPacientes");

        //pesquisa os membros a partir do email e senha recebidos por parâmetro
        Query query = membros.whereEqualTo("email", membro.getEmail()).whereEqualTo("senha", membro.getSenha());

        //recebe uma lista dos 'documentos' de membros resgatados
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        //transforma o documento em uma instância da classe Membro
        Usuarios resultado = null;
        for (DocumentSnapshot document : querySnapshot){
            resultado = document.toObject(Usuarios.class);
        }

        return resultado;
    }

    public Usuarios getMembroByEmail(String email) throws InterruptedException, ExecutionException {
        Usuarios membro = new Usuarios();

        membro.setId(null);
        
        //faz referência á coleção 'Membros' do Banco de dados
        CollectionReference membros = conex.collection("UsuarioPacientes");

        //pesquisa todos o membro a partir da id recebida por parâmetro
        Query query = membros.whereEqualTo("email", email);

        //recebe uma lista dos 'documentos' de membros resgatados (no caso só resgatou 1 membro)
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        //transforma o documento em uma instância da classe Membro
        for (DocumentSnapshot document : querySnapshot){
            membro = document.toObject(Usuarios.class);
        }

        return membro;
    }
    
}
