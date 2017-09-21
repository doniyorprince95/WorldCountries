package com.vyke.Countries;


import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.vyke.worldcountries.R;
import com.vyke.Countries.map.MapActivity;
import com.vyke.Countries.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<Countries> Clist = new ArrayList<>();

    /* Constructor of the Custom Adapter*/
    public CustomAdapter(List<Countries> objects) {
        this.Clist = objects;

    }

    /* Implement getItemCount() of RecyclerView to get the size of List*/
    @Override
    public int getItemCount() {
        return Clist.size();
    }

    /* OnCreateViewHolder inflate the RecyclerView on the screen*/
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listview = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(listview);
    }

    /* onBindViewHolder set the Data into the Views*/
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        final Countries setPosition = Clist.get(holder.getAdapterPosition());

        // String flagUrl ="https://www.countries-ofthe-world.com/flags-normal/flag-of-"+ Cname+".png";

        String Cname = setPosition.getName();

        holder.progressBar.setVisibility(View.VISIBLE);
        holder.countryName.setText(Cname);
        holder.countryCapital.setText(setPosition.getCapital());
        holder.countryRegion.setText(setPosition.getRegion().trim());
        holder.countrySubregion.setText("Subregion: " + setPosition.getSubregion());
        holder.countryArea.setText("Area: " + setPosition.getArea() + " km sq.");
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


        // On click the list map Button Map Activity Start
        holder.mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Mname = Clist.get(holder.getAdapterPosition()).getName();
                Intent intent = new Intent(holder.itemView.getContext(), MapActivity.class);
                intent.putExtra("Mname", Mname);
                intent.setPackage("com.vyke.worldcountries.map");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.itemView.getContext().startActivity(intent);
            }
        });

        //on click Wikiweb Activity start Wikiweb
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String Cname = Clist.get(holder.getAdapterPosition()).getName();
                    if (Cname != null) {
                        Intent intent = new Intent(holder.itemView.getContext(), WikiWeb.class);
                        intent.putExtra("Cname", Cname);
                        //  intent.setPackage("com.vyke.worldcountries.map");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        holder.itemView.getContext().startActivity(intent);
                    } else {
                        Toast.makeText(holder.itemView.getContext(), "Cannot Get the Name", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sname;
                try {
                    sname = setPosition.getName();

                    if (sname != null) {
                        Intent intent = new Intent(holder.itemView.getContext(), WikiWeb.class);
                        intent.putExtra("Cname", sname);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        holder.itemView.getContext().startActivity(intent);
                    } else {
                        Toast.makeText(holder.itemView.getContext(), "Cannot Get the Name", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
*/

       /* To Show the Time of the the Country w.r.t UTC and Time Zone*/
        LocalTime localTime = new LocalTime();
        long localmilli = 0;
        try {
            // To Set The time zone of Countries in case of multiple timezone. Time zone is of the Capital
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
            // Call the methods of LocalTime Class to set timie into the views
            holder.day.setText(localTime.getDay(localmilli));
            holder.date.setText(localTime.getDate(localmilli));
            holder.textClockTime.setText(localTime.getTime(localmilli));

        } catch (Exception e) {
            e.printStackTrace();
        }

        // mages urls   https://upload.wikimedia.org/wikipedia/en/thumb/4/41/Flag_of_India.svg/150px-Flag_of_India.svg.png
        // String flagbycountry="https://www.countries-ofthe-world.com/flags-normal/flag-of-"+Cname+".png";

        /* Glide Labrary to download and set the Flags SVG into the ImageView*/
        GlideApp.with(holder.itemView)
                .as(PictureDrawable.class)
                .load(flag)
                .placeholder(R.mipmap.ic_world_map)
                .error(R.mipmap.ic_launcher_round_red)
                .transition(withCrossFade())
                .listener(new SvgSoftwareLayerSetter())
                .into(holder.imgIcon);

        // GlideApp.with(listview).load().error(R.mipmap.ic_launcher_round_red).into(imgIcon);
        Log.e("FLag", "---Flag SVG Recieved " + Cname);
        holder.progressBar.setVisibility(View.GONE);

    }


    /* setFilter Called in MainActivity in OnCreateOptionsMenu into the Searchview to show the custom search*/
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
                    modifiedAdapter = new CustomAdapter(newList);

                }
            } // if the search querry is null set complete list
            else if (text == null) {
                modifiedAdapter = new CustomAdapter(Clist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        notifyDataSetChanged();
        return modifiedAdapter;
    }


   /*ViewHolder static class extends Recycler.ViewHolder to Declare and intialize view of the recyclerview*/

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView countryName, countryCapital, countryRegion, countrySubregion;
        TextView countryArea, countryPopulation, countryTimeZone, countryCallingCode, countryLanguages, countryBorders, countryCurrency, countryDemonym;
        //   TextView countryAlpha2;
        TextView countryAlpha3, countryNativeName;
        ImageView imgIcon, mapButton;
        TextView textClockTime, date, day;
        ProgressBar progressBar;

        public ViewHolder(View listview) {
            super(listview);
            progressBar = (ProgressBar) listview.findViewById(R.id.progress);
            countryName = (TextView) listview.findViewById(R.id.countryname);
            imgIcon = (ImageView) listview.findViewById(R.id.imgIcon);
            mapButton = (ImageView) listview.findViewById(R.id.map_btn);
            countryCapital = (TextView) listview.findViewById(R.id.countrycapital);
            countryRegion = (TextView) listview.findViewById(R.id.region);
            countrySubregion = (TextView) listview.findViewById(R.id.subregion);
            countryArea = (TextView) listview.findViewById(R.id.area);
            countryPopulation = (TextView) listview.findViewById(R.id.ppln);
            countryTimeZone = (TextView) listview.findViewById(R.id.timezones);
            countryCallingCode = (TextView) listview.findViewById(R.id.callingcode);
            countryLanguages = (TextView) listview.findViewById(R.id.langspoke);
            countryBorders = (TextView) listview.findViewById(R.id.shareborder);
            countryCurrency = (TextView) listview.findViewById(R.id.currencies);
            countryDemonym = (TextView) listview.findViewById(R.id.demomyn);
            //   holder.countryAlpha2 = (TextView) listview.findViewById(R.id.alpha2code);
            countryAlpha3 = (TextView) listview.findViewById(R.id.alpha3code);
            countryNativeName = (TextView) listview.findViewById(R.id.nativename);
            textClockTime = (TextView) listview.findViewById(R.id.time);
            date = (TextView) listview.findViewById(R.id.date);
            day = (TextView) listview.findViewById(R.id.day);

        }

    }
}

