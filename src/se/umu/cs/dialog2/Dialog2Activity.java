/**
 * Small example of two dialogs using DialogFragments (and AlertDialog). 
 * This code is intended for versions after 3.0.
 * 
 */
package se.umu.cs.dialog2;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Dialog2Activity extends Activity {	
	
	private final String[] colorItems = {"Red", "Green", "Blue"};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // When button 1 is clicked, show the first dialog  
        Button btn1 = (Button) findViewById(R.id.button1); 
        btn1.setOnClickListener(new View.OnClickListener() { 
            public void onClick(View v) { 
            	myShowDialog(1); 
            } 
        }); 

        // When button 1 is clicked, show the second dialog          
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() { 
            public void onClick(View v) { 
            	myShowDialog(2); 
            } 
        });         
    }
    
    /**
     * Creates a dialogfragment with id id and shows it.
     * @param id - the identity of the dialogfragment
     */
    public void myShowDialog(int id) {
    	DialogFragment newFragment;
    	switch (id) {
			case 1: 
				// A dialog that asks if the user wants to exit
				// If the user clicks yes the activity is stopped, 
				// otherwize the dialog is closed.								
				newFragment = MyAlertDialogFragment.newInstance(1, "", "Are you sure you want to exit?", "Yes", "No", null);
				newFragment.show(getFragmentManager(), "dialog");
				break;
			case 2: 
				// A dialog where the user chooses a color and then the color is printed 
				// as a toast on the screen and the dialog closes				
				newFragment = MyAlertDialogFragment.newInstance(2, "Pick a color", "", "", "", colorItems);
				newFragment.show(getFragmentManager(), "dialog");
				break;
    	}
    }
    
    /**
     * Handles what should be done when the positive button has 
     * been clicked for the dialogfragment with id id      
     * 
     * @param id - the identity of the dialogfragment 
     */
    public void doPositiveClick(int id) {
    	// At the moment we have only one id that calls this method
    	if (id == 1) {
    		Dialog2Activity.this.finish();				
    	}
    }
    
    /**
     * Handles what should be done when the positive button has 
     * been clicked for the dialogfragment with id id 
     * 
     * @param id - the identity of the dialogfragment 
     */
    public void doNegativeClick(int id) {
    	// At the moment we have only one id that calls this method
    	if (id==1); // Do nothing				
    }
    
    /**
     * Handles what should be done when the positive button has 
     * been clicked for the dialogfragment with id id 
     * @param id - the identity of the dialogfragment 
     * @param item - the item chosen in the dialogfragment
     */
    public void doItemClick(int id, int item) {
    	// At the moment we have only one id that calls this method
    	if (id == 2) {
    		Toast.makeText(getApplicationContext(), colorItems[item], Toast.LENGTH_SHORT).show();
    	}
    }    
}