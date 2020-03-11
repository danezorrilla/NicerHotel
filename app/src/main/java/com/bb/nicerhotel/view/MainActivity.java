package com.bb.nicerhotel.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.bb.nicerhotel.R;
import com.bb.nicerhotel.adapter.GuestAdapter;
import com.bb.nicerhotel.model.Guest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements GuestAdapter.GuestClickListener {

    private EditText mainEditText;
    private RecyclerView mainRecyclerView;

    private List<Guest> guestList = new ArrayList<Guest>();

    private SharedPreferences sharedPreferences;
    private String guestNameKeyPrefix = "GUEST_NAME_";
    private String guestPhoneKeyPrefix = "GUEST_PHONE_";
    private String guestEmailKeyPrefix = "GUEST_EMAIL_";
    private String guestRoomKeyPrefix = "GUEST_ROOM_";
    private String guestCheckInKeyPrefix = "GUEST_CHECKIN_";
    private final String GUEST_COUNT_KEY = "guest_count_key";
    private int guestCount = 0;

    public static final int REQUEST_CODE = 707;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mainEditText = findViewById(R.id.new_guest);
        mainRecyclerView = findViewById(R.id.guest_recycler_view);

        sharedPreferences = getSharedPreferences("com.bb.nicerhotel", Context.MODE_PRIVATE);
        guestCount = sharedPreferences.getInt(GUEST_COUNT_KEY, 0);
        readGuestList();


    }

    public void addNewGuest(View view){
        //System.out.println(mainEditText.getText().toString());
        //String guestName = mainEditText.getText().toString();
        //guestList.add(new Guest(guestName));
        //guestList.add(mainEditText.getText().toString());
        //System.out.println(guestList);
        //mainEditText.setText("");
        //updateGuestList();
        Intent intent = new Intent(this, AddNewGuest.class);
        startActivityForResult(intent, REQUEST_CODE);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(data != null){
                Guest returnGuest = (Guest) data.getSerializableExtra(AddNewGuest.EXTRA_MESSAGE);
                //System.out.println(returnGuest.getName());
                //guestList.add(returnGuest);
                //System.out.println((guestList.get(0)).getName());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                guestCount++;
                editor.putString(guestNameKeyPrefix+guestCount, returnGuest.getName());
                editor.putString(guestPhoneKeyPrefix+guestCount, returnGuest.getPhoneNumber());
                editor.putString(guestEmailKeyPrefix+guestCount, returnGuest.getEmail());
                editor.putString(guestRoomKeyPrefix+guestCount, returnGuest.getRoomNumber());
                editor.putString(guestCheckInKeyPrefix+guestCount, returnGuest.getCheckIn());
                editor.putInt(GUEST_COUNT_KEY, guestCount);
                //editor.clear();
                editor.apply();

                readGuestList();
                //updateGuestList();
            }
        }
    }

    private void readGuestList() {
        guestCount = sharedPreferences.getInt(GUEST_COUNT_KEY, 0);
        guestList.clear();

        Log.d("TAG_X", "Guest key : " + guestCount );

        for (int index = 0; index < guestCount; index++) {
        String name = sharedPreferences.getString(guestNameKeyPrefix + (index+1), null);
        String phone = sharedPreferences.getString(guestPhoneKeyPrefix + (index+1),null);
        String email = sharedPreferences.getString(guestEmailKeyPrefix + (index+1), null);
        String room = sharedPreferences.getString(guestRoomKeyPrefix + (index+1), null);
        String checkin = sharedPreferences.getString(guestCheckInKeyPrefix + (index+1), null);
        Log.d("TAG_X", "Name : " + name);
        System.out.println(name);
        Guest guest = new Guest(name, phone, email, room, checkin);
        guestList.add(guest);
        }

        updateGuestList();
    }

    private void updateGuestList(){
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        Log.d("TAG_X", "Guestlist Size : " + guestList.size());
        mainRecyclerView.setAdapter(new GuestAdapter(guestList,this));
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.addItemDecoration(itemDecoration);
    }

    public void displayGuest(Guest guest){
        String date = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.US).format(new Date());

        Log.d("TAG_X", "guest click item recieved " + guest.getName() + " ON DATE :" + date);

        Intent displayIntent = new Intent(this, DisplayGuestInfo.class);
        //String message = mainEditText.getText().toString();
        displayIntent.putExtra(DisplayGuestInfo.Guest_Key, guest);
        startActivity(displayIntent);
    }


}
