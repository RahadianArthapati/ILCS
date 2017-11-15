package com.ikt.main.to.util;

import com.ikt.main.to.BuildConfig;

public class Config {

    public static final String TAG = "IKT APP#";
    public static final int GET = 1;
    public static final int POST = 2;
    public static final int UPLOAD = 3;
    public static final int DOWNLOAD = 4;
    public static final String CLIENT_ID = "948460267388";
    public static final String CLIENT_SECRET_GCM = "dokupass";

    public static String getUrlCartrust(String url) {
        return BuildConfig.ENDPOINT_CARTRUST + url;
    }

    public static String getVersion(){
        return BuildConfig.VERSION_NAME;
    }

    public static String URL_CARTRUST = getUrlCartrust("");
    public static String URL_LOGIN = URL_CARTRUST + "login/login";
    public static String URL_DRIVER = URL_CARTRUST + "Driver/driver";
    public static String URL_DELETE_DRIVER = URL_CARTRUST + "DeleteDriver";
    public static String URL_ADD_DRIVER = URL_CARTRUST+"AddDriver";
    public static String URL_EDIT_DRIVER = URL_CARTRUST+"EditDriver";
    public static String URL_GET_TICKET = URL_CARTRUST+"GetTicketList";
    public static String URL_GET_TICKET_DETAIL = URL_CARTRUST+"GetTicket";
    public static String URL_GET_STATUS = URL_CARTRUST+"GetStatus";
    public static String URL_GET_TRUCK_ACTIVITIES = URL_CARTRUST+"GetActivityList";
    public static String URL_GET_ACTIVITIES = URL_CARTRUST+"GetActivity";
    public static String URL_ASSIGN_DRIVER = URL_CARTRUST+"AssignDriver";
    public static String URL_CHANGE_PASSWORD = URL_CARTRUST + "ChangePassword";
    public static String URL_GCM = URL_CARTRUST+"Gcm/gcm";
    public static String URL_GCM_MSG_DETAIL = URL_CARTRUST+"GetMessageDetail";
    public static String URL_EDIT_VISIT_RETRIEVE = URL_CARTRUST + "VisitEdit/retrieve";
    public static String URL_EDIT_VISIT_SAVE = URL_CARTRUST + "VisitEdit/edit";
    public static String URL_EDIT_VIN_OUTGOING = URL_CARTRUST + "VisitEdit/getvin";
    public static String URL_EDIT_TRIP_OUTGOING = URL_CARTRUST + "VisitEdit/gettrip";
    public static String URL_EDIT_SAVE_VIN = URL_CARTRUST + "VisitEdit/editvin";
    public static String URL_EDIT_SAVE_TRIP = URL_CARTRUST + "VisitEdit/edittrip";
    public static String URL_CHECK_VIN = URL_CARTRUST + "checkVinStatusAndHold";
    public static String URL_GET_VINS = URL_CARTRUST + "Retrieve/listvin";
    public static String URL_GET_TRIPS = URL_CARTRUST + "Retrieve/cmstrip";
    public static String URL_CHECK_VERSION = URL_CARTRUST + "checkVersion";
    public static String URL_CHECK_VIN_SCAN = URL_CARTRUST + "checkVinScanned";
    public static String URL_GET_SLOT = URL_CARTRUST + "BookSlotOption";
    public static String URL_ANNOUNCE_VIN = URL_CARTRUST + "AnnounceVin";
    public static String URL_DELETE_VISIT = URL_CARTRUST + "VisitEdit/delete";



    public static String URL_TRUCK = URL_CARTRUST + "GetTruck";
    public static String URL_TIMESLOT_DOMESTIC = URL_CARTRUST + "bookSlotDomestic";
    public static String URL_TIMESLOT_INTERNATIONAL = URL_CARTRUST + "bookSlotInternational";
    public static String URL_INCOMING = URL_CARTRUST + "cartosApiIncoming";
    public static String URL_OUTGOING = URL_CARTRUST + "cartosApiOutgoing";
    public static String URL_BACKLOAD = URL_CARTRUST + "cartosApiBackLoad";
    public static String KEY_NAME = "NAME";
    public static String KEY_ID = "ID";
    public static String KEY_TYPE = "TYPE";
    public static String KEY_DISCOUNT = "DISCOUNT";
    public static String KEY_PRICE = "PRICE";
    public static String KEY_THUMB_URL = "THUMB_URL";
    public static String KEY_USER_ID = "user_id";
    public static String KEY_USERNAME = "username";
    public static String KEY_VISIT_ID = "visit_id";
    public static String KEY_ORG_ID = "org_id";
    public static String KEY_SESSION = "session_id";
    public static String KEY_LAST_ID = "last_id";
    public static String KEY_PROFILE = "profile";
    public static String IS_LOGIN = "login";
    public static String KEY_PRIVILEGE = "previlige";
    public static String HAVE_PIN = "pin";
    public static String IS_FIRST = "first";
    public static String TRUCK = "truck";
    public static int total = 0;
    public static int offer = 0;

    public static String keyLock = "arifin88";
    public static String DISPLAY_MESSAGE_ACTION = "act_msg";
    public static String EXTRA_MESSAGE = "msg";
}
