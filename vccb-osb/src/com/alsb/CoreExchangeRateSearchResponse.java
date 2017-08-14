
package com.alsb;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="ExchangeRateList"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
 *                   &lt;element name="ExchangeRate"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="ExchangeRateTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="AverageRate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="BuyAmplitude" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="SellAmplitude" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="BuyRate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="SellRate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="EffectiveDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="NumOfChange" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
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
    "exchangeRateList"
})
@XmlRootElement(name = "CoreExchangeRateSearchResponse")
public class CoreExchangeRateSearchResponse {

    @XmlElement(name = "ExchangeRateList", required = true)
    protected CoreExchangeRateSearchResponse.ExchangeRateList exchangeRateList;

    /**
     * Gets the value of the exchangeRateList property.
     * 
     * @return
     *     possible object is
     *     {@link CoreExchangeRateSearchResponse.ExchangeRateList }
     *     
     */
    public CoreExchangeRateSearchResponse.ExchangeRateList getExchangeRateList() {
        return exchangeRateList;
    }

    /**
     * Sets the value of the exchangeRateList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoreExchangeRateSearchResponse.ExchangeRateList }
     *     
     */
    public void setExchangeRateList(CoreExchangeRateSearchResponse.ExchangeRateList value) {
        this.exchangeRateList = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
     *         &lt;element name="ExchangeRate"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="ExchangeRateTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="AverageRate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="BuyAmplitude" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="SellAmplitude" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="BuyRate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="SellRate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="EffectiveDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="NumOfChange" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
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
        "exchangeRate"
    })
    public static class ExchangeRateList {

        @XmlElement(name = "ExchangeRate")
        protected List<CoreExchangeRateSearchResponse.ExchangeRateList.ExchangeRate> exchangeRate;

        /**
         * Gets the value of the exchangeRate property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the exchangeRate property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getExchangeRate().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CoreExchangeRateSearchResponse.ExchangeRateList.ExchangeRate }
         * 
         * 
         */
        public List<CoreExchangeRateSearchResponse.ExchangeRateList.ExchangeRate> getExchangeRate() {
            if (exchangeRate == null) {
                exchangeRate = new ArrayList<CoreExchangeRateSearchResponse.ExchangeRateList.ExchangeRate>();
            }
            return this.exchangeRate;
        }


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
         *         &lt;element name="ExchangeRateTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="AverageRate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="BuyAmplitude" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="SellAmplitude" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="BuyRate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="SellRate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="EffectiveDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="NumOfChange" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
            "exchangeRateTypeCode",
            "averageRate",
            "buyAmplitude",
            "sellAmplitude",
            "buyRate",
            "sellRate",
            "effectiveDate",
            "numOfChange"
        })
        public static class ExchangeRate {

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
            @XmlElement(name = "EffectiveDate", required = true)
            protected String effectiveDate;
            @XmlElement(name = "NumOfChange", required = true)
            protected String numOfChange;

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
             * Gets the value of the effectiveDate property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEffectiveDate() {
                return effectiveDate;
            }

            /**
             * Sets the value of the effectiveDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEffectiveDate(String value) {
                this.effectiveDate = value;
            }

            /**
             * Gets the value of the numOfChange property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNumOfChange() {
                return numOfChange;
            }

            /**
             * Sets the value of the numOfChange property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNumOfChange(String value) {
                this.numOfChange = value;
            }

        }

    }

}
