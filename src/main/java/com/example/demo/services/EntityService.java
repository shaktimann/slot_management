package com.example.demo.services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import org.apache.lucene.util.fst.PairOutputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.example.demo.model.Entity;
import com.example.demo.model.EntityType;
import com.example.demo.model.User;
import com.example.demo.repos.EntityRepository;

@Service
public class EntityService {

    @Autowired
    private EntityRepository entityRepository;

    public Entity save(Entity entity) {
        return entityRepository.save(entity);
    }

    public List<Entity> getAll() {
        Iterable<Entity> iterable = entityRepository.findAll();
        List<Entity> entities = new ArrayList<>();
        iterable.forEach(new Consumer<Entity>() {
            @Override
            public void accept(Entity entity) {
                entities.add(entity);
            }

        });
        return entities;
    }

    public Optional<Entity> findEntityById(String id) {
        return entityRepository.findById(id);
    }

    public List<Entity> getEntitiesByDistancefromAUser(User user, int distance) {
        Iterable<Entity> iterable = entityRepository.findAll();
        List<Entity> entities = new ArrayList<>();
        iterable.forEach(new Consumer<Entity>() {
            @Override
            public void accept(Entity entity) {
                double distanceBetweenUserAndEntity = getDistanceinKm(user.getGeoLat(), user.getGeoLong(),
                        entity.getGeoLat(), entity.getGeoLong());
                if (distanceBetweenUserAndEntity <= (double) distance && entity.getType() == EntityType.PUBLIC) {
                    entities.add(entity);
                }
            }
        });
        //entities.addAll(user.getPrivateEntityList());
        return entities;

    }

//    public Map<Timestamp, Timestamp> getAllSlotsInADay(Entity entity) {
//
//        // assuming here that the break slots are valid and occupy the entire slots
//        Map<Timestamp, Timestamp> allSlotsAfterBreaks = new HashMap<>();
//        Map<Timestamp, Timestamp> breaks = entity.getBreakDurations();
//        boolean ifSkipSlot = false;
//
//        Timestamp openingTime = entity.getOpeningTime();
//        Timestamp closingTime = entity.getClosingTime();
//        Duration slotDuration = entity.getSlotDuration();
//
//        Timestamp currentOpeningTime = openingTime;
//        Timestamp currentClosingTime = new Timestamp(currentOpeningTime.getTime());
//
//        while (currentOpeningTime.before(closingTime)) {
//            currentClosingTime.setTime(currentOpeningTime.getTime() + slotDuration.getMilliseconds());
//
//            ifSkipSlot = true;
//
//            for (Map.Entry<Timestamp, Timestamp> entry : breaks.entrySet()) {
//                if ((currentOpeningTime.after(entry.getKey())
//                        || currentOpeningTime.getTime() == entry.getKey().getTime())
//                        && (currentClosingTime.before(entry.getValue())
//                                || currentClosingTime.getTime() == entry.getValue().getTime())) {
//                    currentOpeningTime = currentClosingTime;
//                    ifSkipSlot = true;
//                    break;
//                }
//            }
//            if (ifSkipSlot == true)
//                continue;
//            allSlotsAfterBreaks.put(currentOpeningTime,
//                    currentClosingTime.before(closingTime) ? currentClosingTime : closingTime);
//            currentOpeningTime = currentClosingTime;
//        }
//        return allSlotsAfterBreaks;
//
//    }
    
    public Map<Long, Long> getAllSlotsInADay(Entity entity) {

        // assuming here that the break slots are valid and occupy the entire slots
        Map<Long, Long> allSlotsAfterBreaks = new HashMap<>();
        Map<Long, Long> breaks = entity.getBreakDurations();
        boolean ifSkipSlot = false;

        long openingTime = entity.getOpeningTime();
        long closingTime = entity.getClosingTime();
        long slotDuration = entity.getSlotDuration();
        

        long currentOpeningTime = openingTime;
        long currentClosingTime = openingTime;

        while (currentOpeningTime < closingTime) {
            currentClosingTime = currentOpeningTime + slotDuration;

            ifSkipSlot = false;

            for (Map.Entry<Long, Long> entry : breaks.entrySet()) {
                if (currentOpeningTime >= entry.getKey() && currentClosingTime <= entry.getValue()) {
                    currentOpeningTime = currentClosingTime;
                    ifSkipSlot = true;
                    break;
                }
            }
            if (ifSkipSlot == true)
                continue;
            allSlotsAfterBreaks.put(currentOpeningTime,
                    currentClosingTime <= closingTime ? currentClosingTime : closingTime);
            currentOpeningTime = currentClosingTime;
        }
        return allSlotsAfterBreaks;

    }

    public Entity update(String id, Entity newEntity) {
        return entityRepository.findById(id).map(entity -> {
            entity.setAdmins(newEntity.getAdmins());
            entity.setOpeningTime(newEntity.getOpeningTime());
            entity.setClosingTime(newEntity.getClosingTime());
            entity.setSlotDuration(newEntity.getSlotDuration());
            entity.setCapacityPerSlot(newEntity.getCapacityPerSlot());
            entity.setBreakDurations(newEntity.getBreakDurations());
            return entityRepository.save(entity);
        }).orElseGet(() -> {
            newEntity.setId(id);
            return entityRepository.save(newEntity);
        });
    }

    public int gettotalCapacityInADay(Entity entity) {
        return getAllSlotsInADay(entity).size() * entity.getCapacityPerSlot();
    }

    public double getDistanceinKm(double geolat, double geolong, double geolat2, double geolong2) {
        double theta = geolong - geolong2;
        double dist = Math.sin(deg2rad(geolat)) * Math.sin(deg2rad(geolat2))
                + Math.cos(deg2rad(geolat)) * Math.cos(deg2rad(geolat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);
    }

    public double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    public double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public Pair<Long, Long> getCurrentSlot(Entity entity) {
        Map<Long, Long> allSlots = getAllSlotsInADay(entity);
        long currentTimeinSeconds =  LocalTime.now().toSecondOfDay();
        for (Map.Entry<Long, Long> entry : allSlots.entrySet()) {
            if(entry.getKey() <= currentTimeinSeconds && entry.getValue() > currentTimeinSeconds) {
                return Pair.of(entry.getKey(), entry.getValue());
            }
        }
        return null;
    }

}
