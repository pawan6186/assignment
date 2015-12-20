package com.assignment.pawan.bwealthy.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.assignment.pawan.bwealthy.AppController;
import com.assignment.pawan.bwealthy.R;
import com.assignment.pawan.bwealthy.adapters.WordListAdapter;
import com.assignment.pawan.bwealthy.models.BaseWrapper;
import com.assignment.pawan.bwealthy.models.Word;
import com.assignment.pawan.bwealthy.responsehandlers.BaseParser;
import com.assignment.pawan.bwealthy.responsehandlers.ParserFactory;
import com.assignment.pawan.bwealthy.responsehandlers.WordParser;
import com.assignment.pawan.bwealthy.util.PreferenceManager;
import com.assignment.pawan.bwealthy.util.URLHelper;
import com.assignment.pawan.bwealthy.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {


    @Bind(R.id.word_list)
    RecyclerView mWordRV;
    @Bind(R.id.app_bar)
    Toolbar mToolbar;
    private WordListAdapter adapter;
    private List<Word> words;
    private BaseParser parser;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = this;
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        words = new ArrayList<Word>();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mWordRV.setLayoutManager(new LinearLayoutManager(context));
        getData();


    }

    private void getData() {


        parser = ParserFactory.getParser(WordParser.class);
        if (PreferenceManager.isExist(context, PreferenceManager.WORD_STORAGE)) {
            setDataToList(PreferenceManager.getStringPreference(context, PreferenceManager.WORD_STORAGE));
        } else {
            if (!Util.isOnline(context)) {

                Util.showMessesBox(context, R.string.ok, R.string.no_internate_connection, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

                return;
            }
            final ProgressDialog pDialog = new ProgressDialog(context);
            pDialog.setMessage("Loading...");
            pDialog.show();
            String tag_json_obj = "json_obj_req";
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                    URLHelper.getAPIEndpointURL(URLHelper.WORD_LIST),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d(AppController.TAG, response.toString());
                            PreferenceManager.setStringPreference(context, PreferenceManager.WORD_STORAGE, response.toString());
                            setDataToList(PreferenceManager.getStringPreference(context, PreferenceManager.WORD_STORAGE));
                            pDialog.hide();
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(AppController.TAG, "Error: " + error.getMessage());
                    // hide the progress dialog
                    pDialog.hide();
                }
            });

            AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
        }


    }

    private void setDataToList(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("words");
            List<? extends BaseWrapper> list = parser.getParsedData(jsonArray.toString());
            words = (List<Word>) list;

            for (int i = 0; i < words.size(); i++) {
                if (words.get(i).getRatio() < 0) {
                    words.remove(i);
                }
            }

            adapter = new WordListAdapter(context, words);
            mWordRV.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
