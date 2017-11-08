package com.ikt.main.to.object;

/**
 * Created by Arifin on 9/25/16.
 */

public class DeleteTicketObject {

//            {
//                  "message": "::typeParser.invalidCode::HandlingUnit::8993988065111::RemoveHandlingUnitFromVisit visit TRK3250353673535472, handlingunit 8993988065111, incoming true, outgoing false",
//                  "message": "Maaf, gagal menghapus Visit",
//                  "status": false
//            }

    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
