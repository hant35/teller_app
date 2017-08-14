
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
 *         &lt;element name="P_AC_CLASS" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="P_CCY" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="P_EFFECTIVE_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="P_BRANCH" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="P_MAKER" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="P_CHECKER" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "pacclass",
    "pccy",
    "peffectivedate",
    "pbranch",
    "pmaker",
    "pchecker"
})
@XmlRootElement(name = "CoreACInterestRateSearch")
public class CoreACInterestRateSearch {

    @XmlElement(name = "P_AC_CLASS", required = true)
    protected String pacclass;
    @XmlElement(name = "P_CCY", required = true)
    protected String pccy;
    @XmlElement(name = "P_EFFECTIVE_DATE", required = true)
    protected String peffectivedate;
    @XmlElement(name = "P_BRANCH", required = true)
    protected String pbranch;
    @XmlElement(name = "P_MAKER", required = true)
    protected String pmaker;
    @XmlElement(name = "P_CHECKER", required = true)
    protected String pchecker;

    /**
     * Gets the value of the pacclass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACCLASS() {
        return pacclass;
    }

    /**
     * Sets the value of the pacclass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACCLASS(String value) {
        this.pacclass = value;
    }

    /**
     * Gets the value of the pccy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPCCY() {
        return pccy;
    }

    /**
     * Sets the value of the pccy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPCCY(String value) {
        this.pccy = value;
    }

    /**
     * Gets the value of the peffectivedate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPEFFECTIVEDATE() {
        return peffectivedate;
    }

    /**
     * Sets the value of the peffectivedate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPEFFECTIVEDATE(String value) {
        this.peffectivedate = value;
    }

    /**
     * Gets the value of the pbranch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPBRANCH() {
        return pbranch;
    }

    /**
     * Sets the value of the pbranch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPBRANCH(String value) {
        this.pbranch = value;
    }

    /**
     * Gets the value of the pmaker property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPMAKER() {
        return pmaker;
    }

    /**
     * Sets the value of the pmaker property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPMAKER(String value) {
        this.pmaker = value;
    }

    /**
     * Gets the value of the pchecker property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPCHECKER() {
        return pchecker;
    }

    /**
     * Sets the value of the pchecker property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPCHECKER(String value) {
        this.pchecker = value;
    }

}
