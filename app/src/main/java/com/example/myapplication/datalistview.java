package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.myapplication.ui.gallery.GalleryFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class datalistview extends AppCompatActivity {

    private ListView lv;

    String userid, id, title, completed;
    TextView a,b;
    Button ref;
    //json url
    private static String JSON_URL="https://run.mocky.io/v3/97d2b068-5f26-4643-9a62-cdd0391decbf";

    //create array (hashmap) named friendlist
    ArrayList<HashMap<String, String>> friendslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datalistview);

        friendslist=new ArrayList<>();
        lv=findViewById(R.id.listview);
        ref = (Button) findViewById(R.id.refresh);



        //when you click on list view, mainAct2 open and it will display the data of the title
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long i) {
                Intent intent = new Intent(getBaseContext(), MainActivity2.class);
                intent.putExtra("userId", userid );
                intent.putExtra("id", id );
                intent.putExtra("completed", completed);
                startActivity(intent);

            }
        });

        //refresh button: reopen menu activity t orefresh it.
        ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent o = new Intent(datalistview.this, datalistview.class);
                startActivity(o);
                finish();
            }
        });


        //call function
        GetData getData = new GetData();
        getData.execute();





    }

    public class GetData extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String current="";

            //this code is to connect android studio to connect json url
            try {
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(in);

                    int data = isr.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isr.read();

                    }
                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return current;

        }

        @SuppressLint("ResourceAsColor")
        @Override
        protected void onPostExecute(String s) {

            try {
                //get data from json link
                //object
                JSONObject jsonObject = new JSONObject(s);
                //array
                JSONArray jsonArray = jsonObject.getJSONArray("Friends");
                //for loop to pass on all data exist
                for (int i =0; i<=jsonArray.length(); i++){
                    //object
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    //get data
                    userid =jsonObject1.getString("userId");
                    id= jsonObject1.getString("id");
                    title=jsonObject1.getString("title");
                    completed=jsonObject1.getString("completed");


                    //hashmap
                    HashMap<String, String> friends= new HashMap<>();

                    //put data in hashmap
                    friends.put("userId", userid);
                    friends.put("id", id);
                    friends.put("title", title);
                    friends.put("completed", completed);

                    //add data to the array (friendslist)
                    friendslist.add(friends);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            //display the result

            //set data in textview
            ListAdapter adapter= new SimpleAdapter(
                    datalistview.this,friendslist,R.layout.row_layout,
                    new String[] {"userId", "id", "title", "completed"},
                    new int[] {R.id.textView, R.id.textView2, R.id.textView3, R.id.textView4});

            lv.setAdapter(adapter);
        }


    }
}