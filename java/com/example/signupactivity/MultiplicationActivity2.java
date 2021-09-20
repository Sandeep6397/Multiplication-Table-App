package com.example.signupactivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MultiplicationActivity2 extends AppCompatActivity implements View.OnClickListener{
    EditText editText;
    Button button;
    TextView result;
    int ans = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_multiplication2);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        result = (TextView) findViewById(R.id.textView);


        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button:
                StringBuffer buffer = new StringBuffer();


                String fs = editText.getText().toString();


                int n = Integer.parseInt(fs);
                buffer.append("\n");

                for (int i = 1; i <= 10; i++) {
                    ans = (i * n);
                    buffer.append("   "+n + " X " + i
                            + " = " + ans + "\n\n");
                }
                buffer.append("\n");
                result.setText(buffer);
                break;
        }
    }

}
