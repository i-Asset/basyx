//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.16 at 09:48:33 AM CET 
//


package org.eclipse.basyx.onem2m.xml.protocols;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for securityInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="securityInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sit" type="{http://www.onem2m.org/xml/protocols}securityInfoType" minOccurs="0"/&gt;
 *         &lt;element name="daq" type="{http://www.onem2m.org/xml/protocols}dynAuthDasRequest" minOccurs="0"/&gt;
 *         &lt;element name="dres" type="{http://www.onem2m.org/xml/protocols}dynAuthDasResponse" minOccurs="0"/&gt;
 *         &lt;element name="ero" type="{http://www.onem2m.org/xml/protocols}receiverESPrimRandObject" minOccurs="0"/&gt;
 *         &lt;element name="epo" type="{http://www.onem2m.org/xml/protocols}e2eCompactJWE" minOccurs="0"/&gt;
 *         &lt;element name="eckm" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "securityInfo", propOrder = {
    "sit",
    "daq",
    "dres",
    "ero",
    "epo",
    "eckm"
})
public class SecurityInfo {

    protected BigInteger sit;
    protected DynAuthDasRequest daq;
    protected DynAuthDasResponse dres;
    protected ReceiverESPrimRandObject ero;
    protected String epo;
    protected byte[] eckm;

    /**
     * Gets the value of the sit property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSit() {
        return sit;
    }

    /**
     * Sets the value of the sit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSit(BigInteger value) {
        this.sit = value;
    }

    /**
     * Gets the value of the daq property.
     * 
     * @return
     *     possible object is
     *     {@link DynAuthDasRequest }
     *     
     */
    public DynAuthDasRequest getDaq() {
        return daq;
    }

    /**
     * Sets the value of the daq property.
     * 
     * @param value
     *     allowed object is
     *     {@link DynAuthDasRequest }
     *     
     */
    public void setDaq(DynAuthDasRequest value) {
        this.daq = value;
    }

    /**
     * Gets the value of the dres property.
     * 
     * @return
     *     possible object is
     *     {@link DynAuthDasResponse }
     *     
     */
    public DynAuthDasResponse getDres() {
        return dres;
    }

    /**
     * Sets the value of the dres property.
     * 
     * @param value
     *     allowed object is
     *     {@link DynAuthDasResponse }
     *     
     */
    public void setDres(DynAuthDasResponse value) {
        this.dres = value;
    }

    /**
     * Gets the value of the ero property.
     * 
     * @return
     *     possible object is
     *     {@link ReceiverESPrimRandObject }
     *     
     */
    public ReceiverESPrimRandObject getEro() {
        return ero;
    }

    /**
     * Sets the value of the ero property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReceiverESPrimRandObject }
     *     
     */
    public void setEro(ReceiverESPrimRandObject value) {
        this.ero = value;
    }

    /**
     * Gets the value of the epo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEpo() {
        return epo;
    }

    /**
     * Sets the value of the epo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEpo(String value) {
        this.epo = value;
    }

    /**
     * Gets the value of the eckm property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getEckm() {
        return eckm;
    }

    /**
     * Sets the value of the eckm property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setEckm(byte[] value) {
        this.eckm = value;
    }

}
