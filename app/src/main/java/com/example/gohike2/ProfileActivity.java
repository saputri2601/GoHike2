package com.example.gohike2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;

public class ProfileActivity extends AppCompatActivity {

    private TextView profileName, profileEmail, profileUsername, profilePassword;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        // Initialize Firebase Authentication and Database Reference
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        // Initialize UI components
        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profileUsername = findViewById(R.id.profileUsername);
        profilePassword = findViewById(R.id.profilePassword);
        Button editButton = findViewById(R.id.editButton);
        ImageView backButton = findViewById(R.id.back);

        // Show user profile data
        showUserProfile();

        // Listener for edit profile button
        editButton.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
            intent.putExtra("name", profileName.getText().toString());
            intent.putExtra("email", profileEmail.getText().toString());
            intent.putExtra("username", profileUsername.getText().toString());
            intent.putExtra("password", profilePassword.getText().toString());
            startActivityForResult(intent, 1); // Expecting result from EditProfileActivity
        });

        // Listener for back button
        backButton.setOnClickListener(view -> {
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            finish();
        });
    }

    // Show user profile data from Firebase
    private void showUserProfile() {
        String userId = mAuth.getCurrentUser().getUid();  // Get current user ID

        Query userQuery = mDatabase.child(userId);

        userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String nameFromDB = snapshot.child("name").getValue(String.class);
                    String emailFromDB = snapshot.child("email").getValue(String.class);
                    String usernameFromDB = snapshot.child("username").getValue(String.class);
                    String passwordFromDB = snapshot.child("password").getValue(String.class);

                    profileName.setText(nameFromDB);
                    profileEmail.setText(emailFromDB);
                    profileUsername.setText(usernameFromDB);
                    profilePassword.setText(passwordFromDB);
                } else {
                    Toast.makeText(ProfileActivity.this, "User data not found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Failed to load data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            String name = data.getStringExtra("name");
            String email = data.getStringExtra("email");
            String username = data.getStringExtra("username");
            String password = data.getStringExtra("password");

            // Update profile UI with new data
            profileName.setText(name);
            profileEmail.setText(email);
            profileUsername.setText(username);
            profilePassword.setText(password);

            // Save updated profile to Firebase
            saveUpdatedProfile(name, email, username, password);
        }
    }

    // Save updated profile data to Firebase
    private void saveUpdatedProfile(String name, String email, String username, String password) {
        String userId = mAuth.getCurrentUser().getUid();  // Get current user ID

        DatabaseReference userRef = mDatabase.child(userId);
        userRef.child("name").setValue(name);
        userRef.child("email").setValue(email);
        userRef.child("username").setValue(username);
        userRef.child("password").setValue(password);

        Toast.makeText(ProfileActivity.this, "Profile updated successfully.", Toast.LENGTH_SHORT).show();
    }
}
