package com.example.prac5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.prac5.fragments.SelectBreadDialogFragment;
import com.example.prac5.fragments.SelectSauceDialogFragment;
import com.example.prac5.fragments.SelectToppingsDialogFragment;

public class MainActivity extends AppCompatActivity implements SelectBreadDialogFragment.ItemSelectedListener,
        SelectToppingsDialogFragment.ItemsSelectedListener,
        SelectSauceDialogFragment.ItemSelectedListener {

    Button buttonSelectBread;
    Button buttonSelectToppings;
    Button buttonSelectSauce;

    Button buttonStartPreparing;

    int selectedBread;
    boolean[] selectedToppings;
    int selectedSauce;

    public static final String NOTIFICATION_CHANNEL_ID = "MyChannelId";
    public static final int NOTIFICATION_ID = 101;
    //User visible Channel Name
    public static final String CHANNEL_NAME = "Notification Channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Retrieve the topping_choice as a string[] array from
        // the res/values/strings.xml file
        //
        String[] toppingArray =
                getResources().getStringArray(R.array.topping_choice);

        // Initialize our selectedToppings boolean[] array with the
        // same number of items as our topping_choice array from the strings.xml file.
        //
        selectedToppings = new boolean[toppingArray.length];

        buttonSelectBread = findViewById(R.id.buttonSelectBread);
        buttonSelectBread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Opens our SelectBreadDialogFragment
                //
                SelectBreadDialogFragment dialog = new SelectBreadDialogFragment();
                dialog.show(getSupportFragmentManager(), "SelectBreadDialogFragment");
            }
        });

        buttonSelectToppings = findViewById(R.id.buttonSelectToppings);
        buttonSelectToppings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remember that in our onCreateDialog in the SelectToppingsDialogFragment
                // we wrote some code to retrieve the "checkedItems" parameter from the Bundle?
                // This code is how we will pass the information of the previously selected
                // toppings to that SelectToppingsDialogFragment.
                //
                Bundle params = new Bundle();
                params.putBooleanArray("checkedItems", selectedToppings);

                SelectToppingsDialogFragment dialog = new SelectToppingsDialogFragment();
                dialog.setArguments(params);        // pass the params Bundle to our fragment here.
                dialog.show(getSupportFragmentManager(), "SelectToppingsDialogFragment");
            }
        });

        buttonSelectSauce = findViewById(R.id.buttonSelectSauce);
        buttonSelectSauce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Again, the SelectSauceDialogFragment requires that we pass in the
                // checkedItem, which is the previously selected sauce index to the dialog
                // fragment. So we create a Bundle to do just that.
                //
                Bundle params = new Bundle();
                params.putInt("checkedItem", selectedSauce);

                SelectSauceDialogFragment dialog = new SelectSauceDialogFragment();
                dialog.setArguments(params);        // set the params here.
                dialog.show(getSupportFragmentManager(), "SelectSauceDialogFragment");
            }
        });

        buttonStartPreparing = findViewById(R.id.buttonStartPreparing);
        buttonStartPreparing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createNotificationChannel();


                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        for (int i = 0; i <= 100; i = i + 10) {
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID);

                            builder.setContentTitle("Bread Builder");
                            builder.setContentText("Preparation in progress...");
                            builder.setSmallIcon(R.drawable.sandwich);
                            builder.setProgress(100, i, false);
                            Notification notification = builder.build();
                            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                            notificationManagerCompat.notify(NOTIFICATION_ID, notification);

                            try {
                                Thread.sleep(1000);     // Sleeps for 1 second.
                            } catch (InterruptedException ex) {
                            }
                        }
                    }
                }).start();
            }
        });
    }

    @Override
    public void onItemClick(DialogFragment fragment, int position) {
        // Lets check which dialog fragment communicated to this method
        // that the user has just made a selection.
        //
        if (fragment instanceof SelectBreadDialogFragment) {
            // This is how we can retrieve the array from the strings.xml file.
            //
            String[] breadArray = getResources().getStringArray(R.array.bread_choice);

            // breadArray[position] gives us the actual name of the bread
            // selected by the user. So we use it to set it the button's text.
            //
            buttonSelectBread.setText(breadArray[position]);
        }

        if (fragment instanceof SelectSauceDialogFragment) {
            // Retrieve our sauce_choice from the res/values/strings.xml.
            //
            String[] sauceArray = getResources().getStringArray(R.array.sauce_choice);

            // Set the selected sauce's name to our button.
            //
            buttonSelectSauce.setText(sauceArray[position]);
        }
    }

    @Override
    public void onItemsSelected(DialogFragment fragment, boolean[] checkedItems) {
        // Let's check again which dialog fragment communicated to this method
        // that the user has completed the selection and clicked the OK button.
        //
        if (fragment instanceof SelectToppingsDialogFragment) {
            // Again retrieve the topping_choice from our res/values/strings.xml file
            //
            String[] toppingArray = getResources().getStringArray(R.array.topping_choice);

            // What we are doing is here to run through all the toppings and
            // see which toppings were selected by the user.
            // For those toppings that are selected, we add the name of that
            // topping to the "toppings" variable as a long string.
            //
            String toppings = "";
            for (int i = 0; i < toppingArray.length; i++) {
                if (checkedItems[i] == true)    // This checks if the topping was selected.
                    toppings = toppings + toppingArray[i] + " ";    // This adds the name to the "toppings" string.
            }

            // Now that we have all the names of the toppings in one long string in the "toppings"
            // variable, we will display it on our button.
            //
            buttonSelectToppings.setText(toppings);
        }
    }

    private void createNotificationChannel() {


        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel notificationChannel;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, CHANNEL_NAME, importance);

            //Boolean value to set if lights are enabled for Notifications from this Channel
            notificationChannel.enableLights(true);

            //Boolean value to set if vibration are enabled for Notifications from this Channel
            notificationChannel.enableVibration(true);

            //Sets the color of Notification Light
            notificationChannel.setLightColor(Color.GREEN);

            //Set the vibration pattern for notifications. Pattern is in milliseconds with the format {delay,play,sleep,play,sleep...}
            notificationChannel.setVibrationPattern(new long[]{
                    500,
                    500,
                    500,
                    500,
                    500
            });

            //Sets whether notifications from these Channel should be visible on Lockscreen or not
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}