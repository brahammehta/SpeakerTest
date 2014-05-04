package com.example.speakertest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;


	
public class MainActivity extends Activity implements TextToSpeech.OnInitListener{
	
	private static final int DATA_CHECK = 123;
	private TextToSpeech newTTS;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, DATA_CHECK);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public void onInit(int initStatus){
		newTTS.speak("Hello there.", TextToSpeech.QUEUE_FLUSH, null);
		
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if(requestCode == DATA_CHECK){
			if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
				newTTS = new TextToSpeech(this, this);
			}
			else{
				Intent installIntent = new Intent();
				installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installIntent);
			}
				
		}
	}
	
	
	public void onDestroy(){
		if(newTTS != null){
			newTTS.stop();
			newTTS.shutdown();
		}
		super.onDestroy();
	}
	
	
} //speaker class