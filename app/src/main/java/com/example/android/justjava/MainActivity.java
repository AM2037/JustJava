package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    //made quantity a global (purple) variable instead of local (black) so the app can add past 3
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked
     */
    public void increment(View view) {
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     * This method is called when the minus button is clicked
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        display(quantity);
    }


    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        //specify data type which is String then variable name (~name tag) which is priceMessage
        //initialize with an equal sign and an initial value which is "free" + ;
        //Only store one string (letters, numbers, or symbols) at a time or a string literal (fixed value)
        int price = quantity * 5;
        String priceMessage = "Total: $" + price;
        priceMessage = priceMessage + "\nThank you!";
        displayMessage(priceMessage);
        // she erased displayPrice(quantity * 5); when adding the total string
        //display(2);
        //displayPrice(2 * 5);

    /**
     * This method reflects the # of coffees ordered
     */

        //create an integer value to store number of coffees
        //int quantity = 5; commented out because there is a global quantity variable
        //Use variable
        //display(quantity);
        //commented out 2nd declaration int coffeesOrdered = 2;
    }




    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}