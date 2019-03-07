//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.16 at 09:48:33 AM CET 
//


package org.eclipse.basyx.onem2m.xml.protocols;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.onem2m.org/xml/protocols}announcedResource"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="apn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="api" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="aei" type="{http://www.onem2m.org/xml/protocols}ID" minOccurs="0"/&gt;
 *         &lt;element name="poa" type="{http://www.onem2m.org/xml/protocols}poaList" minOccurs="0"/&gt;
 *         &lt;element name="or" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *         &lt;element name="nl" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *         &lt;element name="rr" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="csz" type="{http://www.onem2m.org/xml/protocols}serializations" minOccurs="0"/&gt;
 *         &lt;element name="esi" type="{http://www.onem2m.org/xml/protocols}e2eSecInfo" minOccurs="0"/&gt;
 *         &lt;choice minOccurs="0"&gt;
 *           &lt;element name="ch" type="{http://www.onem2m.org/xml/protocols}childResourceRef" maxOccurs="unbounded"/&gt;
 *           &lt;choice maxOccurs="unbounded"&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}cnt"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}cntA"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}grp"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}grpA"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}acp"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}acpA"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}sub"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}schA"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}smd"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}ts"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}tsa"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}trptA"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}sg_flexContainerResource"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}sg_announcedFlexContainerResource"/&gt;
 *           &lt;/choice&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "apn",
    "api",
    "aei",
    "poa",
    "or",
    "nl",
    "rr",
    "csz",
    "esi",
    "ch",
    "cntOrCntAOrGrp"
})
public class AeA
    extends AnnouncedResource
{

    protected String apn;
    protected String api;
    @XmlSchemaType(name = "anyURI")
    protected String aei;
    @XmlList
    protected List<String> poa;
    @XmlSchemaType(name = "anyURI")
    protected String or;
    @XmlSchemaType(name = "anyURI")
    protected String nl;
    protected Boolean rr;
    @XmlList
    protected List<PermittedMediaTypes> csz;
    protected E2ESecInfo esi;
    protected List<ChildResourceRef> ch;
    @XmlElementRefs({
        @XmlElementRef(name = "grp", namespace = "http://www.onem2m.org/xml/protocols", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "ts", namespace = "http://www.onem2m.org/xml/protocols", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "trptA", namespace = "http://www.onem2m.org/xml/protocols", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "cnt", namespace = "http://www.onem2m.org/xml/protocols", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "cntA", namespace = "http://www.onem2m.org/xml/protocols", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "sg_announcedFlexContainerResource", namespace = "http://www.onem2m.org/xml/protocols", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "sub", namespace = "http://www.onem2m.org/xml/protocols", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "smd", namespace = "http://www.onem2m.org/xml/protocols", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "grpA", namespace = "http://www.onem2m.org/xml/protocols", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "sg_flexContainerResource", namespace = "http://www.onem2m.org/xml/protocols", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "tsa", namespace = "http://www.onem2m.org/xml/protocols", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "acp", namespace = "http://www.onem2m.org/xml/protocols", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "acpA", namespace = "http://www.onem2m.org/xml/protocols", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "schA", namespace = "http://www.onem2m.org/xml/protocols", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> cntOrCntAOrGrp;

    /**
     * Gets the value of the apn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApn() {
        return apn;
    }

    /**
     * Sets the value of the apn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApn(String value) {
        this.apn = value;
    }

    /**
     * Gets the value of the api property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApi() {
        return api;
    }

    /**
     * Sets the value of the api property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApi(String value) {
        this.api = value;
    }

    /**
     * Gets the value of the aei property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAei() {
        return aei;
    }

    /**
     * Sets the value of the aei property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAei(String value) {
        this.aei = value;
    }

    /**
     * Gets the value of the poa property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the poa property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPoa().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPoa() {
        if (poa == null) {
            poa = new ArrayList<String>();
        }
        return this.poa;
    }

    /**
     * Gets the value of the or property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOr() {
        return or;
    }

    /**
     * Sets the value of the or property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOr(String value) {
        this.or = value;
    }

    /**
     * Gets the value of the nl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNl() {
        return nl;
    }

    /**
     * Sets the value of the nl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNl(String value) {
        this.nl = value;
    }

    /**
     * Gets the value of the rr property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRr() {
        return rr;
    }

    /**
     * Sets the value of the rr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRr(Boolean value) {
        this.rr = value;
    }

    /**
     * Gets the value of the csz property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the csz property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCsz().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PermittedMediaTypes }
     * 
     * 
     */
    public List<PermittedMediaTypes> getCsz() {
        if (csz == null) {
            csz = new ArrayList<PermittedMediaTypes>();
        }
        return this.csz;
    }

    /**
     * Gets the value of the esi property.
     * 
     * @return
     *     possible object is
     *     {@link E2ESecInfo }
     *     
     */
    public E2ESecInfo getEsi() {
        return esi;
    }

    /**
     * Sets the value of the esi property.
     * 
     * @param value
     *     allowed object is
     *     {@link E2ESecInfo }
     *     
     */
    public void setEsi(E2ESecInfo value) {
        this.esi = value;
    }

    /**
     * Gets the value of the ch property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ch property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCh().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChildResourceRef }
     * 
     * 
     */
    public List<ChildResourceRef> getCh() {
        if (ch == null) {
            ch = new ArrayList<ChildResourceRef>();
        }
        return this.ch;
    }

    /**
     * Gets the value of the cntOrCntAOrGrp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cntOrCntAOrGrp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCntOrCntAOrGrp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Ajmc }{@code >}
     * {@link JAXBElement }{@code <}{@link Ajif }{@code >}
     * {@link JAXBElement }{@code <}{@link Ajsw }{@code >}
     * {@link JAXBElement }{@code <}{@link Grp }{@code >}
     * {@link JAXBElement }{@code <}{@link Ajfw }{@code >}
     * {@link JAXBElement }{@code <}{@link Ajsoa }{@code >}
     * {@link JAXBElement }{@code <}{@link Ajswa }{@code >}
     * {@link JAXBElement }{@code <}{@link TrptA }{@code >}
     * {@link JAXBElement }{@code <}{@link Ajmda }{@code >}
     * {@link JAXBElement }{@code <}{@link Ajfwa }{@code >}
     * {@link JAXBElement }{@code <}{@link Smd }{@code >}
     * {@link JAXBElement }{@code <}{@link GrpA }{@code >}
     * {@link JAXBElement }{@code <}{@link Gioa }{@code >}
     * {@link JAXBElement }{@code <}{@link Tsa }{@code >}
     * {@link JAXBElement }{@code <}{@link Ajmd }{@code >}
     * {@link JAXBElement }{@code <}{@link Gis }{@code >}
     * {@link JAXBElement }{@code <}{@link SchA }{@code >}
     * {@link JAXBElement }{@code <}{@link Ajapa }{@code >}
     * {@link JAXBElement }{@code <}{@link Ts }{@code >}
     * {@link JAXBElement }{@code <}{@link Gio }{@code >}
     * {@link JAXBElement }{@code <}{@link Cnt }{@code >}
     * {@link JAXBElement }{@code <}{@link Ajpra }{@code >}
     * {@link JAXBElement }{@code <}{@link Gisa }{@code >}
     * {@link JAXBElement }{@code <}{@link Ajap }{@code >}
     * {@link JAXBElement }{@code <}{@link CntA }{@code >}
     * {@link JAXBElement }{@code <}{@link AnnouncedFlexContainerResource }{@code >}
     * {@link JAXBElement }{@code <}{@link Ajifa }{@code >}
     * {@link JAXBElement }{@code <}{@link Ajmca }{@code >}
     * {@link JAXBElement }{@code <}{@link Sub }{@code >}
     * {@link JAXBElement }{@code <}{@link Ajpr }{@code >}
     * {@link JAXBElement }{@code <}{@link Ajso }{@code >}
     * {@link JAXBElement }{@code <}{@link FlexContainerResource }{@code >}
     * {@link JAXBElement }{@code <}{@link Acp }{@code >}
     * {@link JAXBElement }{@code <}{@link AcpA }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getCntOrCntAOrGrp() {
        if (cntOrCntAOrGrp == null) {
            cntOrCntAOrGrp = new ArrayList<JAXBElement<?>>();
        }
        return this.cntOrCntAOrGrp;
    }

}