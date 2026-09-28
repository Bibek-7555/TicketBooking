package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity 
public class Show {
    public Show() {
    }

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime showTime;
    private double price;

    @ManyToOne 
    @JoinColumn (name = "movie_id")
    private Movie movie;

    @ManyToOne 
    @JoinColumn (name = "screen_id")
    private Screen screen;

    public Long getId() {
        return id;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

}
