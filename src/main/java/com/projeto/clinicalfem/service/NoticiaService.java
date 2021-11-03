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
import com.projeto.clinicalfem.models.Noticia;

import org.springframework.stereotype.Service;

@Service
public class NoticiaService {
    Firestore conex = FirestoreClient.getFirestore();

    public boolean salvar(Noticia noticia) throws InterruptedException, ExecutionException {
        
        
        // cria um ID aleatório a partir da coleção "Membros" do banco de dados
        DocumentReference doc = conex.collection("Noticias").document(); 

        // bota esse ID aleatório como ID do membro
        noticia.setId(doc.getId());

        // salva os dados do membro :)
        ApiFuture<WriteResult> writeResult = doc.set(noticia); // salva os dados do membro :)

        

        return true;
    }

    public Noticia getNoticiaById(String id) throws InterruptedException, ExecutionException {
        Noticia noticia = new Noticia();
        
        CollectionReference noticias = (CollectionReference) conex.collection("Noticias");

        Query query = (Query) noticias.whereEqualTo("id", id);
        
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        

        for (DocumentSnapshot document : querySnapshot){
            noticia = document.toObject(Noticia.class);
        }

        return noticia;

    }
    public ArrayList<Noticia> getAllNoticias() throws InterruptedException, ExecutionException {
        ArrayList<Noticia> lista = new ArrayList<Noticia>();

        ApiFuture<QuerySnapshot> future = conex.collection("Noticias").orderBy("data").get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            Noticia adic = document.toObject(Noticia.class);
            lista.add(adic);
        }
        return lista;
    }
}
