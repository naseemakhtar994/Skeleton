package io.rmiri.sample.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import io.rmiri.sample.Adapter.AdapterSample2;
import io.rmiri.sample.Data.DataObject;
import io.rmiri.sample.Data.GenarateDataFake;
import io.rmiri.skeleton.Master.SkeletonDetail;
import io.rmiri.skeleton.sample.R;


public class Sample2Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterSample2 adapterSample2;
    private ArrayList<DataObject> dataObjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_2);


        //toolbar
        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //initial recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        // initial SkeletonDetail and set in adapter
        SkeletonDetail skeletonDetail = new SkeletonDetail();
        skeletonDetail.setSkeletonIsOn(true);
        adapterSample2 = new AdapterSample2(getApplicationContext(), dataObjects, skeletonDetail);

        //set adapter in recyclerView
        recyclerView.setAdapter(adapterSample2);


        //after 5 second get data fake
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dataObjects = new GenarateDataFake().genarateDataFake();
                adapterSample2.addMoreDataAndSkeletonFinish(dataObjects);
            }
        }, 5000);
    }


}
