package com.example.app;

/**
 * Created by Robert on 26/01/14.
 */
import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;

public class ChannelXmlParser {

    // names of the XML tags
    static final String TV = "tv";
    static final String CHANNEL = "channel";
    static final String CHANNELID = "id";
    static final String DISPLAYNAME = "display-name";

    ArrayList<Channel> channelList = null;
    private Channel currentChannel = null;
    private boolean done = false;
    private String currentTag = null;

    public ArrayList<Channel> parse(Context context) {

        XmlPullParser parser = context.getResources().getXml(R.xml.channel_list);

        try {

            int eventType = parser.getEventType();

            // Following logic modified from http://www.ibm.com/developerworks/library/x-android/
            // Also look at http://developer.android.com/training/basics/network-ops/xml.html

            while (eventType != XmlPullParser.END_DOCUMENT && !done) {

                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        channelList = new ArrayList<Channel>();
                        break;
                    case XmlPullParser.START_TAG:
                        currentTag = parser.getName();
                        if (currentTag.equalsIgnoreCase(CHANNEL)) {
                            currentChannel = new Channel();
                        } else if (currentChannel != null) {
                            if (currentTag.equalsIgnoreCase(CHANNELID)) {
                                currentChannel.setId(parser.nextText());
                                // currentEmployee.setId(parser.nextText());
                            } else if (currentTag.equalsIgnoreCase(DISPLAYNAME)) {
                                currentChannel.setDisplayName(parser.nextText());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        currentTag = parser.getName();
                        if (currentTag.equalsIgnoreCase(CHANNEL) && currentChannel != null) {
                            channelList.add(currentChannel);
                        } else if (currentTag.equalsIgnoreCase(TV)) {
                            done = true;
                        }
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return channelList;

    }

}