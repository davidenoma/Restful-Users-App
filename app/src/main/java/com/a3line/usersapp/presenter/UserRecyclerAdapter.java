package com.a3line.usersapp.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a3line.usersapp.R;
import com.a3line.usersapp.views.UserDetail;
import com.a3line.usersapp.models.User;

import java.util.List;

@SuppressWarnings("MagicConstant")
public class UserRecyclerAdapter extends
        RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private final List<User> mUsers;
    private final LayoutInflater mlayoutInflater;


    public UserRecyclerAdapter(Context context, List<User> users){
       this.mContext = context;
       this.mUsers = users;
       this.mlayoutInflater = LayoutInflater.from(context);
    }



    @Override
    public UserRecyclerAdapter.ViewHolder onCreateViewHolder( ViewGroup viewGroup, int viewType) {

        View itemView = mlayoutInflater.inflate(R.layout.user_content,
                viewGroup, false);


        return new ViewHolder(itemView);
    }


    public void onBindViewHolder(ViewHolder viewHolder, int position) {
                 final User user = mUsers.get(position);

                viewHolder.mFullName.setText(user.getName());
                viewHolder.mUserName.setText(user.getUsername());
                viewHolder.mEmail.setText(user.getEmail());

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, UserDetail.class);
                        intent.putExtra("user", user);
                        mContext.startActivity(intent);
                    }
                });


    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }



    public  class ViewHolder extends RecyclerView.ViewHolder{
        public  TextView mFullName;
        public   TextView mUserName;
        public  TextView mEmail;
        public View mView;


        public ViewHolder(View itemView) {
            super(itemView);
            mFullName = itemView.findViewById(R.id.full_name_holder);
            mUserName = itemView.findViewById(R.id.user_name);
            mEmail = itemView.findViewById(R.id.email);
            mView = itemView;


        }
    }
}
