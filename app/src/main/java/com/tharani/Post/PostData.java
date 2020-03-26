package com.tharani.Post;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.tharani.MainPage;
import com.tharani.R;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class PostData extends AppCompatActivity {
    TextView tvName;
    TextView tvEmail;
    TextView tvPhone;
    TextView tvAddress;
    TextView tvSaleOrder;
    TextView tvBrand;
    TextView tvOrderValue;
    TextView tvNextAction;

    Button button;
    String name;
    String email;
    String phone;
    String address;
    String saleOrder;
    String brand;
    String orderValue;
    String nextAction;

    String eName;
    String customerTypeValue;
    String pCatogeryValue;
    String designation;

    Spinner execSpinner;
    Spinner customerTypeSpinner;
    Spinner pCatogerySpinner;
    Spinner designationSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
        button = (Button) findViewById(R.id.btn_signup);

        tvName = (EditText) findViewById(R.id.input_name);
        tvEmail = (EditText) findViewById(R.id.input_email);
        tvPhone= (EditText) findViewById(R.id.input_phone);

        tvAddress = (EditText) findViewById(R.id.input_address);
        tvSaleOrder = (EditText) findViewById(R.id.input_sOrder);
        tvBrand = (EditText) findViewById(R.id.input_brand);
        tvOrderValue = (EditText) findViewById(R.id.input_orderValue);
        tvNextAction = (EditText) findViewById(R.id.input_nextAction);

        execSpinner = (Spinner) findViewById(R.id.exeName);
        customerTypeSpinner = (Spinner) findViewById(R.id.custType);
        pCatogerySpinner = (Spinner) findViewById(R.id.pCatogery);
        designationSpinner = (Spinner) findViewById(R.id.designation);


        // Spinner Drop down elements
        List<String> execNames = new ArrayList<String>();
        execNames.add("Basavaraj");
        execNames.add("Jayanth");
        execNames.add("Ravi");
        execNames.add("Kumar");
        execNames.add("Rajesh");
        execNames.add("Hariprasad");
        execNames.add("Somashekha");
        execNames.add("Sudhakar");
        execNames.add("Bhagya");
        execNames.add("Vishnu Priya");

        List<String> customerType = new ArrayList<String>();
        customerType.add("Plumbers");
        customerType.add("E mail");
        customerType.add("Interior Designers ");
        customerType.add("Walkin");
        customerType.add("Phone");
        customerType.add("Builders");
        customerType.add("Marketing");
        customerType.add("Architects");


        List<String> pCatogery = new ArrayList<String>();
        pCatogery.add("1 Plumbing items");
        pCatogery.add("2 C.P. Faucets");
        pCatogery.add("3 Sanitaryware");
        pCatogery.add("4 Tube and Wellness");
        pCatogery.add("5 Tiles and Stone cladding");
        pCatogery.add("6 Wooden Floorings");
        pCatogery.add("7 Shower Enclosures");
        pCatogery.add("8 Bathroom Vanity");
        pCatogery.add("9 Kitchen Sinks");
        pCatogery.add("10 Water Heaters");
        pCatogery.add("11 Glass Blocks & Bathroom Mirrors");
        pCatogery.add("12 Pressure Pumps & Water softeners");
        pCatogery.add("13 Bathroom Accessories");



        List<String> designationlist = new ArrayList<String>();
        designationlist.add("Senior associate");
        designationlist.add("Purchase manager");
        designationlist.add("Site supervisor");
        designationlist.add("Contractor");
        designationlist.add("Senior associate");
        designationlist.add("Owner");


        // Creating adapter for spinner
        ArrayAdapter<String> execuDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, execNames);
        ArrayAdapter<String> custTypeDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, customerType);
        ArrayAdapter<String> pCatogeryDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pCatogery);
        ArrayAdapter<String> pdesignationDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, designationlist);

        // Drop down layout style - list view with radio button
        execuDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        custTypeDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pCatogeryDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pdesignationDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // attaching data adapter to spinner
        execSpinner.setAdapter(execuDataAdapter);
        customerTypeSpinner.setAdapter(custTypeDataAdapter);
        pCatogerySpinner.setAdapter(pCatogeryDataAdapter);
        designationSpinner.setAdapter(pdesignationDataAdapter);

        System.out.println(  String.valueOf(execSpinner.getSelectedItem()));
        System.out.println(  String.valueOf(designationSpinner.getSelectedItem()));
        System.out.println(  String.valueOf(customerTypeSpinner.getSelectedItem()));
        System.out.println(  String.valueOf(pCatogerySpinner.getSelectedItem()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = tvName.getText().toString();
                email = tvEmail.getText().toString();
                phone = tvPhone.getText().toString();

                address = tvAddress.getText().toString();
                saleOrder = tvSaleOrder.getText().toString();
                brand = tvBrand.getText().toString();
                orderValue = tvOrderValue.getText().toString();
                nextAction = tvNextAction.getText().toString();

                eName = String.valueOf(execSpinner.getSelectedItem());
                designation = String.valueOf(designationSpinner.getSelectedItem());
                customerTypeValue = String.valueOf(customerTypeSpinner.getSelectedItem());
                pCatogeryValue = String.valueOf(pCatogerySpinner.getSelectedItem()) ;

                new SendRequest().execute();
                Intent intent = new Intent(getApplicationContext(), MainPage.class);
                startActivity(intent);
            }
        });

    }


    public class SendRequest extends AsyncTask<String, Void, String> {
        protected void onPreExecute() {
        }

        protected String doInBackground(String... arg0) {

            try {
                //Change your web app deployed URL or u can use this for attributes (name, country)
                URL url = new URL("https://script.google.com/macros/s/AKfycbz7tQw1SI_JTr3B8i12cDw7dsnSqQNxkBbghl2Hec2lpoqV4hmJ/exec");

                JSONObject postDataParams = new JSONObject();
                String id = "Items";

                postDataParams.put("action", "addItem");
                postDataParams.put("name", name);
                postDataParams.put("email", email);
                postDataParams.put("phone",phone);
                postDataParams.put("address", address);
                postDataParams.put("saleOrder", saleOrder);
                postDataParams.put("brand", brand);
                postDataParams.put("saleOrder", saleOrder);
                postDataParams.put("nextAction",nextAction);
                postDataParams.put("orderValue", orderValue);
                postDataParams.put("eName", eName);
                postDataParams.put("designation",designation);
                postDataParams.put("customerTypeValue", customerTypeValue);
                postDataParams.put("pCatogeryValue", pCatogeryValue);


                Log.e("params", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), result,
                    Toast.LENGTH_LONG).show();

        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }
}
