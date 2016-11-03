package com.versatilemobitech.fmc.asynctask;

import com.versatilemobitech.fmc.models.Model;

/**
 * @author Shankar
 *         <p/>
 *         Interface for async executor to get callback response either Model
 *         object or collection of Model object
 */
public interface IAsyncCaller {

    void onComplete(Model model);

    // public void onComplete(ArrayList<Model> model);
}
