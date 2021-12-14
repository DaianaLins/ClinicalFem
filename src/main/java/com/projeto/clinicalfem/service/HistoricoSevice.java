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
import com.projeto.clinicalfem.models.Historico;

import org.springframework.stereotype.Service;

@Service
public class HistoricoSevice {
    Firestore conex = FirestoreClient.getFirestore();
    public boolean salvar(Historico historico) throws InterruptedException, ExecutionException {
        
        
        // cria um ID aleatório a partir da coleção "Membros" do banco de dados
        DocumentReference doc = conex.collection("historicos").document(historico.getId()); 

        // salva os dados do membro :)
        ApiFuture<WriteResult> writeResult = doc.set(historico); // salva os dados do membro :)

        

        return true;
    }

    public Historico getHistoricoById(String id) throws InterruptedException, ExecutionException {
        Historico historico = new Historico();
        
        CollectionReference historicos = (CollectionReference) conex.collection("historicos");

        Query query = (Query) historicos.whereEqualTo("id", id);
        
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        

        for (DocumentSnapshot document : querySnapshot){
            historico = document.toObject(Historico.class);
        }

        return historico;

    }
    public Historico getHistoricoByNome(String nome) throws InterruptedException, ExecutionException {
        Historico historico = new Historico();
        
        CollectionReference historicos = (CollectionReference) conex.collection("historicos");

        Query query = (Query) historicos.whereEqualTo("nome", nome);
        
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        

        for (DocumentSnapshot document : querySnapshot){
            historico = document.toObject(Historico.class);
        }

        return historico;

    }
    public ArrayList<Historico> getAllHistoricos() throws InterruptedException, ExecutionException {
        ArrayList<Historico> lista = new ArrayList<Historico>();

        ApiFuture<QuerySnapshot> future = conex.collection("historicos").orderBy("data").get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            Historico adic = document.toObject(Historico.class);
            lista.add(adic);
        }
        return lista;
    }
    public void deletar(String id){
        ApiFuture<WriteResult> writeResult = conex.collection("historicos").document(id).delete();
    }
    public void editar(Historico historico) {
      

        DocumentReference doc = conex.collection("historicos").document(historico.getId()); // resgata o doc pelo ID

        ApiFuture<WriteResult> writeResult = doc.set(historico); 
    }
  
}
