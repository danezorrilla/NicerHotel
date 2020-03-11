package com.bb.nicerhotel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bb.nicerhotel.R;
import com.bb.nicerhotel.model.Guest;

import java.util.List;

public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.GuestViewHolder>{

    public interface GuestClickListener {
        void displayGuest(Guest guest);
    }

    private List<Guest> guestList;
    private GuestClickListener guestClickListener;

    public GuestAdapter(List<Guest> guestList, GuestClickListener guestClickListener) {
        this.guestList = guestList;
        this.guestClickListener = guestClickListener;
    }

    class GuestViewHolder extends RecyclerView.ViewHolder{
        TextView guestNameTextView;
        public GuestViewHolder(View view){
            super(view);
            guestNameTextView = view.findViewById(R.id.guest_name_text);
        }
    }

    @Override
    public int getItemCount(){return guestList.size();}

    @Override
    public GuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.guest_name, parent, false);
        return new GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GuestViewHolder holder, int position){
        Animation slide = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.right_to_left);
        holder.itemView.startAnimation(slide);

        int index = position;
        holder.guestNameTextView.setText(guestList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("Button is clicked");
                guestClickListener.displayGuest(guestList.get(index));


            }
        });

    }

}
