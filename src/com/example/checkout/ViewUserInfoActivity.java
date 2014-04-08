package com.example.checkout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ViewUserInfoActivity extends Activity {
	long userId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_view_user_info);

		// Get the message from the intent
		Intent intent = getIntent();
		userId = intent.getLongExtra(MainActivity.EXTRA_USERID, 0);
	}

	@Override 
	protected void onStart() {
		super.onStart();
				
		TextView idTxt = (TextView) findViewById(R.id.id_field);
		idTxt.setText(Long.toString(userId));
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
