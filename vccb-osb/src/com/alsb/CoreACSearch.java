
package com.alsb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="P_AC_CLASS_TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="P_MAKERID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="P_CHECKERID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "pacclasstype",
    "pmakerid",
    "pcheckerid"
})
@XmlRootElement(name = "CoreACSearch")
public class CoreACSearch {

    @XmlElement(name = "P_AC_CLASS_TYPE", required = true)
    protected String pacclasstype;
    @XmlElement(name = "P_MAKERID", required = true)
    protected String pmakerid;
    @XmlElement(name = "P_CHECKERID", required = true)
    protected String pcheckerid;

    /**
     * Gets the value of the pacclasstype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACCLASSTYPE() {
        return pacclasstype;
    }

    /**
     * Sets the value of the pacclasstype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACCLASSTYPE(String value) {
        this.pacclasstype = value;
    }

    /**
     * Gets the value of the pmakerid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPMAKERID() {
        return pmakerid;
    }

    /**
     * Sets the value of the pmakerid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPMAKERID(String value) {
        this.pmakerid = value;
    }

    /**
     * Gets the value of the pcheckerid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPCHECKERID() {
        return pcheckerid;
    }

    /**
     * Sets the value of the pcheckerid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPCHECKERID(String value) {
        this.pcheckerid = value;
    }

}
