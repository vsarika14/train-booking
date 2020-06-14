package com.sarika.trainbooking.repository;

import com.sarika.trainbooking.model.Train;
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
}
