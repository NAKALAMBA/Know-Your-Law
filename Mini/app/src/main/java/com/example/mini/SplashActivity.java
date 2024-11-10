package com.example.mini;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    private TextView animatedText;
    private Button getStartedButton;
    private Button bookAppointmentButton;  // New button for booking appointment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Initialize the TextView and Buttons
        animatedText = findViewById(R.id.welcome_text);
        getStartedButton = findViewById(R.id.get_started_button);
        bookAppointmentButton = findViewById(R.id.book_appointment_button);  // Initialize the new button

        // Initially hide the Get Started button and Book Appointment button
        getStartedButton.setVisibility(View.INVISIBLE);
        bookAppointmentButton.setVisibility(View.INVISIBLE);  // Set to invisible initially

        // Start the text animation
        animateText();

        // Set the OnClickListener for the Get Started button
        getStartedButton.setOnClickListener(v -> onGetStartedClicked());

        // Set the OnClickListener for the Book Appointment button
        bookAppointmentButton.setOnClickListener(v -> onBookAppointmentClicked());
    }

    // Method to animate the text "Know Your Law"
    private void animateText() {
        final String text = "Jolly LLB Welcomes You!!!";
        final int length = text.length();
        final Handler handler = new Handler();

        // Set the initial text to empty for typing effect
        animatedText.setText("");

        // Make the TextView visible
        animatedText.setVisibility(View.VISIBLE);

        // Create the typing animation with delay
        for (int i = 0; i < length; i++) {
            final int index = i;
            handler.postDelayed(() -> {
                // Append one character at a time to create the typing effect
                animatedText.append(String.valueOf(text.charAt(index)));

                // Show the "Get Started" button after the last character is typed
                if (index == length - 1) {
                    getStartedButton.setVisibility(View.VISIBLE);
                    bookAppointmentButton.setVisibility(View.VISIBLE);  // Show the Book Appointment button
                }
            }, 100 * (i + 1));  // Adjust the delay to control typing speed
        }
    }

    // Method to handle the "Get Started" button click
    public void onGetStartedClicked() {
        // Check if the user is logged in via Firebase Authentication
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            // If logged in, go to MainActivity
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        } else {
            // If not logged in, go to LoginActivity
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }

        // Close SplashActivity so the user can't go back to it
        finish();
    }

    // Method to handle the "Book Appointment" button click
    public void onBookAppointmentClicked() {
        // Navigate to the BookAppointmentActivity
        startActivity(new Intent(SplashActivity.this, BookAppointment.class));

        // Close SplashActivity so the user can't go back to it
        finish();
    }
}
