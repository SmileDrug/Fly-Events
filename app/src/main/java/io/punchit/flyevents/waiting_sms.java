package io.punchit.flyevents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class waiting_sms extends AppCompatActivity {
    EditText Code;
    String text;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms);

        Code = (EditText) findViewById(R.id.edittxt);
        btn = (Button) findViewById(R.id.button3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {Intent intent = getIntent();
                    String code = intent.getStringExtra("Code");
                    text = Code.getText().toString();
                    if (code.equals(text)) {
                        Log.d("Myapp", "Sucess");
                        Toast.makeText(getApplicationContext(), "Sms confirmed", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(waiting_sms.this,form_1.class);
                        startActivity(intent1);
                    }
                    else{
                        Log.d("MyApp",text);
                    }
                }
                catch(Exception e)
                {
                    Log.d("Myapp", e.toString());
                }

            }
        });
    }


}
