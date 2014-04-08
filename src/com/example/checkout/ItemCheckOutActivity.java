package com.example.checkout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ItemCheckOutActivity extends Activity {
	long userId;
	String itemId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_item_check_out);

		// Get the message from the intent
		Intent intent = getIntent();
		userId = intent.getLongExtra(MainActivity.EXTRA_USERID, 0);
		itemId = intent.getStringExtra(MainActivity.EXTRA_ITEMID);
	}

	@Override 
	protected void onStart() {
		super.onStart();
				
		TextView idTxt = (TextView) findViewById(R.id.id_message);
		idTxt.setText("Hi, " + userId + "! You have checked out item " + itemId + ". Bring it back safe!");
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
	
	public void doneClicked(View view) {
		finish();
	}
}
