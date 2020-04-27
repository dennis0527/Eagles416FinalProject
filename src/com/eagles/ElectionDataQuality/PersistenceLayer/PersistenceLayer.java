package com.eagles.ElectionDataQuality.PersistenceLayer;

import com.eagles.ElectionDataQuality.Entity.State;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceLayer {

    public String getStateJsonString(){

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("devPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        State state = entityManager.find(State.class, "state_NY");
        entityManager.close();
        entityManagerFactory.close();

        return state.getGeojson();

    }
}
