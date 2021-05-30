package com.sakshi.miwok;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

    /** Resource Id for the background color for the list of words */
    private int mColorResourceID;

    /**
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param words is the list of (@link word) to be displayed.
     */
    public WordAdapter(Activity context, ArrayList<Word> words, int ColorResourceID) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        mColorResourceID = ColorResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the miwok translation from the currentWord object and set this text on the Miwok TextView
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID default_text_view
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the default translation from the currentWord object and set this text on the default TextView
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // Find the imageview in the list_item.xml layout with the ID image
        ImageView imageView = (ImageView) listItemView.findViewById( R.id.image );

        //Check if an image is provided for this word or not
        if(currentWord.hasImage()){
            // If an image is available , display the provided image based on the resource ID
            imageView.setImageResource( currentWord.getImageResourceID() );
            // Make sure the view is visible
            imageView.setVisibility( View.VISIBLE );
        }else {
            // Otherwise hide the ImageView
            imageView.setVisibility( View.GONE );
        }

        // set the theme color for the list item
        View textContainer = listItemView.findViewById( R.id.text_containor );
        // find the color that the resource ID maps to
        int color = ContextCompat.getColor( getContext(), mColorResourceID );
        // set the background color of the text container View
        textContainer.setBackgroundColor( color );


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
