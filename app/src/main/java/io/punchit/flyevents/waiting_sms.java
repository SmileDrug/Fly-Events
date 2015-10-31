package io.punchit.flyevents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class waiting_sms extends AppCompatActivity {
    EditText Code;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms);
        Code = (EditText) findViewById(R.id.edittxt);
        text = Code.getText().toString();
    }

    public void next(View view) {
        try {
        Intent intent = getIntent();
        String code = intent.getStringExtra("Code");
        if (code == text) {
            Log.d("Myapp", "Sucess");
        }
        else{
            Toast.makeText(getApplicationContext(), "Sms confirmed", Toast.LENGTH_LONG).show();
        }
    }
    catch(Exception e)
    {
        Log.d("Myapp", e.toString());
    }
    }
}
