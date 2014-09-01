package ws.mahesh.travelmumbai.misc;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;

import ws.mahesh.travelmumbai.R;

public class WebViewActivity extends ActionBarActivity {
    String location;
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        wv = (WebView) findViewById(R.id.webView);
        Bundle extras = getIntent().getExtras();
        Boolean asset;
        if (extras != null) {
            asset = extras.getBoolean("asset");
            location = extras.getString("location");
            if (asset)
                setAssetView();
            else
                setWebView();
        }
    }

    private void setAssetView() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.show();
        actionBar.setTitle("Travel Mumbai - Rail Map");
        wv.getSettings().setUseWideViewPort(true);
        wv.getSettings().setBuiltInZoomControls(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.loadUrl("file:///android_asset/" + location);
    }

    private void setWebView() {
        wv.loadUrl(location);
    }

}
