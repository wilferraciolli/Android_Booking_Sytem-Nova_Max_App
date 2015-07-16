package com.wiltech.novamaxapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;


import com.wiltech.novamaxapp.dummy.DummyContent;

public class ProductDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    private DummyContent.DummyItem mItem;

    public ProductDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {

            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_detail, container, false);

        // Show the content as text in a web View.
        if (mItem != null) {
            //display web view content to load the web sites
            ((WebView)rootView.findViewById(R.id.webView)).loadUrl(mItem.url);
            /*
             WebView view = (WebView) rootView.findViewById(R.id.detail_area);
            view.setWebViewClient(new WebViewClient());
            view.loadUrl(mItem.url);
             */
        }

        return rootView;
    }
}
