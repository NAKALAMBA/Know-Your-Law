## Project Name : Know Your Law App

## Description

This Android application allows users to search through sections of the Indian Penal Code (IPC) by entering keywords. It retrieves data from a Firebase Realtime Database, filtering based on section descriptions, offenses, and punishments that match the keyword provided. Built with a simple, user-friendly UI, it displays results in a RecyclerView, making legal information easily accessible.

## Features

Keyword Search: Users can search IPC sections based on keywords related to descriptions, offenses, or punishments.

Firebase Integration: The app fetches IPC sections stored in Firebase Realtime Database.

Live Data Retrieval: Data is retrieved dynamically, and results are displayed in real-time.

Responsive UI: Utilizes RecyclerView for smooth scrolling and a clean display of results.

Logging: Debug logs for tracking data retrieval and filtering processes.

## *Installation*

Clone the repository
```bash
  git clone https://github.com/NAKALAMBA/mini-ipc-search.git
```
Open the project in Android Studio.

Add your Firebase project credentials to google-services.json in the app folder.

Sync the project to download dependencies.

## How to Use

Open the app.

Enter a keyword (e.g., "theft" or "punishment") in the search field.

Tap the search button to see a list of matching IPC sections.

Scroll through the results to view section descriptions, offenses, and punishment details.

## Project Structure

MainActivity: Handles UI interactions, searches for IPC sections, and manages the RecyclerView display.

IPCSection Class: Model class representing an IPC section with attributes for description, offense, and punishment.

IPCAdapter: Adapter for populating RecyclerView with IPC sections.

## Code Snippet

MainActivity.java

```Java
searchButton.setOnClickListener(v -> searchIPCSections(searchKeywords.getText().toString()));

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
                if (ipcSection != null && (ipcSection.getDescription().contains(keyword) ||
                        ipcSection.getOffense().contains(keyword) ||
                        ipcSection.getPunishment().contains(keyword))) {
                    ipcSectionsList.add(ipcSection);
                }
            }
            ipcAdapter.notifyDataSetChanged();
        }
    });
}

```

## Future Improvements

Enhanced Filtering: Add more complex search logic, including multi-keyword searches.

Offline Access: Enable offline caching for sections.

Search Optimization: Implement a more efficient search by indexing IPC data.

## Requirements

Android Studio

Firebase Realtime Database

Java 8 or later
