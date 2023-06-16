# LeetCode Contest Rating App

## Description

The LeetCode Contest Rating App is an Android application that provides information about contests on LeetCode and displays the ratings of the contestants. It allows users to search for specific usernames to view their ratings and track their progress over time.

The app fetches data from the LeetCode API to retrieve the list of contests and contestant ratings. It utilizes Retrofit for making API requests and Gson for JSON parsing. The data is presented in a user-friendly manner using RecyclerView for displaying the contest list and ProgressBar for indicating the loading progress.

## Features

- View a list of contests on LeetCode with details such as contest ID, duration, and start time.
- Display the ratings of the contestants for each contest.
- Search for a specific username to view their rating history.
- Real-time updates for new contests and updated ratings.

## Usage

- Launch the LeetCode Contest Rating App on your Android device.
- The app will display a list of recent contests on LeetCode, including details such as contest ID, duration, and start time.
- Scroll through the list to browse the contests and their respective ratings.
- To search for a specific username, enter the username in the search bar and press the "Search" button or hit Enter.
- The app will fetch and display the rating history of the user, showing their ratings for each contest over time.
- You can go back to the list of contests by pressing the Back button on your device.

## Dependencies
The LeetCode Contest Rating App relies on the following dependencies:
- Retrofit: A type-safe HTTP client for making API requests.
- Gson: A Java library for converting Java objects to JSON and vice versa.
- RecyclerView: A flexible view for providing a limited window into a large dataset.
- ProgressBar: An indicator of loading progress.
