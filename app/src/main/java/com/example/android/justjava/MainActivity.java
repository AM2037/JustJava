package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
        //Space for user to enter name
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        //Log.v("MainActivity", "Name: " + name);

        //Figure out if user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        //Log.v("MainActivity", "Has whipped cream: " + hasWhippedCream);

        //Figure out if user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice();
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);
        //displayMessage(createOrderSummary(price));

        /*Lesson 11.3 Update Order Summary EXAMPLE
        Log.v("MainActivity", "The price is " + price); <== example to check log

        String priceMessage = createOrderSummary(price);
        displayMessage(priceMessage);*/

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
     * @param name of the customer
     * @param price of the order
     * @param addWhippedCream is whether the user wants whipped cream or not
     * @param addChocolate checks if user wants chocolate or not
     * @return text summary
     */
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
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
     *
     * changed initial TextView type to View which threw an error because only view methods
     * so cast View into (TextView)
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}