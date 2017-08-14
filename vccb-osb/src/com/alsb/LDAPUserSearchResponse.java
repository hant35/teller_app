
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
 *         &lt;element name="UserList"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
 *                   &lt;element name="User"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="UserID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="PhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="BRID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="DEPTID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="Maker" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="Approver" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "userList"
})
@XmlRootElement(name = "LDAPUserSearchResponse")
public class LDAPUserSearchResponse {

    @XmlElement(name = "UserList", required = true)
    protected LDAPUserSearchResponse.UserList userList;

    /**
     * Gets the value of the userList property.
     * 
     * @return
     *     possible object is
     *     {@link LDAPUserSearchResponse.UserList }
     *     
     */
    public LDAPUserSearchResponse.UserList getUserList() {
        return userList;
    }

    /**
     * Sets the value of the userList property.
     * 
     * @param value
     *     allowed object is
     *     {@link LDAPUserSearchResponse.UserList }
     *     
     */
    public void setUserList(LDAPUserSearchResponse.UserList value) {
        this.userList = value;
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
     *         &lt;element name="User"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="UserID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="PhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="BRID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="DEPTID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="Maker" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="Approver" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "user"
    })
    public static class UserList {

        @XmlElement(name = "User")
        protected List<LDAPUserSearchResponse.UserList.User> user;

        /**
         * Gets the value of the user property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the user property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getUser().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link LDAPUserSearchResponse.UserList.User }
         * 
         * 
         */
        public List<LDAPUserSearchResponse.UserList.User> getUser() {
            if (user == null) {
                user = new ArrayList<LDAPUserSearchResponse.UserList.User>();
            }
            return this.user;
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
         *         &lt;element name="UserID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="PhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="BRID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="DEPTID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
            "userID",
            "username",
            "name",
            "email",
            "phoneNumber",
            "brid",
            "deptid",
            "maker",
            "approver"
        })
        public static class User {

            @XmlElement(name = "UserID", required = true)
            protected String userID;
            @XmlElement(name = "Username", required = true)
            protected String username;
            @XmlElement(name = "Name", required = true)
            protected String name;
            @XmlElement(name = "Email", required = true)
            protected String email;
            @XmlElement(name = "PhoneNumber", required = true)
            protected String phoneNumber;
            @XmlElement(name = "BRID", required = true)
            protected String brid;
            @XmlElement(name = "DEPTID", required = true)
            protected String deptid;
            @XmlElement(name = "Maker", required = true)
            protected String maker;
            @XmlElement(name = "Approver", required = true)
            protected String approver;

            /**
             * Gets the value of the userID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUserID() {
                return userID;
            }

            /**
             * Sets the value of the userID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUserID(String value) {
                this.userID = value;
            }

            /**
             * Gets the value of the username property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUsername() {
                return username;
            }

            /**
             * Sets the value of the username property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUsername(String value) {
                this.username = value;
            }

            /**
             * Gets the value of the name property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Gets the value of the email property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEmail() {
                return email;
            }

            /**
             * Sets the value of the email property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEmail(String value) {
                this.email = value;
            }

            /**
             * Gets the value of the phoneNumber property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPhoneNumber() {
                return phoneNumber;
            }

            /**
             * Sets the value of the phoneNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPhoneNumber(String value) {
                this.phoneNumber = value;
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
             * Gets the value of the deptid property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDEPTID() {
                return deptid;
            }

            /**
             * Sets the value of the deptid property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDEPTID(String value) {
                this.deptid = value;
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

    }

}
