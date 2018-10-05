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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mgmtResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mgmtResource"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.onem2m.org/xml/protocols}announceableResource"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="mgd" type="{http://www.onem2m.org/xml/protocols}mgmtDefinition"/&gt;
 *         &lt;element name="obis" type="{http://www.onem2m.org/xml/protocols}listOfURIs" minOccurs="0"/&gt;
 *         &lt;element name="obps" type="{http://www.onem2m.org/xml/protocols}listOfURIs" minOccurs="0"/&gt;
 *         &lt;element name="dc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mgmtResource", propOrder = {
    "mgd",
    "obis",
    "obps",
    "dc"
})
@XmlSeeAlso({
    Acmp.class,
    Andi.class,
    Ani.class,
    Bat.class,
    Cmbf.class,
    Cmdf.class,
    Cmdv.class,
    Cmpv.class,
    Cml.class,
    Cmnr.class,
    Cmwr.class,
    Cmp.class,
    Mem.class,
    Fwr.class,
    Swr.class,
    Dvi.class,
    Dvc.class,
    Rbo.class,
    Evl.class
})
public class MgmtResource
    extends AnnounceableResource
{

    @XmlElement(required = true)
    protected BigInteger mgd;
    @XmlList
    protected List<String> obis;
    @XmlList
    protected List<String> obps;
    protected String dc;

    /**
     * Gets the value of the mgd property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMgd() {
        return mgd;
    }

    /**
     * Sets the value of the mgd property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMgd(BigInteger value) {
        this.mgd = value;
    }

    /**
     * Gets the value of the obis property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the obis property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObis().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getObis() {
        if (obis == null) {
            obis = new ArrayList<String>();
        }
        return this.obis;
    }

    /**
     * Gets the value of the obps property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the obps property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObps().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getObps() {
        if (obps == null) {
            obps = new ArrayList<String>();
        }
        return this.obps;
    }

    /**
     * Gets the value of the dc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDc() {
        return dc;
    }

    /**
     * Sets the value of the dc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDc(String value) {
        this.dc = value;
    }

}
