package com.vyke.worldcountries;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vyke.worldcountries.GlideApp;


import com.vyke.worldcountries.map.MapActivity;
import com.vyke.worldcountries.time.LocalTime;

import com.android.vyke.worldcountries.R;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


public class CustomAdapter extends ArrayAdapter<Countries> {
    private List<Countries> Clist = new ArrayList<>();
    private ImageView imgIcon;


    public CustomAdapter(Context context, int resource, List<Countries> objects) {
        super(context, resource, objects);
        this.Clist = objects;

    }


    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listview = convertView;

        ViewHolder holder;
        Countries setPosition = Clist.get(position);

        if (listview == null) {
            holder = new ViewHolder();
            listview = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
            listview.setTag(holder);
        } else {
            holder = (ViewHolder) listview.getTag();

        }

        // String flagUrl ="https://www.countries-ofthe-world.com/flags-normal/flag-of-"+ Cname+".png";

        holder.countryName = (TextView) listview.findViewById(R.id.countryname);
        imgIcon = (ImageView) listview.findViewById(R.id.imgIcon);
        holder.mapButton = (ImageView) listview.findViewById(R.id.map_btn);
        holder.countryCapital = (TextView) listview.findViewById(R.id.countrycapital);
        holder.countryRegion = (TextView) listview.findViewById(R.id.region);
        holder.countrySubregion = (TextView) listview.findViewById(R.id.subregion);
        holder.countryArea = (TextView) listview.findViewById(R.id.area);
        holder.countryPopulation = (TextView) listview.findViewById(R.id.ppln);
        holder.countryTimeZone = (TextView) listview.findViewById(R.id.timezones);
        holder.countryCallingCode = (TextView) listview.findViewById(R.id.callingcode);
        holder.countryLanguages = (TextView) listview.findViewById(R.id.langspoke);
        holder.countryBorders = (TextView) listview.findViewById(R.id.shareborder);
        holder.countryCurrency = (TextView) listview.findViewById(R.id.currencies);
        holder.countryDemonym = (TextView) listview.findViewById(R.id.demomyn);
        //   holder.countryAlpha2 = (TextView) listview.findViewById(R.id.alpha2code);
        holder.countryAlpha3 = (TextView) listview.findViewById(R.id.alpha3code);
        holder.countryNativeName = (TextView) listview.findViewById(R.id.nativename);
        holder.textClockTime = (TextView) listview.findViewById(R.id.time);
        holder.date = (TextView) listview.findViewById(R.id.date);
        holder.day = (TextView) listview.findViewById(R.id.day);


        String Cname = setPosition.getName();
        ProgressBar progressBar = (ProgressBar) listview.findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        holder.countryName.setText(Cname);
        holder.countryCapital.setText(setPosition.getCapital());
        holder.countryRegion.setText(setPosition.getRegion().trim());
        holder.countrySubregion.setText("Subregion: " + setPosition.getSubregion());
        holder.countryArea.setText("Area: " + setPosition.getArea()+" km sq.");
        holder.countryPopulation.setText("Population: " + setPosition.getPopulation());


        holder.countryTimeZone.setText("Time Zones: " + setPosition.getTimezones().toString().replace("[", "").replace("]", ""));
        holder.countryCallingCode.setText("Calling Code: " + setPosition.getCallingCodes().toString().replace("[", "").replace("]", ""));

        holder.countryLanguages.setText("Languages: ");
        StringBuilder Bl = new StringBuilder();
        for (Countries.Languages l : setPosition.getLanguages()) {
            Bl.append(l);
        }
        holder.countryLanguages.append(Bl);//+Clist.get(position).getLanguages().toString());

        if (Clist.get(position).getBorders().size() == 0) {
            holder.countryBorders.setText("No Neighbour");
        } else
            holder.countryBorders.setText("Neighbours: " + setPosition.getBorders().toString().replace("[", "").replace("]", ""));

        holder.countryCurrency.setText("Currency: ");
        StringBuffer Bc = new StringBuffer();
        for (Countries.Currency c : setPosition.getCurrencies()) {
            Bc.append(c);
        }
        holder.countryCurrency.append(Bc);//Clist.get(position).getCurrencies().toString());
        holder.countryDemonym.setText("Demonym: " + setPosition.getDemonym());
        //  holder.countryAlpha2.setText(Clist.get(position).getAlpha2Code());
        holder.countryAlpha3.setText("Alpha 3 Code: " + setPosition.getAlpha3Code());
        holder.countryNativeName.setText("Native Name: " + setPosition.getNativeName());
        String flag = setPosition.getFlag();

        holder.mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Mname = Clist.get(position).getName();
                Intent intent = new Intent(getContext(), MapActivity.class);
                intent.putExtra("Mname", Mname);
                intent.setPackage("com.vyke.worldcountries.map");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });

        listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String Cname = Clist.get(position).getName();
                    if (Cname != null) {
                        Intent intent = new Intent(getContext(), WikiWeb.class);
                        intent.putExtra("Cname", Cname);
                        intent.setPackage("com.vyke.worldcountries.map");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getContext().startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), "Cannot Get the Name", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        LocalTime localTime = new LocalTime();

        long localmilli = 0;

        try {
            for (int i = 0; i < setPosition.getTimezones().size(); i++) {
                if (Cname.equalsIgnoreCase("Denmark")) {
                    localmilli = localTime.getLocalTimeOffSet("UTC+01:00");
                } else if (Cname.equalsIgnoreCase("France")) {
                    localmilli = localTime.getLocalTimeOffSet("UTC+02:00");
                } else if (Cname.equalsIgnoreCase("United States of America")) {
                    localmilli = localTime.getLocalTimeOffSet("UTC-04:00");
                } else if (Cname.equalsIgnoreCase("Russian Federation")) {
                    localmilli = localTime.getLocalTimeOffSet("UTC+03:00");
                } else if (Cname.equalsIgnoreCase("Antarctica")) {
                    localmilli = localTime.getLocalTimeOffSet("UTC+12:00");
                } else if (Cname.equalsIgnoreCase("United Kingdom of Great Britain and Northern Ireland")) {
                    localmilli = localTime.getLocalTimeOffSet("UTC+01:00");
                } else if (Cname.equalsIgnoreCase("Australia")) {
                    localmilli = localTime.getLocalTimeOffSet("UTC+10:00");
                } else if (Cname.equalsIgnoreCase("Canada")) {
                    localmilli = localTime.getLocalTimeOffSet("UTC-04:00");
                } else if (Cname.equalsIgnoreCase("New Zealand")) {
                    localmilli = localTime.getLocalTimeOffSet("UTC+12:00");
                } else if (Cname.equalsIgnoreCase("Brazil")) {
                    localmilli = localTime.getLocalTimeOffSet("UTC-03:00");
                } else if (Cname.equalsIgnoreCase("Mexico")) {
                    localmilli = localTime.getLocalTimeOffSet("UTC-05:00");
                } else if (Cname.equalsIgnoreCase("Chile")) {
                    localmilli = localTime.getLocalTimeOffSet("UTC-03:00");
                } else if (Cname.equalsIgnoreCase("Indonesia")) {
                    localmilli = localTime.getLocalTimeOffSet("UTC+07:00");
                } else if (Cname.equalsIgnoreCase("Kiribati")) {
                    localmilli = localTime.getLocalTimeOffSet("UTC-12:00");
                } else if (Cname.equalsIgnoreCase("Democratic Republic of the Congo")) {
                    localmilli = localTime.getLocalTimeOffSet("UTC+01:00");
                } else if (Cname.equalsIgnoreCase("Ecuador")) {
                    localmilli = localTime.getLocalTimeOffSet("UTC-05:00");
                } else if (Cname.equalsIgnoreCase("Federated States of Micronesia")) {
                    localmilli = localTime.getLocalTimeOffSet(("UTC+11:00"));
                } else if (Cname.equalsIgnoreCase("Kazakhstan")) {
                    localmilli = localTime.getLocalTimeOffSet(("UTC+06:00"));
                } else if (Cname.equalsIgnoreCase("Netherlands")) {
                    localmilli = localTime.getLocalTimeOffSet(("UTC+02:00"));
                } else if (Cname.equalsIgnoreCase("Mongolia")) {
                    localmilli = localTime.getLocalTimeOffSet(("UTC+08:00"));
                } else if (Cname.equalsIgnoreCase("Papua New Guinea")) {
                    localmilli = localTime.getLocalTimeOffSet(("UTC+10:00"));
                } else if (Cname.equalsIgnoreCase("Portugal")) {
                    localmilli = localTime.getLocalTimeOffSet(("UTC+01:00"));
                } else if (Cname.equalsIgnoreCase("South Africa")) {
                    localmilli = localTime.getLocalTimeOffSet(("UTC+02:00"));
                } else if (Cname.equalsIgnoreCase("Spain")) {
                    localmilli = localTime.getLocalTimeOffSet(("UTC+02:00"));
                } else if (Cname.equalsIgnoreCase("Ukraine")) {
                    localmilli = localTime.getLocalTimeOffSet("UTC+03:00");
                } else if (Cname.equalsIgnoreCase("United states minor outlying islands")) {
                    localmilli = localTime.getLocalTimeOffSet("UTC+11:00");
                } else {
                    localmilli = localTime.getLocalTimeOffSet(Clist.get(position).getTimezones().toString());
                }
            }
            holder.day.setText(localTime.getDay(localmilli));
            holder.date.setText(localTime.getDate(localmilli));
            holder.textClockTime.setText(localTime.getTime(localmilli));

        } catch (Exception e) {
            e.printStackTrace();
        }


        //  Glide.with(listview).as(SVG.class).load("http://upload.wikimedia.org/wikipedia/commons/e/e8/Svg_example3.svg");
        // mages urls   https://upload.wikimedia.org/wikipedia/en/thumb/4/41/Flag_of_India.svg/150px-Flag_of_India.svg.png

        // String flagbycountry="https://www.countries-ofthe-world.com/flags-normal/flag-of-"+Cname+".png";

        //    GetIcon getIcon=new GetIcon();
        //   getIcon.execute(flag);

        GlideApp.with(listview)
                .as(PictureDrawable.class)
                .load(flag)
                .placeholder(R.mipmap.ic_world_map)
                .error(R.mipmap.ic_launcher_round_red)
                .transition(withCrossFade())
                .listener(new SvgSoftwareLayerSetter())
                .into(imgIcon);


        // GlideApp.with(listview).load().error(R.mipmap.ic_launcher_round_red).into(imgIcon);
        Log.e("FLag", "---Flag SVG Recieved " + Cname);
        progressBar.setVisibility(View.GONE);
        return listview;
    }


    public CustomAdapter setFilter(String text) {
        text = text.toLowerCase();
        CustomAdapter modifiedAdapter = null;
        List<Countries> newList = new ArrayList<>();
        Clist.addAll(newList);
        try {
            if (text != null) {
                for (Countries c : Clist) {
                    String name = c.getName().toLowerCase();
                    if (name.contains(text)) {
                        newList.add(c);
                    }
                    modifiedAdapter = new CustomAdapter(getContext(), R.layout.row, newList);

                }
            } else if (text == null) {
                modifiedAdapter = new CustomAdapter(getContext(), R.layout.row, Clist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        notifyDataSetChanged();
        return modifiedAdapter;
    }

    /*
        class GetIcon extends AsyncTask<String, Void,Bitmap> {


            @Override
            protected Bitmap doInBackground(String... params) {
                HttpURLConnection imageCon = null;

                try {
                    URL url = new URL(params[0]);
                    imageCon = (HttpURLConnection) url.openConnection();
                    imageCon.connect();
                    if (imageCon.getResponseCode() == 200) {
                        Bitmap svg = SVGHelper.noContext().open(url).checkSVGSize().getBitmap();

                        return svg;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (imageCon != null) {
                        imageCon.disconnect();
                    }
                }
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                // progressBar.setVisibility(View.VISIBLE);
                    }

            @Override
            protected void onPostExecute(Bitmap bmp) {
                super.onPostExecute(bmp);
                //   progressBar.setVisibility(View.GONE);
              //     imgIcon.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            //       imgIcon.setImageBitmap(bmp);

            }

        }
    */
    private class ViewHolder {

        TextView countryName;
        TextView countryCapital;
        TextView countryRegion;
        TextView countrySubregion;
        TextView countryArea;
        TextView countryPopulation;
        TextView countryTimeZone;
        TextView countryCallingCode;
        TextView countryLanguages;
        TextView countryBorders;
        TextView countryCurrency;
        TextView countryDemonym;
        //   TextView countryAlpha2;
        TextView countryAlpha3;
        TextView countryNativeName;
        ImageView mapButton;
        TextView textClockTime;
        TextView date;
        TextView day;


    }
}

