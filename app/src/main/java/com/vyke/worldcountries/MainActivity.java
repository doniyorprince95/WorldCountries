package com.vyke.worldcountries;
/* This Project is Created by Vipul Kumar Maurya*/

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.vyke.worldcountries.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.vyke.worldcountries.about.About;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Countries>> {

    public static final double APP_VER = 1.1;
    private static final int COUNTRIES_LOADER_ID = 1;
    private final String exeUrlAll = "https://restcountries.eu/rest/v2/all";
    //  private ListView listView;
    private ProgressBar progressBar;
    private List<Countries> CountriesList = new ArrayList<>();
    private CustomAdapter adapter;
    private RecyclerView mRecyclerView;
    //  private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;


    //  To check Connection of Internet. If Id connected through Mobile Data Show a message
    public boolean isConnected() {
        ConnectivityManager getNetworkStatus = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = getNetworkStatus.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(MainActivity.this, "Mobile Data Will be used", Toast.LENGTH_SHORT).show();
            }
            return true;
        } else {
            Toast.makeText(MainActivity.this, " Oops! Your are off the Internet", Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this, " Turn on Internet and Refresh", Toast.LENGTH_LONG).show();
            return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isConnected();

        //   listView = (ListView) findViewById(R.id.main_list);

        /* The RecyclerView, progessbar, LayoutManager, Adapter are initialized */
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.list_progress);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter = new CustomAdapter(CountriesList);
        mRecyclerView.setAdapter(adapter);

        /* Show the Ads*/
/* **Warning** */
/* If You are testing your App with Google Ad Mob * Use adTestDevice(.....).build(); */

        //   AdRequest adRequest = new AdRequest.Builder().addTestDevice("860C5A531681CA9A3CA94D8936AC515F").build();
        AdView mAdView = (AdView) findViewById(R.id.adView);
        mAdView.setVisibility(View.INVISIBLE);
        // AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setVisibility(View.VISIBLE);


        /* Check if Connected start The Loader Manager a*/
        try {
            if (isConnected()) {

                this.getSupportLoaderManager().initLoader(COUNTRIES_LOADER_ID, null, this);

            } else {
                progressBar.setVisibility(View.GONE);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* ItemDecoration show the Divider between the items*/
        RecyclerView.ItemDecoration dividerDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerDecoration);
        // new CountriesLoader().execute("https://restcountries.eu/rest/v2/name/india?fullText=true");


    }

    /* The Menu is inflated and Search query in Search view handled*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_items, menu);


        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.searchbtn));
        searchView.setSearchableInfo(searchableInfo);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {

                    //  adapter.setFilter(newText);
                    CustomAdapter newAdapter = adapter.setFilter(newText);
                    mRecyclerView.setAdapter(newAdapter);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        return true;
    }

    /* Refresh Button and About button call implemented*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.refresh:

                if (isConnected()) {
                    this.getSupportLoaderManager().restartLoader(COUNTRIES_LOADER_ID, null, this);

                } else {

                    progressBar.setVisibility(View.GONE);

                }
                return true;
            case R.id.about:
                Intent intent = new Intent(this, About.class);
                startActivity(intent);
                return true;
        }

        return false;

        // return super.onOptionsItemSelected(item);
    }

    /* DO not Finish the Activity, It will result in Closing of activity*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    /* OnCreateLoader starts the Loader and perform background operations */
    @Override
    public Loader<List<Countries>> onCreateLoader(int id, Bundle args) {

        return new CountriesLoader(this, exeUrlAll);

    }

    /* onLoadFinish Draw the fetched result from Loader on the Screen */
    @Override
    public void onLoadFinished(Loader<List<Countries>> loader, List<Countries> data) {
        adapter = new CustomAdapter(data);
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    /* When Loader restarted invalidate the previous used data*/
    @Override
    public void onLoaderReset(Loader<List<Countries>> loader) {
        adapter = null;

    }

/*
        // Handling the Network call of API and Pasing JSON objects
    private class GetInfo extends AsyncTask<String, String, List<Countries>> {


        @Override
        public List<Countries> doInBackground(String... params) {

            StringBuilder builder = new StringBuilder();
            HttpURLConnection urlConnection = null;
            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                if (urlConnection.getResponseCode() == 200) {

                    InputStream inputStream = urlConnection.getInputStream();
                    //  InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    String Line;

                    while ((Line = bufferedReader.readLine()) != null) {
                        builder.append(Line);
                    }
                    Log.e("JSON", "  DATA is Received");
                } else {
                    return null;
                }

                CountriesList = jsonParsing(builder.toString());
                return CountriesList;
            } catch (MalformedURLException m) {
                m.getCause().printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (bufferedReader != null) try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }
        // JSON Objects storesin Countries List<Countries>
        private List<Countries> jsonParsing(String jsonData) {
            try {
                JSONArray jsonArray = new JSONArray(jsonData);
                for (int j = 0; j < jsonArray.length(); j++) {
                    Countries cts = new Countries();
                    cts.setName(jsonArray.getJSONObject(j).getString("name"));
                    cts.setCapital(jsonArray.getJSONObject(j).getString("capital"));
                    cts.setAlpha2Code(jsonArray.getJSONObject(j).getString("alpha2Code"));
                    cts.setAlpha3Code(jsonArray.getJSONObject(j).getString("alpha3Code"));
                    cts.setRegion(jsonArray.getJSONObject(j).getString("region"));
                    cts.setSubregion(jsonArray.getJSONObject(j).getString("subregion"));
                    cts.setArea(jsonArray.getJSONObject(j).getString("area"));
                    cts.setDemonym(jsonArray.getJSONObject(j).getString("demonym"));
                    cts.setNativeName(jsonArray.getJSONObject(j).getString("nativeName"));
                    cts.setPopulation(jsonArray.getJSONObject(j).getString("population"));
                    cts.setFlag(jsonArray.getJSONObject(j).getString("flag"));
                    //  cts.setFlag(jsonArray.getJSONObject(j).getString("name"));
                    // Get time Zone
                    List<String> timeZone = new ArrayList<>();
                    for (int i = 0; i < jsonArray.getJSONObject(j).getJSONArray("timezones").length(); i++) {
                        String S = jsonArray.getJSONObject(j).getJSONArray("timezones").getString(i);
                        timeZone.add(S);
                    }
                    cts.setTimezones(timeZone);
                    // for border
                    List<String> Borders = new ArrayList<>();
                    for (int i = 0; i < jsonArray.getJSONObject(j).getJSONArray("borders").length(); i++) {
                        String S = jsonArray.getJSONObject(j).getJSONArray("borders").getString(i);
                        Borders.add(S);
                    }
                    cts.setBorders(Borders);
                    // Get Currency
                    List<Countries.Currency> CurrencyList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.getJSONObject(j).getJSONArray("currencies").length(); i++) {
                        Countries.Currency Curli = new Countries.Currency();
                        JSONObject CurJSONObject = jsonArray.getJSONObject(j).getJSONArray("currencies").getJSONObject(i);
                        Curli.setCurrencyCode(CurJSONObject.getString("code"));

                        Curli.setSymbol(CurJSONObject.getString("symbol"));

                        Curli.setCurrencyName(CurJSONObject.getString("name"));
                        CurrencyList.add(Curli);
                    }
                    cts.setCurrencies(CurrencyList);

                    // Get Languages
                    List<Countries.Languages> LanguageList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.getJSONObject(j).getJSONArray("languages").length(); i++) {
                        Countries.Languages Lingo = new Countries.Languages();
                        JSONObject CurJSONObject = jsonArray.getJSONObject(j).getJSONArray("languages").getJSONObject(i);
                        Lingo.setIso639_1(CurJSONObject.getString("iso639_1"));

                        Lingo.setName(CurJSONObject.getString("name"));


                        LanguageList.add(Lingo);
                    }
                    cts.setLanguages(LanguageList);

                    // Get Calling Code
                    List<String> CallingCode = new ArrayList<>();
                    for (int i = 0; i < jsonArray.getJSONObject(j).getJSONArray("callingCodes").length(); i++) {
                        String S = jsonArray.getJSONObject(j).getJSONArray("callingCodes").getString(i);
                        CallingCode.add(S);

                    }
                    cts.setCallingCodes(CallingCode);
                    CountriesList.add(cts);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return CountriesList;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mRecyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPostExecute(List<Countries> CL) {
            super.onPostExecute(CL);
            adapter = new CustomAdapter(CL);
            try {
                mRecyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
*/


}

