//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.16 at 09:48:33 AM CET 
//


package org.eclipse.basyx.onem2m.xml.protocols;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deletionContexts complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deletionContexts"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tod" type="{http://www.onem2m.org/xml/protocols}scheduleEntry" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="lr" type="{http://www.onem2m.org/xml/protocols}locationRegion" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deletionContexts", propOrder = {
    "tod",
    "lr"
})
public class DeletionContexts {

    protected List<String> tod;
    protected List<LocationRegion> lr;

    /**
     * Gets the value of the tod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTod() {
        if (tod == null) {
            tod = new ArrayList<String>();
        }
        return this.tod;
    }

    /**
     * Gets the value of the lr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLr().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocationRegion }
     * 
     * 
     */
    public List<LocationRegion> getLr() {
        if (lr == null) {
            lr = new ArrayList<LocationRegion>();
        }
        return this.lr;
    }

}
