package com.kolaczynski.astroweather;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

public class APIRequestAsync extends Activity {

/*

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_get_android_example);



        saveme.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v)
            {
                //ALERT MESSAGE
                Toast.makeText(getBaseContext(),"Please wait, connecting to server.",Toast.LENGTH_LONG).show();

                try{

                    // URLEncode user defined data

                    String loginValue    = URLEncoder.encode(login.getText().toString(), "UTF-8");
                    String fnameValue  = URLEncoder.encode(fname.getText().toString(), "UTF-8");
                    String emailValue   = URLEncoder.encode(email.getText().toString(), "UTF-8");
                    String passValue    = URLEncoder.encode(pass.getText().toString(), "UTF-8");

                    // Create http cliient object to send request to server

                    HttpClient Client = new DefaultHttpClient();

                    // Create URL string

                    String URL = "/media/webservice/httpget.php?user="+loginValue+"&name="+fnameValue+"&email="+emailValue+"&pass="+passValue;

                    //Log.i("httpget", URL);

                    try
                    {
                        String SetServerString = "";

                        // Create Request to server and get response

                        HttpGet httpget = new HttpGet(URL);
                        ResponseHandler<String> responseHandler = new BasicResponseHandler();
                        SetServerString = Client.execute(httpget, responseHandler);

                        // Show response on activity

                        content.setText(SetServerString);
                    }
                    catch(Exception ex)
                    {
                        content.setText("Fail!");
                    }
                }
                catch(UnsupportedEncodingException ex)
                {
                    content.setText("Fail");
                }
            }
        });
    }
}
*/
}