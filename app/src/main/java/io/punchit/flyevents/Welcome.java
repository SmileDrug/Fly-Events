package io.punchit.flyevents;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.parse.ParseUser;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;


public class Welcome extends AppCompatActivity {
    EditText mobileno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_view);
        mobileno = (EditText) findViewById(R.id.editText);

    }

    public void next(View view) {
        ParseUser usr = ParseUser.getCurrentUser();
        String mobileNumber = mobileno.getText().toString();
        usr.put("Phone",mobileNumber);
        usr.saveInBackground();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://192.168.137.245:5000/mobileConfirmation?phoneno=" + mobileNumber, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String str = new String(responseBody, "UTF-8");
                    Log.d("Myapp", str);
                    Intent intent=new Intent(Welcome.this,waiting_sms.class);
                    intent.putExtra("Code",str.toString());
                    startActivity(intent);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                Log.d("Myapp",responseBody.toString());

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Myapp",error.toString());
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });

    }
}
