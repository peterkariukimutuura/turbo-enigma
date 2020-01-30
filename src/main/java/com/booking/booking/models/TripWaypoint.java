package com.booking.booking.models;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;
import com.booking.booking.models.Booking;
@Entity
@Table(name = "trip_way_point")
public class TripWaypoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trip_way_point_id")
    private UUID tripWayPointId;
//    @Column(name = "booking")
//    private Booking booking;
    @Column(name = "last_stop")
    private Boolean lastStop;
    @Column(name = "locality")
    private String locality;
    @Column(name = "lat")
    private Double lat;
    @Column(name = "lng")
    private Double lng;
    @Column(name = "trip_way_point_timestamp")
    private Instant tripWayPointTimestamp;

    public TripWaypoint(Boolean lastStop, String locality, Double lat, Double lng, Instant tripWayPointTimestamp) {
//        this.booking = booking;
        this.lastStop = lastStop;
        this.locality = locality;
        this.lat = lat;
        this.lng = lng;
        this.tripWayPointTimestamp = tripWayPointTimestamp;
    }

    public UUID getTripWayPointId() {
        return tripWayPointId;
    }

    public void setTripWayPointId(UUID tripWayPointId) {
        this.tripWayPointId = tripWayPointId;
    }

//    public Booking getBooking() {
//        return booking;
//    }
//
//    public void setBooking(Booking booking) {
//        this.booking = booking;
//    }

    public Boolean getLastStop() {
        return lastStop;
    }

    public void setLastStop(Boolean lastStop) {
        this.lastStop = lastStop;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Instant getTripWayPointTimestamp() {
        return tripWayPointTimestamp;
    }

    public void setTripWayPointTimestamp(Instant tripWayPointTimestamp) {
        this.tripWayPointTimestamp = tripWayPointTimestamp;
    }

    @Override
    public String toString() {
        return "TripWaypoint{" +
                "tripWayPointId=" + tripWayPointId +
//                ", booking=" + booking +
                ", lastStop=" + lastStop +
                ", locality='" + locality + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", tripWayPointTimestamp=" + tripWayPointTimestamp +
                '}';
    }
}
