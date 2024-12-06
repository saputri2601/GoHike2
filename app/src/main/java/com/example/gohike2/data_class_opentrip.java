package com.example.gohike2;

import java.util.List;

public class data_class_opentrip {


        // Model data OpenTrip
        public static class OpenTrip {
            private final String title;
            private final String duration;
            private final String status;
            private final List<data_class_gabungtim> teams;

            // Constructor
            public OpenTrip(String title, String duration, String status, List<data_class_gabungtim> teams) {
                this.title = title;
                this.duration = duration;
                this.status = status;
                this.teams = teams;
            }

            // Getter for title
            public String getTitle() {
                return title;
            }

            // Getter for duration
            public String getDuration() {
                return duration;
            }

            // Getter for status
            public String getStatus() {
                return status;
            }
            public List<data_class_gabungtim> getTeams() {
                return teams;
            }
        }
}

