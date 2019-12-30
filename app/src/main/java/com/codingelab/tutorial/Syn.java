package com.codingelab.tutorial;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by xbcis on 22/05/16.
 */
public class Syn extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String ... params) {
        if(params.length>0){
            if(params[0].equalsIgnoreCase("syn")){
                onSyn();
            }else if(params[0].equalsIgnoreCase("insert")){
                return onInsert(params);
            }
            else if (params[0].equalsIgnoreCase("update")){
                return onupdate(params);
            }

            else if (params[0].equalsIgnoreCase("delete")){
                return onDelete(params);
            }

        }
        return null;
    }
    private String onInsert(String ... params){
        String phpPageULR="http://172.20.10.11:8888/sqli/mysql_write.php";
        try {
            // preparing the URL for the connection
            URL url=new URL(phpPageULR);
            // open a channel between the client(android device) and the server (PHP)
            HttpURLConnection channel=(HttpURLConnection) url.openConnection();
            // specify what do you need post or get method
            channel.setRequestMethod("POST");
            channel.setDoOutput(true);
            // create a sub-channel inside the channel to specify weither you want to write or read
            // output means write, input means read
            OutputStream subChannel=channel.getOutputStream();
            // create a pen to write in a specific information and in which language should this pen write.
            OutputStreamWriter pen=new OutputStreamWriter(subChannel, StandardCharsets.UTF_8);
            // create a object to start write the information
            BufferedWriter student =new BufferedWriter(pen);
            // information to write
            String name = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
            String phone = URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");
            String email = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8");
            String information=name+"&"+phone+"&"+email;
            // student will start writing the information
            student.write(information);
            // student will push the information from the client side to the server side
            student.flush();
            // student finished his job
            student.close();
            System.out.println(params[1]);
            // closing the sub-channel
            subChannel.close();
            InputStream serverResponse = channel.getInputStream();
            serverResponse.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Insert data to MySQL";
    }

    private String onupdate(String ... params){
        String phpPageULR="http://172.20.10.11:8888/sqli/updatephp.php";
        try {
            // preparing the URL for the connection
            URL url=new URL(phpPageULR);
            // open a channel between the client(android device) and the server (PHP)
            HttpURLConnection channel=(HttpURLConnection) url.openConnection();
            // specify what do you need post or get method
            channel.setRequestMethod("POST");
            channel.setDoOutput(true);
            // create a sub-channel inside the channel to specify weither you want to write or read
            // output means write, input means read
            OutputStream subChannel=channel.getOutputStream();
            // create a pen to write in a specific information and in which language should this pen write.
            OutputStreamWriter pen=new OutputStreamWriter(subChannel, StandardCharsets.UTF_8);
            // create a object to start write the information
            BufferedWriter student =new BufferedWriter(pen);
            // information to write
            String id = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
            String name = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");
            String phone = URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8");
            String email = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(params[4], "UTF-8");
            String information=id+"&"+name+"&"+phone+"&"+email;
            // student will start writing the information
            student.write(information);
            // student will push the information from the client side to the server side
            student.flush();
            // student finished his job
            student.close();
            System.out.println(params[1]);
            // closing the sub-channel
            subChannel.close();
            InputStream serverResponse = channel.getInputStream();
            serverResponse.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Insert data to MySQL";
    }

    private String onDelete(String ... params){
        String phpPageULR="http://172.20.10.11:8888/sqli/DELETOS.php";
        try {
            // preparing the URL for the connection
            URL url=new URL(phpPageULR);
            // open a channel between the client(android device) and the server (PHP)
            HttpURLConnection channel=(HttpURLConnection) url.openConnection();
            // specify what do you need post or get method
            channel.setRequestMethod("POST");
            channel.setDoOutput(true);
            // create a sub-channel inside the channel to specify weither you want to write or read
            // output means write, input means read
            OutputStream subChannel=channel.getOutputStream();
            // create a pen to write in a specific information and in which language should this pen write.
            OutputStreamWriter pen=new OutputStreamWriter(subChannel, StandardCharsets.UTF_8);
            // create a object to start write the information
            BufferedWriter student =new BufferedWriter(pen);
            // information to write
            String id = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");

            String information=id;
            // student will start writing the information
            student.write(information);
            // student will push the information from the client side to the server side
            student.flush();
            // student finished his job
            student.close();
            System.out.println(params[1]);
            // closing the sub-channel
            subChannel.close();
            InputStream serverResponse = channel.getInputStream();
            serverResponse.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Delete  data From MySQL";
    }


    private void onSyn(){
        System.out.println(" INSERTING DATA insertin data");
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}

