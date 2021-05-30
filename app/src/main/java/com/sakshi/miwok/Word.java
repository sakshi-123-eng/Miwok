package com.sakshi.miwok;

/**
 * {@Link Word} represents a vacabulary word that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 */



public class Word {
    // Default translation for the word
    private String mDefaultTranslation;

    // Miwok translation for the word
    private String mMiwokTranslation;

    // Image resource Id for the word
    private int mImageResourceID = NO_IMAGE_PROVIDED;
    /**  Constant value that represents no image was provided for this word  */
    private static final int NO_IMAGE_PROVIDED = -1;
    /**  audio resource id for the word*/
    private int mAudioResourceID;

    /**
     * Create a new word object
     *
     * @param DefaultTranslation is the word in a language that user is already familiar (such as English)
     * @param MiwokTranslation is the word in the Miwok language
     * @param AudioResourceID is for audio file associated with the word
     */

    public Word(String DefaultTranslation, String MiwokTranslation, int AudioResourceID){
        mDefaultTranslation = DefaultTranslation;
        mMiwokTranslation = MiwokTranslation;
        mAudioResourceID = AudioResourceID;
    }

    /**
     * Create a new word object
     *
     * @param DefaultTranslation is the word in a language that user is already familiar (such as English)
     * @param MiwokTranslation is the word in the Miwok language
     * @param ImageResourceID is the drawable resource ID for the image associated with the word
     * @param AudioResourceID is for audio file associated with the word
     */

    public Word(String DefaultTranslation, String MiwokTranslation, int ImageResourceID, int AudioResourceID ){
        mDefaultTranslation = DefaultTranslation;
        mMiwokTranslation = MiwokTranslation;
        mImageResourceID = ImageResourceID;
        mAudioResourceID = AudioResourceID;
    }

    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    /**
     * Get the Miwok translation of the word.
     */
    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }

    /**
     * Return the image resource ID of the word
     */
    public int getImageResourceID() {
        return mImageResourceID;
    }
    /**
     * Returns whether or not there is an image for the word
     */
    public boolean hasImage(){
        return mImageResourceID != NO_IMAGE_PROVIDED;
    }
    /**
     * Return the audio resource ID of the word
     */
    public int getmAudioResourceID(){
        return mAudioResourceID;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceID=" + mImageResourceID +
                ", mAudioResourceID=" + mAudioResourceID +
                '}';
    }
}
