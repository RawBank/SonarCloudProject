
package com.rawbank.admin.client.stubs.getAccountList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour getAccountListRequest complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getAccountListRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="accountCategory" type="{http://soprabanking.com/amplitude}accountCategory" minOccurs="0"/&gt;
 *         &lt;element name="branch" type="{http://soprabanking.com/amplitude}branch" minOccurs="0"/&gt;
 *         &lt;element name="account" type="{http://soprabanking.com/amplitude}accountFile" minOccurs="0"/&gt;
 *         &lt;element name="accountType" type="{http://soprabanking.com/amplitude}accountType" minOccurs="0"/&gt;
 *         &lt;element name="product" type="{http://soprabanking.com/amplitude}product" minOccurs="0"/&gt;
 *         &lt;element name="accountStatus" type="{http://soprabanking.com/amplitude}accountStatus" minOccurs="0"/&gt;
 *         &lt;element name="balance" type="{http://soprabanking.com/amplitude}comparisonAmount" minOccurs="0"/&gt;
 *         &lt;element name="accountTitle" type="{http://soprabanking.com/amplitude}designation" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAccountListRequest", propOrder = {
    "accountCategory",
    "branch",
    "account",
    "accountType",
    "product",
    "accountStatus",
    "balance",
    "accountTitle"
})
public class GetAccountListRequest {

    @XmlSchemaType(name = "string")
    protected AccountCategory accountCategory;
    protected Branch branch;
    protected AccountFile account;
    protected AccountType accountType;
    protected Product product;
    @XmlSchemaType(name = "string")
    protected AccountStatus accountStatus;
    protected ComparisonAmount balance;
    protected Designation accountTitle;

    /**
     * Obtient la valeur de la propriété accountCategory.
     * 
     * @return
     *     possible object is
     *     {@link AccountCategory }
     *     
     */
    public AccountCategory getAccountCategory() {
        return accountCategory;
    }

    /**
     * Définit la valeur de la propriété accountCategory.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountCategory }
     *     
     */
    public void setAccountCategory(AccountCategory value) {
        this.accountCategory = value;
    }

    /**
     * Obtient la valeur de la propriété branch.
     * 
     * @return
     *     possible object is
     *     {@link Branch }
     *     
     */
    public Branch getBranch() {
        return branch;
    }

    /**
     * Définit la valeur de la propriété branch.
     * 
     * @param value
     *     allowed object is
     *     {@link Branch }
     *     
     */
    public void setBranch(Branch value) {
        this.branch = value;
    }

    /**
     * Obtient la valeur de la propriété account.
     * 
     * @return
     *     possible object is
     *     {@link AccountFile }
     *     
     */
    public AccountFile getAccount() {
        return account;
    }

    /**
     * Définit la valeur de la propriété account.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountFile }
     *     
     */
    public void setAccount(AccountFile value) {
        this.account = value;
    }

    /**
     * Obtient la valeur de la propriété accountType.
     * 
     * @return
     *     possible object is
     *     {@link AccountType }
     *     
     */
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * Définit la valeur de la propriété accountType.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountType }
     *     
     */
    public void setAccountType(AccountType value) {
        this.accountType = value;
    }

    /**
     * Obtient la valeur de la propriété product.
     * 
     * @return
     *     possible object is
     *     {@link Product }
     *     
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Définit la valeur de la propriété product.
     * 
     * @param value
     *     allowed object is
     *     {@link Product }
     *     
     */
    public void setProduct(Product value) {
        this.product = value;
    }

    /**
     * Obtient la valeur de la propriété accountStatus.
     * 
     * @return
     *     possible object is
     *     {@link AccountStatus }
     *     
     */
    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    /**
     * Définit la valeur de la propriété accountStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountStatus }
     *     
     */
    public void setAccountStatus(AccountStatus value) {
        this.accountStatus = value;
    }

    /**
     * Obtient la valeur de la propriété balance.
     * 
     * @return
     *     possible object is
     *     {@link ComparisonAmount }
     *     
     */
    public ComparisonAmount getBalance() {
        return balance;
    }

    /**
     * Définit la valeur de la propriété balance.
     * 
     * @param value
     *     allowed object is
     *     {@link ComparisonAmount }
     *     
     */
    public void setBalance(ComparisonAmount value) {
        this.balance = value;
    }

    /**
     * Obtient la valeur de la propriété accountTitle.
     * 
     * @return
     *     possible object is
     *     {@link Designation }
     *     
     */
    public Designation getAccountTitle() {
        return accountTitle;
    }

    /**
     * Définit la valeur de la propriété accountTitle.
     * 
     * @param value
     *     allowed object is
     *     {@link Designation }
     *     
     */
    public void setAccountTitle(Designation value) {
        this.accountTitle = value;
    }

}
