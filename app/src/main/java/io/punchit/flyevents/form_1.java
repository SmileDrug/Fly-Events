package io.punchit.flyevents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ASUS on 31-10-2015.
 */
public class form_1 extends AppCompatActivity {
    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form1);
    }

    public void form_o(View view) {
        Intent intent = new Intent(form_1.this,form_org.class);
        startActivity(intent);
    }

    public void form_p(View view) {
        Intent intent = new Intent(form_1.this,form_pro.class);
        startActivity(intent);
    }
}
