package com.bb.nicerhotel.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bb.nicerhotel.R;
import com.bb.nicerhotel.model.Guest;

public class DisplayGuestInfo extends AppCompatActivity {

    private TextView displayGuestName;
    private TextView displayGuestPhone;
    private TextView displayGuestEmail;
    private TextView displayGuestRoom;
    private TextView displayGuestCheckIn;

    public static final String Guest_Key = "get.guest.info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_guest_info);

        Guest displayGuest = ((Guest) getIntent().getSerializableExtra(Guest_Key));
        //System.out.println(displayGuest);
        displayGuestName = findViewById(R.id.display_guest_name);
        displayGuestName.setText(displayGuest.getName());

        displayGuestPhone = findViewById(R.id.display_guest_phone);
        displayGuestPhone.setText(displayGuest.getPhoneNumber());

        displayGuestEmail = findViewById(R.id.display_guest_email);
        displayGuestEmail.setText(displayGuest.getEmail());

        displayGuestRoom = findViewById(R.id.display_guest_room);
        displayGuestRoom.setText(displayGuest.getRoomNumber());

        displayGuestCheckIn = findViewById(R.id.display_guest_checkin);
        displayGuestCheckIn.setText(displayGuest.getCheckIn());

    }

    public void returnMain(View view){
        Intent returnIntent = new Intent();
        finish();
    }
}
