package com.apress.aviplayer;

import java.io.IOException;

//import android.R;
import android.app.AlertDialog;
import android.app.Activity;

public abstract class AbstractPlayerActivity extends Activity {
    public static final String EXTRA_FILE_NAME = "com.apress.aviplayer.EXTRA_FILE_NAME";
    
    protected long avi = 0;
    
    protected void onStart() {
	super.onStart();
	
	try {
	    avi = open(getFileName());
	} catch( IOException e ) {
	    new AlertDialog.Builder(this)
	    		.setTitle(R.string.error_alert_title)
	    		.setMessage(e.getMessage())
	    		.show();
	}
    }
    
    protected void onStop() {
	super.onStop();
	
	if( 0 != avi ) {
	    close(avi);
	    avi = 0;
	}
    }
    
    protected String getFileName() {
	return getIntent().getExtras().getString(EXTRA_FILE_NAME);
    }
    
    protected native static long open(String fileName) throws IOException;
    
    protected native static int getWidth(long avi);
    
    protected native static int getHeight(long avi);
    
    protected native static double getFrameRate(long avi);
    
    protected native static void close(long avi);
    
    static {
	System.loadLibrary("AVIPlayer");
    }

}
