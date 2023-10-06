package com.example.firstapi.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapi.Models.ModelPosts;
import com.example.firstapi.databinding.ItemPostBinding;

import java.util.List;

public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.Holder> {
    private List<ModelPosts> postList;

    private OnItemClick onItemClick;

    public static int clickPosition;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }


    public void setPostList(List<ModelPosts> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostBinding binding = ItemPostBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(postList.get(position));
    }

    @Override
    public int getItemCount() {
        return postList != null ? postList.size() : 0;
    }


    class Holder extends RecyclerView.ViewHolder {
        ItemPostBinding binding;

        public Holder(ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClick != null) {
                        onItemClick.OnClick(postList.get(getLayoutPosition()).getId());
                        clickPosition=postList.get(getLayoutPosition()).getId();
                    }
                }
            });
        }

        public void bind(ModelPosts posts) {
            binding.tvTitle.setText(posts.getTitle());
            binding.tvBudy.setText(posts.getBody());


        }

    }

    public interface OnItemClick {
        void OnClick(int id);
    }

}
