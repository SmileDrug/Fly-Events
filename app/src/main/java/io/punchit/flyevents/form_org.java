package io.punchit.flyevents;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class form_org extends AppCompatActivity {
    EditText org,name,num,info,tags,local;
    String orgt,namet,numt,infot,tagst,localt;
    RadioButton r1,r2,r3,r4;
    String catagory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_o);

        org = (EditText) findViewById(R.id.org1);
        name = (EditText) findViewById(R.id.org2);
        num = (EditText) findViewById(R.id.org3);
        info = (EditText) findViewById(R.id.org4);
        r1 = (RadioButton) findViewById(R.id.radioButton);
        r2 = (RadioButton) findViewById(R.id.radioButton1);
        r3 = (RadioButton) findViewById(R.id.radioButton2);
        r4 = (RadioButton) findViewById(R.id.radioButton3);

        local = (EditText) findViewById(R.id.org6);
        tags = (EditText) findViewById(R.id.org7);



    }



    public void radiobutton(View view) {
        boolean checked = ((RadioButton) view ).isChecked();

        switch(view.getId()) {
            case R.id.radioButton:
                if (checked){
                    catagory="culture";
                    r2.setChecked(false);
                    r3.setChecked(false);
                    r4.setChecked(false);
                    break;
                }

            case R.id.radioButton1:
                if (checked){
                    r1.setChecked(false);
                    r3.setChecked(false);
                    r4.setChecked(false);
                    catagory="Technical";
                    break;
                }

            case R.id.radioButton2:
                if (checked){
                    r1.setChecked(false);
                    r2.setChecked(false);
                    r4.setChecked(false);
                    catagory="Theme party";
                    break;
                }

            case R.id.radioButton3:
                if (checked){
                    r3.setChecked(false);
                    r2.setChecked(false);
                    r1.setChecked(false);
                    catagory="Others";
                    break;
                }

        }
    }

    public void flyer(View view) {
        orgt = org.getText().toString();
        namet = name.getText().toString();
        numt = num.getText().toString();
        infot = info.getText().toString();
        tagst = tags.getText().toString();
        localt = local.getText().toString();

        ArrayList list = null;
        list.add(orgt);
        list.add(namet);
        list.add(numt);
        list.add(infot);
        list.add(tagst);
        list.add(localt);





        ParseObject obj = new ParseObject("organization");
        ParseUser user = ParseUser.getCurrentUser();
        obj.put("User",user);
        obj.put("Organization_name", orgt);
        obj.put("Organizer_name", namet);
        obj.put("Phone", numt);
        obj.put("Info", infot);
        obj.put("Tag", tagst);
        obj.put("Catagory", catagory);
        obj.put("City", localt);

        obj.saveInBackground();


        AsyncHttpClient client = new AsyncHttpClient();
        client.setConnectTimeout(30000);
        String url = "http://fly-events.herokuapp.com/getImage?url=http://fly-events.herokuapp.com/getOrg?pc=" + orgt + "&providername=" + namet + "&products=" + numt + "&Desp=" + infot + "&Cat=" + catagory + "&area=" + localt + "&Tag=" + tagst;
        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String str = new String(responseBody, "UTF-8");
                    Log.d("Myapp", str);
                    Intent intent = new Intent(form_org.this, image.class);

                    intent.putExtra("url", str);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.d("Myapp", e.toString());
                }
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Myapp", error.toString());
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }

}

