package com.example.mini;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText searchKeywords;
    private Button searchButton;
    private RecyclerView recyclerView;
    private IPCAdapter ipcAdapter;
    private List<IPCSection> ipcSectionsList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchKeywords = findViewById(R.id.search_keywords);
        searchButton = findViewById(R.id.search_button);
        recyclerView = findViewById(R.id.results_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ipcSectionsList = new ArrayList<>();
        ipcAdapter = new IPCAdapter(ipcSectionsList);
        recyclerView.setAdapter(ipcAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("laws/central/Indian Penal Code/sections");

        searchButton.setOnClickListener(v -> searchIPCSections(searchKeywords.getText().toString()));
    }

    private void searchIPCSections(String keyword) {
        if (keyword.isEmpty()) {
            return;
        }

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ipcSectionsList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    IPCSection ipcSection = snapshot.getValue(IPCSection.class);

                    if (ipcSection != null) {
                        String description = ipcSection.getDescription() != null ? ipcSection.getDescription().toLowerCase() : "";
                        String offense = ipcSection.getOffense() != null ? ipcSection.getOffense().toLowerCase() : "";
                        String punishment = ipcSection.getPunishment() != null ? ipcSection.getPunishment().toLowerCase() : "";

                        // Log the field values to debug the issue
                        Log.d("IPCSection", "Checking section: " + snapshot.getKey() +
                                ", Description: " + description +
                                ", Offense: " + offense +
                                ", Punishment: " + punishment);

                        if (description.contains(keyword.toLowerCase()) ||
                                offense.contains(keyword.toLowerCase()) ||
                                punishment.contains(keyword.toLowerCase())) {
                            ipcSectionsList.add(ipcSection);
                        }
                    } else {
                        Log.e("IPCSection", "IPCSection object is null for key: " + snapshot.getKey());
                    }
                }
                ipcAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("IPCSection", "Database error: " + databaseError.getMessage());
            }
        });
    }


}