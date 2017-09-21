package com.vyke.Countries;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by Raider on 18-09-2017.
 */

public class CountriesLoader extends AsyncTaskLoader<List<Countries>> {

    private String url;
    /*   Cache the list
         Show that On rotation of screen or switch between app prevents from reloading
         Override deliverResult()
    */
    private List<Countries> cacheList;

    /* Constructor to intialize url*/
    public CountriesLoader(Context context, String cUrl) {
        super(context);
        url = cUrl;
    }

    /*When data start loading, check there is data in cached LIst<>
       if Yes, then deliverResult
       if No, then forceLoad the data from Network.
     */
    @Override
    protected void onStartLoading() {
        if (cacheList == null) {
            forceLoad();
        } else
            deliverResult(cacheList);
    }


    /* Call the GetInfo class Method fetchcountriesInfo() to fetch the data*/
    @Override
    public List<Countries> loadInBackground() {
        if (url == null) {
            return null;
        }
        //  List<Countries> Countries= GetInfo.fetchCountriesInfo(url);
        return GetInfo.fetchCountriesInfo(url);
    }

    /* deliverResult to the onLoadFinish()*/
    @Override
    public void deliverResult(List<Countries> data) {
        cacheList = data;
        super.deliverResult(data);
    }
}
