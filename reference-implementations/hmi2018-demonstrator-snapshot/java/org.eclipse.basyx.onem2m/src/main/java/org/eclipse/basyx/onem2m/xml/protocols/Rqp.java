//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.16 at 09:48:33 AM CET 
//


package org.eclipse.basyx.onem2m.xml.protocols;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="op" type="{http://www.onem2m.org/xml/protocols}operation"/&gt;
 *         &lt;element name="to" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="fr" type="{http://www.onem2m.org/xml/protocols}ID" minOccurs="0"/&gt;
 *         &lt;element name="rqi" type="{http://www.onem2m.org/xml/protocols}requestID"/&gt;
 *         &lt;element name="ty" type="{http://www.onem2m.org/xml/protocols}resourceType" minOccurs="0"/&gt;
 *         &lt;element name="pc" type="{http://www.onem2m.org/xml/protocols}primitiveContent" minOccurs="0"/&gt;
 *         &lt;element name="rids" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction&gt;
 *               &lt;simpleType&gt;
 *                 &lt;list itemType="{http://www.onem2m.org/xml/protocols}roleID" /&gt;
 *               &lt;/simpleType&gt;
 *               &lt;minLength value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ot" type="{http://www.onem2m.org/xml/protocols}timestamp" minOccurs="0"/&gt;
 *         &lt;element name="rqet" type="{http://www.onem2m.org/xml/protocols}absRelTimestamp" minOccurs="0"/&gt;
 *         &lt;element name="rset" type="{http://www.onem2m.org/xml/protocols}absRelTimestamp" minOccurs="0"/&gt;
 *         &lt;element name="oet" type="{http://www.onem2m.org/xml/protocols}absRelTimestamp" minOccurs="0"/&gt;
 *         &lt;element name="rt" type="{http://www.onem2m.org/xml/protocols}responseTypeInfo" minOccurs="0"/&gt;
 *         &lt;element name="rp" type="{http://www.onem2m.org/xml/protocols}absRelTimestamp" minOccurs="0"/&gt;
 *         &lt;element name="rcn" type="{http://www.onem2m.org/xml/protocols}resultContent" minOccurs="0"/&gt;
 *         &lt;element name="ec" type="{http://www.onem2m.org/xml/protocols}eventCat" minOccurs="0"/&gt;
 *         &lt;element name="da" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="gid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fc" type="{http://www.onem2m.org/xml/protocols}filterCriteria" minOccurs="0"/&gt;
 *         &lt;element name="drt" type="{http://www.onem2m.org/xml/protocols}discResType" minOccurs="0"/&gt;
 *         &lt;element name="tkns" type="{http://www.onem2m.org/xml/protocols}dynAuthJWT" minOccurs="0"/&gt;
 *         &lt;element name="tids" type="{http://www.onem2m.org/xml/protocols}tokenID" minOccurs="0"/&gt;
 *         &lt;element name="ltids" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction&gt;
 *               &lt;simpleType&gt;
 *                 &lt;list itemType="{http://www.w3.org/2001/XMLSchema}NCName" /&gt;
 *               &lt;/simpleType&gt;
 *               &lt;minLength value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="tqi" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "op",
    "to",
    "fr",
    "rqi",
    "ty",
    "pc",
    "rids",
    "ot",
    "rqet",
    "rset",
    "oet",
    "rt",
    "rp",
    "rcn",
    "ec",
    "da",
    "gid",
    "fc",
    "drt",
    "tkns",
    "tids",
    "ltids",
    "tqi"
})
@XmlRootElement(name = "rqp")
public class Rqp {

    @XmlElement(required = true)
    protected BigInteger op;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String to;
    @XmlSchemaType(name = "anyURI")
    protected String fr;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String rqi;
    protected BigInteger ty;
    protected PrimitiveContent pc;
    @XmlList
    protected List<String> rids;
    protected String ot;
    @XmlSchemaType(name = "anySimpleType")
    protected String rqet;
    @XmlSchemaType(name = "anySimpleType")
    protected String rset;
    @XmlSchemaType(name = "anySimpleType")
    protected String oet;
    protected ResponseTypeInfo rt;
    @XmlSchemaType(name = "anySimpleType")
    protected String rp;
    protected BigInteger rcn;
    @XmlSchemaType(name = "anySimpleType")
    protected String ec;
    protected Boolean da;
    protected String gid;
    protected FilterCriteria fc;
    protected BigInteger drt;
    @XmlSchemaType(name = "anySimpleType")
    protected String tkns;
    protected String tids;
    @XmlList
    protected List<String> ltids;
    protected Boolean tqi;

    /**
     * Gets the value of the op property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOp() {
        return op;
    }

    /**
     * Sets the value of the op property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOp(BigInteger value) {
        this.op = value;
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
     * Gets the value of the ty property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTy() {
        return ty;
    }

    /**
     * Sets the value of the ty property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTy(BigInteger value) {
        this.ty = value;
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
     * Gets the value of the rids property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rids property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRids().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRids() {
        if (rids == null) {
            rids = new ArrayList<String>();
        }
        return this.rids;
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
     * Gets the value of the rqet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRqet() {
        return rqet;
    }

    /**
     * Sets the value of the rqet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRqet(String value) {
        this.rqet = value;
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
     * Gets the value of the oet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOet() {
        return oet;
    }

    /**
     * Sets the value of the oet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOet(String value) {
        this.oet = value;
    }

    /**
     * Gets the value of the rt property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseTypeInfo }
     *     
     */
    public ResponseTypeInfo getRt() {
        return rt;
    }

    /**
     * Sets the value of the rt property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseTypeInfo }
     *     
     */
    public void setRt(ResponseTypeInfo value) {
        this.rt = value;
    }

    /**
     * Gets the value of the rp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRp() {
        return rp;
    }

    /**
     * Sets the value of the rp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRp(String value) {
        this.rp = value;
    }

    /**
     * Gets the value of the rcn property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRcn() {
        return rcn;
    }

    /**
     * Sets the value of the rcn property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRcn(BigInteger value) {
        this.rcn = value;
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
     * Gets the value of the da property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDa() {
        return da;
    }

    /**
     * Sets the value of the da property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDa(Boolean value) {
        this.da = value;
    }

    /**
     * Gets the value of the gid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGid() {
        return gid;
    }

    /**
     * Sets the value of the gid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGid(String value) {
        this.gid = value;
    }

    /**
     * Gets the value of the fc property.
     * 
     * @return
     *     possible object is
     *     {@link FilterCriteria }
     *     
     */
    public FilterCriteria getFc() {
        return fc;
    }

    /**
     * Sets the value of the fc property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterCriteria }
     *     
     */
    public void setFc(FilterCriteria value) {
        this.fc = value;
    }

    /**
     * Gets the value of the drt property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDrt() {
        return drt;
    }

    /**
     * Sets the value of the drt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDrt(BigInteger value) {
        this.drt = value;
    }

    /**
     * Gets the value of the tkns property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTkns() {
        return tkns;
    }

    /**
     * Sets the value of the tkns property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTkns(String value) {
        this.tkns = value;
    }

    /**
     * Gets the value of the tids property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTids() {
        return tids;
    }

    /**
     * Sets the value of the tids property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTids(String value) {
        this.tids = value;
    }

    /**
     * Gets the value of the ltids property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ltids property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLtids().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLtids() {
        if (ltids == null) {
            ltids = new ArrayList<String>();
        }
        return this.ltids;
    }

    /**
     * Gets the value of the tqi property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTqi() {
        return tqi;
    }

    /**
     * Sets the value of the tqi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTqi(Boolean value) {
        this.tqi = value;
    }

}
