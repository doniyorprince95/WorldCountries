package com.android.vyke.worldcountries;


import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
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


import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder;
import com.scand.svg.SVGHelper;
import com.scand.svg.parser.SVGParser;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


public class CustomAdapter extends ArrayAdapter<Countries> {
    private List<Countries> Clist = new ArrayList<>();
    private ImageView imgIcon;




    public CustomAdapter(Context context, int resource, List<Countries> objects) {
        super(context, resource, objects);
        this.Clist = objects;

    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listview = convertView;

        ViewHolder holder;
      Countries setPosition=Clist.get(position);

        if (listview == null) {
            // listview.setTag(holder);
            holder = new ViewHolder();
            listview = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
            listview.setTag(holder);



        } else {
            holder = (ViewHolder) listview.getTag();

        }


        // TextView countryFlag;

        //  progressBar=(ProgressBar)listview.findViewById(R.id.progress);


        // String flagUrl ="https://www.countries-ofthe-world.com/flags-normal/flag-of-"+ Cname+".png";


        //    new GetIcon().execute(flagUrl);
        //


        holder.countryName = (TextView) listview.findViewById(R.id.countryname);
        imgIcon = (ImageView) listview.findViewById(R.id.imgIcon);

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


        String Cname = setPosition.getName();
        ProgressBar progressBar = (ProgressBar) listview.findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        holder.countryName.setText(Cname);
        holder.countryCapital.setText(setPosition.getCapital());
        holder.countryRegion.setText(setPosition.getRegion().trim());
        holder.countrySubregion.setText("Subregion: " + setPosition.getSubregion());
        holder.countryArea.setText("Area: " + setPosition.getArea());
        holder.countryPopulation.setText("Population: " +setPosition.getPopulation());


        holder.countryTimeZone.setText("Time Zones: " + setPosition.getTimezones().toString().replace("[", "").replace("]", ""));
        holder.countryCallingCode.setText("Calling Code: " + setPosition.getCallingCodes().toString().replace("[", "").replace("]", ""));

        holder.countryLanguages.setText("Languages Most Spoken: ");
        StringBuilder Bl = new StringBuilder();
        for (Countries.Languages l : setPosition.getLanguages()) {
            Bl.append(l);
        }
        holder.countryLanguages.append(Bl);//+Clist.get(position).getLanguages().toString());

        if (Clist.get(position).getBorders().size() == 0) {
            holder.countryBorders.setText("Share Borders with None ");
        } else
            holder.countryBorders.setText("Share Borders with: " + setPosition.getBorders().toString().replace("[", "").replace("]", ""));

        holder.countryCurrency.setText("Currency: ");
        StringBuffer Bc = new StringBuffer();
        for (Countries.Currency c : setPosition.getCurrencies()) {
            Bc.append(c);
        }
        holder.countryCurrency.append(Bc);//Clist.get(position).getCurrencies().toString());
        holder.countryDemonym.setText("Demonym: " + setPosition.getDemonym());
        //  holder.countryAlpha2.setText(Clist.get(position).getAlpha2Code());
        holder.countryAlpha3.setText("Alpha 3 Code: " + setPosition.getAlpha3Code());
        holder.countryNativeName.setText("Natives Name: " +setPosition.getNativeName());
        String flag = setPosition.getFlag();




        //  Glide.with(listview).as(SVG.class).load("http://upload.wikimedia.org/wikipedia/commons/e/e8/Svg_example3.svg");
        // mages urls   https://upload.wikimedia.org/wikipedia/en/thumb/4/41/Flag_of_India.svg/150px-Flag_of_India.svg.png

        // String flagbycountry="https://www.countries-ofthe-world.com/flags-normal/flag-of-"+Cname+".png";

       //
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
        Log.e("FLag","---Flag SVG Recieved");
        progressBar.setVisibility(View.GONE);



        return listview;
    }


    public CustomAdapter setFilter(String text) {
        text = text.toLowerCase();
        CustomAdapter modifiedAdapter=null;
        List<Countries> newList = new ArrayList<>();
        Clist.addAll(newList);
        if(text!=null) {
            for (Countries c : Clist) {
                String name = c.getName().toLowerCase();
                if (name.contains(text)) {
                    newList.add(c);

                }
                modifiedAdapter = new CustomAdapter(getContext(), R.layout.row, newList);
            }
        }else
            if (text==null){
                modifiedAdapter = new CustomAdapter(getContext(), R.layout.row, Clist);
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



//            imgIcon.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
  //          imgIcon.setImageBitmap(bmp);


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



    }


}

