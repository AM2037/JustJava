package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    //To test if staying within 100 cups bound change this value and XML to 99
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked
     *
     * Modified to catch when number of coffees goes above max limit of 100
     */
    public void increment(View view) {
        if (quantity == 100) {
            //show error message as a toast-- this for this activity aka context
            Toast.makeText(this, getString(R.string.toast_increment), Toast.LENGTH_SHORT).show();
            return;
//            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
//            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     *
     * Modified to catch when number of coffees goes below min limit of 1
     */
    public void decrement(View view) {
        if (quantity == 1) {
            //show error message as a toast
            Toast.makeText(this, getString(R.string.toast_decrement), Toast.LENGTH_SHORT).show();
            return;
//            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
//            return;
            //exit method immediately without executing lines of code below
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }


    /**
     * This method is called when the order button is clicked.
     *
     * Added calculatePrice method and call under submitOrder for Lesson 10.
     *
     * price variables are local and are different to each other
     *
     * Used chaining method calls b/c EditText getText returns Editable
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

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO); //All caps = constant
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject, name));
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage); //orderSummary in email body
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        //displayMessage(priceMessage); --REMOVED IN LESSON 11.19 because routes to email
        //displayMessage(createOrderSummary(price));

        /*Lesson 11.3 Update Order Summary EXAMPLE
        Log.v("MainActivity", "The price is " + price); <== example to check log

        String priceMessage = createOrderSummary(price);
        displayMessage(priceMessage);*/

    }

    /**
     * Calculates the price of the order.
     * Pass toppings information (whipped cream $1/cup, chocolate $2/cup), x quantity
     * @param addWhippedCream is whether user wants whipped topping
     * @param addChocolate is whether user wants chocolate topping
     * @return total price-- replaced quantity * 5 with quantity * basePrice
     *
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;

        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }

        //no else statement necessary because if false code will do nothing

        if (addChocolate) {
            basePrice = basePrice + 2;
        }

        return quantity * basePrice;

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
        String priceMessage = getString(R.string.order_summary_name, name);
        priceMessage += "\n" + getString(R.string.order_summary_whipped_cream) + addWhippedCream;
        priceMessage += "\n" + getString(R.string.order_summary_chocolate) + addChocolate;
        priceMessage += "\n" + getString(R.string.order_summary_quantity) + quantity;
        priceMessage += "\n" + getString(R.string.order_summary_price) + price;
        priceMessage += "\n" + getString(R.string.thank_you);
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

//    /**
//     * This method displays the given text on the screen. Removed for lesson 11
//     *
//     * changed R.id from price_text_view to order_summary_text_view & variable names
//     *
//     * changed initial TextView type to View which threw an error because only view methods
//     * so cast View into (TextView)
//     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }
}