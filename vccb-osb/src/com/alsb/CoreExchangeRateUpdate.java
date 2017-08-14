
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
 *         &lt;element name="SourceRef" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="BRID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CCY1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CCY2" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ExchangeRateTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="AverageRate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="BuyAmplitude" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SellAmplitude" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="BuyRate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SellRate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="EffectDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Maker" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Approver" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "sourceRef",
    "brid",
    "ccy1",
    "ccy2",
    "exchangeRateTypeCode",
    "averageRate",
    "buyAmplitude",
    "sellAmplitude",
    "buyRate",
    "sellRate",
    "effectDate",
    "maker",
    "approver"
})
@XmlRootElement(name = "CoreExchangeRateUpdate")
public class CoreExchangeRateUpdate {

    @XmlElement(name = "SourceRef", required = true)
    protected String sourceRef;
    @XmlElement(name = "BRID", required = true)
    protected String brid;
    @XmlElement(name = "CCY1", required = true)
    protected String ccy1;
    @XmlElement(name = "CCY2", required = true)
    protected String ccy2;
    @XmlElement(name = "ExchangeRateTypeCode", required = true)
    protected String exchangeRateTypeCode;
    @XmlElement(name = "AverageRate", required = true)
    protected String averageRate;
    @XmlElement(name = "BuyAmplitude", required = true)
    protected String buyAmplitude;
    @XmlElement(name = "SellAmplitude", required = true)
    protected String sellAmplitude;
    @XmlElement(name = "BuyRate", required = true)
    protected String buyRate;
    @XmlElement(name = "SellRate", required = true)
    protected String sellRate;
    @XmlElement(name = "EffectDate", required = true)
    protected String effectDate;
    @XmlElement(name = "Maker", required = true)
    protected String maker;
    @XmlElement(name = "Approver", required = true)
    protected String approver;

    /**
     * Gets the value of the sourceRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceRef() {
        return sourceRef;
    }

    /**
     * Sets the value of the sourceRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceRef(String value) {
        this.sourceRef = value;
    }

    /**
     * Gets the value of the brid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBRID() {
        return brid;
    }

    /**
     * Sets the value of the brid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBRID(String value) {
        this.brid = value;
    }

    /**
     * Gets the value of the ccy1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCCY1() {
        return ccy1;
    }

    /**
     * Sets the value of the ccy1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCCY1(String value) {
        this.ccy1 = value;
    }

    /**
     * Gets the value of the ccy2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCCY2() {
        return ccy2;
    }

    /**
     * Sets the value of the ccy2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCCY2(String value) {
        this.ccy2 = value;
    }

    /**
     * Gets the value of the exchangeRateTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExchangeRateTypeCode() {
        return exchangeRateTypeCode;
    }

    /**
     * Sets the value of the exchangeRateTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExchangeRateTypeCode(String value) {
        this.exchangeRateTypeCode = value;
    }

    /**
     * Gets the value of the averageRate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAverageRate() {
        return averageRate;
    }

    /**
     * Sets the value of the averageRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAverageRate(String value) {
        this.averageRate = value;
    }

    /**
     * Gets the value of the buyAmplitude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuyAmplitude() {
        return buyAmplitude;
    }

    /**
     * Sets the value of the buyAmplitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuyAmplitude(String value) {
        this.buyAmplitude = value;
    }

    /**
     * Gets the value of the sellAmplitude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellAmplitude() {
        return sellAmplitude;
    }

    /**
     * Sets the value of the sellAmplitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellAmplitude(String value) {
        this.sellAmplitude = value;
    }

    /**
     * Gets the value of the buyRate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuyRate() {
        return buyRate;
    }

    /**
     * Sets the value of the buyRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuyRate(String value) {
        this.buyRate = value;
    }

    /**
     * Gets the value of the sellRate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSellRate() {
        return sellRate;
    }

    /**
     * Sets the value of the sellRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSellRate(String value) {
        this.sellRate = value;
    }

    /**
     * Gets the value of the effectDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEffectDate() {
        return effectDate;
    }

    /**
     * Sets the value of the effectDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffectDate(String value) {
        this.effectDate = value;
    }

    /**
     * Gets the value of the maker property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaker() {
        return maker;
    }

    /**
     * Sets the value of the maker property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaker(String value) {
        this.maker = value;
    }

    /**
     * Gets the value of the approver property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApprover() {
        return approver;
    }

    /**
     * Sets the value of the approver property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApprover(String value) {
        this.approver = value;
    }

}
