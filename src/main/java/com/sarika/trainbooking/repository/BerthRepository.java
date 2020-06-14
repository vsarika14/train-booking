package com.sarika.trainbooking.repository;

import com.sarika.trainbooking.model.BerthAvailability;
import com.sarika.trainbooking.model.Train;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;


@Repository("berthRepository")
public class BerthRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<BerthAvailability> getAvailability(int trainId) //, Date travelDate)
    {
        return entityManager.createQuery("SELECT bi FROM BerthAvailability bi WHERE bi.trainId = :trainId"
                ,BerthAvailability.class)
                .setParameter("trainId", trainId)
                //.setParameter("travelDate", travelDate)
                .getResultList();

    }
}