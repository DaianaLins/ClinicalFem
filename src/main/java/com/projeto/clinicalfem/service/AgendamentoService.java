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
import com.projeto.clinicalfem.models.Agendamento;

import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {
    Firestore conex = FirestoreClient.getFirestore();

    public boolean cadastrar(Agendamento agendamento) throws InterruptedException, ExecutionException {
        
        
        // cria um ID aleatório a partir da coleção "Membros" do banco de dados
        DocumentReference doc = conex.collection("Agendamentos").document(); 

        // bota esse ID aleatório como ID do membro
        agendamento.setCodigo(doc.getId());

        // salva os dados do membro :)
        ApiFuture<WriteResult> writeResult = doc.set(agendamento); // salva os dados do membro :)

        return true;
    }

    public ArrayList<Agendamento> getAllAgendamentos() throws InterruptedException, ExecutionException {
        ArrayList<Agendamento> lista = new ArrayList<Agendamento>();

        ApiFuture<QuerySnapshot> future = conex.collection("Agendamentos").orderBy("nome").get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            Agendamento adic = document.toObject(Agendamento.class);
            lista.add(adic);
        }
        return lista;
    }

    public Agendamento getAgendamentoByCodigo(String codigo) throws InterruptedException, ExecutionException {
        Agendamento agendamento = new Agendamento();
        
        CollectionReference agendamentos = (CollectionReference) conex.collection("Agendamentos");

        Query query = (Query) agendamentos.whereEqualTo("codigo", codigo);
        
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        //diz add cast to HUMMMMM KKKKKKKK FOIIII  :D


        for (DocumentSnapshot document : querySnapshot){
            agendamento = document.toObject(Agendamento.class);
        }

        return agendamento;

    }


    public void deletar(String codigo){
        ApiFuture<WriteResult> writeResult = conex.collection("Agendamentos").document(codigo).delete();
    }

    public void editar(Agendamento agendamento) {
      

        DocumentReference doc = conex.collection("Agendamentos").document(agendamento.getCodigo()); // resgata o doc pelo ID

        ApiFuture<WriteResult> writeResult = doc.set(agendamento); 
    }
    
}
