//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.29 at 10:30:36 PM CEST 
//


package com.centralapi.domain.xml.xml_ftn.users;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.centralapi.domain.xml.xml_ftn.users package. 
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

    private final static QName _RegistredUser_QNAME = new QName("http://www.xml-ftn.xml.domain.centralapi.com/Users", "RegistredUser");
    private final static QName _User_QNAME = new QName("http://www.xml-ftn.xml.domain.centralapi.com/Users", "User");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.centralapi.domain.xml.xml_ftn.users
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetReservationForUserDTO }
     * 
     */
    public GetReservationForUserDTO createGetReservationForUserDTO() {
        return new GetReservationForUserDTO();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link GetRoomsForUserDTO }
     * 
     */
    public GetRoomsForUserDTO createGetRoomsForUserDTO() {
        return new GetRoomsForUserDTO();
    }

    /**
     * Create an instance of {@link RegistredUser }
     * 
     */
    public RegistredUser createRegistredUser() {
        return new RegistredUser();
    }

    /**
     * Create an instance of {@link GetMessagesForUserDTO }
     * 
     */
    public GetMessagesForUserDTO createGetMessagesForUserDTO() {
        return new GetMessagesForUserDTO();
    }

    /**
     * Create an instance of {@link UserLoginDTO }
     * 
     */
    public UserLoginDTO createUserLoginDTO() {
        return new UserLoginDTO();
    }

    /**
     * Create an instance of {@link LoginClientDTO }
     * 
     */
    public LoginClientDTO createLoginClientDTO() {
        return new LoginClientDTO();
    }

    /**
     * Create an instance of {@link LoginDTO }
     * 
     */
    public LoginDTO createLoginDTO() {
        return new LoginDTO();
    }

    /**
     * Create an instance of {@link AgentUser }
     * 
     */
    public AgentUser createAgentUser() {
        return new AgentUser();
    }

    /**
     * Create an instance of {@link Role }
     * 
     */
    public Role createRole() {
        return new Role();
    }

    /**
     * Create an instance of {@link SendMessageDTO }
     * 
     */
    public SendMessageDTO createSendMessageDTO() {
        return new SendMessageDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistredUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xml-ftn.xml.domain.centralapi.com/Users", name = "RegistredUser")
    public JAXBElement<RegistredUser> createRegistredUser(RegistredUser value) {
        return new JAXBElement<RegistredUser>(_RegistredUser_QNAME, RegistredUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xml-ftn.xml.domain.centralapi.com/Users", name = "User")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

}
