package com.digital.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.fckeditor.requestcycle.UserAction;


/**
 * FCK 
 *
 */
public class UserActionImpl implements UserAction {

    public boolean isEnabledForFileBrowsing(HttpServletRequest req) {        
        return true;
    }

    public boolean isEnabledForFileUpload(HttpServletRequest req) {
       
        return true;
    }

}
