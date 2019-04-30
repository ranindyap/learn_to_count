package paramitha.ranindya.learntocount;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Intent starterIntent = new Intent();
    TextView firstNumber;
    TextView sign;
    TextView lastNumber;
    TextView benarSalah;
    View buttons;
    Intent backIntent;
    int jawabanBenar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();

        final EditText editText = findViewById(R.id.editText);

        final SpeechRecognizer mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        firstNumber = findViewById(R.id.firstNumber);
        sign = findViewById(R.id.sign);
        lastNumber = findViewById(R.id.lastNumber);
        benarSalah = findViewById(R.id.benarSalah);
        buttons = findViewById(R.id.buttons);
        benarSalah.setVisibility(View.INVISIBLE);
        buttons.setVisibility(View.INVISIBLE);
        int random1 = new Random().nextInt(9);
        int random2 = new Random().nextInt(9);
        int randomSign = new Random().nextInt(1);
        if (randomSign == 1){
            while(random1 < random2){
                random1 = new Random().nextInt(9);
            }
            sign.setText("-");
            jawabanBenar = random1 - random2;
        } else{
            sign.setText("+");
            jawabanBenar = random1 + random2;
        }
        firstNumber.setText(String.valueOf(random1));
        lastNumber.setText(String.valueOf(random2));

        starterIntent = getIntent();
        backIntent = new Intent(this, StartActivity.class);

        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());


        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                //getting all the matches
                ArrayList<String> matches = bundle
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                //displaying the first match
                if (matches != null) {
                    editText.setText(matches.get(0));
                    int jawaban = toInt(matches.get(0));
                    if (jawaban == jawabanBenar){
                        benarSalah.setText("Benar!");
                    } else{
                        benarSalah.setText("Salah, coba lagi.");
                    }
                    benarSalah.setVisibility(View.VISIBLE);
                    findViewById(R.id.button).setEnabled(false);
                    buttons.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        findViewById(R.id.resetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(starterIntent);
            }
        });
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(backIntent);
            }
        });
        findViewById(R.id.button).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        mSpeechRecognizer.stopListening();
                        editText.setHint("You will see input here");
                        break;

                    case MotionEvent.ACTION_DOWN:
                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                        editText.setText("");
                        editText.setHint("Listening...");
                        break;
                }
                return false;
            }
        });
    }

    private int toInt(String s){
        int result = 0;
        if ((s.compareTo("nol") == 0) || (s.compareTo("0") == 0)){
            result = 0;
        } else if ((s.compareTo("satu") == 0) || (s.compareTo("1") == 0)){
            result = 1;
        } else if ((s.compareTo("dua") == 0) || (s.compareTo("2") == 0)){
            result = 2;
        } else if ((s.compareTo("tiga") == 0) || (s.compareTo("3") == 0)){
            result = 3;
        } else if ((s.compareTo("empat") == 0) || (s.compareTo("4") == 0)){
            result = 4;
        } else if ((s.compareTo("lima") == 0) || (s.compareTo("5") == 0)){
            result = 5;
        } else if ((s.compareTo("enam") == 0) || (s.compareTo("6") == 0)){
            result = 6;
        } else if ((s.compareTo("tujuh") == 0) || (s.compareTo("7") == 0)){
            result = 7;
        } else if ((s.compareTo("delapan") == 0) || (s.compareTo("8") == 0)){
            result = 8;
        } else if ((s.compareTo("sembilan") == 0) || (s.compareTo("9") == 0)){
            result = 9;
        } else if (s.compareTo("10") == 0){
            result = 10;
        } else if (s.compareTo("11") == 0){
            result = 11;
        } else if (s.compareTo("12") == 0){
            result = 12;
        } else if (s.compareTo("13") == 0){
            result = 13;
        } else if (s.compareTo("14") == 0){
            result = 14;
        } else if (s.compareTo("15") == 0){
            result = 15;
        } else if (s.compareTo("16") == 0){
            result = 16;
        } else if (s.compareTo("17") == 0){
            result = 17;
        } else if (s.compareTo("18") == 0){
            result = 18;
        }
        return result;
    }
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                finish();
            }
        }
    }
}