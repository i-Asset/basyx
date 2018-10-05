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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for operationResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="operationResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="rsc" type="{http://www.onem2m.org/xml/protocols}responseStatusCode"/&gt;
 *         &lt;element name="rqi" type="{http://www.onem2m.org/xml/protocols}requestID"/&gt;
 *         &lt;element name="pc" type="{http://www.onem2m.org/xml/protocols}primitiveContent" minOccurs="0"/&gt;
 *         &lt;element name="to" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *         &lt;element name="fr" type="{http://www.onem2m.org/xml/protocols}ID" minOccurs="0"/&gt;
 *         &lt;element name="ot" type="{http://www.onem2m.org/xml/protocols}timestamp" minOccurs="0"/&gt;
 *         &lt;element name="rset" type="{http://www.onem2m.org/xml/protocols}absRelTimestamp" minOccurs="0"/&gt;
 *         &lt;element name="ec" type="{http://www.onem2m.org/xml/protocols}eventCat" minOccurs="0"/&gt;
 *         &lt;element name="cnst" type="{http://www.onem2m.org/xml/protocols}contentStatus" minOccurs="0"/&gt;
 *         &lt;element name="cnot" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "operationResult", propOrder = {
    "rsc",
    "rqi",
    "pc",
    "to",
    "fr",
    "ot",
    "rset",
    "ec",
    "cnst",
    "cnot"
})
public class OperationResult {

    @XmlElement(required = true)
    protected BigInteger rsc;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String rqi;
    protected PrimitiveContent pc;
    @XmlSchemaType(name = "anyURI")
    protected String to;
    @XmlSchemaType(name = "anyURI")
    protected String fr;
    protected String ot;
    @XmlSchemaType(name = "anySimpleType")
    protected String rset;
    @XmlSchemaType(name = "anySimpleType")
    protected String ec;
    protected BigInteger cnst;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger cnot;

    /**
     * Gets the value of the rsc property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRsc() {
        return rsc;
    }

    /**
     * Sets the value of the rsc property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRsc(BigInteger value) {
        this.rsc = value;
    }

    /**
     * Gets the value of the rqi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRqi() {
        return rqi;
    }

    /**
     * Sets the value of the rqi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRqi(String value) {
        this.rqi = value;
    }

    /**
     * Gets the value of the pc property.
     * 
     * @return
     *     possible object is
     *     {@link PrimitiveContent }
     *     
     */
    public PrimitiveContent getPc() {
        return pc;
    }

    /**
     * Sets the value of the pc property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrimitiveContent }
     *     
     */
    public void setPc(PrimitiveContent value) {
        this.pc = value;
    }

    /**
     * Gets the value of the to property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTo(String value) {
        this.to = value;
    }

    /**
     * Gets the value of the fr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFr() {
        return fr;
    }

    /**
     * Sets the value of the fr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFr(String value) {
        this.fr = value;
    }

    /**
     * Gets the value of the ot property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOt() {
        return ot;
    }

    /**
     * Sets the value of the ot property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOt(String value) {
        this.ot = value;
    }

    /**
     * Gets the value of the rset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRset() {
        return rset;
    }

    /**
     * Sets the value of the rset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRset(String value) {
        this.rset = value;
    }

    /**
     * Gets the value of the ec property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEc() {
        return ec;
    }

    /**
     * Sets the value of the ec property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEc(String value) {
        this.ec = value;
    }

    /**
     * Gets the value of the cnst property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCnst() {
        return cnst;
    }

    /**
     * Sets the value of the cnst property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCnst(BigInteger value) {
        this.cnst = value;
    }

    /**
     * Gets the value of the cnot property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCnot() {
        return cnot;
    }

    /**
     * Sets the value of the cnot property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCnot(BigInteger value) {
        this.cnot = value;
    }

}
