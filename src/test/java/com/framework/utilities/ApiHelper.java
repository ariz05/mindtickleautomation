package com.framework.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class ApiHelper {
    public HttpURLConnection httpURLConnection;
    static ObjectMapper objectMapper;
    static URL url;
    static OutputStreamWriter outputStreamWriter;
    public String responseString = null;
    public String statusCode = null;

    public String submitPostRequest(String endpoint, String requestString) {

        try {
            url = new URL(endpoint);
            System.out.println("POST URL: " + url.toString());
            ReportListener.saveTextLog("POST URL: " + url.toString());
            httpURLConnection = (HttpURLConnection) url.openConnection();
            objectMapper = new ObjectMapper();
            System.out.println(requestString);
            ReportListener.saveTextLog("Request Payload : " + requestString);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-type", "application/json");

            outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
            outputStreamWriter.write(requestString);
            outputStreamWriter.flush();

            System.out.println(httpURLConnection.getResponseCode());
            ReportListener.saveTextLog("Response Status Code : " + httpURLConnection.getResponseCode());
            System.out.println(httpURLConnection.getResponseMessage());

            if (httpURLConnection.getResponseCode() >= 200 && httpURLConnection.getResponseCode() < 400)
                responseString = readStream(httpURLConnection.getInputStream());
            else
                responseString = readStream(httpURLConnection.getErrorStream());

            System.out.println(responseString);
            ReportListener.saveTextLog("API Response : " + responseString);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return responseString;
    }

    public String submitGetRequest(String endpoint) {

        try {
            url = new URL(endpoint);
            System.out.println("GET URL: " + url.toString());
            ReportListener.saveTextLog("GET URL: " + url.toString());
            httpURLConnection = (HttpURLConnection) url.openConnection();
            objectMapper = new ObjectMapper();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.connect();

            System.out.println(httpURLConnection.getResponseCode());
            ReportListener.saveTextLog("Response Status Code : " + httpURLConnection.getResponseCode());


            if (httpURLConnection.getResponseCode() >= 200 && httpURLConnection.getResponseCode() < 400)
                responseString = readStream(httpURLConnection.getInputStream());
            else
                responseString = readStream(httpURLConnection.getErrorStream());


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(responseString);
        ReportListener.saveTextLog("API Response : " + responseString);

        return responseString;
    }


    public String submitPutRequest(String endpoint, String requestString) {

        try {
            url = new URL(endpoint);
            System.out.println("PUT URL: " + url.toString());
            ReportListener.saveTextLog("PUT URL: " + url.toString());
            httpURLConnection = (HttpURLConnection) url.openConnection();
            objectMapper = new ObjectMapper();

            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("PUT");
            httpURLConnection.setRequestProperty("Content-type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");

            outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
            System.out.println(requestString);
            ReportListener.saveTextLog("Request Payload : " + requestString);
            outputStreamWriter.write(requestString);
            outputStreamWriter.flush();

            System.out.println(httpURLConnection.getResponseCode());
            ReportListener.saveTextLog("Response Status Code : " + httpURLConnection.getResponseCode());

            if (httpURLConnection.getResponseCode() >= 200 && httpURLConnection.getResponseCode() < 400)
                responseString = readStream(httpURLConnection.getInputStream());
            else
                responseString = readStream(httpURLConnection.getErrorStream());


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(responseString);
        ReportListener.saveTextLog("API Response : " + responseString);

        return responseString;
    }


    static String readStream(InputStream stream) {
        StringBuilder response = new StringBuilder();
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

}
