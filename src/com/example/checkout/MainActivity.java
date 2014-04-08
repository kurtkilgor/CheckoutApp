package com.example.checkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends ActionBarActivity {
	public final static String EXTRA_MODE = "com.example.checkout.MODE";
	public final static String EXTRA_USERID = "com.example.checkout.USERID";
	public final static String EXTRA_ITEMID = "com.example.checkout.ITEMID";
	
	public final static int CHECKOUT_MODE = 0;
	public final static int CHECKIN_MODE = 1;
	public final static int VIEWITEMINFO_MODE = 2;
	public final static int VIEWUSERINFO_MODE = 3;
	
	int mode = -1;
	long checkoutId = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
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
	
	public void checkOutClicked(View view) {
		mode = CHECKOUT_MODE;
		checkoutId = 0;
		startActivityForResult(new Intent(this, ScanBadgeActivity.class), CHECKOUT_MODE);
	}

	public void checkInClicked(View view) {
		mode = CHECKIN_MODE;
		IntentIntegrator scanIntegrator = new IntentIntegrator(this);
		scanIntegrator.initiateScan();
	}
	
	public void itemInfoClicked(View view) {
		mode = VIEWITEMINFO_MODE;
		IntentIntegrator scanIntegrator = new IntentIntegrator(this);
		scanIntegrator.initiateScan();
	}
	
	public void userInfoClicked(View view) {
		mode = VIEWUSERINFO_MODE;
		startActivityForResult(new Intent(this, ScanBadgeActivity.class), VIEWUSERINFO_MODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		
		if(mode == CHECKOUT_MODE && requestCode == CHECKOUT_MODE) {
			if(resultCode != 0)
				return;
			
			if(!intent.hasExtra(EXTRA_USERID))
				return;
			
			checkoutId = intent.getLongExtra(EXTRA_USERID, 0);
			
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();
			return;
		}

		if(mode == CHECKOUT_MODE && requestCode == IntentIntegrator.REQUEST_CODE) {
			mode = -1;
			IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
	    	if (scanningResult != null) {
	    		String scanContent = scanningResult.getContents();
				Intent newIntent = new Intent(this, ItemCheckOutActivity.class);
				newIntent.putExtra(EXTRA_USERID, checkoutId);
				newIntent.putExtra(EXTRA_ITEMID, scanContent);
				startActivity(newIntent);	    		
			}else{
			    Toast toast = Toast.makeText(getApplicationContext(),
			            "No scan data received!", Toast.LENGTH_SHORT);
		        toast.show();
		        return;
			}
	    	return;
		}
		
		if(mode == CHECKIN_MODE && requestCode == IntentIntegrator.REQUEST_CODE) {
			mode = -1;
			IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
	    	if (scanningResult != null) {
	    		String scanContent = scanningResult.getContents();
				Intent newIntent = new Intent(this, ItemCheckInActivity.class);
				newIntent.putExtra(EXTRA_ITEMID, scanContent);
				startActivity(newIntent);	    		
			}else{
			    Toast toast = Toast.makeText(getApplicationContext(),
			            "No scan data received!", Toast.LENGTH_SHORT);
		        toast.show();
		        return;
			}
	    	return;
		}
		
		if(mode == VIEWUSERINFO_MODE && requestCode == VIEWUSERINFO_MODE) {
			mode = -1;
			if(resultCode != 0)
				return;
			
			if(!intent.hasExtra(EXTRA_USERID))
				return;
			
			long userId = intent.getLongExtra(EXTRA_USERID, 0);
			
			Intent newIntent = new Intent(this, ViewUserInfoActivity.class);
			newIntent.putExtra(EXTRA_USERID, userId);
			startActivity(newIntent);
			return;
		} 
		
		if(mode == VIEWITEMINFO_MODE && requestCode == IntentIntegrator.REQUEST_CODE) {
			mode = -1;
			IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
	    	if (scanningResult != null) {
	    		String scanContent = scanningResult.getContents();
				Intent newIntent = new Intent(this, ViewItemInfoActivity.class);
				newIntent.putExtra(EXTRA_ITEMID, scanContent);
				startActivity(newIntent);	    		
			}else{
			    Toast toast = Toast.makeText(getApplicationContext(),
			            "No scan data received!", Toast.LENGTH_SHORT);
		        toast.show();
		        return;
			}
	    	return;
		}
	}
}
