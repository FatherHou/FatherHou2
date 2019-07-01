
package com.hou.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MyService", targetNamespace = "http://service.hou.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MyService {


    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "add", targetNamespace = "http://service.hou.com/", className = "com.hou.service.Add")
    @ResponseWrapper(localName = "addResponse", targetNamespace = "http://service.hou.com/", className = "com.hou.service.AddResponse")
    @Action(input = "http://service.hou.com/MyService/addRequest", output = "http://service.hou.com/MyService/addResponse")
    public int add(
        @WebParam(name = "arg0", targetNamespace = "")
        Event arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "register", targetNamespace = "http://service.hou.com/", className = "com.hou.service.Register")
    @ResponseWrapper(localName = "registerResponse", targetNamespace = "http://service.hou.com/", className = "com.hou.service.RegisterResponse")
    @Action(input = "http://service.hou.com/MyService/registerRequest", output = "http://service.hou.com/MyService/registerResponse")
    public boolean register(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "clear", targetNamespace = "http://service.hou.com/", className = "com.hou.service.Clear")
    @ResponseWrapper(localName = "clearResponse", targetNamespace = "http://service.hou.com/", className = "com.hou.service.ClearResponse")
    @Action(input = "http://service.hou.com/MyService/clearRequest", output = "http://service.hou.com/MyService/clearResponse")
    public void clear(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "delete", targetNamespace = "http://service.hou.com/", className = "com.hou.service.Delete")
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://service.hou.com/", className = "com.hou.service.DeleteResponse")
    @Action(input = "http://service.hou.com/MyService/deleteRequest", output = "http://service.hou.com/MyService/deleteResponse")
    public int delete(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<com.hou.service.Event>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "query", targetNamespace = "http://service.hou.com/", className = "com.hou.service.Query")
    @ResponseWrapper(localName = "queryResponse", targetNamespace = "http://service.hou.com/", className = "com.hou.service.QueryResponse")
    @Action(input = "http://service.hou.com/MyService/queryRequest", output = "http://service.hou.com/MyService/queryResponse")
    public List<Event> query(
        @WebParam(name = "arg0", targetNamespace = "")
        XMLGregorianCalendar arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        XMLGregorianCalendar arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "login", targetNamespace = "http://service.hou.com/", className = "com.hou.service.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://service.hou.com/", className = "com.hou.service.LoginResponse")
    @Action(input = "http://service.hou.com/MyService/loginRequest", output = "http://service.hou.com/MyService/loginResponse")
    public int login(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

}
