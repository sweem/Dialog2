/**
 * Example of a AlertDialogFragment  
 * This code is intended for versions after 3.0.
 * 
 */
package se.umu.cs.dialog2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class MyAlertDialogFragment extends DialogFragment {

	/** 
	 * Defines a new instance of a myalertdialog fragment.
	 * 
	 * @param id - the id of the dialog
	 * @param title - the title of the dialog (omitted if "")
	 * @param message - the message in the dialog  (omitted if "")
	 * @param posButtonText - the text that should go on the positive button. 
	 * 						  If not submitted a deafult text from the strings.xml file is used
	 * @param negButtonText - the text that should go on the negative button. 
	 * 						  If not submitted a deafult text from the strings.xml file is used
	 * @param items - a list of items (null if the dialog does not have a list)
	 * @return - a new instance of a myalertdialog fragment.
	 */
    public static MyAlertDialogFragment newInstance(int id, String title, String message, 
    		String posButtonText, String negButtonText, String[] items) {
        MyAlertDialogFragment frag = new MyAlertDialogFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        args.putString("title", title);
        args.putString("message", message);
        args.putString("posButtonText", posButtonText);
        args.putString("negButtonText", negButtonText);
        args.putStringArray("items", items);
        frag.setArguments(args);
        return frag;
    }
    
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	// Get the set up for the current instance
    	final int id = getArguments().getInt("id");
    	String title = getArguments().getString("title");
    	String message = getArguments().getString("message");
    	String posButtonText = getArguments().getString("posButtonText");
    	String negButtonText = getArguments().getString("negButtonText");
    	String[] items = getArguments().getStringArray("items");

    	// Start building the dialog
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	if(title!="") builder.setTitle(title);  // Only have a title if a text is provided
    	
    	if(items == null){    	
    		if(message!="") builder.setMessage(message); // Only have a message if a text is provided
    		// It should not be possible to push "back" and close the dialog that way
    		builder.setCancelable(false);
    		// Set the text on the positive button
    		if(posButtonText.equals("")){
    			posButtonText = (String) getActivity().getText(R.string.alert_dialog_ok);
    		}
    		// Set the text on the negative button
    		if(negButtonText.equals("")){
    			negButtonText = (String) getActivity().getText(R.string.alert_dialog_cancel);
    		}
    		// Handle the clicking on buttons
    		builder.setPositiveButton(posButtonText,
    				new DialogInterface.OnClickListener() {
    			public void onClick(DialogInterface dialog, int whichButton) {
    				((Dialog2Activity)getActivity()).doPositiveClick(id);
    			}
    		});
    		builder.setNegativeButton(negButtonText,
    				new DialogInterface.OnClickListener() {
    			public void onClick(DialogInterface dialog, int whichButton) {
    				((Dialog2Activity)getActivity()).doNegativeClick(id);
    			}
    		});    		
    	}else{
    		// A listdialog is set up.
    		builder.setItems(items, 
    				new DialogInterface.OnClickListener() {    
				public void onClick(DialogInterface dialog, int item) { 
					((Dialog2Activity)getActivity()).doItemClick(id, item);					 
				}
			});
    	}
    	return builder.create();
    }
}