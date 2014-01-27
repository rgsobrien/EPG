package com.example.app;

/**
 * Created by Robert on 26/01/14.
 */
public class Channel {
    String _channelID;
    String _displayName;

    // constructor
    public Channel() {

    }

    // constructor with parameters
    public Channel(String channelID, String displayName) {
        this._channelID = channelID;
        this._displayName = displayName;
    }

    // All set methods

    public void setId(String channelID) {
        this._channelID = channelID;
    }

    public void setDisplayName(String displayName) {
        this._displayName = displayName;
    }



    // All get methods

    public String getId() {
        return this._channelID;
    }

    public String getDisplayName() {
        return this._displayName;
    }


    //
    @Override
    public String toString() {
        return _channelID + "\n" + _displayName;
    }

}
