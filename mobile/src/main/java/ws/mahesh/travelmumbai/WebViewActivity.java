package ws.mahesh.travelmumbai;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import ws.mahesh.travelmumbai.R;

public class WebViewActivity extends Activity {
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
            asset=extras.getBoolean("asset");
            location = extras.getString("location");
            if(asset)
                setAssetView();
            else
                setWebView();
        }
    }

    private void setAssetView() {
        getActionBar().setTitle("Travel Mumbai - Rail Map");

        wv.getSettings().setUseWideViewPort(true);
        wv.getSettings().setBuiltInZoomControls(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.loadUrl("file:///android_asset/"+location);
    }

    private void setWebView() {
        wv.loadUrl(location);
    }

}
