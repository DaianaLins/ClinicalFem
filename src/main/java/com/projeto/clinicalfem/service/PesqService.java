package com.projeto.clinicalfem.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.projeto.clinicalfem.models.CadMedico;

import org.springframework.stereotype.Service;

@Service
public class PesqService {
    Firestore conex = FirestoreClient.getFirestore();
    public CadMedico getPesquisa(String especialidade) throws InterruptedException, ExecutionException{
        CadMedico cadmedico = new CadMedico();
        
        CollectionReference cadpacientes = (CollectionReference) conex.collection("CadMedicos");

        Query query = (Query) cadpacientes.whereEqualTo("especialidade", especialidade);
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();


        for (DocumentSnapshot document : querySnapshot){
            cadmedico = document.toObject(CadMedico.class);
        }

        return cadmedico;
    }
}
