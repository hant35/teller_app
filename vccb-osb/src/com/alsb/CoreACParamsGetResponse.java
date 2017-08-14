
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
 *         &lt;element name="ParameterList"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
 *                   &lt;element name="Parameter"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="ParamCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="ParamName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "parameterList"
})
@XmlRootElement(name = "CoreACParamsGetResponse")
public class CoreACParamsGetResponse {

    @XmlElement(name = "ParameterList", required = true)
    protected CoreACParamsGetResponse.ParameterList parameterList;

    /**
     * Gets the value of the parameterList property.
     * 
     * @return
     *     possible object is
     *     {@link CoreACParamsGetResponse.ParameterList }
     *     
     */
    public CoreACParamsGetResponse.ParameterList getParameterList() {
        return parameterList;
    }

    /**
     * Sets the value of the parameterList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoreACParamsGetResponse.ParameterList }
     *     
     */
    public void setParameterList(CoreACParamsGetResponse.ParameterList value) {
        this.parameterList = value;
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
     *         &lt;element name="Parameter"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="ParamCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="ParamName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "parameter"
    })
    public static class ParameterList {

        @XmlElement(name = "Parameter")
        protected List<CoreACParamsGetResponse.ParameterList.Parameter> parameter;

        /**
         * Gets the value of the parameter property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the parameter property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getParameter().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CoreACParamsGetResponse.ParameterList.Parameter }
         * 
         * 
         */
        public List<CoreACParamsGetResponse.ParameterList.Parameter> getParameter() {
            if (parameter == null) {
                parameter = new ArrayList<CoreACParamsGetResponse.ParameterList.Parameter>();
            }
            return this.parameter;
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
         *         &lt;element name="ParamName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
            "paramCode",
            "paramName",
            "maker",
            "approver"
        })
        public static class Parameter {

            @XmlElement(name = "ParamCode", required = true)
            protected String paramCode;
            @XmlElement(name = "ParamName", required = true)
            protected String paramName;
            @XmlElement(name = "Maker", required = true)
            protected String maker;
            @XmlElement(name = "Approver", required = true)
            protected String approver;

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
             * Gets the value of the paramName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getParamName() {
                return paramName;
            }

            /**
             * Sets the value of the paramName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setParamName(String value) {
                this.paramName = value;
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
