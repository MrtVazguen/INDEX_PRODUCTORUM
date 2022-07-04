package com.dev.mrvazguen.indexproductorum.data.repository.firestore.manager;

import android.util.ArrayMap;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dev.mrvazguen.indexproductorum.data.model.Articulo;
import com.dev.mrvazguen.indexproductorum.data.repository.firestore.FirebaseDao;
import com.dev.mrvazguen.indexproductorum.data.repository.iFirestoreNotification;
import com.dev.mrvazguen.indexproductorum.utils.GlobarArgs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class ArticuloManagerDB   extends FirebaseDao<Articulo>{
 FirebaseFirestore db = FirebaseFirestore.getInstance();
String collectionPath;
    public ArticuloManagerDB(String articuloCollection) {
        this.collectionPath = articuloCollection;
    }

    @Override
    public void append(Articulo articulo, iFirestoreNotification transactionNotification) {
        DocumentReference articulosCR = db
                .collection(GlobarArgs.GLOBAL_Collection)
                .document(GlobarArgs.ARTICULO_DOCUMENTO);
        //articulosCR.document(articulo.getCategoria().get(0)).set(articulo);



        articulosCR.set(articulo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                transactionNotification.OnSuccess();
            }
        });



    }
    @Override
    public void readLiveDate(ArrayList<Articulo> list, Object tipoClase) {
        //CollectionReference userRef = db.collection(collectionPath);
       /* CollectionReference articuloRef = db
                  .collection(GlobarArgs.GLOBAL_Collection)
                  .document(GlobarArgs.ARTICULO_DOCUMENTO);
        articuloRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

            @Override
            public void onSuccess(QuerySnapshot snapshot) {
                //List lista =  snapshot.toObjects(FirestoreDB.class)


                for (DocumentChange doc : snapshot.getDocumentChanges()) {
                    switch (doc.getType()) {
                        case ADDED:
                            Map<String, Object> documents = (Map<String, Object>) doc.getDocument().getData();
                            //Object[] fieldArray = documents.entrySet().toArray();//All field of firestore document

                            list.add(((Articulo) documents));
                            break;
                        case REMOVED:
                            String id = doc.getDocument().getId();
                            int currentPosition = -1;
                            for (int i = 0; i < list.size(); i++) {
                                if (id.equals(list.get(i))) {
                                    currentPosition = i;
                                    break;
                                }
                            }
                            list.remove(currentPosition);
                            break;
                        case MODIFIED:

                            break;


                    }
                }


            }

        });
        articuloRef.get().addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        */
    }

    @Override
    public void   read(ArrayList<Articulo>list, iFirestoreNotification notification) {


        ///region tmp
       /* FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference cfArticulo =db.collection(GlobarArgs.ARTICULO_COLLECTION_PATH);


       // if(!cfArticulo.get().isSuccessful())  notification.OnFailure();

         if( !cfArticulo.get().isComplete()){
            notification.OnFailure();
        }

        else {
            cfArticulo.get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<DocumentSnapshot> df = queryDocumentSnapshots.getDocuments();

                            Log.e("TAG_Firestore", df.get(0).getId());
                        }
                    })
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                               @Override
                                               public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                   if (task.isSuccessful()) {
                                                       for (QueryDocumentSnapshot document : task.getResult()) {
                                                           Log.d("TAG_Firestore", document.getId() + " => " + document.getData());
                                                       }
                                                   } else {
                                                       Log.w("TAG_Firestore", "Error getting documents.", task.getException());
                                                   }
                                               }
                                           }

                    ).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("TAG_Firestore", e.getMessage());
                        }
                    });



        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        list.add(document.toObject(Articulo.class));
                    }
                } else {

                }
            }
        });


             String TAG="Leer_ARTICUlo";
             DocumentReference docRef = db.collection(GlobarArgs.ARTICULO_COLLECTION_PATH).document("prueba");
             docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                 @Override
                 public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                     if (task.isSuccessful()) {
                         DocumentSnapshot document = task.getResult();
                         if (document.exists()) {
                             Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                             list.add(new Articulo(document.getString("nombre")));
                         } else {
                             Log.d(TAG, "No such document");
                         }
                     } else {
                         Log.d(TAG, "get failed with ", task.getException());
                     }
                 }
             });
        }

        */
       ///endregion
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("GlobalCollection/articulos/collectionArticulos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                             List<Articulo>articuloList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Firestore_Result", document.getId() + " => " + document.getData());
                                Articulo articulo = document.toObject(Articulo.class);
                                list.add(articulo);

                            }
                        } else {
                           // Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                    }

                });
        //return   list;
    }
}
