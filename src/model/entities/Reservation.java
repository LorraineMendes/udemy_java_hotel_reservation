package model.entities;

import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
        if (!checkOut.after(checkOut)){
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public long duration() {
        long diff = checkOut.getTime() - checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void updateDate(Date checkIn, Date checkOut){
        Date now = new Date();
        if (checkIn.before(now)||checkOut.before(now)){
            throw new DomainException("Reservation dates for update must be future dates");
        }
        if (!checkOut.after(checkIn)){
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "roomNumber=" + roomNumber +
                ", checkIn: " + sdf.format(checkIn) +
                ", checkOut: " + sdf.format(checkOut) +
                " " + duration() +
                " nights }";
    }

    /* public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }As datas nao podem ser alteradas aleatoriamente, vamos implementar metodos para essa alteracao*/

    /*public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    } As datas nao podem ser alteradas aleatoriamente, vamos implementar metodos para essa alteracao*/

}
