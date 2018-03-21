package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

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
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     *
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }


    /**
     * This method is called when the order button is clicked.
     *
     * Added calculatePrice method and call under submitOrder for Lesson 10.
     *
     * price variables are local and are different to each other
     */

    public void submitOrder(View view) {
        int price = calculatePrice();
        displayMessage(createOrderSummary(price));

    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     *
     */
    private int calculatePrice() {
        return quantity * 5;

        //used to say int price = quantity * 5 /n return price;
    }

    /**
     * This method creates an order summary
     *
     * @param price of the order
     *
     * @return text summary
     */
    private String createOrderSummary(int price) {
        String priceMessage = "Name: Kaptain Kunal";
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;

        //priceMessage = priceMessage + .. changed to +=
        /*could have put calculatePrice instead of + price but she showed passing a method
     * to another method*/
    }


    /**
     * This method displays the given quantity value on the screen.
     *
     * Renamed number to amount for inputs to a method activity.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method displays the given price on the screen.
     *
     * commented out when I changed "Price" TextView text to "Order Summary" in XML
     */
    /*private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }*/

    /**
     * This method displays the given text on the screen.
     *
     * changed R.id from price_text_view to order_summary_text_view & variable names
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}