package com.jakuboc.TennisCourtSystem.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="reservations")
public class Reservation {
    @Id
//    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="reservation_id_seq")
    Long id;

    // PERSIST -> created only if does not exist
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "phone_number")
    User user;

    @ManyToOne
    @JoinColumn(name = "court_id", referencedColumnName = "id")
    Court court;

    @Column(name = "is_single")
    boolean isSingle;

    @Column(name = "start_time")
    LocalDateTime startTime;

    @Column(name = "end_time")
    LocalDateTime endTime;

    @Column(name = "created_time")
    LocalDateTime createdTime;

    public Double getPrice() {
        long minutes = Duration.between(startTime, endTime).toMinutes();
        double paymentPerMinute = court.getSurfaceType().paymentPerMinute;
        double multiplier = isSingle? 1 : 1.5;
        return minutes * paymentPerMinute * multiplier;
    }

}
