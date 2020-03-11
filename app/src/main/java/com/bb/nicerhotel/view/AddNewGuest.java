package com.bb.nicerhotel.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bb.nicerhotel.R;
import com.bb.nicerhotel.model.Guest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddNewGuest extends AppCompatActivity {

    private EditText guestNameText;
    private EditText guestPhoneNumberText;
    private EditText guestEmailText;
    private EditText guestRoomNumberText;
    private String guestCheckIn = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(new Date());

    //private Guest guest;

    public static final String EXTRA_MESSAGE = "com.bb.nicerhotel.extra.message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_guest);

        guestNameText = findViewById(R.id.add_guest_name);
        guestPhoneNumberText = findViewById(R.id.add_guest_phone);
        guestEmailText = findViewById(R.id.add_guest_email);
        guestRoomNumberText = findViewById(R.id.add_guest_room);
    }

    public void registerNewGuest(View view){
        String guestName = guestNameText.getText().toString();
        String guestPhoneNumber = guestPhoneNumberText.getText().toString();
        String guestEmail = guestEmailText.getText().toString();
        String guestRoomNumber = guestRoomNumberText.getText().toString();

        //System.out.println(guestName);

        //guest.setName(guestName);
        Guest guest = new Guest(guestName, guestPhoneNumber, guestEmail, guestRoomNumber, guestCheckIn);

        //System.out.println(guest.getName());
        //System.out.println(guest);

        Intent registerGuestIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_MESSAGE, guest);
        registerGuestIntent.putExtras(bundle);
        setResult(MainActivity.REQUEST_CODE, registerGuestIntent);
        finish();
    }
}
