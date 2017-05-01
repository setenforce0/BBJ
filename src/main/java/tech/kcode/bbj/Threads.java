package tech.kcode.bbj;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class Threads extends AppCompatActivity {

    public String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threads);

        final FloatingActionButton button = (FloatingActionButton) findViewById(R.id.refreshButton);

        JSONObject jsonData = new JSONObject();
        try {
            jsonData.put("include_op", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        postRequest("thread_index", jsonData);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TextView threadText = (TextView) findViewById(R.id.threadText);
                String[] titles = {};
                try {
                    titles = getThreadTitles(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("BBJ", Arrays.toString(titles));
                for (int i = 0; i < titles.length; i++) {
                    //threadText.append(titles[i] + "\n");
                }
                ListView threadList = (ListView) findViewById(R.id.threadList);
                try {
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), R.layout.singlerow, R.id.test, getThreadTitles(result));
                    threadList.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                button.performClick();
            }
        }, 500);


    }

    public void postRequest(String endpoint, JSONObject MyData) {
        //final TextView text = (TextView)findViewById(R.id.resultText);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://45.63.79.85/api/" + endpoint;
        final JSONObject jsonBody = MyData;
        final String mRequestBody = jsonBody.toString();
        //text.setText(mRequestBody);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOG_VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");

                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {

                    switch (responseString = String.valueOf(response.statusCode)) {
                    }

                }
                try {
                    //TextView threadText = (TextView) findViewById(R.id.threadText);
                    String r = new String(response.data, "UTF-8");
                    result = r;
                    Log.d("BBJ", result);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        requestQueue.add(stringRequest);

    }

    public String[] getThreadTitles(String result) throws JSONException {
        JSONObject jsonObj = new JSONObject(result);
        Log.d("BBJ", jsonObj.toString());
        JSONArray jsonResponseData = jsonObj.getJSONArray("data");
        String[] titles = new String[jsonResponseData.length()];
        for (int i = 0; i < jsonResponseData.length(); i++) {
            JSONObject thing = jsonResponseData.getJSONObject(i);
            String title = thing.get("title").toString();
            titles[i] = title;
        }
        return titles;
    }

}

