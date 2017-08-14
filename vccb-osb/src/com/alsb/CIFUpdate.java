
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
 *         &lt;element name="CIFCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="BRID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="FullName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ShortName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Gender" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="DateOfBirth" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="IsEmployee" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="TaxCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="IdentityType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="IdentityNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="IssueDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="IssuePlace" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ExpireDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Address1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Nationality" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Guardian" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Classification" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CustomerCategory" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ChargeGroup" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SWIFTCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="REUTERCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CITADCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="GIINCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Maker" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Checker" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "cifCode",
    "brid",
    "type",
    "fullName",
    "shortName",
    "gender",
    "dateOfBirth",
    "isEmployee",
    "taxCode",
    "identityType",
    "identityNumber",
    "issueDate",
    "issuePlace",
    "expireDate",
    "address1",
    "country",
    "nationality",
    "language",
    "guardian",
    "classification",
    "customerCategory",
    "chargeGroup",
    "swiftCode",
    "reuterCode",
    "citadCode",
    "giinCode",
    "maker",
    "checker"
})
@XmlRootElement(name = "CIFUpdate")
public class CIFUpdate {

    @XmlElement(name = "SourceRef", required = true)
    protected String sourceRef;
    @XmlElement(name = "CIFCode", required = true)
    protected String cifCode;
    @XmlElement(name = "BRID", required = true)
    protected String brid;
    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElement(name = "FullName", required = true)
    protected String fullName;
    @XmlElement(name = "ShortName", required = true)
    protected String shortName;
    @XmlElement(name = "Gender", required = true)
    protected String gender;
    @XmlElement(name = "DateOfBirth", required = true)
    protected String dateOfBirth;
    @XmlElement(name = "IsEmployee", required = true)
    protected String isEmployee;
    @XmlElement(name = "TaxCode", required = true)
    protected String taxCode;
    @XmlElement(name = "IdentityType", required = true)
    protected String identityType;
    @XmlElement(name = "IdentityNumber", required = true)
    protected String identityNumber;
    @XmlElement(name = "IssueDate", required = true)
    protected String issueDate;
    @XmlElement(name = "IssuePlace", required = true)
    protected String issuePlace;
    @XmlElement(name = "ExpireDate", required = true)
    protected String expireDate;
    @XmlElement(name = "Address1", required = true)
    protected String address1;
    @XmlElement(name = "Country", required = true)
    protected String country;
    @XmlElement(name = "Nationality", required = true)
    protected String nationality;
    @XmlElement(name = "Language", required = true)
    protected String language;
    @XmlElement(name = "Guardian", required = true)
    protected String guardian;
    @XmlElement(name = "Classification", required = true)
    protected String classification;
    @XmlElement(name = "CustomerCategory", required = true)
    protected String customerCategory;
    @XmlElement(name = "ChargeGroup", required = true)
    protected String chargeGroup;
    @XmlElement(name = "SWIFTCode", required = true)
    protected String swiftCode;
    @XmlElement(name = "REUTERCode", required = true)
    protected String reuterCode;
    @XmlElement(name = "CITADCode", required = true)
    protected String citadCode;
    @XmlElement(name = "GIINCode", required = true)
    protected String giinCode;
    @XmlElement(name = "Maker", required = true)
    protected String maker;
    @XmlElement(name = "Checker", required = true)
    protected String checker;

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
     * Gets the value of the cifCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCIFCode() {
        return cifCode;
    }

    /**
     * Sets the value of the cifCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCIFCode(String value) {
        this.cifCode = value;
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
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the fullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the value of the fullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullName(String value) {
        this.fullName = value;
    }

    /**
     * Gets the value of the shortName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Sets the value of the shortName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortName(String value) {
        this.shortName = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * Gets the value of the dateOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the value of the dateOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateOfBirth(String value) {
        this.dateOfBirth = value;
    }

    /**
     * Gets the value of the isEmployee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsEmployee() {
        return isEmployee;
    }

    /**
     * Sets the value of the isEmployee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsEmployee(String value) {
        this.isEmployee = value;
    }

    /**
     * Gets the value of the taxCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxCode() {
        return taxCode;
    }

    /**
     * Sets the value of the taxCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxCode(String value) {
        this.taxCode = value;
    }

    /**
     * Gets the value of the identityType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentityType() {
        return identityType;
    }

    /**
     * Sets the value of the identityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentityType(String value) {
        this.identityType = value;
    }

    /**
     * Gets the value of the identityNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentityNumber() {
        return identityNumber;
    }

    /**
     * Sets the value of the identityNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentityNumber(String value) {
        this.identityNumber = value;
    }

    /**
     * Gets the value of the issueDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueDate() {
        return issueDate;
    }

    /**
     * Sets the value of the issueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueDate(String value) {
        this.issueDate = value;
    }

    /**
     * Gets the value of the issuePlace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuePlace() {
        return issuePlace;
    }

    /**
     * Sets the value of the issuePlace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuePlace(String value) {
        this.issuePlace = value;
    }

    /**
     * Gets the value of the expireDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpireDate() {
        return expireDate;
    }

    /**
     * Sets the value of the expireDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpireDate(String value) {
        this.expireDate = value;
    }

    /**
     * Gets the value of the address1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * Sets the value of the address1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress1(String value) {
        this.address1 = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the nationality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Sets the value of the nationality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationality(String value) {
        this.nationality = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the guardian property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuardian() {
        return guardian;
    }

    /**
     * Sets the value of the guardian property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuardian(String value) {
        this.guardian = value;
    }

    /**
     * Gets the value of the classification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassification() {
        return classification;
    }

    /**
     * Sets the value of the classification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassification(String value) {
        this.classification = value;
    }

    /**
     * Gets the value of the customerCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerCategory() {
        return customerCategory;
    }

    /**
     * Sets the value of the customerCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerCategory(String value) {
        this.customerCategory = value;
    }

    /**
     * Gets the value of the chargeGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeGroup() {
        return chargeGroup;
    }

    /**
     * Sets the value of the chargeGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeGroup(String value) {
        this.chargeGroup = value;
    }

    /**
     * Gets the value of the swiftCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSWIFTCode() {
        return swiftCode;
    }

    /**
     * Sets the value of the swiftCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSWIFTCode(String value) {
        this.swiftCode = value;
    }

    /**
     * Gets the value of the reuterCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREUTERCode() {
        return reuterCode;
    }

    /**
     * Sets the value of the reuterCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREUTERCode(String value) {
        this.reuterCode = value;
    }

    /**
     * Gets the value of the citadCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCITADCode() {
        return citadCode;
    }

    /**
     * Sets the value of the citadCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCITADCode(String value) {
        this.citadCode = value;
    }

    /**
     * Gets the value of the giinCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGIINCode() {
        return giinCode;
    }

    /**
     * Sets the value of the giinCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGIINCode(String value) {
        this.giinCode = value;
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
     * Gets the value of the checker property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChecker() {
        return checker;
    }

    /**
     * Sets the value of the checker property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChecker(String value) {
        this.checker = value;
    }

}
