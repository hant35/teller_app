
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
 *         &lt;element name="ExchangeRateTypeList"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
 *                   &lt;element name="ExchangeRateType"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="ExchangeRateTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="ExchangeRateTypeName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "exchangeRateTypeList"
})
@XmlRootElement(name = "CoreExchangeRateTypeSearchResponse")
public class CoreExchangeRateTypeSearchResponse {

    @XmlElement(name = "ExchangeRateTypeList", required = true)
    protected CoreExchangeRateTypeSearchResponse.ExchangeRateTypeList exchangeRateTypeList;

    /**
     * Gets the value of the exchangeRateTypeList property.
     * 
     * @return
     *     possible object is
     *     {@link CoreExchangeRateTypeSearchResponse.ExchangeRateTypeList }
     *     
     */
    public CoreExchangeRateTypeSearchResponse.ExchangeRateTypeList getExchangeRateTypeList() {
        return exchangeRateTypeList;
    }

    /**
     * Sets the value of the exchangeRateTypeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoreExchangeRateTypeSearchResponse.ExchangeRateTypeList }
     *     
     */
    public void setExchangeRateTypeList(CoreExchangeRateTypeSearchResponse.ExchangeRateTypeList value) {
        this.exchangeRateTypeList = value;
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
     *         &lt;element name="ExchangeRateType"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="ExchangeRateTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="ExchangeRateTypeName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "exchangeRateType"
    })
    public static class ExchangeRateTypeList {

        @XmlElement(name = "ExchangeRateType")
        protected List<CoreExchangeRateTypeSearchResponse.ExchangeRateTypeList.ExchangeRateType> exchangeRateType;

        /**
         * Gets the value of the exchangeRateType property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the exchangeRateType property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getExchangeRateType().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CoreExchangeRateTypeSearchResponse.ExchangeRateTypeList.ExchangeRateType }
         * 
         * 
         */
        public List<CoreExchangeRateTypeSearchResponse.ExchangeRateTypeList.ExchangeRateType> getExchangeRateType() {
            if (exchangeRateType == null) {
                exchangeRateType = new ArrayList<CoreExchangeRateTypeSearchResponse.ExchangeRateTypeList.ExchangeRateType>();
            }
            return this.exchangeRateType;
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
         *         &lt;element name="ExchangeRateTypeName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
            "exchangeRateTypeName"
        })
        public static class ExchangeRateType {

            @XmlElement(name = "ExchangeRateTypeCode", required = true)
            protected String exchangeRateTypeCode;
            @XmlElement(name = "ExchangeRateTypeName", required = true)
            protected String exchangeRateTypeName;

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
             * Gets the value of the exchangeRateTypeName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getExchangeRateTypeName() {
                return exchangeRateTypeName;
            }

            /**
             * Sets the value of the exchangeRateTypeName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setExchangeRateTypeName(String value) {
                this.exchangeRateTypeName = value;
            }

        }

    }

}
