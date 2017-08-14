
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
 *         &lt;element name="P_BRANCH" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="P_CCY1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="P_CCY2" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "pbranch",
    "pccy1",
    "pccy2",
    "pmaker",
    "pchecker"
})
@XmlRootElement(name = "CoreExchangeRateSearch")
public class CoreExchangeRateSearch {

    @XmlElement(name = "P_BRANCH", required = true)
    protected String pbranch;
    @XmlElement(name = "P_CCY1", required = true)
    protected String pccy1;
    @XmlElement(name = "P_CCY2", required = true)
    protected String pccy2;
    @XmlElement(name = "P_MAKER", required = true)
    protected String pmaker;
    @XmlElement(name = "P_CHECKER", required = true)
    protected String pchecker;

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
     * Gets the value of the pccy1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPCCY1() {
        return pccy1;
    }

    /**
     * Sets the value of the pccy1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPCCY1(String value) {
        this.pccy1 = value;
    }

    /**
     * Gets the value of the pccy2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPCCY2() {
        return pccy2;
    }

    /**
     * Sets the value of the pccy2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPCCY2(String value) {
        this.pccy2 = value;
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
