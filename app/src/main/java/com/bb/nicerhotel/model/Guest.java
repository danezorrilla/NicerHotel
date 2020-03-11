package com.bb.nicerhotel.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Guest implements Serializable{

    private String Name;
    private String PhoneNumber;
    private String Email;
    private String RoomNumber;
    private String CheckIn;

    public Guest(String Name, String PhoneNumber, String Email, String RoomNumber, String CheckIn) {
        this.Name = Name;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.RoomNumber = RoomNumber;
        this.CheckIn = CheckIn;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPhoneNumber(){return PhoneNumber;}

    public void setPhoneNumber(String PhoneNumber){this.PhoneNumber = PhoneNumber;}

    public String getEmail(){return Email;}

    public void setEmail(String Email){this.Email = Email;}

    public String getRoomNumber(){return RoomNumber;}

    public void setRoomNumber(String RoomNumber){this.RoomNumber = RoomNumber;}

    public String getCheckIn(){return CheckIn;}

    public void setCheckIn(String CheckIn){this.CheckIn = CheckIn;}


}
