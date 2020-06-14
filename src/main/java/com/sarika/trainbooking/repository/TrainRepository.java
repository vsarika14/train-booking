package com.sarika.trainbooking.repository;

import com.sarika.trainbooking.enums.CoachType;
import com.sarika.trainbooking.model.BerthAvailability;
import com.sarika.trainbooking.model.Train;
import com.sarika.trainbooking.model.TrainCoach;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("trainRepository")
public class TrainRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Train find(int id)
    {
        return entityManager.find(Train.class, id);
    }

    public TrainCoach getCost(int trainId, String coachType)
    {
        return entityManager.createQuery("SELECT tc FROM TrainCoach tc WHERE tc.trainId = :trainId " +
                        "and tc.coachType=:coachType", TrainCoach.class)
                .setParameter("trainId", trainId)
                .setParameter("coachType", coachType)
                .getSingleResult();

    }
}
