package com.apress.aviplayer;

import java.io.File;

//import android.R;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends Activity implements OnClickListener {
    private EditText fileNameEdit;
    
    private RadioGroup playerRadioGroup;
    
    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
	fileNameEdit = (EditText)findViewById(R.id.file_name_edit);
	playerRadioGroup = (RadioGroup)findViewById(R.id.player_radio_group);
	playButton = (Button)findViewById(R.id.play_button);
	playButton.setOnClickListener(this);
    }
    
    public void onClick(View view) {
	switch (view.getId()) {
	case R.id.play_button:
	    	onPlayButtonClick();
	    	break;
	}
    }
    
    private void onPlayButtonClick() {
	Intent intent = new Intent();
	
	int radioId = playerRadioGroup.getCheckedRadioButtonId();
	
	/*switch (radioId) {
	default:
	    	throw new UnsupportedOperationException(
	    			"radioId=" + radioId);
	}*/
	
	File file = new File(Environment.getExternalStorageDirectory(), fileNameEdit.getText().toString());
	intent.putExtra(AbstractPlayerActivity.EXTRA_FILE_NAME, file.getAbsolutePath());
	startActivity(intent);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }*/

}
