package com.example.firstapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.firstapi.Adapters.AdapterPosts;
import com.example.firstapi.Models.ModelPosts;
import com.example.firstapi.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    AdapterPosts adapterPosts = new AdapterPosts();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapterPosts.setOnItemClick(new AdapterPosts.OnItemClick() {
            @Override
            public void OnClick(int id) {
                startActivity(new Intent(MainActivity.this,CommentActivity.class));
            }
        });

        RetroConnection.getInstance().getPosts()
                .enqueue(new Callback<List<ModelPosts>>() {
                    @Override
                    public void onResponse(Call<List<ModelPosts>> call,Response<List<ModelPosts>> response) {
                        if(response.code() == 200){

                            adapterPosts.setPostList(response.body());
                            binding.recyclerPosts.setAdapter(adapterPosts);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ModelPosts>> call, Throwable t) {
                        Toast.makeText(MainActivity.this,
                                "Fail: "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}