package com.example.hely;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hely.adapters.HomeVerAdapter;
import com.example.hely.models.HomeVerModel;
import com.example.hely.ui.yorum.YorumYapActivity;
import com.example.hely.ui.yorum.YorumlarActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class ParcaActivity extends AppCompatActivity {
    TextView lblParcaAdi, lblTarih, lblSanatci, lblParcaSozleri;
    ImageButton btnGeriParca;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String parcaID;
    CollapsingToolbarLayout collapsing_toolbar;
    ImageView imgKapak;


    FloatingActionButton fabOpenClose, fabSeslendir, fabYorumYap, fabTumYorumlar;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    boolean isOpen = false;

    HomeVerAdapter homeVerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parca);


        getSupportActionBar().hide();

        imgKapak = findViewById(R.id.imgKapak);
        collapsing_toolbar = findViewById(R.id.collapsing_toolbar);
        lblParcaAdi = findViewById(R.id.lblParcaAdi);
        lblTarih = findViewById(R.id.lblTarih);
        lblSanatci = findViewById(R.id.lblSanatci);
        lblParcaSozleri = findViewById(R.id.lblParcaSozleri);
        btnGeriParca = findViewById(R.id.btnGeriParca);


        fabOpenClose = findViewById(R.id.fab_open_close);
        fabSeslendir = findViewById(R.id.fab_seslendir);
        fabYorumYap = findViewById(R.id.fab_yorumyap);
        fabTumYorumlar = findViewById(R.id.fab_tumyorumlar);

        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);

        rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);



        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //parcaID = fAuth.getCurrentUser().getUid();



        DocumentReference docRef = db.collection("Parcalar").document("6JOyGv0mKBs87f8zMUoK");
        docRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                String uri = documentSnapshot.getString("Kapak");
                Glide.with(ParcaActivity.this).load(uri).into(imgKapak);
            }
        });

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    String albumAd = document.getString("Album");
                    if (document.exists()) {
                        collapsing_toolbar.setTitle(albumAd);
                        lblParcaAdi.setText(document.getString("Ad"));
                        lblTarih.setText(document.getString("Yil"));
                        lblSanatci.setText(document.getString("Sanatci"));
                        lblParcaSozleri.setText(document.getString("Sozler"));
                    } else {

                    }
                } else {

                }
            }
        });

        btnGeriParca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ParcaActivity.this, NavigationDrawer2.class);
                startActivity(i);
            }
        });


        fabOpenClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
            }
        });
        fabSeslendir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
            }
        });
        fabYorumYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                Intent i = new Intent(ParcaActivity.this, YorumYapActivity.class);
                startActivity(i);
            }
        });
        fabTumYorumlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                Intent i = new Intent(ParcaActivity.this, YorumlarActivity.class);
                startActivity(i);
            }
        });


        homeVerAdapter.setOnClickListener(new HomeVerAdapter.MyOnClickListener() {
            @Override
            public void MyOnClick(HomeVerModel homeVerModel1, int position) {
                Toast.makeText(ParcaActivity.this, "AnaSayfaFragment" + homeVerModel1.getName(), Toast.LENGTH_SHORT).show();
            }
        });


        }

    private void animateFab(){
        if(isOpen){
            fabOpenClose.startAnimation(rotateForward);

            fabSeslendir.startAnimation(fabClose);
            fabYorumYap.startAnimation(fabClose);
            fabTumYorumlar.startAnimation(fabClose);

            fabSeslendir.setClickable(false);
            fabYorumYap.setClickable(false);
            fabTumYorumlar.setClickable(false);

            isOpen = false;
        }else{
            fabOpenClose.startAnimation(rotateBackward);

            fabSeslendir.startAnimation(fabOpen);
            fabYorumYap.startAnimation(fabOpen);
            fabTumYorumlar.startAnimation(fabOpen);

            fabSeslendir.setClickable(true);
            fabYorumYap.setClickable(true);
            fabTumYorumlar.setClickable(true);

            isOpen = true;
        }

    }
}