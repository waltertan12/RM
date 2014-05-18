package com.example.rm;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	//checks if EditText is empty
	private boolean isEmpty(EditText etText) {
	    if (etText.getText().toString().trim().length() == 0) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public void calculate(View v){
		//Pull data from EditText boxes
		EditText weightText = (EditText)findViewById(R.id.weight);
		EditText repsText = (EditText)findViewById(R.id.reps);
		EditText rmText = (EditText)findViewById(R.id.rm);
		Double ans = 0.0;
		
		//Fill 1RM
		if(!isEmpty(weightText) && !isEmpty(repsText) && isEmpty(rmText)){
			Double weight = Double.parseDouble(weightText.getText().toString());
			Double reps = Double.parseDouble(repsText.getText().toString());
			ans = (double) Math.round(weight / (1.0278-(0.0278*reps)));
			rmText.setText(ans.toString());
		}
		
		//Fill Weight
		if(isEmpty(weightText) && !isEmpty(repsText) && !isEmpty(rmText)){
			Double reps = Double.parseDouble(repsText.getText().toString());
			Double rm = Double.parseDouble(rmText.getText().toString());
			ans = (double) Math.round(rm*(1.0278*(0.0278*reps)));
			weightText.setText(ans.toString());
		}
		
		//Fill reps
		if(!isEmpty(weightText) && isEmpty(repsText) && !isEmpty(rmText)){
			Double weight = Double.parseDouble(weightText.getText().toString());
			Double rm = Double.parseDouble(rmText.getText().toString());
			ans = (double) Math.round((1.0278-(weight/rm))/0.0278);
			repsText.setText(ans.toString());
		}
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
