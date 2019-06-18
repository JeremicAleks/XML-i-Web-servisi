//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.19 at 12:26:29 AM CEST 
//


package com.centralapi.domain.xml.xml_ftn.rooms;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.centralapi.domain.xml.xml_ftn.rooms package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.centralapi.domain.xml.xml_ftn.rooms
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetRooms }
     * 
     */
    public GetRooms createGetRooms() {
        return new GetRooms();
    }

    /**
     * Create an instance of {@link Room }
     * 
     */
    public Room createRoom() {
        return new Room();
    }

    /**
     * Create an instance of {@link PriceList }
     * 
     */
    public PriceList createPriceList() {
        return new PriceList();
    }

    /**
     * Create an instance of {@link Image }
     * 
     */
    public Image createImage() {
        return new Image();
    }

    /**
     * Create an instance of {@link ImageReturn }
     * 
     */
    public ImageReturn createImageReturn() {
        return new ImageReturn();
    }

    /**
     * Create an instance of {@link AllowReservation }
     * 
     */
    public AllowReservation createAllowReservation() {
        return new AllowReservation();
    }

    /**
     * Create an instance of {@link AddRoomDTO }
     * 
     */
    public AddRoomDTO createAddRoomDTO() {
        return new AddRoomDTO();
    }

    /**
     * Create an instance of {@link RateAndComment }
     * 
     */
    public RateAndComment createRateAndComment() {
        return new RateAndComment();
    }

    /**
     * Create an instance of {@link RoomAdditionalService }
     * 
     */
    public RoomAdditionalService createRoomAdditionalService() {
        return new RoomAdditionalService();
    }

    /**
     * Create an instance of {@link RoomCancel }
     * 
     */
    public RoomCancel createRoomCancel() {
        return new RoomCancel();
    }

}
