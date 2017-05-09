/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.it;

import javax.jws.WebService;

/**
 *
 * @author it353s728
 */
@WebService(serviceName = "TextSenderService", portName = "TextSenderPort", endpointInterface = "edu.ilstu.it.TextSender", targetNamespace = "http://it.ilstu.edu/", wsdlLocation = "WEB-INF/wsdl/TextMessageService/gfish2.it.ilstu.edu/ISUTextSMS2/TextSenderService.wsdl")
public class TextMessageService {

    public java.util.List<java.lang.String> getCarriers() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int sendSMS(java.lang.String carrier, java.lang.String telephoneNum, java.lang.String text) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
