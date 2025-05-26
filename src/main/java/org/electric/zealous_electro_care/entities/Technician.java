package org.electric.zealous_electro_care.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Technician {
    @Id
    private String username;
    private Double latitude; // Geographical coordinate
    private Double longitude; // Geographical coordinate
    private Double rating=0.0;
    private int ratingCount=0;
    private boolean available=true;
    private Double avgRating=0.0;
    public Double getAvgRating() {
        return rating/ratingCount;
    }
    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }
    @Override
    public String toString() {
        return "Technician [username=" + username + ", latitude=" + latitude + ", longitude=" + longitude + ", rating="
                + rating/ratingCount + ", ratingCount=" + ratingCount + ", available=" + available + "]";
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
    public int getRatingCount() {
        return ratingCount;
    }
    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
