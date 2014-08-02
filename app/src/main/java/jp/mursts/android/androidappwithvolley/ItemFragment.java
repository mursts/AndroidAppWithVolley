package jp.mursts.android.androidappwithvolley;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import jp.mursts.android.androidappwithvolley.models.LWWS;


public class ItemFragment extends ListFragment {

    private static final String TAG = ItemFragment.class.getSimpleName();
    private static final String REQUEST_URL =
            "http://weather.livedoor.com/forecast/webservice/json/v1?city=230010";

    private OnFragmentInteractionListener mListener;

    private RequestQueue mRequestQueue;

    private LWWS mLWWS;

    // TODO: Rename and change types of parameters
    public static ItemFragment newInstance() {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRequestQueue.add(new JsonObjectRequest(Request.Method.GET, REQUEST_URL,
                null, mSuccessListener, mErrorListener));

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement OnFragmentInteractionListener");
        }

        mRequestQueue = Volley.newRequestQueue(activity);
    }

    Response.Listener<JSONObject> mSuccessListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            Log.d(TAG, response.toString());
            Gson gson = new Gson();
            mLWWS = gson.fromJson(response.toString(), LWWS.class);

            setListAdapter(new ArrayAdapter<LWWS.Forecasts>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, mLWWS.getForecasts()));

        }
    };

    Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(TAG, error.toString());
        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (null != mListener) {
            String temperatures =
                mLWWS.getForecasts().get(position).getTemperatures();
            mListener.onFragmentInteraction(temperatures);
        }
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String id);
    }

}
