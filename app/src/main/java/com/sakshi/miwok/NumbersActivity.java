package com.sakshi.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;


    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo( 0 );
                    }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        mMediaPlayer.start();
                    }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        releaseMediaPlayer();
                    }
                }
            };










    /**
     * This listner gets triggered when the{@link MediaPlayer} has completed playing the audio file
     */
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.word_list );

        // create and setup  the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService( Context.AUDIO_SERVICE );

        /*
        ArrayList<String> words = new ArrayList<String>();
        words.add( "one" );
        words.add( "two" );
        words.add("three");


        ArrayAdapter<String>itemAdapter = new ArrayAdapter<String>(this, R.layout.list_item , words);
        ListView listView = (ListView)findViewById( R.id.list );
        listView.setAdapter( itemAdapter );
         */




         /*
        String[] words = new String[5];
        words[0] = "one";
        words[1]= "two";
        words[2] = "three";
        words[3]="four";
        words[4]="five";
        // Log.v( "NumbersActivity", "words at index 0: " + words[0] );

      */


        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));


        WordAdapter adapter = new WordAdapter( this, words, R.color.category_numbers);
        ListView listView = (ListView)findViewById( R.id.list );
        listView.setAdapter( adapter );

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                // Log.v("NumberActivity","Current Word:" + word.toString()); // used for debug

                // Release the media player if it currently exists because we are about to play a different sound  file.
                releaseMediaPlayer();

                // request audio focus playback
                int  result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener ,
                        // Use music stream
                        AudioManager.STREAM_MUSIC,
                        //Request permission focus,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // mAudioManager.registerMediaButtonEventReceiver(mOnAudioFocusChangeListener);
                    // we have audio focus now.


                    // create and setup {@link MediaPlayer for the audio resource associated with the current word
                    mMediaPlayer = MediaPlayer.create( NumbersActivity.this, word.getmAudioResourceID() );
                    mMediaPlayer.start();

                    // setup a listener on the media player , so that we can stop and release the media player once the sounds finished playing.
                    mMediaPlayer.setOnCompletionListener( onCompletionListener );
                }


            }
        } );


        /*
        // dynamically created textview with for loop

        LinearLayout rootView = findViewById( R.id.root_view );

        for(int idx = 0; idx<words.size(); idx++){
            TextView wordView = new TextView(this );
            wordView.setText( words.get( idx ) );
            rootView.addView( wordView );
        }
         */


        // dynamically created textview with while loop
        /*
        LinearLayout rootView = findViewById( R.id.root_view );
        int idx = 0;
        while (idx < words.size()){
            TextView wordView = new TextView(this );
            wordView.setText( words.get( idx ) );
            rootView.addView( wordView );
            idx++;
        }
        */




        /*
        // dynamically created textview without loops
        LinearLayout rootView = findViewById( R.id.root_view );
        TextView wordView = new TextView(this );
        wordView.setText( words.get( 0 ) );
        rootView.addView( wordView );

        TextView wordView1 = new TextView(this );
        wordView1.setText( words.get( 1 ) );
        rootView.addView( wordView1 );

        TextView wordView2 = new TextView(this );
        wordView2.setText( words.get( 2 ) );
        rootView.addView( wordView2 );

        */




    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Abandon

                mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}

