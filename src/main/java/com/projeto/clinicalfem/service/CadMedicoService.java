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
import com.projeto.clinicalfem.models.CadMedico;

import org.springframework.stereotype.Service;

@Service
public class CadMedicoService {

    Firestore conex = FirestoreClient.getFirestore();

    public boolean cadastrar(CadMedico cadmedico) throws InterruptedException, ExecutionException {
        
        
        // cria um ID aleatório a partir da coleção "Membros" do banco de dados
        DocumentReference doc = conex.collection("CadMedicos").document(); 

        // bota esse ID aleatório como ID do membro
        cadmedico.setCod(doc.getId());

        // salva os dados do membro :)
        ApiFuture<WriteResult> writeResult = doc.set(cadmedico); // salva os dados do membro :)

        return true;
    }

    public ArrayList<CadMedico> getAllCadMedicos() throws InterruptedException, ExecutionException {
        ArrayList<CadMedico> lista = new ArrayList<CadMedico>();

        ApiFuture<QuerySnapshot> future = conex.collection("CadMedicos").orderBy("nome").get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            CadMedico adic = document.toObject(CadMedico.class);
            lista.add(adic);
        }
        return lista;
    }

    public CadMedico getCadMedicoByCod(String cod) throws InterruptedException, ExecutionException {
        CadMedico cadmedico = new CadMedico();
        
        CollectionReference cadmedicos = (CollectionReference) conex.collection("CadMedicos");

        Query query = (Query) cadmedicos.whereEqualTo("cod", cod);
        
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        

        for (DocumentSnapshot document : querySnapshot){
            cadmedico = document.toObject(CadMedico.class);
        }

        return cadmedico;

    }

    public CadMedico getCadMedicoByName(String nome) throws InterruptedException, ExecutionException {
        CadMedico cadmedico = new CadMedico();
        
        CollectionReference cadmedicos = (CollectionReference) conex.collection("CadMedicos");

        Query query = (Query) cadmedicos.whereEqualTo("nome", nome);
        
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        

        for (DocumentSnapshot document : querySnapshot){
            cadmedico = document.toObject(CadMedico.class);
        }

        return cadmedico;

    }

    public CadMedico getCadMedicoByCRM(String crm) throws InterruptedException, ExecutionException{
        CadMedico cadmedico = new CadMedico();
        
        CollectionReference cadmedicos = (CollectionReference) conex.collection("CadMedicos");

        Query query = (Query) cadmedicos.whereEqualTo("crm", crm);
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();


        for (DocumentSnapshot document : querySnapshot){
            cadmedico = document.toObject(CadMedico.class);
        }

        return cadmedico;
    }


    public void deletar(String cod){
        ApiFuture<WriteResult> writeResult = conex.collection("CadMedicos").document(cod).delete();
    }

    public void editar(CadMedico cadmedico) {
      

        DocumentReference doc = conex.collection("CadMedicos").document(cadmedico.getCod()); // resgata o doc pelo ID

        ApiFuture<WriteResult> writeResult = doc.set(cadmedico); 
    }
}
