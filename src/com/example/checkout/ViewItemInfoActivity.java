package com.example.checkout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ViewItemInfoActivity extends Activity {
	String itemId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_view_item_info);

		// Get the message from the intent
		Intent intent = getIntent();
		itemId = intent.getStringExtra(MainActivity.EXTRA_ITEMID);
	}

	@Override 
	protected void onStart() {
		super.onStart();
				
		TextView idTxt = (TextView) findViewById(R.id.code_field);
		idTxt.setText(itemId);
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
