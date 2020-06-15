package com.sarika.trainbooking.repository;

import com.sarika.trainbooking.model.Train;
import com.sarika.trainbooking.model.Coach;
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

    public Coach getCostByTrainAndCoachType(int trainId, String coachType)
    {
        return entityManager.createQuery("SELECT c FROM Coach c WHERE c.trainId = :trainId " +
                        "and c.coachType=:coachType", Coach.class)
                .setParameter("trainId", trainId)
                .setParameter("coachType", coachType)
                .getSingleResult();

    }
}
