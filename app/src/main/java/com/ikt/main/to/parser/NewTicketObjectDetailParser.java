package com.ikt.main.to.parser;

import android.content.Context;
import android.util.Log;

import com.ikt.main.to.controller.IParser;
import com.ikt.main.to.model.VectorModel;
import com.ikt.main.to.net.DefaultErrorModel;
import com.ikt.main.to.object.TicketObjectDetail;
import com.ikt.main.to.util.APPException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arifin on 4/10/16.
 */
public class NewTicketObjectDetailParser extends DefaultErrorModel implements IParser{

    @Override
    public void parser(Context context, int pid, String json) {
        try {
            checkError(context,json);
            setData(context,json);
        } catch (APPException e) {
            setError(e.getMessage());
        }
    }

    private void checkError(Context context, String json) throws APPException {
        try {
            if (json == null || json.isEmpty()) {
                setError("No Response from server");
                throw new APPException(getErrorMessage());
            }

            isValidJSON(json);
            JSONObject obj = new JSONObject(json);
            if (obj != null) {
                boolean status = obj.optBoolean("status");
                String msg = obj.optString("message");
                if (!status) {
                    setError(msg);
                    throw new APPException(getErrorMessage());
                }
            }
        } catch (JSONException e) {
            Log.e(DefaultErrorModel.class.getSimpleName(), String.format("JSON Exception : ", e.getMessage()));
            setError(e.getMessage());
            throw new APPException(getErrorMessage());
        }
    }

    private void setData(Context context, String json){
        try {
            // LAMA
//            "data": [{
//                "CARRIER": "PT. DUNIA EXPRESS TRANSINDO",
//                        "DRIVER": "dfg", DONE
//                        "TRUCK": "B9003WV",
//                        "OUTGOING_VOYAGE_NR": null, del
//                        "OUTGOING_VESSEL": null, del
//                        "TIME_BEGIN": "30-MAR-2016 13:00",
//                        "TIME_END": "30-MAR-2016 17:00",
//                        "LOADAREA_IN": "CD005",
//                        "LOADAREA_OUT": null,
//                        "AMOUNT_CAR_PICKUP": "0",
//                        "AMOUNT_CAR_VESSEL": "9"
//            }],
            // BARU
//            "data":{
//                "CARRIER":"PT. DUNIA EXPRESS TRANSINDO",
//                        "DRIVER":"Pardede",
//                        "LICENSEPLATE":"B 9015 WV", x
//                        "TRUCK":"B9015WV",
//                        "TIME_BEGIN":"27-OCT-2017 08:00",
//                        "TIME_END":"27-OCT-2017 16:00",
//                        "LOADAREA_IN":null,
//                        "LOADAREA_OUT":"Loding Area E",
//                        "AMOUNT_CAR_PICKUP":"91",
//                        "AMOUNT_CAR_VESSEL":"0",
//                        "INFO":"Truk sudah Gate In di Terminal IKT pada 01-NOV-2017 14:36", x
//                        "DELETABLE":false, x
//                        "EDITABLE":true,
//                        "NEED_ASSIGN":false x
//                        "PHONE":"123123123"

            JSONObject jsonObject = new JSONObject(json);
            JSONObject object = jsonObject.optJSONObject("data");
            if(object != null){
                VectorModel.getInstance().clearTicketObjectDetails();
                    TicketObjectDetail objectDetail = new TicketObjectDetail();
                    objectDetail.setDriverName(object.optString("DRIVER"));
                    objectDetail.setEnd(object.optString("TIME_END"));
                    objectDetail.setAmountCarPickup(object.optString("AMOUNT_CAR_PICKUP"));
                    objectDetail.setBegin(object.optString("TIME_BEGIN"));
                    objectDetail.setAmountCarVessel(object.optString("AMOUNT_CAR_VESSEL"));
                    objectDetail.setAreaIn(object.optString("LOADAREA_IN"));
                    objectDetail.setAreaOut(object.optString("LOADAREA_OUT"));
                    objectDetail.setCarrier(object.optString("CARRIER"));
                    objectDetail.setOutGoingVessel(object.optString("OUTGOING_VESSEL"));
                    objectDetail.setOutGoingVoyage(object.optString("OUTGOING_VOYAGE_NR"));
                    objectDetail.setPlatNo(object.optString("TRUCK"));
                    objectDetail.setLicenseplate(object.optString("LICENSEPLATE"));
                    objectDetail.setInfo(object.optString("INFO"));
                    objectDetail.setNeed_assign(object.optBoolean("NEED_ASSIGN"));
                    objectDetail.setEditable(object.optBoolean("EDITABLE"));
                    objectDetail.setDeletable(object.optBoolean("DELETABLE"));
                    objectDetail.setPhone(object.optString("PHONE"));
                VectorModel.getInstance().setTicketObjectDetails(objectDetail);
            }
        } catch (JSONException e) {
            setError(e.getMessage());
        }
    }
}
