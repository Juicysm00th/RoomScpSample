package com.example.roomscpsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewScpActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY =
            "com.example.android.roomwordssample.REPLY";

    private EditText mEditScpView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_scp);
        mEditScpView = findViewById(R.id.edit_scp);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditScpView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String scp = mEditScpView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, scp);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}