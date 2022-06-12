package com.example.recommentflowchartui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Frag2 extends Fragment {

    RecyclerView recyclerView2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag2, container, false);
        recyclerView2 = view.findViewById(R.id.recyclerView2);


        return getPosts(view);

//        Log.i("fuck", "image size : "+images.size());
//        Log.i("fuck", "name size : "+name.size());


    }

    private View getPosts(View view) {

        Call<List<Content>> call = RetrofitClient.getInstance().getMyApi().getPosts();

        call.enqueue(new Callback<List<Content>>() {
            @Override
            public void onResponse(Call<List<Content>> call, Response<List<Content>> response) {

                List<Content> postList = response.body();

                Log.i("fuck", ""+response.body().size());

                HelperAdapter helperAdapter = new HelperAdapter(getContext(), postList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView2.setLayoutManager(linearLayoutManager);
                recyclerView2.setAdapter(helperAdapter);
            }

            @Override
            public void onFailure(Call<List<Content>> call, Throwable t) {
                Toast.makeText(getContext(), "An error has occured in get", Toast.LENGTH_LONG).show();
            }

        });

        return view;
    }
}
