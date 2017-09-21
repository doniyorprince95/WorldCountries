package com.vyke.Countries.about;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.widget.TextView;

import com.vyke.Countries.MainActivity;
import com.android.vyke.worldcountries.R;




public class About extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_about);
        TextView version=(TextView) findViewById(R.id.version);
        TextView feature=(TextView)findViewById(R.id.feature);
        TextView resources=(TextView)findViewById(R.id.resources);
        TextView disclaimer=(TextView)findViewById(R.id.disclaimer);

        String ver = "Paises" + "  " + MainActivity.APP_VER;
        String Feature="This app will help you find information about the countries of the world easily. The idea is to get most of the references at a single Place. \n"+
                        "There are Total "+ 250+" Countries information in this application"+"\n"+
                         "* This shows important information in List \n"+
                          "* Right to the Flag, Pin Drop image will take you the Location of that Country on Google Map\n"+
                          "* Left to the Flag, Current Locale Time of the Country is Shown\n"+
                          "* Touching the List will take you to the Wikipedia Page of the Country\n"+
                           "* In the Search bar You Can Search about Specific Country\n"+
                           "* Refesh will Refresh the List";

        String resource="Currently This app is using Open Source APIs and Libraries";

        String disc="Note: In case of the Mutliple Timezones Local Current time of the Capital is shown\n"+
                    "Local current Time is w.r.t UTC\n"+
                    "\n We will update few features and add a lot more soon...";
        version.setText(ver);
        feature.setText(Feature);
        resources.setText(resource);
        disclaimer.setText(disc);
    }

}