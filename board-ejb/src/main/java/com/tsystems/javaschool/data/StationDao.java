package com.tsystems.javaschool.data;


import com.tsystems.javaschool.model.Station;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class StationDao extends AbstractDao<Station> {

    public StationDao() {
        super(Station.class);
    }

    public Station findByName(String name) {
        String queryString = "SELECT s FROM Station s WHERE UPPER(s.name) = :name";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", name.toLowerCase());
        List<Station> stations = query.getResultList();

        if (stations.isEmpty()) {
            return null;
        } else {
            return stations.get(0);
        }
    }
}
