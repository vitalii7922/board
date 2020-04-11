package com.tsystems.javaschool.data;

import com.tsystems.javaschool.model.Schedule;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class ScheduleDao extends AbstractDao<Schedule> {

    public ScheduleDao() {
        super(Schedule.class);
    }

    public Schedule findByTrainId(long id) {
        String queryString = "SELECT s FROM Schedule s WHERE (s.train.id) = :id";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("id", id);
        List<Schedule> schedules = query.getResultList();
        if (schedules.isEmpty()) {
            return null;
        } else {
            return schedules.get(1);
        }
    }


    public List findByStationId(long id) {
        String queryString = "SELECT s FROM Schedule s WHERE (s.station.id) = :id";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
