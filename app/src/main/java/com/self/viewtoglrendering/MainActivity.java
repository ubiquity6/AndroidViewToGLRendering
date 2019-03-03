package com.self.viewtoglrendering;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.self.viewtoglrendering.cuberenerer.CubeGLRenderer;


public class MainActivity extends AppCompatActivity {

    private GLSurfaceView mGLSurfaceView;
    private GLWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        setContentView(R.layout.activity_main);

        ViewToGLRenderer viewToGlRenderer = new CubeGLRenderer(this);

        mGLSurfaceView = (GLSurfaceView) findViewById(R.id.gl_surface_view);
        mWebView = (GLWebView) findViewById(R.id.web_view);

        mGLSurfaceView.setEGLContextClientVersion(2);
        mGLSurfaceView.setPreserveEGLContextOnPause(true);
        mGLSurfaceView.setRenderer(viewToGlRenderer);

//        mGLSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0); // Alpha used for plane blending.


        mWebView.setViewToGLRenderer(viewToGlRenderer);

        //mWebView.setWebViewClient(new WebViewClient());
        //mWebView.setWebChromeClient(new WebChromeClient());

        this.mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.setBackgroundColor(0x00000000);
            }
        });

        mWebView.setWebContentsDebuggingEnabled(true);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        mWebView.loadUrl("https://demos.littleworkshop.fr/infinitown");
    }


}
