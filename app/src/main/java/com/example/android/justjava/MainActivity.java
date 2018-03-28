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

    private CheckBox whippedCreamCheckBox = null; // Global Variables
    private CheckBox chocolateCheckBox = null;
    private EditText nameField = null;
    private TextView quantityTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        nameField = (EditText) findViewById(R.id.name_field);
        quantityTextView = (TextView) findViewById(R.id.quantity_text_view);

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
        String name = nameField.getText().toString();

        //Figure out if user wants whipped cream topping
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        //Figure out if user wants chocolate topping
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

    }


    /**
     * This method displays the given quantity value on the screen.
     *
     * Renamed number to amount for inputs to a method activity.
     */
    private void displayQuantity(int numberOfCoffees) {
        quantityTextView.setText("" + numberOfCoffees);
    }

}