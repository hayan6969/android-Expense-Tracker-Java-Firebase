package com.example.expensetrackerfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AddActivity.this, MainActivity.class);
        startActivity(intent);
    }

    EditText ItemName, Date, Time, Description, ToOrBy, participant, Amount;
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ItemName=(EditText)findViewById(R.id.addName);
        Date=(EditText)findViewById(R.id.addDate);
        Time = (EditText) findViewById(R.id.addTime);
        Description = (EditText) findViewById(R.id.addDescription);
        ToOrBy=(EditText) findViewById(R.id.addToOrBy);
        participant=(EditText)findViewById(R.id.addParticipant);
        Amount=(EditText) findViewById(R.id.addAmount);


        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnBack=(Button) findViewById(R.id.btnBack);

        clearAll();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
insertData();
clearAll();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                clearAll();
//                finish();

                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
    private void insertData(){
        String itemName = ItemName.getText().toString();
        String amount = Amount.getText().toString();
        String date = Date.getText().toString();
        String time = Time.getText().toString();
        String description = Description.getText().toString();
        String toOrBy = ToOrBy.getText().toString();
        String Participant = participant.getText().toString();

        // Check if any data is entered before creating Toast message
        if (!itemName.isEmpty() || !amount.isEmpty() || !date.isEmpty() || !time.isEmpty() || !description.isEmpty() || !toOrBy.isEmpty() || !Participant.isEmpty()) {
            Map<String, Object> map = new HashMap<>();
            map.put("ItemName", itemName);
            map.put("Amount", amount);
            map.put("Date", date);
            map.put("Time", time);
            map.put("Description", description);
            map.put("ToOrBy", toOrBy);
            map.put("participant", Participant);

            FirebaseDatabase.getInstance().getReference().child("Items").push().setValue(map)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(AddActivity.this, "Expense Added Successfully!", Toast.LENGTH_SHORT).show();
                            clearAll();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddActivity.this, "Error while adding Expense", Toast.LENGTH_SHORT).show();
                        }
                    });

        } else {
            Toast.makeText(AddActivity.this, "Please enter some data before adding", Toast.LENGTH_SHORT).show();
        }
    }
    private void clearAll(){
        ItemName.setText("");
        Date.setText("");
        Time.setText("");
        participant.setText("");
        ToOrBy.setText("");
        Description.setText("");
        Amount.setText("");
        if (Toast.makeText(AddActivity.this, "Expense Added Successfully!", Toast.LENGTH_SHORT) != null) {
            Toast.makeText(AddActivity.this, "Expense Added Successfully!", Toast.LENGTH_SHORT).cancel();
        }
        if (Toast.makeText(AddActivity.this, "Error while adding Expense", Toast.LENGTH_SHORT) != null) {
            Toast.makeText(AddActivity.this, "Error while adding Expense", Toast.LENGTH_SHORT).cancel();
        }


    }


}