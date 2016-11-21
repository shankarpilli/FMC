package com.versatilemobitech.fmc.utility;

/**
 * Created by shankar on 10/20/2016.
 */

public class APIConstants {

    public static final String GSON = "Gson";
    public static final String JSON = "Json";
    public static final String STATUS = "status";

    public enum REQUEST_TYPE {
        GET, POST, MULTIPART_GET, MULTIPART_POST, DELETE, PUT, PATCH
    }

    public static final String BASE_URL = "http://facilitymanagementcouncil.com/admin/service/";
    public static final String REGISTRATION_URL = BASE_URL + "registration";
    public static final String LOGIN_URL = BASE_URL + "login";
    public static final String PHOTO_ALBUMS = BASE_URL + "photoalbums/"/*{page number}*/;
    public static final String PHOTO_GALLERY = BASE_URL + "photogallery/"/*{album id}/{page number}*/;


}
