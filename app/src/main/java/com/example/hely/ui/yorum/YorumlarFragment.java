package com.example.hely.ui.yorum;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hely.NavigationDrawer2;
import com.example.hely.ParcaActivity;
import com.example.hely.R;
import com.example.hely.adapters.EditorAdapter;
import com.example.hely.adapters.YorumAdapter;
import com.example.hely.models.EditorModel;
import com.example.hely.models.YorumModel;

import java.util.ArrayList;
import java.util.List;

public class YorumlarFragment extends Fragment {

    ImageButton btnGeriYorumlar;
    ImageView yorumlarImage;
    TextView yorumlarParcaAdi;


    RecyclerView recyclerView;
    List<YorumModel> yorumModels;
    YorumAdapter yorumAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_yorumlar, container,false);

        yorumlarImage = rootView.findViewById(R.id.yorumlar_img);
        yorumlarParcaAdi = rootView.findViewById(R.id.yorumlar_isim_txt);

        btnGeriYorumlar = rootView.findViewById(R.id.btnGeriYorumlar);

        //yorumlarImage.setImageResource(R.drawable.ay_sarkisi);
        //yorumlarParcaAdi.setText("ay şarkısı");


        btnGeriYorumlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ParcaActivity.class);
                view.getContext().startActivity(i);
            }
        });


        recyclerView = rootView.findViewById(R.id.yorumlar_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        yorumModels = new ArrayList<>();

        yorumModels.add(new YorumModel("Tuana Kaya", "Sagopa bir şeyler anlatıyor, dinlerken betimletiyor yoksa yine mi bir Story Telling!", 3.5F));
        yorumModels.add(new YorumModel("Hilal Seyhan", "Sagopa yine bildiğimiz gibi, bu parça bir harika!", 4.0F));
        yorumModels.add(new YorumModel("Nurhanım Hamdi", "Sagopa şaheseri. Öyle bir şarkıdır ki Sagopa şimdi denese bile bir daha böyle bir şey çıkmayacak!", 4.5F));
        yorumModels.add(new YorumModel("Enes Yüksel", "Negatif bir izlenim veren bu parça haftanın trendlerine girmiş durumda!", 2.0F));



        yorumAdapter = new YorumAdapter(getContext(), yorumModels);
        recyclerView.setAdapter(yorumAdapter);

        yorumAdapter.notifyDataSetChanged();




        return rootView;
    }
}