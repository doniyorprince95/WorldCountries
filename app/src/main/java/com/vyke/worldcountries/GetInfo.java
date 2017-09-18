package com.vyke.worldcountries;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raider on 18-09-2017.
 */

public class GetInfo {

    private static final String LOG_TAG = MainActivity.class.getName();

    //private Constructor so this class can not be instantiated.
    private GetInfo() {
    }


    // To convert String link into Url
    private static URL createUrl(String Url) {
        URL url = null;
        try {
            url = new URL(Url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /* To Make the HTTP connectionand download the JSON Data */
    private static String makeHttpRequest(URL Url) throws IOException {
        String jsonResponse = "";
        if (Url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) Url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {

                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromInputStream(inputStream);
                return jsonResponse;
            }
        } catch (MalformedURLException m) {
            m.getCause().printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return jsonResponse;
    }

    /* Read the JSON data from the Data Obtain through makeHttpRequest()*/
    private static String readFromInputStream(InputStream inputStream) {
        //  InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader bufferedReader = null;
        StringBuilder builder = new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String Line;

            while ((Line = bufferedReader.readLine()) != null) {
                builder.append(Line);
            }
            Log.e("JSON", "  DATA is Received");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }

    /* Read the JSOn objects and set then to List<>*/
    private static List<Countries> jsonParsing(String jsonData) {

        List<Countries> CountriesList = new ArrayList<>();
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

    /* Entry point of the Class. This method called in Countries Loader Class */
    public static List<Countries> fetchCountriesInfo(String jsonUrl) {
        URL JsonURL = createUrl(jsonUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(JsonURL);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // List<Countries> CountriesList=jsonParsing(jsonResponse);
        return jsonParsing(jsonResponse);
    }
}


