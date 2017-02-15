package com.example.webservis;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    private String TAG ="Vik";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncCallWS task = new AsyncCallWS();
        task.execute(); 
    }

    private class AsyncCallWS extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            Log.i(TAG, "doInBackground");
            calculate();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.i(TAG, "onPostExecute");
        }

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Log.i(TAG, "onProgressUpdate");
        }

    }

    public void calculate() 
    {
        String SOAP_ACTION = "http://tempuri.org/CelsiusToFahrenheit";
        String METHOD_NAME = "CelsiusToFahrenheit";
        String NAMESPACE = "http://tempuri.org/";
        String URL = "http://www.w3schools.com/webservices/tempconvert.asmx";

        try { 
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("Celsius", "32");

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport= new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            SoapPrimitive resultString = (SoapPrimitive)soapEnvelope.getResponse();

            Log.i(TAG, "Result Celsius: " + resultString);
        }
        catch(Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }

        SOAP_ACTION = "http://tempuri.org/FahrenheitToCelsius";
        METHOD_NAME = "FahrenheitToCelsius";
        try { 
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("Fahrenheit", "100");

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);
            
            HttpTransportSE transport= new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            SoapPrimitive resultString = (SoapPrimitive)soapEnvelope.getResponse();

            Log.i(TAG, "Result Fahrenheit: " + resultString);
        }
        catch(Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }
    }

}