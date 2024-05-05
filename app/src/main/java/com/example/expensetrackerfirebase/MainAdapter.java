package com.example.expensetrackerfirebase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder,final int position, @NonNull MainModel model) {
holder.ItemName.setText(model.getItemName());
holder.Date.setText(model.getDate());
holder.ToOrBy.setText(model.getToOrBy());
holder.participant.setText(model.getParticipant());
holder.Description.setText(model.Description);
holder.Time.setText(model.getTime());
holder.Amount.setText(model.getAmount());

holder.btnEdit.setOnClickListener(new View.OnClickListener() { //binding edit button to popup.xml
    @Override
    public void onClick(View v) {
        final DialogPlus dialogPlus = DialogPlus.newDialog(holder.ItemName.getContext()).setContentHolder(new ViewHolder(R.layout.update_popup)).setExpanded(true,1935).create();

        View view = dialogPlus.getHolderView();
        EditText itemName= view.findViewById(R.id.txtName);
        EditText Date= view.findViewById(R.id.txtDate);
        EditText Time = view.findViewById(R.id.txtTime);
        EditText Description = view.findViewById(R.id.txtDescription);
        EditText Amount = view.findViewById(R.id.txtAmount);
        EditText participant = view.findViewById(R.id.txtParticipant);
        EditText ToOrBy = view.findViewById(R.id.txtToOrBy);

        Button btnUpdate = view.findViewById(R.id.btnUpdate);

        itemName.setText(model.getItemName());
        Date.setText(model.getDate());
        Time.setText(model.getTime());
        Description.setText(model.getDescription());
        Amount.setText(model.getAmount());
        participant.setText(model.getParticipant());
        ToOrBy.setText(model.getToOrBy());

        dialogPlus.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object>map= new HashMap<>();
                // Get values from EditTexts
                String ItemName = itemName.getText().toString();
                String date = Date.getText().toString();
                String time = Time.getText().toString();
                String description = Description.getText().toString();
                String amount = Amount.getText().toString();
                String Participant = participant.getText().toString();
                String toOrBy = ToOrBy.getText().toString();

// Check for empty EditTexts and add values to map only if not empty

                    map.put("ItemName", ItemName);


                    map.put("Date", date);


                    map.put("Time", time);


                    map.put("Description", description);


                    map.put("Amount", amount);


                    map.put("participant", Participant);


                    map.put("ToOrBy", toOrBy);


                FirebaseDatabase.getInstance().getReference().child("Items").child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(holder.ItemName.getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                        dialogPlus.dismiss();

                        notifyItemChanged(position);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(holder.ItemName.getContext(), "Error while updating", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


    }
});
holder.btnDelete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(holder.ItemName.getContext());
        builder.setTitle("Are you sure?");
        builder.setMessage("Deleted Data can't be Recovered");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseDatabase.getInstance().getReference().child("Items").child(getRef(position).getKey()).removeValue();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(holder.ItemName.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();

            }
        });

        builder.show();
    }
});

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
         TextView ItemName, Description, Time, ToOrBy, Amount, Date,participant;
Button btnEdit, btnDelete;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemName=(TextView)itemView.findViewById(R.id.ItemName);
            Description=(TextView)itemView.findViewById(R.id.Description);
            Amount=(TextView)itemView.findViewById(R.id.expense);
            Time=(TextView)itemView.findViewById(R.id.Time);
            ToOrBy=(TextView)itemView.findViewById(R.id.ToOrby);
            Date=(TextView)itemView.findViewById(R.id.Date);
            participant=(TextView)itemView.findViewById(R.id.participant);

            btnEdit=(Button) itemView.findViewById(R.id.btnEdit);
            btnDelete=(Button) itemView.findViewById(R.id.btnDelete);

        }
    }
}
