package tech.kcode.bbj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class threadReplies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_replies);
        String s = getIntent().getStringExtra("threadTitle");
        final TextView title = (TextView) findViewById(R.id.threadTitle);
        title.setText(s);
    }
}
