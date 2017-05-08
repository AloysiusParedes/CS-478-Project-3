package com.example.com.cs478proj3a3;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;


public class BaseballActivity extends AppCompatActivity implements ListSelectionListener{
    private static final String APP_PERMISSION = "edu.uic.cs478.project3";
    private static final int PERMISSION_REQUEST_CODE = 0;

    public int currentIndex = -1;

    private final TeamSiteFragment siteFragment = new TeamSiteFragment();

    private FragmentManager fragmentManager;
    private FrameLayout teamFrameLayout, siteFrameLayout;

    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(checkPermissions()) {
            setContentView(R.layout.activity_baseball);

            siteFragment.setSites(DataProvider.sitesMLB);

            //reference to the two frame layouts
            teamFrameLayout = (FrameLayout) findViewById(R.id.team_fragment_container);
            siteFrameLayout = (FrameLayout) findViewById(R.id.site_fragment_container);

            //reference to fragment manager
            fragmentManager = getFragmentManager();

            //start a new FragmentTransaction
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            TeamListFragment tlf = new TeamListFragment();
            tlf.setTeam(DataProvider.listMLB);

            //add the fragment to the layout
            fragmentTransaction.replace(R.id.team_fragment_container, tlf);

            //commit the fragment transaction
            fragmentTransaction.commit();

            //add a OnBackStackChangedListener to reset the layout when the back stack changes
            fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                public void onBackStackChanged() {
                    setLayout();
                }
            });
        }
        else
            ActivityCompat.requestPermissions(this, new String[]{APP_PERMISSION}, 0);
    }//end onCreate(...)

    private void setLayout() {
        //see if the siteFragment has been added
        if (!siteFragment.isAdded()) {
            //make the list of teams fill the entire screen
            teamFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
            siteFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT));
        }
        else {
            //check which orientation the phone is currently in
            if(getScreenOrientation() == 1){
                //list is full screen
                teamFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 0f));
                siteFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 1f));
            }
            else if(getScreenOrientation() == 2){
                //list is 1/3, web is 2/3
                teamFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 1f));
                siteFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 2f));
            }
            else
                Toast.makeText(this, "Uh-Oh... I broke", Toast.LENGTH_SHORT).show();
        }
    }//end setLayout()

    //method gets called after user chooses whether to grant application permission or not
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode == PERMISSION_REQUEST_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                Intent i = getIntent();
                finish();
                startActivity(i);
            }
            else
                Toast.makeText(this, "Application won't work without permission, my friend", Toast.LENGTH_LONG).show();
        }
    }//end onRequestPermissionsResult(...)

    //creates the menu for this activity using main_menu xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.baseball_menu, menu);
        return true;
    }//end onCreateOptionsMenu(...)

    //handles click events for menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.switchToBasketball){
            Toast.makeText(this, "Switching to Basketball", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, BasketballActivity.class);
            startActivity(i);
            return true;
        }

        else if(item.getItemId() == R.id.checkPermission){
            if (ContextCompat.checkSelfPermission(this, APP_PERMISSION) != PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(this, new String[]{APP_PERMISSION}, 0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }//end onOptionsItemSelected(...)

    @Override
    public void onListSelection(int index) {
        currentIndex = index;
        //add fragment if it hasn't been added yet
        if (!siteFragment.isAdded()) {
            //start a new FragmentTransaction
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            //add the site fragment to the layout
            fragmentTransaction.add(R.id.site_fragment_container, siteFragment);

            //add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            //commit the FragmentTransaction
            fragmentTransaction.commit();

            //force Android to execute the committed FragmentTransaction
            fragmentManager.executePendingTransactions();
        }

        if (siteFragment.getShownIndex() != index)
            //change the webpage being displayed on the webView
            siteFragment.showSiteAtIndex(index);

    }//end onListSelection(...)

    //return 1 if portrait, return 2 if landscape
    public int getScreenOrientation() {
        Display getOrient = getWindowManager().getDefaultDisplay();
        int orientation;
        if(getOrient.getWidth()==getOrient.getHeight()){
            orientation = Configuration.ORIENTATION_SQUARE;
        } else{
            if(getOrient.getWidth() < getOrient.getHeight()){
                orientation = Configuration.ORIENTATION_PORTRAIT;
            }else {
                orientation = Configuration.ORIENTATION_LANDSCAPE;
            }
        }
        return orientation;
    }//end getScreenOrientation()

    //http://stackoverflow.com/questions/6896243/how-can-i-detect-screen-rotation
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        int orientation = newConfig.orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT){
            //see if the siteFragment has been added
            if (!siteFragment.isAdded()) {
                //make the list of teams fill the entire screen
                teamFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
                siteFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT));
            }
            else {
                teamFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 0f));
                siteFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 1f));

            }
        }
        else if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            //see if the siteFragment has been added
            if (!siteFragment.isAdded()) {
                //make the list of teams fill the entire screen
                teamFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
                siteFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT));
            }
            else {
                teamFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 1f));
                siteFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 2f));

            }
        }
    }//end onConfigurationChanged(...)

    //checks to see if application has been granted the correct permission
    public boolean checkPermissions(){
        return (ContextCompat.checkSelfPermission(this, APP_PERMISSION) == PackageManager.PERMISSION_GRANTED);
    }//end checkPermissions()

}//end BaseballActivity

