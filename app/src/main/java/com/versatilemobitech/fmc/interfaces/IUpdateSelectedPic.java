package com.versatilemobitech.fmc.interfaces;

import java.io.File;

/**
 * Created by Shankar on 11/21/2016.
 */
public interface IUpdateSelectedPic {

    void updateProfilePic(String path);

    void updateDoc(File path);

    void updatePdf(File path);

}
