/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.List;
import model.TextSender;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author billylim
 */

@ManagedBean
@RequestScoped
public class SMSController implements Serializable {

    private java.util.List<java.lang.String> cellPhoneCarriers;
    private String cellPhoneCarrierChosen;
    private String phone;
    private String sentStatus;
    

    /**
     * Creates a new instance of SMSController
     */
    public SMSController() {
    }

    public List<String> getCellPhoneCarriers() {
        cellPhoneCarriers = TextSender.getCarriers();
        return cellPhoneCarriers;
    }

    public void setCellPhoneCarriers(List<String> cellPhoneCarriers) {
        this.cellPhoneCarriers = cellPhoneCarriers;
    }

    /**
     * @return the cellPhoneCarrierChosen
     */
    public String getCellPhoneCarrierChosen() {
        return cellPhoneCarrierChosen;
    }

    /**
     * @param cellPhoneCarrierChosen the cellPhoneCarrierChosen to set
     */
    public void setCellPhoneCarrierChosen(String cellPhoneCarrierChosen) {
        this.cellPhoneCarrierChosen = cellPhoneCarrierChosen;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void sendSMS() {
        TextSender.sendSMS(cellPhoneCarrierChosen, phone, "Hello There!");
        sentStatus = " Sent! (to " + phone + " on " + cellPhoneCarrierChosen + ")";
    }

    /**
     * @return the sentStatus
     */
    public String getSentStatus() {
        return sentStatus;
    }

    /**
     * @param sentStatus the sentStatus to set
     */
    public void setSentStatus(String sentStatus) {
        this.sentStatus = sentStatus;
    }
}
