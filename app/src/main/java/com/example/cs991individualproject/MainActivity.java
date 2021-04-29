/**
 *This is the main method for the application using the activity_main.xml layout.
 * It allows the learner to interact with the GUI to push, pop and clear data from an Array
 * as in a stack ADT. It also displays the size, top, isEmpty and popped values
 * as a visual representation for the learner.
 *
 * @Author: Stuart Mcpherson
 * version 05/03/2021
 */

package com.example.cs991individualproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.BreakIterator;

import static android.widget.Toast.LENGTH_SHORT;


public class MainActivity extends AppCompatActivity {
    /**
     * Global variables for the main method
     *
     */
    private TextView sizeDisplay; //Display the size of the array
    private TextView errorDisplay; //Display error messages
    private TextView topDisplay; //Displays top value
    private TextView popDisplay; //Display popped values
    private TextView zeroDisplay; //Display value at index 0 in the visual representation of the array
    private TextView oneDisplay; //Display value at index 1 in the visual representation of the array
    private TextView twoDisplay; //Display value at index 2 in the visual representation of the array
    private TextView threeDisplay; //Display value at index 3 in the visual representation of the array
    private TextView fourDisplay; //Display value at index 4 in the visual representation of the array
    private TextView fiveDisplay; //Display value at index 5 in the visual representation of the array
    private TextView sixDisplay; //Display value at index 6 in the visual representation of the array
    private EditText enterVal; //Get input from the learner

    /**
     * The create method which will load the activity_main.xml of the app and assign
     * global variables to their elements.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sizeDisplay = findViewById(R.id.sizeDisplay);
        errorDisplay = findViewById(R.id.errorDisplay);
        topDisplay = findViewById(R.id.topDisplay);
        enterVal = findViewById(R.id.enterValue);
        popDisplay = findViewById(R.id.popDisplay);
        zeroDisplay = findViewById(R.id.zeroDisplay);
        oneDisplay = findViewById(R.id.oneDisplay);
        twoDisplay = findViewById(R.id.twoDisplay);
        threeDisplay = findViewById(R.id.threeDisplay);
        fourDisplay = findViewById(R.id.fourDisplay);
        fiveDisplay = findViewById(R.id.fiveDisplay);
        sixDisplay = findViewById(R.id.sixDisplay);

    }

    /**
     * A method to get the size of the array.
     *
     * @return the integer size of the array
     */
    public int getSize() {
        String sizeString =  sizeDisplay.getText().toString();
        return Integer.parseInt(sizeString);
    }



    /**
     * A method to check if the array is empty and set the display of the isEmpty on the GUI as
     * either "true" or "false"  for the learner.
     */
    public void isEmpty() {
        TextView isEmptyDisplay = findViewById(R.id.isEmptyDisplay);
        if (getSize() > 0){ isEmptyDisplay.setText("false");
        } else {
            isEmptyDisplay.setText("true");
            topDisplay.setText("null");
        }
    }

    /**
     * A method to clear all data from the visual representation of the array and set all displays
     * back to initial values.
     *
     * @param view the clear button on the GUI
     */
    public void clearArray(View view){
        //Set all array displays to default
        zeroDisplay.setText("");
        oneDisplay.setText("");
        twoDisplay.setText("");
        threeDisplay.setText("");
        fourDisplay.setText("");
        fiveDisplay.setText("");
        sixDisplay.setText("");
        sizeDisplay.setText("0");

        //Update isEmpty display
        isEmpty();

        //reset the hint in user input
        enterVal.setText("");
        enterVal.setHint("Enter value here");

        //Remove error display text
        errorDisplay.setText("");

        //Set Top display to null
        topDisplay.setText("null");

        //Set popped value display to default
        popDisplay.setText("");

        //Toast to inform user of application status
        Toast.makeText(getApplicationContext(), "Clearing array", LENGTH_SHORT).show();
    }

    /**
     * A method to get the current TextView in the visual representation of the array.
     * It checks the size the of the array and then returns the appropriate display.
     *
     * @param size the current size of the array.
     *
     * @return the TextView that is the next index in the representation of the array.
     */
    public TextView getArrayDisplay(int size) {
            switch (size) {
            case 1:
                return oneDisplay;
            case 2:
                return twoDisplay;
            case 3:
                return threeDisplay;
            case 4:
                return fourDisplay;
            case 5:
                return fiveDisplay;
            case 6:
                return sixDisplay;

        }
        return zeroDisplay;
    }


    /**
     * A method to extract the input from the learner and push this into the array.
     * The method checks the size of the array and the value input from the learner.
     * The method displays error messages if the array is full or value is empty.
     * The method updates all displays to provide information for the user.
     *
     * @param view the push button
     */
    public void pushValue(View view) {
        //get the current size of the array
        int size = getSize();

        //Get the user input as a String
        String valueToPush = enterVal.getText().toString();

        //Check user has input a valid value
        if(!valueToPush.equals("")){

            //Check if array is full
            if (size <= 6) {

                //remove error text
                errorDisplay.setText("");

                //get current array display
                TextView currentIndexDisplay = getArrayDisplay(size);

                //Add user input to the current array display
                currentIndexDisplay.setText(valueToPush);

                //Increase the size of the array
                size++;

                //Update size display
                String currentSizeStr = String.valueOf(size);
                sizeDisplay.setText(currentSizeStr);

                //reset the hint in the user input
                enterVal.setText("");
                enterVal.setHint("Enter value here");

                //update the isEmpty display
                isEmpty();

                //Update the top display
                topDisplay.setText(valueToPush);

                //Toast to inform the user of the status of the application
                Toast.makeText(getApplicationContext(), "Pushing value to array", LENGTH_SHORT).show();
            } else {
                //Display error if array is full
                errorDisplay.setText("Array is full (pop or clear)");
            }
        } else {
            //Display error if no vlaue entered
            errorDisplay.setText("Enter a value to push");
        }
    }

    /**
     * A method to pop the value from last index in the array.
     * The method displays the popped value for the learner.
     * The method displays error messages if the array is empty.
     * The method updates all information displays for the user.
     *
     * @param view the pop button
     */
    public void pop(View view){
        //check size of array
        if(getSize() > 0) {
            //remove error text
            errorDisplay.setText("");

            //get the array size after an element has been removed
            int prev = getSize() - 1;

            //Get the next array display
            TextView prevDisplay = getArrayDisplay(prev);

            //Update pop display
            String poppedValue = (String) prevDisplay.getText();
            popDisplay.setText(poppedValue);

            //set text of previous display to default
            prevDisplay.setText("");

            //Toast to inform user of application status
            Toast.makeText(getApplicationContext(), "Popping value from array", LENGTH_SHORT).show();

            //If array is empty update top display to null
            if( prev - 1 < 0){
                topDisplay.setText("null");

                //If array is not empty update top display with value
            } else {
                TextView newTop = getArrayDisplay(prev - 1 );
                String newTopStr = (String) newTop.getText();
                topDisplay.setText(newTopStr);
            }

            //Update size of the array
            String newSize = String.valueOf(prev);
            sizeDisplay.setText(newSize);

            //display error if array is empty
        } else {
            errorDisplay.setText("Array is empty (push)");
        }

        //Update isEmpty display
        isEmpty();
    }


}





