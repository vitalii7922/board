package com.tsystems.javaschool.data;

import com.tsystems.javaschool.model.Train;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class TrainDao extends AbstractDao<Train> {
    String queryString;

    public TrainDao() {
        super(Train.class);
    }

    public Train findByStationDepartureId(int trainNumber, long id) {
        queryString = "SELECT t FROM Train t WHERE (t.originStation.id) = :id and t.number = :trainNumber";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("id", id);
        query.setParameter("trainNumber", trainNumber);
        List trains = query.getResultList();
        return (Train) trains.get(0);
    }

    public Train findByStationArrivalId(int trainNumber, long id) {
        queryString = "SELECT t FROM Train t WHERE (t.destinationStation.id) = :id and t.number = :trainNumber";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("id", id);
        query.setParameter("trainNumber", trainNumber);
        List trains = query.getResultList();
        return (Train) trains.get(0);
    }

    public List findTrainsByNumber(int number) {
        queryString = "SELECT t FROM Train t WHERE (t.number) = :number";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("number", number);
        return query.getResultList();
        }

    public Train findByNumber(int number) {
        queryString = "select t from Train t inner join Station s on  s.id=t.originStation.id where (t.number) = :number";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("number", number);
        List<Train> trains = query.getResultList();
        if (trains.isEmpty()) {
            return null;
        } else {
            return trains.get(trains.size() - 1);
        }
    }

    public List findByStations(long fromId, long toId, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        String queryString1 = "select t from Train t inner join Schedule s on t.id = s.train.id" +
                " where t.originStation.id = :fromId and s.departureTime >= :departureTime";
        String queryString2 = "select t from Train t inner join Schedule s on t.id = s.train.id" +
                " where t.destinationStation.id = :toId and s.arrivalTime <= :arrivalTime";
        Query query1 = entityManager.createQuery(queryString1);
        Query query2 = entityManager.createQuery(queryString2);
        query1.setParameter("fromId", fromId);
        query2.setParameter("toId", toId);
        query1.setParameter("departureTime", departureTime);
        query2.setParameter("arrivalTime", arrivalTime);
        List trains = query1.getResultList();
        List trains2 = query2.getResultList();
        trains.addAll(trains2);
        return trains;
    }

    public List findAllTrainsBetweenTwoStations(long trainDepartureId, long trainArrivalId) {
        String queryString1 = "select t from Train t where t.id >= :trainDepartureId and t.id <= :trainArrivalId";
        Query query = entityManager.createQuery(queryString1);
        query.setParameter("trainDepartureId", trainDepartureId);
        query.setParameter("trainArrivalId", trainArrivalId);
        return query.getResultList();
    }

    public List findAllByNumber(int number) {
        String queryString = "select t from Train t where t.number = :number";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("number", number);
        return query.getResultList();
    }
}
