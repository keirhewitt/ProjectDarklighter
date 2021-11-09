package com.company;

/**
 * Notes may contain scripture/crafting tips/locations?/story points?
 *
 */
public class Note {

    private Scripture scripture = null;     // Scripture may be initialised if given in Note

    public Note() {
        random_note();
    }

    public String random_note() {
        return "";
        // These will be stored in DB_ and will be called from here.
    }


}
