package com.sarika.trainbooking.repository;

import com.sarika.trainbooking.model.BerthAvailability;
import com.sarika.trainbooking.model.Reservation;
import com.sarika.trainbooking.model.Train;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("reservationRepository")
public class ReservationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Reservation find(int pnr)
    {
        return entityManager.find(Reservation.class, pnr);
    }

}
