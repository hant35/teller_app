
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
 *         &lt;element name="P_MSGID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="P_ACCLASSFROM" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="P_ACCLASSTO" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="P_OPERATIONUSER" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "pmsgid",
    "pacclassfrom",
    "pacclassto",
    "poperationuser",
    "pmakerid",
    "pcheckerid"
})
@XmlRootElement(name = "CoreACClassTransfer")
public class CoreACClassTransfer {

    @XmlElement(name = "P_MSGID", required = true)
    protected String pmsgid;
    @XmlElement(name = "P_ACCLASSFROM", required = true)
    protected String pacclassfrom;
    @XmlElement(name = "P_ACCLASSTO", required = true)
    protected String pacclassto;
    @XmlElement(name = "P_OPERATIONUSER", required = true)
    protected String poperationuser;
    @XmlElement(name = "P_MAKERID", required = true)
    protected String pmakerid;
    @XmlElement(name = "P_CHECKERID", required = true)
    protected String pcheckerid;

    /**
     * Gets the value of the pmsgid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPMSGID() {
        return pmsgid;
    }

    /**
     * Sets the value of the pmsgid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPMSGID(String value) {
        this.pmsgid = value;
    }

    /**
     * Gets the value of the pacclassfrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACCLASSFROM() {
        return pacclassfrom;
    }

    /**
     * Sets the value of the pacclassfrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACCLASSFROM(String value) {
        this.pacclassfrom = value;
    }

    /**
     * Gets the value of the pacclassto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACCLASSTO() {
        return pacclassto;
    }

    /**
     * Sets the value of the pacclassto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACCLASSTO(String value) {
        this.pacclassto = value;
    }

    /**
     * Gets the value of the poperationuser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOPERATIONUSER() {
        return poperationuser;
    }

    /**
     * Sets the value of the poperationuser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOPERATIONUSER(String value) {
        this.poperationuser = value;
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
