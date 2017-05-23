package com.example.kh.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MyImage> list1;
    ArrayList<String> array =new ArrayList<String>();
    private MyImage myImage;
    ImageView imageView;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list1 = new ArrayList<MyImage>();
        imageView = (ImageView) findViewById(R.id.imgmain);

        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(imageView);
        spinner = (Spinner) findViewById(R.id.spinner);


            array.add("http://saigonflowers.biz/wp-content/uploads/2016/01/nhung-bo-hoa-hong-dep-nhat-the-gioi-031-13.jpg");
        array.add("https://blog.bizweb.vn/wp-content/uploads/2015/01/kinh-nghiem-ban-hoa-tuoi-vi-sao-lo-von-vao-dip-tet-7.jpg");
        array.add("http://demo3.thietkeweb888.com/dienhoathuha/wp-content/uploads/2016/02/a3-4.jpg");
        array.add("http://cuoihoi365.com.vn/wp-content/uploads/2014/08/hoa-hong-do-cho-co-dau-cam-tay-trong-ngay-cuoi-3.jpg");
        array.add("http://demo3.thietkeweb888.com/dienhoathuha/wp-content/uploads/2016/02/a3-4.jpg");
        String[] a  = {"1","2","3","4","5"};

        for(int i =0; i< array.size() && i< a.length; i++){
            myImage = new MyImage();
            myImage.title = a[i];
            myImage.img = array.get(i);
            list1.add(myImage);
        }

        ListView listView = (ListView) findViewById(R.id.list_view);
        MyListViewAdapter adapter1 = new MyListViewAdapter(this,R.layout.mylistview , list1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
        listView.setAdapter(new MyListViewAdapter(this,R.layout.mylistview , list1));

    }

    class MyListViewAdapter extends ArrayAdapter<MyImage>{
        private static final String TAG ="vo cong vinh" ;
        Context context;
        int resource;
        LayoutInflater inflater;
       ArrayList<MyImage> list = new ArrayList<MyImage>();
        String[] a =new String[]{};

        public MyListViewAdapter(@NonNull Context context, @LayoutRes int resource,ArrayList<MyImage> object) {
            super(context, resource, object);
            this.context  =context;
            this.resource = resource;
            this.list  =object;

        }



        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = convertView;
            ViewHolder holder ;
            if(view==null){
                holder = new ViewHolder();
                inflater = (LayoutInflater)  context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                view =  inflater.inflate(resource, parent, false);
                holder.txt = (TextView) view.findViewById(R.id.txtTitle);
                holder.im = (ImageView) view.findViewById(R.id.img);
                view.setTag(holder);
            } else holder = (ViewHolder) view.getTag();
                holder.txt.setText(list.get(position).getTitle());
            //holder.im.setImageResource(list.get(position));
           // Picasso.with(MainActivity.this).load(list.get(position)).placeholder(R.mipmap.ic_launcher_round).into(holder.im);
            Picasso.with(context).load(Uri.parse( list.get(position).getImg()))
                    .into(holder.im);
            Log.i(TAG, "getView: "+list.get(position));
            return view;

    }
        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
          return getView(position, convertView, parent);
        }

        class ViewHolder{
            public TextView txt;
            public ImageView im;

        }
    }
}
