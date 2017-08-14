
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
 *         &lt;element name="AccountClassList"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
 *                   &lt;element name="AccountClass"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="ACCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="ACName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="Term" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="CCY" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="DinhKyGui" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="Rd_min_ins" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="Min_amt" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="ACChuyenKhiDaoHan" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="HinhThucDaoHan" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="MinRemainingBalance" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="OverDraft" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="InterestReceiveType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="SPTichLuy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "accountClassList"
})
@XmlRootElement(name = "CoreACSearchResponse")
public class CoreACSearchResponse {

    @XmlElement(name = "AccountClassList", required = true)
    protected CoreACSearchResponse.AccountClassList accountClassList;

    /**
     * Gets the value of the accountClassList property.
     * 
     * @return
     *     possible object is
     *     {@link CoreACSearchResponse.AccountClassList }
     *     
     */
    public CoreACSearchResponse.AccountClassList getAccountClassList() {
        return accountClassList;
    }

    /**
     * Sets the value of the accountClassList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoreACSearchResponse.AccountClassList }
     *     
     */
    public void setAccountClassList(CoreACSearchResponse.AccountClassList value) {
        this.accountClassList = value;
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
     *         &lt;element name="AccountClass"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="ACCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="ACName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="Term" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="CCY" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="DinhKyGui" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="Rd_min_ins" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="Min_amt" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="ACChuyenKhiDaoHan" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="HinhThucDaoHan" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="MinRemainingBalance" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="OverDraft" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="InterestReceiveType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="SPTichLuy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "accountClass"
    })
    public static class AccountClassList {

        @XmlElement(name = "AccountClass")
        protected List<CoreACSearchResponse.AccountClassList.AccountClass> accountClass;

        /**
         * Gets the value of the accountClass property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the accountClass property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAccountClass().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CoreACSearchResponse.AccountClassList.AccountClass }
         * 
         * 
         */
        public List<CoreACSearchResponse.AccountClassList.AccountClass> getAccountClass() {
            if (accountClass == null) {
                accountClass = new ArrayList<CoreACSearchResponse.AccountClassList.AccountClass>();
            }
            return this.accountClass;
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
         *         &lt;element name="ACCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="ACName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="Term" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="CCY" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="DinhKyGui" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="Rd_min_ins" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="Min_amt" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="ACChuyenKhiDaoHan" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="HinhThucDaoHan" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="MinRemainingBalance" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="OverDraft" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="InterestReceiveType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="SPTichLuy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
            "acCode",
            "acName",
            "term",
            "ccy",
            "dinhKyGui",
            "rdMinIns",
            "minAmt",
            "acChuyenKhiDaoHan",
            "hinhThucDaoHan",
            "minRemainingBalance",
            "overDraft",
            "interestReceiveType",
            "spTichLuy"
        })
        public static class AccountClass {

            @XmlElement(name = "ACCode", required = true)
            protected String acCode;
            @XmlElement(name = "ACName", required = true)
            protected String acName;
            @XmlElement(name = "Term", required = true)
            protected String term;
            @XmlElement(name = "CCY", required = true)
            protected String ccy;
            @XmlElement(name = "DinhKyGui", required = true)
            protected String dinhKyGui;
            @XmlElement(name = "Rd_min_ins", required = true)
            protected String rdMinIns;
            @XmlElement(name = "Min_amt", required = true)
            protected String minAmt;
            @XmlElement(name = "ACChuyenKhiDaoHan", required = true)
            protected String acChuyenKhiDaoHan;
            @XmlElement(name = "HinhThucDaoHan", required = true)
            protected String hinhThucDaoHan;
            @XmlElement(name = "MinRemainingBalance", required = true)
            protected String minRemainingBalance;
            @XmlElement(name = "OverDraft", required = true)
            protected String overDraft;
            @XmlElement(name = "InterestReceiveType", required = true)
            protected String interestReceiveType;
            @XmlElement(name = "SPTichLuy", required = true)
            protected String spTichLuy;

            /**
             * Gets the value of the acCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getACCode() {
                return acCode;
            }

            /**
             * Sets the value of the acCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setACCode(String value) {
                this.acCode = value;
            }

            /**
             * Gets the value of the acName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getACName() {
                return acName;
            }

            /**
             * Sets the value of the acName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setACName(String value) {
                this.acName = value;
            }

            /**
             * Gets the value of the term property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTerm() {
                return term;
            }

            /**
             * Sets the value of the term property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTerm(String value) {
                this.term = value;
            }

            /**
             * Gets the value of the ccy property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCCY() {
                return ccy;
            }

            /**
             * Sets the value of the ccy property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCCY(String value) {
                this.ccy = value;
            }

            /**
             * Gets the value of the dinhKyGui property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDinhKyGui() {
                return dinhKyGui;
            }

            /**
             * Sets the value of the dinhKyGui property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDinhKyGui(String value) {
                this.dinhKyGui = value;
            }

            /**
             * Gets the value of the rdMinIns property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRdMinIns() {
                return rdMinIns;
            }

            /**
             * Sets the value of the rdMinIns property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRdMinIns(String value) {
                this.rdMinIns = value;
            }

            /**
             * Gets the value of the minAmt property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMinAmt() {
                return minAmt;
            }

            /**
             * Sets the value of the minAmt property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMinAmt(String value) {
                this.minAmt = value;
            }

            /**
             * Gets the value of the acChuyenKhiDaoHan property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getACChuyenKhiDaoHan() {
                return acChuyenKhiDaoHan;
            }

            /**
             * Sets the value of the acChuyenKhiDaoHan property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setACChuyenKhiDaoHan(String value) {
                this.acChuyenKhiDaoHan = value;
            }

            /**
             * Gets the value of the hinhThucDaoHan property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getHinhThucDaoHan() {
                return hinhThucDaoHan;
            }

            /**
             * Sets the value of the hinhThucDaoHan property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setHinhThucDaoHan(String value) {
                this.hinhThucDaoHan = value;
            }

            /**
             * Gets the value of the minRemainingBalance property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMinRemainingBalance() {
                return minRemainingBalance;
            }

            /**
             * Sets the value of the minRemainingBalance property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMinRemainingBalance(String value) {
                this.minRemainingBalance = value;
            }

            /**
             * Gets the value of the overDraft property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOverDraft() {
                return overDraft;
            }

            /**
             * Sets the value of the overDraft property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOverDraft(String value) {
                this.overDraft = value;
            }

            /**
             * Gets the value of the interestReceiveType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getInterestReceiveType() {
                return interestReceiveType;
            }

            /**
             * Sets the value of the interestReceiveType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setInterestReceiveType(String value) {
                this.interestReceiveType = value;
            }

            /**
             * Gets the value of the spTichLuy property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSPTichLuy() {
                return spTichLuy;
            }

            /**
             * Sets the value of the spTichLuy property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSPTichLuy(String value) {
                this.spTichLuy = value;
            }

        }

    }

}
