package com.assignment.pawan.bwealthy.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.InputStream;
import java.io.OutputStream;

public class Util {

    public Util() {

    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {

            NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null
                    && activeNetwork.isConnectedOrConnecting();
            return isConnected;

        }
        return false;

    }

    public static void showMessesBox(Context context, int heading, int msg,
                                     DialogInterface.OnClickListener clickListener) {

        AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(heading));
        builder.setMessage(context.getResources().getString(msg));

        builder.setPositiveButton("OK", clickListener);

        AlertDialog alert = builder.create();
        alert.setCancelable(false);
        if (!((Activity) context).isFinishing())
            alert.show();
    }

    public static void showMessesBox(Context context, int heading, int msg,
                                     DialogInterface.OnClickListener positiveListener,
                                     DialogInterface.OnClickListener negativeListener) {

        AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(heading));
        builder.setMessage(context.getResources().getString(msg));

        builder.setPositiveButton("OK", positiveListener);
        builder.setNegativeButton("Cancel", negativeListener);

        AlertDialog alert = builder.create();
        alert.setCancelable(false);
        if (!((Activity) context).isFinishing())
            alert.show();
    }

    public static void showMessesBox(Context context, int heading, int msg,
                                     DialogInterface.OnClickListener positiveListener,
                                     DialogInterface.OnClickListener negativeListener, String okString,
                                     String cancelString) {

        AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(heading));
        builder.setMessage(context.getResources().getString(msg));

        builder.setPositiveButton(okString, positiveListener);
        builder.setNegativeButton(cancelString, negativeListener);

        AlertDialog alert = builder.create();
        alert.setCancelable(false);
        if (!((Activity) context).isFinishing())
            alert.show();
    }


    public static void showMessesBox(Context context, String heading,
                                     String msg,
                                     DialogInterface.OnClickListener clickListener) {

        AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(context);
        builder.setTitle(heading);
        builder.setMessage(msg);
        builder.setPositiveButton("OK", clickListener);
        AlertDialog alert = builder.create();
        alert.setCancelable(false);
        if (!((Activity) context).isFinishing())
            if (!((Activity) context).isFinishing())
                alert.show();

    }

    public static void showMessesBox(Context context, String heading,
                                     String msg, DialogInterface.OnClickListener positiveListener,
                                     DialogInterface.OnClickListener negativeListener, String okString,
                                     String cancelString) {

        AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(context);
        builder.setTitle(heading);
        builder.setMessage(msg);

        builder.setPositiveButton(okString, positiveListener);
        builder.setNegativeButton(cancelString, negativeListener);

        AlertDialog alert = builder.create();
        alert.setCancelable(false);
        if (!((Activity) context).isFinishing())
            if (!((Activity) context).isFinishing())
                alert.show();
    }

    public static String toSentenceCase(String str) {
        StringBuilder txt = new StringBuilder(str);
        int pos = -1;
        do {
            txt.replace(pos + 1, pos + 2, txt.substring(pos + 1, pos + 2).toUpperCase());
            pos = txt.indexOf(".", pos) + 1;
        }
        while (pos > 0 && pos < txt.length());
        return txt.toString();
    }

}
