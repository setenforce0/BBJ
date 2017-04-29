package tech.kcode.bbj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = (Button) findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText user = (EditText) findViewById(R.id.usernameText);
                TextView text1 = (TextView) findViewById(R.id.userTest);
                EditText pass = (EditText) findViewById(R.id.passwordText);
                TextView text2 = (TextView) findViewById(R.id.passTest);
                text1.setText(user.getText().toString());
                text2.setText(pass.getText().toString());

            }
        });

    }

}
