package com.booking.booking.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_id")
    private UUID bookingId;
    @Column(name = "passenger_name")
    private String passengerName;
    @Column(name = "passenger_contact_number")
    private String passengerContactNumber;
    @Column(name = "pickup_time")
    private OffsetDateTime pickupTime;
    @Column(name = "asap")
    private Boolean asap = true;
    @Column(name = "waiting_time")
    private Integer waitingTime;
    @Column(name = "no_of_passengers")
    private Integer noOfPassengers;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "created_on")
    private Instant createdOn;
    @Column(name = "last_modified_on")
    private Instant lastModifiedOn;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "trip_way_point", joinColumns = @JoinColumn(name = "booking_id"), inverseJoinColumns = @JoinColumn(name = "trip_way_point_id"))
    private List<TripWaypoint> tripWayPoints;

    public Booking(String passengerName, String passengerContactNumber, OffsetDateTime pickupTime, Boolean asap, Integer waitingTime, Integer noOfPassengers, BigDecimal price, Integer rating, Instant createdOn, Instant lastModifiedOn, List<TripWaypoint> tripWayPoints) {
        this.passengerName = passengerName;
        this.passengerContactNumber = passengerContactNumber;
        this.pickupTime = pickupTime;
        this.asap = asap;
        this.waitingTime = waitingTime;
        this.noOfPassengers = noOfPassengers;
        this.price = price;
        this.rating = rating;
        this.createdOn = createdOn;
        this.lastModifiedOn = lastModifiedOn;
        this.tripWayPoints = tripWayPoints;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerContactNumber() {
        return passengerContactNumber;
    }

    public void setPassengerContactNumber(String passengerContactNumber) {
        this.passengerContactNumber = passengerContactNumber;
    }

    public OffsetDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(OffsetDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public Boolean getAsap() {
        return asap;
    }

    public void setAsap(Boolean asap) {
        this.asap = asap;
    }

    public Integer getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(Integer waitingTime) {
        this.waitingTime = waitingTime;
    }

    public Integer getNoOfPassengers() {
        return noOfPassengers;
    }

    public void setNoOfPassengers(Integer noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Instant lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public List<TripWaypoint> getTripWayPoints() {
        return tripWayPoints;
    }

    public void setTripWayPoints(List<TripWaypoint> tripWayPoints) {
        this.tripWayPoints = tripWayPoints;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", passengerName='" + passengerName + '\'' +
                ", passengerContactNumber='" + passengerContactNumber + '\'' +
                ", pickupTime=" + pickupTime +
                ", asap=" + asap +
                ", waitingTime=" + waitingTime +
                ", noOfPassengers=" + noOfPassengers +
                ", price=" + price +
                ", rating=" + rating +
                ", createdOn=" + createdOn +
                ", lastModifiedOn=" + lastModifiedOn +
                ", tripWayPoints=" + tripWayPoints +
                '}';
    }
}
