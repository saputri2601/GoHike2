package com.example.gohike2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    private EditText editName, editEmail, editUsername, editPassword;
    private Button saveButton;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private TextView backButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit);

        // Initialize Firebase Authentication and Database Reference
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        // Initialize UI components
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        saveButton = findViewById(R.id.saveButton);

        // Receive data passed from ProfileActivity
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        // Set initial values in the EditText fields
        editName.setText(name);
        editEmail.setText(email);
        editUsername.setText(username);
        editPassword.setText(password);

        // Handle save button click
        saveButton.setOnClickListener(view -> {
            String updatedName = editName.getText().toString();
            String updatedEmail = editEmail.getText().toString();
            String updatedUsername = editUsername.getText().toString();
            String updatedPassword = editPassword.getText().toString();

            if (updatedName.isEmpty() || updatedEmail.isEmpty() || updatedUsername.isEmpty() || updatedPassword.isEmpty()) {
                Toast.makeText(EditProfileActivity.this, "All fields must be filled.", Toast.LENGTH_SHORT).show();
            } else {
                // Save updated profile data to Firebase
                saveUpdatedProfile(updatedName, updatedEmail, updatedUsername, updatedPassword);

                // Return updated data back to ProfileActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", updatedName);
                resultIntent.putExtra("email", updatedEmail);
                resultIntent.putExtra("username", updatedUsername);
                resultIntent.putExtra("password", updatedPassword);
                setResult(RESULT_OK, resultIntent);
                finish();  // Close EditProfileActivity
            }
        });

    }

    // Save updated profile data to Firebase Realtime Database
    private void saveUpdatedProfile(String name, String email, String username, String password) {
        String userId = mAuth.getCurrentUser().getUid();  // Get current user ID

        DatabaseReference userRef = mDatabase.child(userId);
        userRef.child("name").setValue(name);
        userRef.child("email").setValue(email);
        userRef.child("username").setValue(username);
        userRef.child("password").setValue(password);

        Toast.makeText(EditProfileActivity.this, "Profile updated successfully.", Toast.LENGTH_SHORT).show();
    }
}
