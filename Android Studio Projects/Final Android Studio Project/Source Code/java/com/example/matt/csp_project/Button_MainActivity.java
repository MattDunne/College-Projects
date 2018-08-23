package com.example.matt.csp_project;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;

public class Button_MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttons);


        ImageButton programsButton = (ImageButton) findViewById(R.id.programsBtn);
        ImageButton contactsButton = (ImageButton) findViewById(R.id.contactsBtn);
        ImageButton calendarButton = (ImageButton) findViewById(R.id.calendarBtn);
        ImageButton newsButton = (ImageButton) findViewById(R.id.newsBtn);
        ImageButton metrobusButton = (ImageButton) findViewById(R.id.metrobusBtn);
        ImageButton infoButton = (ImageButton) findViewById(R.id.infoBtn);
        ImageButton scheduleButton = (ImageButton) findViewById(R.id.scheduleBtn);

        programsButton.setOnClickListener(new View.OnClickListener()
        {
            //@Override
            public void onClick(View view)
            {
                Intent programsScreen = new Intent(getApplicationContext(), ProgramList_MainActivity.class);
                startActivity(programsScreen);
            }
        });

        contactsButton.setOnClickListener(new View.OnClickListener()
        {
            //@Override
            public void onClick(View view)
            {
                Intent contactsScreen = new Intent(getApplicationContext(), Contacts_MainActivity.class);
                startActivity(contactsScreen);
            }
        });

        calendarButton.setOnClickListener(new View.OnClickListener()
        {
            //@Override
            public void onClick(View view)
            {
                Intent calendarScreen = new Intent(getApplicationContext(), Calendar_MainActivity.class);
                startActivity(calendarScreen);
            }
        });

        newsButton.setOnClickListener(new View.OnClickListener()
        {
            //@Override
            public void onClick(View view)
            {
                Intent newsScreen = new Intent(getApplicationContext(), News_MainActivity.class);
                startActivity(newsScreen);
            }
        });

        metrobusButton.setOnClickListener(new View.OnClickListener()
        {
            //@Override
            public void onClick(View view)
            {
                Intent busScreen = new Intent(getApplicationContext(), bus_MainActivity.class);
                startActivity(busScreen);
            }
        });


       scheduleButton.setOnClickListener(new View.OnClickListener()
        {
            //@Override
            public void onClick(View view)
            {
                Intent scheduleScreen = new Intent(getApplicationContext(), Schedule_MainActivity.class);
                startActivity(scheduleScreen);
            }
        });

        infoButton.setOnClickListener(new View.OnClickListener()
        {
            //@Override
            public void onClick(View view)
            {
                Intent infoScreen = new Intent(getApplicationContext(), Info_MainActivity.class);
                startActivity(infoScreen);
            }
        });

    }
}
