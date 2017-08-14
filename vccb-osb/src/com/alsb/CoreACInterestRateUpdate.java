
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
 *         &lt;element name="SourceRef" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="AccountClass" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CCYCD" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="EffectiveDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="BRID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="InterestRateList"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded"&gt;
 *                   &lt;element name="InterestRate"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="ParamCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="InterestRate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="InterestRateCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "accountClass",
    "ccycd",
    "effectiveDate",
    "brid",
    "interestRateList",
    "maker",
    "approver"
})
@XmlRootElement(name = "CoreACInterestRateUpdate")
public class CoreACInterestRateUpdate {

    @XmlElement(name = "SourceRef", required = true)
    protected String sourceRef;
    @XmlElement(name = "AccountClass", required = true)
    protected String accountClass;
    @XmlElement(name = "CCYCD", required = true)
    protected String ccycd;
    @XmlElement(name = "EffectiveDate", required = true)
    protected String effectiveDate;
    @XmlElement(name = "BRID", required = true)
    protected String brid;
    @XmlElement(name = "InterestRateList", required = true)
    protected CoreACInterestRateUpdate.InterestRateList interestRateList;
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
     * Gets the value of the accountClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountClass() {
        return accountClass;
    }

    /**
     * Sets the value of the accountClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountClass(String value) {
        this.accountClass = value;
    }

    /**
     * Gets the value of the ccycd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCCYCD() {
        return ccycd;
    }

    /**
     * Sets the value of the ccycd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCCYCD(String value) {
        this.ccycd = value;
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
     * Gets the value of the interestRateList property.
     * 
     * @return
     *     possible object is
     *     {@link CoreACInterestRateUpdate.InterestRateList }
     *     
     */
    public CoreACInterestRateUpdate.InterestRateList getInterestRateList() {
        return interestRateList;
    }

    /**
     * Sets the value of the interestRateList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoreACInterestRateUpdate.InterestRateList }
     *     
     */
    public void setInterestRateList(CoreACInterestRateUpdate.InterestRateList value) {
        this.interestRateList = value;
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


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence maxOccurs="unbounded"&gt;
     *         &lt;element name="InterestRate"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="ParamCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="InterestRate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="InterestRateCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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

        @XmlElement(name = "InterestRate", required = true)
        protected List<CoreACInterestRateUpdate.InterestRateList.InterestRate> interestRate;

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
         * {@link CoreACInterestRateUpdate.InterestRateList.InterestRate }
         * 
         * 
         */
        public List<CoreACInterestRateUpdate.InterestRateList.InterestRate> getInterestRate() {
            if (interestRate == null) {
                interestRate = new ArrayList<CoreACInterestRateUpdate.InterestRateList.InterestRate>();
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
         *         &lt;element name="InterestRate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="InterestRateCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
            "interestRate",
            "interestRateCode"
        })
        public static class InterestRate {

            @XmlElement(name = "ParamCode", required = true)
            protected String paramCode;
            @XmlElement(name = "InterestRate", required = true)
            protected String interestRate;
            @XmlElement(name = "InterestRateCode", required = true)
            protected String interestRateCode;

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

        }

    }

}
