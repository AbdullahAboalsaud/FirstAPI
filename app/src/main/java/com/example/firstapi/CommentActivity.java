package com.example.firstapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.firstapi.Adapters.AdapterComments;
import com.example.firstapi.Adapters.AdapterPosts;
import com.example.firstapi.Models.ModelComments;
import com.example.firstapi.databinding.ActivityCommentBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity {

    ActivityCommentBinding binding;
    AdapterComments adapterComments = new AdapterComments();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int postId = AdapterPosts.clickPosition;

        RetroConnection.getInstance()
                .getComments(postId).enqueue(new Callback<List<ModelComments>>() {
                    @Override
                    public void onResponse(Call<List<ModelComments>> call, Response<List<ModelComments>> response) {
                        if (response.code() == 200) {

                            adapterComments.setCommentList(response.body());
                            binding.recyclerComments.setAdapter(adapterComments);

                        }
                    }

                    @Override
                    public void onFailure(Call<List<ModelComments>> call, Throwable t) {
                        Toast.makeText(CommentActivity.this,
                                "Fail: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

}