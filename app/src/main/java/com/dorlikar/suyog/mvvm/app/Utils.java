package com.dorlikar.suyog.mvvm.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static void showToast(Context context, String toasts) {
        final Toast toast = Toast.makeText(context, toasts, Toast.LENGTH_SHORT);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 4000);
    }


    public static boolean emailValidator(final String mailAddress) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(mailAddress);
        return matcher.matches();
    }


    public static void showMessageOKCancel(Context context, String message,
                                           DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    public static void showMessageOK(Context context, String message) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .create()
                .show();
    }

    public static void showpopup(Context context, String title, String message) {
        new AlertDialog.Builder(context).setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .create()
                .show();
    }

    public static void showMessageOKClick(Context context, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .create()
                .show();
    }

    public static void showMessageOKClickNonCancel(Context context, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", okListener)
                .create()
                .show();
    }



    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src.trim());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            LoggerGeneral.info("myBitmap  "+myBitmap);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }



    public static byte[] tobyteArray(Bitmap thumbnail) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // Compress the bitmap to jpeg format and 50% image quality
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] byteArray = stream.toByteArray();
        LoggerGeneral.info("Byte  "+byteArray);
        return byteArray;
    }


//    public static LatLng convertStringToLatLan(String latlan) {
//        String location = latlan.replaceAll("[\\[\\](){}]", "").substring(9);
//        Log.d("SuyogCheck", "setTriggerPosition  " + location.substring(9));
//        String[] latlong = location.split(",");
//        double latitude = Double.parseDouble(latlong[0]);
//        double longitude = Double.parseDouble(latlong[1]);
//        LatLng lanLat = new LatLng(latitude, longitude);
//        return lanLat;
//    }


    public static void openGoogleMapForDirection(String latatitude, String longitude, Context context) {

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("www.google.com")
                .appendPath("maps")
                .appendPath("dir")
                .appendPath("")
                .appendQueryParameter("api", "1")
                .appendQueryParameter("destination", latatitude + "," +longitude);
        String url = builder.build().toString();
        Log.d("Directions", url);
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);
    }



    public static void callNextActivity(Context context, Class aClass, int SPLASH_DISPLAY_LENGTH){

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                context.startActivity(new Intent(context, aClass));
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

//    public static void zoomCameraOnMap(LatLng latlng, float zoom, GoogleMap map) {
//        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latlng, zoom);
//        map.animateCamera(cameraUpdate);
//    }


//  public fun showSnackBarMessage(
//            view: View,
//            message: String,
//            action: String,
//            onClickListener: View.OnClickListener?
//    ) {
//        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
//                .setAction(action, onClickListener).show()
//    }

    public static void hideKeyBoard(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }




    /**
     * CHECK WHETHER INTERNET CONNECTION IS AVAILABLE OR NOT
     */
    public static boolean checkInternetConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connMgr != null) {
            NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

            if (activeNetworkInfo != null) { // connected to the internet
                // connected to the mobile provider's data plan
                if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                    return true;
                } else return activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            }
        }else {
            showToast(context,"Please check internet connection ");
        }
        return false;
    }



    public static File createDirIfNotExists() {

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + "/APAC");

        //File file = new File(Environment.getExternalStorageDirectory(), "/APAC");
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("TravellerLog :: ", "Problem creating Image folder");

            }
        }
        return file;
    }


}

