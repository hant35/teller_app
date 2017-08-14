
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
 *         &lt;element name="InterestRateList"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
 *                   &lt;element name="InterestRate"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="ParamCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="InterestRateCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="InterestRate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "interestRateList"
})
@XmlRootElement(name = "CoreACInterestRateSearchResponse")
public class CoreACInterestRateSearchResponse {

    @XmlElement(name = "InterestRateList", required = true)
    protected CoreACInterestRateSearchResponse.InterestRateList interestRateList;

    /**
     * Gets the value of the interestRateList property.
     * 
     * @return
     *     possible object is
     *     {@link CoreACInterestRateSearchResponse.InterestRateList }
     *     
     */
    public CoreACInterestRateSearchResponse.InterestRateList getInterestRateList() {
        return interestRateList;
    }

    /**
     * Sets the value of the interestRateList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoreACInterestRateSearchResponse.InterestRateList }
     *     
     */
    public void setInterestRateList(CoreACInterestRateSearchResponse.InterestRateList value) {
        this.interestRateList = value;
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
     *         &lt;element name="InterestRate"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="ParamCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="InterestRateCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="InterestRate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "interestRate"
    })
    public static class InterestRateList {

        @XmlElement(name = "InterestRate")
        protected List<CoreACInterestRateSearchResponse.InterestRateList.InterestRate> interestRate;

        /**
         * Gets the value of the interestRate property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the interestRate property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInterestRate().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CoreACInterestRateSearchResponse.InterestRateList.InterestRate }
         * 
         * 
         */
        public List<CoreACInterestRateSearchResponse.InterestRateList.InterestRate> getInterestRate() {
            if (interestRate == null) {
                interestRate = new ArrayList<CoreACInterestRateSearchResponse.InterestRateList.InterestRate>();
            }
            return this.interestRate;
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
         *         &lt;element name="ParamCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="InterestRateCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="InterestRate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
            "paramCode",
            "interestRateCode",
            "interestRate"
        })
        public static class InterestRate {

            @XmlElement(name = "ParamCode", required = true)
            protected String paramCode;
            @XmlElement(name = "InterestRateCode", required = true)
            protected String interestRateCode;
            @XmlElement(name = "InterestRate", required = true)
            protected String interestRate;

            /**
             * Gets the value of the paramCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getParamCode() {
                return paramCode;
            }

            /**
             * Sets the value of the paramCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setParamCode(String value) {
                this.paramCode = value;
            }

            /**
             * Gets the value of the interestRateCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getInterestRateCode() {
                return interestRateCode;
            }

            /**
             * Sets the value of the interestRateCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setInterestRateCode(String value) {
                this.interestRateCode = value;
            }

            /**
             * Gets the value of the interestRate property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getInterestRate() {
                return interestRate;
            }

            /**
             * Sets the value of the interestRate property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setInterestRate(String value) {
                this.interestRate = value;
            }

        }

    }

}
