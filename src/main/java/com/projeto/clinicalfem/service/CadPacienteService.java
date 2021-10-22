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
import com.projeto.clinicalfem.models.CadPaciente;

import org.springframework.stereotype.Service;

@Service
public class CadPacienteService {
    Firestore conex = FirestoreClient.getFirestore();

    public boolean cadastrar(CadPaciente cadpaciente){
        DocumentReference doc = conex.collection("CadPacientes").document();
        cadpaciente.setId(doc.getId());
        ApiFuture<WriteResult> writeResult = doc.set(cadpaciente);
        return true;
    }
    public ArrayList<CadPaciente> getAllCadPacientes() throws InterruptedException, ExecutionException {
        ArrayList<CadPaciente> lista = new ArrayList<CadPaciente>();

        ApiFuture<QuerySnapshot> future = conex.collection("CadPacientes").orderBy("nome").get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            CadPaciente adic = document.toObject(CadPaciente.class);
            lista.add(adic);
        }
        return lista;
    }
    public CadPaciente getCadPacienteById(String id) throws InterruptedException, ExecutionException {
        CadPaciente cadpaciente = new CadPaciente();
        
        CollectionReference CadPacientes = (CollectionReference) conex.collection("CadPacientes");

        Query query = (Query) CadPacientes.whereEqualTo("id", id);
        
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        //diz add cast to HUMMMMM KKKKKKKK FOIIII  :D


        for (DocumentSnapshot document : querySnapshot){
            cadpaciente = document.toObject(CadPaciente.class);
        }

        return cadpaciente;

    }
    public void deletar(String id){
        ApiFuture<WriteResult> writeResult = conex.collection("CadPacientes").document(id).delete();
    }

    public void editar(CadPaciente cadPaciente) {
      

        DocumentReference doc = conex.collection("CadPacientes").document(cadPaciente.getId()); // resgata o doc pelo ID

        ApiFuture<WriteResult> writeResult = doc.set(cadPaciente); 
    }

}
