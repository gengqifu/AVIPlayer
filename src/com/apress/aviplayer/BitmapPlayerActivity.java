package com.apress.aviplayer;

import java.util.concurrent.atomic.AtomicBoolean;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class BitmapPlayerActivity extends AbstractPlayerActivity {
    private final AtomicBoolean isPlaying = new AtomicBoolean();
    
    private SurfaceHolder surfaceHolder;
    
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_bitmap_player);
	
	SurfaceView surfaceView = (SurfaceView)findViewById(R.id.surface_view);
	
	surfaceHolder = surfaceView.getHolder();
	surfaceHolder.addCallback(surfaceHolderCallback);
    }
    
    private final Callback surfaceHolderCallback = new Callback() {
	public void surfaceChanged(SurfaceHolder holder, int format,
			int width, int height) {
	}
	
	public void surfaceCreated(SurfaceHolder holder) {
	    isPlaying.set(true);
	    new Thread(renderer).start();
	}
	
	public void surfaceDestroyed(SurfaceHolder holder) {
	    isPlaying.set(false);
	}
    };
    
    private final Runnable renderer = new Runnable() {
	public void run() {
	    Bitmap bitmap = Bitmap.createBitmap(
		    getWidth(avi),
		    getHeight(avi),
		    Bitmap.Config.RGB_565);
	    
	    long frameDelay = (long) (1000 / getFrameRate(avi));
	    
	    while (isPlaying.get()) {
		render(avi, bitmap);
		
		Canvas canvas = surfaceHolder.lockCanvas();
		
		canvas.drawBitmap(bitmap, 0, 0, null);
		
		surfaceHolder.unlockCanvasAndPost(canvas);
		
		try {
		    Thread.sleep(frameDelay);
		} catch (InterruptedException e) {
		    break;
		}
	    }
	}
    };
    
    private native static boolean render(long avi, Bitmap bitmap);
}
