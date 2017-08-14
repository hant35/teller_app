
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
 *         &lt;element name="InterestRateCodeList"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
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
    "interestRateCodeList"
})
@XmlRootElement(name = "CoreInterestRateCodeGetResponse")
public class CoreInterestRateCodeGetResponse {

    @XmlElement(name = "InterestRateCodeList", required = true)
    protected CoreInterestRateCodeGetResponse.InterestRateCodeList interestRateCodeList;

    /**
     * Gets the value of the interestRateCodeList property.
     * 
     * @return
     *     possible object is
     *     {@link CoreInterestRateCodeGetResponse.InterestRateCodeList }
     *     
     */
    public CoreInterestRateCodeGetResponse.InterestRateCodeList getInterestRateCodeList() {
        return interestRateCodeList;
    }

    /**
     * Sets the value of the interestRateCodeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoreInterestRateCodeGetResponse.InterestRateCodeList }
     *     
     */
    public void setInterestRateCodeList(CoreInterestRateCodeGetResponse.InterestRateCodeList value) {
        this.interestRateCodeList = value;
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
        "interestRateCode"
    })
    public static class InterestRateCodeList {

        @XmlElement(name = "InterestRateCode")
        protected List<String> interestRateCode;

        /**
         * Gets the value of the interestRateCode property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the interestRateCode property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInterestRateCode().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getInterestRateCode() {
            if (interestRateCode == null) {
                interestRateCode = new ArrayList<String>();
            }
            return this.interestRateCode;
        }

    }

}
