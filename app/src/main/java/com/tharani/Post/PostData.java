package com.tharani.Post;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
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
import java.util.Calendar;

import javax.net.ssl.HttpsURLConnection;


public class PostData extends AppCompatActivity {
    TextView tvName;
    TextView tvEmail;
    TextView tvPhone;
    TextView tvAddress;
    TextView tvSaleOrder;
    TextView tvOrderValue;
    TextView tvNextAction;
    TextView date;


    Button button;
    Button selectDate;
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
    String fdate;


    Spinner customerTypeSpinner;
    Spinner pCatogerySpinner;
    Spinner designationSpinner;
    Spinner BrandSpinner;



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
       // tvBrand = (EditText) findViewById(R.id.pBrand);
        tvOrderValue = (EditText) findViewById(R.id.input_orderValue);
        tvNextAction = (EditText) findViewById(R.id.input_nextAction);

        customerTypeSpinner = (Spinner) findViewById(R.id.custType);
        pCatogerySpinner = (Spinner) findViewById(R.id.pCatogery);
        designationSpinner = (Spinner) findViewById(R.id.designation);
        BrandSpinner = (Spinner) findViewById(R.id.pBrand);



        List<String> customerType = new ArrayList<String>();
        customerType.add("Customer Type");
        customerType.add("Plumbers");
        customerType.add("E mail");
        customerType.add("Interior Designers ");
        customerType.add("Walkin");
        customerType.add("Phone");
        customerType.add("Builders");
        customerType.add("Marketing");
        customerType.add("Architects");


        List<String> pCatogery = new ArrayList<String>();
        pCatogery.add("Category");
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
        designationlist.add("Designation List");
        designationlist.add("Senior associate");
        designationlist.add("Purchase manager");
        designationlist.add("Site supervisor");
        designationlist.add("Contractor");
        designationlist.add("Senior associate");
        designationlist.add("Owner");

        List<String> pBrandlist = new ArrayList<String>();
        pBrandlist.add("Brand List");
        pBrandlist.add("Aashirwaad, Supreme");
        pBrandlist.add("Viega");
        pBrandlist.add("Geberit");
        pBrandlist.add("Viking");
        pBrandlist.add("Watertec");
        pBrandlist.add("Palladio");
        pBrandlist.add("Piccolo");
        pBrandlist.add("Inax");
        pBrandlist.add("RAK");
        pBrandlist.add("Johnson");
        pBrandlist.add("Nitco");
        pBrandlist.add("Nexion");
        pBrandlist.add("Somany");
        pBrandlist.add("Orient Bell");
        pBrandlist.add("Simpolo");
        pBrandlist.add("Xylos");
        pBrandlist.add("Grohe");
        pBrandlist.add("Jaquar");
        pBrandlist.add("Hindware");
        pBrandlist.add("Alchymi,");
        pBrandlist.add("Glocera");
        pBrandlist.add("Hansgrohe");
        pBrandlist.add("Oyster");
        pBrandlist.add("Toto");
        pBrandlist.add("Nirali,");
        pBrandlist.add("Franke");
        pBrandlist.add("3M");
        pBrandlist.add("Zero B");
        pBrandlist.add("Carysil");
        pBrandlist.add("Grundfos");
        pBrandlist.add("Kajaria");
        pBrandlist.add("Gujarat Tiles");




        // Creating adapter for spinner
        ArrayAdapter<String> custTypeDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, customerType);
        ArrayAdapter<String> pCatogeryDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pCatogery);
        ArrayAdapter<String> pdesignationDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, designationlist);
        ArrayAdapter<String> pBrandlistDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pBrandlist);

        // Drop down layout style - list view with radio button
        custTypeDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pCatogeryDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pdesignationDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pBrandlistDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // attaching data adapter to spinner
        customerTypeSpinner.setAdapter(custTypeDataAdapter);
        pCatogerySpinner.setAdapter(pCatogeryDataAdapter);
        designationSpinner.setAdapter(pdesignationDataAdapter);
        BrandSpinner.setAdapter(pBrandlistDataAdapter);

        System.out.println(  String.valueOf(designationSpinner.getSelectedItem()));
        System.out.println(  String.valueOf(customerTypeSpinner.getSelectedItem()));
        System.out.println(  String.valueOf(pCatogerySpinner.getSelectedItem()));
        System.out.println(  String.valueOf(BrandSpinner.getSelectedItem()));


       selectDate = findViewById(R.id.btnDate);
       date = findViewById(R.id.tvSelectedDate);

       selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(PostData.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "-" + (month + 1) + "-" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = tvName.getText().toString();
                email = tvEmail.getText().toString();
                phone = tvPhone.getText().toString();

                address = tvAddress.getText().toString();
                saleOrder = tvSaleOrder.getText().toString();
                orderValue = tvOrderValue.getText().toString();
                nextAction = tvNextAction.getText().toString();

                designation = String.valueOf(designationSpinner.getSelectedItem());
                customerTypeValue = String.valueOf(customerTypeSpinner.getSelectedItem());
                pCatogeryValue = String.valueOf(pCatogerySpinner.getSelectedItem());
                brand = String.valueOf(BrandSpinner.getSelectedItem());
                fdate=date.getText().toString();
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
                /* bhasavaraj https://script.google.com/macros/s/AKfycbwPHVeFEh_ERQ6SmDKqLKRZj5e1zPhxjsuPNkn6xp5xUVwIjssh/exec
                 * bhagya  https://script.google.com/macros/s/AKfycbwFU4E2maBZrKJEKVz5smeU7TUKmdLOUyin_9hmOG7cF10C6oo/exec
                 * vishnu priya https://script.google.com/macros/s/AKfycbwb6XHa8UstHaX1IT5HtQIVDEP2PYhdtz70YXfGK2_66Ux7XCXn/exec
                 * Bhargavi https://script.google.com/macros/s/AKfycbymG9bSYogjhppS360fXpwK-3KOi39x_PnLLfW84gNDstTalU4/exec
                 * deepak  https://script.google.com/macros/s/AKfycbywx54z2eEOG6zzT_VjgRZ4SoxRwtLMJNltuZPUmxLqgD8nIZcc/exec
                 * Francy https://script.google.com/macros/s/AKfycbz8JrmmXh_p9cKQ0mCGcO6XV2bTop30McoX29stqZT3yXhVrHs/exec
                 * Hari prasad https://script.google.com/macros/s/AKfycbyFbO63k-O-5T02IetrT9brigA13bG3UOM6d6yL6mhnMhpqpFas/exec
                 * Jayanth https://script.google.com/macros/s/AKfycbxBckSmey6i7GICxoIAXL3ZPq36SZBTopb4Ac3IBvFsaeI_crbO/exec
                 * Kumar https://script.google.com/macros/s/AKfycbysMiffqUhLf-fGBxvDh8bmlkxqXCQ2dftZKrqPsszxV1Rp9rA/exec
                 * Neeta https://script.google.com/macros/s/AKfycbys6fChCYVMm-xiW5rWj7hVYMfvIS9weWRHzZfixKZybmiJ8f07/exec
                 * pradhan https://script.google.com/macros/s/AKfycbwmM7VPkP3j-8DGyosL68NqqZIYsNdCJGBJL-9f22NAx3dtUwbY/exec
                 * Raghu https://script.google.com/macros/s/AKfycbwO0NdbwZxBUy3IOC019drSWvTMw379QYwQ3jAa6r_1VqUDocpx/exec
                 * Rajendra https://script.google.com/macros/s/AKfycbwzSxX6TfYXX-agdmgIaqsQMOa76Bk0nKyg-3hTrfWNJx0XBQg/exec
                 * Rajesh https://script.google.com/macros/s/AKfycbya5fkALZcl5RAghI0dybVS1i1sh8wp-CFlsYy31gXnOwEX_qgi/exec
                 * Ravi  https://script.google.com/macros/s/AKfycbyG4QxxkWYHURO7jYrluoDuFkK1W8TvgxfBVGMR_XD4CMCz3FRV/exec
                 * Santhosh https://script.google.com/macros/s/AKfycbxlyrw_jmwvM_4g8nkQFh6gBbvo1mL_mOWnjW4tuzU3Od8OjMc/exec
                 * Somashekar  https://script.google.com/macros/s/AKfycbwCNO90xL9YioKKVBqPrqdsFRoEF0pIdOPN5P2wLthvHsTS6CIX/exec
                 * Subhash https://script.google.com/macros/s/AKfycbxd_R-EGl_BffllsiU8l3An9RwJdgb0SmDQCIkDSnqciv9wsr0/exec
                 * Sudhakar https://script.google.com/macros/s/AKfycbzbWWMffEygjjqBtRs9ba8VV6wapyLH8_qruIQGCINCp9C0eRU/exec
                 * Swapna https://script.google.com/macros/s/AKfycbzg0QyRA652KYLUVFrmzHwE5rPufUyaSSzd_-PPyRYbDmflTDY/exec
                 * Thejas https://script.google.com/macros/s/AKfycbx4ILEus_uM1NYJB4XHCpF6Q5q5Boctl1hR7CAc1xTIp0ApiHU/exec
                 * Mamtha https://script.google.com/macros/s/AKfycbyVR6gWKeiLHDkWJqzGgbexfDPV-23JzFpYbdKzPZ_PyOEBSR4/exec
                 * Karthik https://script.google.com/macros/s/AKfycbw_-3p1CzHG0Ti_OWG8flja-OpyAFrQXmIx16Bc4h3y7c62wWI/exec
                 * Sunil https://script.google.com/macros/s/AKfycbzBadHq0_pNJNmocZO-VqStJ0asfNMqvUl2JnIzeEWCk-mxBxo/exec
                 *
                 *
                 */







                URL url = new URL("https://script.google.com/macros/s/AKfycbzBadHq0_pNJNmocZO-VqStJ0asfNMqvUl2JnIzeEWCk-mxBxo/exec");

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
                postDataParams.put("eName", "");
                postDataParams.put("designation",designation);
                postDataParams.put("customerTypeValue", customerTypeValue);
                postDataParams.put("pCatogeryValue", pCatogeryValue);
                postDataParams.put("fdate", fdate);


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
