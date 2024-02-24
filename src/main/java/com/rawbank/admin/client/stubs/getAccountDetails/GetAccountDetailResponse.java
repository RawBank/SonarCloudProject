
package com.rawbank.admin.client.stubs.getAccountDetails;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour getAccountDetailResponse complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getAccountDetailResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="branch" type="{http://soprabanking.com/amplitude}branch" minOccurs="0"/&gt;
 *         &lt;element name="currency" type="{http://soprabanking.com/amplitude}simpleCurrency" minOccurs="0"/&gt;
 *         &lt;element name="accountNumber" type="{http://soprabanking.com/amplitude}accountNumber" minOccurs="0"/&gt;
 *         &lt;element name="accountSuffix" type="{http://soprabanking.com/amplitude}accountSuffix" minOccurs="0"/&gt;
 *         &lt;element name="accountClass" type="{http://soprabanking.com/amplitude}accountClass" minOccurs="0"/&gt;
 *         &lt;element name="accountKey" type="{http://soprabanking.com/amplitude}charMax2" minOccurs="0"/&gt;
 *         &lt;element name="customer" type="{http://soprabanking.com/amplitude}restrictedCustomer" minOccurs="0"/&gt;
 *         &lt;element name="accountDesignation" type="{http://soprabanking.com/amplitude}charMax30" minOccurs="0"/&gt;
 *         &lt;element name="service" type="{http://soprabanking.com/amplitude}service" minOccurs="0"/&gt;
 *         &lt;element name="accountSide" type="{http://soprabanking.com/amplitude}accountSide" minOccurs="0"/&gt;
 *         &lt;element name="matchingCode" type="{http://soprabanking.com/amplitude}matchingCode" minOccurs="0"/&gt;
 *         &lt;element name="accountType" type="{http://soprabanking.com/amplitude}accountType" minOccurs="0"/&gt;
 *         &lt;element name="accountSubjectToInterestCalculation" type="{http://soprabanking.com/amplitude}accountSubjectToInterestCalculation" minOccurs="0"/&gt;
 *         &lt;element name="codeForInterestLadderPrinting" type="{http://soprabanking.com/amplitude}codeForInterestLadderPrinting" minOccurs="0"/&gt;
 *         &lt;element name="accountStatementCode" type="{http://soprabanking.com/amplitude}char1" minOccurs="0"/&gt;
 *         &lt;element name="taxableAccount" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="accountNotToBePurged" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="pendingClosure" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="directCreditCeiling" type="{http://soprabanking.com/amplitude}decimal12_0" minOccurs="0"/&gt;
 *         &lt;element name="thresholdForReorderingCheques" type="{http://soprabanking.com/amplitude}decimal4_0" minOccurs="0"/&gt;
 *         &lt;element name="closedAccount" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="openingDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="lastModificationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="pendingClosureStatusDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="closureDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="modificationSheetNumber" type="{http://soprabanking.com/amplitude}decimal4_0" minOccurs="0"/&gt;
 *         &lt;element name="accountSubjectToDeductionAtSource" type="{http://soprabanking.com/amplitude}decimal1_0" minOccurs="0"/&gt;
 *         &lt;element name="accountingBalance" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="valueDateBalance" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="valueDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="historyBalance" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="historyDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="interestCalculationBalance" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="interestCalculationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="indicativeBalance" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="unavailableFundsWithoutDirectCredit" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="unavailableDirectCreditFunds" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="dailyUnavailableFundsWithoutDirectCredit" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="dailyUnavailableDirectCreditFunds" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="debitTurnovers" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="creditTurnovers" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="lastMovementDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="lastCreditDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="lastDebitDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="userWhoInitiated" type="{http://soprabanking.com/amplitude}user" minOccurs="0"/&gt;
 *         &lt;element name="frequencyOfDebitInterestCalculation" type="{http://soprabanking.com/amplitude}charMax1" minOccurs="0"/&gt;
 *         &lt;element name="transferToDebtRecoveryProcedure" type="{http://soprabanking.com/amplitude}charMax1" minOccurs="0"/&gt;
 *         &lt;element name="mergeBranch" type="{http://soprabanking.com/amplitude}branch" minOccurs="0"/&gt;
 *         &lt;element name="mergeCurrency" type="{http://soprabanking.com/amplitude}simpleCurrency" minOccurs="0"/&gt;
 *         &lt;element name="mergeClass" type="{http://soprabanking.com/amplitude}accountClass" minOccurs="0"/&gt;
 *         &lt;element name="mergeAccountNumber" type="{http://soprabanking.com/amplitude}accountNumber" minOccurs="0"/&gt;
 *         &lt;element name="mergeAccountSuffix" type="{http://soprabanking.com/amplitude}accountSuffix" minOccurs="0"/&gt;
 *         &lt;element name="frequencyOfCreditInterestCalculation" type="{http://soprabanking.com/amplitude}charMax1" minOccurs="0"/&gt;
 *         &lt;element name="lastMatchingPairAllocated" type="{http://soprabanking.com/amplitude}charMax2" minOccurs="0"/&gt;
 *         &lt;element name="lastMatchingDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="overdraftLimit1" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="expiryDate1" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="lastDebitMovementDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="lastOverdraftLimitDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="beginningOfOccasionalInstalment" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="realTimeTransferCode" type="{http://soprabanking.com/amplitude}charMax1" minOccurs="0"/&gt;
 *         &lt;element name="originalDateWhenAccountShowedDebitBalance" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="originalDateWhenOverdraftLimitExceeded" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="checkDigitDeclared" type="{http://soprabanking.com/amplitude}charMax2" minOccurs="0"/&gt;
 *         &lt;element name="product" type="{http://soprabanking.com/amplitude}product" minOccurs="0"/&gt;
 *         &lt;element name="maturityDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="accountPledging" type="{http://soprabanking.com/amplitude}charMax1" minOccurs="0"/&gt;
 *         &lt;element name="chequeDeliveryMethod" type="{http://soprabanking.com/amplitude}chequeBookDeliveryMethod" minOccurs="0"/&gt;
 *         &lt;element name="defaultChequeBookType" type="{http://soprabanking.com/amplitude}charMax2" minOccurs="0"/&gt;
 *         &lt;element name="branchThatDeliveredChequeBook" type="{http://soprabanking.com/amplitude}branch" minOccurs="0"/&gt;
 *         &lt;element name="reasonForAccountClosure" type="{http://soprabanking.com/amplitude}charMax3" minOccurs="0"/&gt;
 *         &lt;element name="userWhoCreated" type="{http://soprabanking.com/amplitude}user" minOccurs="0"/&gt;
 *         &lt;element name="userWhoSetThePendingClosureStatus" type="{http://soprabanking.com/amplitude}user" minOccurs="0"/&gt;
 *         &lt;element name="userWhoClosed" type="{http://soprabanking.com/amplitude}user" minOccurs="0"/&gt;
 *         &lt;element name="branchWhereTheAccountWasCreated" type="{http://soprabanking.com/amplitude}branch" minOccurs="0"/&gt;
 *         &lt;element name="branchFromAccountInformationForm" type="{http://soprabanking.com/amplitude}branch" minOccurs="0"/&gt;
 *         &lt;element name="lastBranchThatHeldTheAccount" type="{http://soprabanking.com/amplitude}branch" minOccurs="0"/&gt;
 *         &lt;element name="chequeAddressType" type="{http://soprabanking.com/amplitude}charMax1" minOccurs="0"/&gt;
 *         &lt;element name="chequeAddressCode" type="{http://soprabanking.com/amplitude}charMax2" minOccurs="0"/&gt;
 *         &lt;element name="iBANAccountKey" type="{http://soprabanking.com/amplitude}charMax2" minOccurs="0"/&gt;
 *         &lt;element name="temporaryOpening" type="{http://soprabanking.com/amplitude}charMax1" minOccurs="0"/&gt;
 *         &lt;element name="package" type="{http://soprabanking.com/amplitude}package" minOccurs="0"/&gt;
 *         &lt;element name="amountOfReservedFunds" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="indicativeBalanceUpdatedByFundReservation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="freeInputAmount2" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="freeInputDate1" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="freeInputDate2" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="accountStatementDeliveryMethod" type="{http://soprabanking.com/amplitude}charMax10" minOccurs="0"/&gt;
 *         &lt;element name="freeInputField2" type="{http://soprabanking.com/amplitude}charMax10" minOccurs="0"/&gt;
 *         &lt;element name="freeInputField3" type="{http://soprabanking.com/amplitude}charMax10" minOccurs="0"/&gt;
 *         &lt;element name="lastAccountCurrency" type="{http://soprabanking.com/amplitude}simpleCurrency" minOccurs="0"/&gt;
 *         &lt;element name="currencyChangeDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="jointAccount" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="responsibleCustomer" type="{http://soprabanking.com/amplitude}charMax15" minOccurs="0"/&gt;
 *         &lt;element name="jointAccountTitle" type="{http://soprabanking.com/amplitude}charMax2" minOccurs="0"/&gt;
 *         &lt;element name="freeAttributes" type="{http://soprabanking.com/amplitude}getAccountFreeAttributesDetailResponse" minOccurs="0"/&gt;
 *         &lt;element name="stoppages" type="{http://soprabanking.com/amplitude}getStoppageListResponse" minOccurs="0"/&gt;
 *         &lt;element name="overdraftAuthorization" type="{http://soprabanking.com/amplitude}getOverdraftAuthNDetailResponse" minOccurs="0"/&gt;
 *         &lt;element name="availableBalance" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAccountDetailResponse", propOrder = {
    "branch",
    "currency",
    "accountNumber",
    "accountSuffix",
    "accountClass",
    "accountKey",
    "customer",
    "accountDesignation",
    "service",
    "accountSide",
    "matchingCode",
    "accountType",
    "accountSubjectToInterestCalculation",
    "codeForInterestLadderPrinting",
    "accountStatementCode",
    "taxableAccount",
    "accountNotToBePurged",
    "pendingClosure",
    "directCreditCeiling",
    "thresholdForReorderingCheques",
    "closedAccount",
    "openingDate",
    "lastModificationDate",
    "pendingClosureStatusDate",
    "closureDate",
    "modificationSheetNumber",
    "accountSubjectToDeductionAtSource",
    "accountingBalance",
    "valueDateBalance",
    "valueDate",
    "historyBalance",
    "historyDate",
    "interestCalculationBalance",
    "interestCalculationDate",
    "indicativeBalance",
    "unavailableFundsWithoutDirectCredit",
    "unavailableDirectCreditFunds",
    "dailyUnavailableFundsWithoutDirectCredit",
    "dailyUnavailableDirectCreditFunds",
    "debitTurnovers",
    "creditTurnovers",
    "lastMovementDate",
    "lastCreditDate",
    "lastDebitDate",
    "userWhoInitiated",
    "frequencyOfDebitInterestCalculation",
    "transferToDebtRecoveryProcedure",
    "mergeBranch",
    "mergeCurrency",
    "mergeClass",
    "mergeAccountNumber",
    "mergeAccountSuffix",
    "frequencyOfCreditInterestCalculation",
    "lastMatchingPairAllocated",
    "lastMatchingDate",
    "overdraftLimit1",
    "expiryDate1",
    "lastDebitMovementDate",
    "lastOverdraftLimitDate",
    "beginningOfOccasionalInstalment",
    "realTimeTransferCode",
    "originalDateWhenAccountShowedDebitBalance",
    "originalDateWhenOverdraftLimitExceeded",
    "checkDigitDeclared",
    "product",
    "maturityDate",
    "accountPledging",
    "chequeDeliveryMethod",
    "defaultChequeBookType",
    "branchThatDeliveredChequeBook",
    "reasonForAccountClosure",
    "userWhoCreated",
    "userWhoSetThePendingClosureStatus",
    "userWhoClosed",
    "branchWhereTheAccountWasCreated",
    "branchFromAccountInformationForm",
    "lastBranchThatHeldTheAccount",
    "chequeAddressType",
    "chequeAddressCode",
    "ibanAccountKey",
    "temporaryOpening",
    "_package",
    "amountOfReservedFunds",
    "indicativeBalanceUpdatedByFundReservation",
    "freeInputAmount2",
    "freeInputDate1",
    "freeInputDate2",
    "accountStatementDeliveryMethod",
    "freeInputField2",
    "freeInputField3",
    "lastAccountCurrency",
    "currencyChangeDate",
    "jointAccount",
    "responsibleCustomer",
    "jointAccountTitle",
    "freeAttributes",
    "stoppages",
    "overdraftAuthorization",
    "availableBalance"
})
public class GetAccountDetailResponse {

    protected Branch branch;
    protected SimpleCurrency currency;
    protected String accountNumber;
    protected String accountSuffix;
    protected AccountClass accountClass;
    protected String accountKey;
    protected RestrictedCustomer customer;
    protected String accountDesignation;
    protected Service service;
    @XmlSchemaType(name = "string")
    protected AccountSide accountSide;
    protected String matchingCode;
    protected AccountType accountType;
    @XmlSchemaType(name = "string")
    protected AccountSubjectToInterestCalculation accountSubjectToInterestCalculation;
    @XmlSchemaType(name = "string")
    protected CodeForInterestLadderPrinting codeForInterestLadderPrinting;
    protected String accountStatementCode;
    protected Boolean taxableAccount;
    protected Boolean accountNotToBePurged;
    protected Boolean pendingClosure;
    protected BigDecimal directCreditCeiling;
    protected BigDecimal thresholdForReorderingCheques;
    protected Boolean closedAccount;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar openingDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastModificationDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar pendingClosureStatusDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar closureDate;
    protected BigDecimal modificationSheetNumber;
    protected BigDecimal accountSubjectToDeductionAtSource;
    protected BigDecimal accountingBalance;
    protected BigDecimal valueDateBalance;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar valueDate;
    protected BigDecimal historyBalance;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar historyDate;
    protected BigDecimal interestCalculationBalance;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar interestCalculationDate;
    protected BigDecimal indicativeBalance;
    protected BigDecimal unavailableFundsWithoutDirectCredit;
    protected BigDecimal unavailableDirectCreditFunds;
    protected BigDecimal dailyUnavailableFundsWithoutDirectCredit;
    protected BigDecimal dailyUnavailableDirectCreditFunds;
    protected BigDecimal debitTurnovers;
    protected BigDecimal creditTurnovers;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastMovementDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastCreditDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastDebitDate;
    protected User userWhoInitiated;
    protected String frequencyOfDebitInterestCalculation;
    protected String transferToDebtRecoveryProcedure;
    protected Branch mergeBranch;
    protected SimpleCurrency mergeCurrency;
    protected AccountClass mergeClass;
    protected String mergeAccountNumber;
    protected String mergeAccountSuffix;
    protected String frequencyOfCreditInterestCalculation;
    protected String lastMatchingPairAllocated;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastMatchingDate;
    protected BigDecimal overdraftLimit1;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expiryDate1;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastDebitMovementDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastOverdraftLimitDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar beginningOfOccasionalInstalment;
    protected String realTimeTransferCode;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar originalDateWhenAccountShowedDebitBalance;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar originalDateWhenOverdraftLimitExceeded;
    protected String checkDigitDeclared;
    protected Product product;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar maturityDate;
    protected String accountPledging;
    @XmlSchemaType(name = "string")
    protected ChequeBookDeliveryMethod chequeDeliveryMethod;
    protected String defaultChequeBookType;
    protected Branch branchThatDeliveredChequeBook;
    protected String reasonForAccountClosure;
    protected User userWhoCreated;
    protected User userWhoSetThePendingClosureStatus;
    protected User userWhoClosed;
    protected Branch branchWhereTheAccountWasCreated;
    protected Branch branchFromAccountInformationForm;
    protected Branch lastBranchThatHeldTheAccount;
    protected String chequeAddressType;
    protected String chequeAddressCode;
    @XmlElement(name = "iBANAccountKey")
    protected String ibanAccountKey;
    protected String temporaryOpening;
    @XmlElement(name = "package")
    protected Package _package;
    protected BigDecimal amountOfReservedFunds;
    protected Boolean indicativeBalanceUpdatedByFundReservation;
    protected BigDecimal freeInputAmount2;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar freeInputDate1;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar freeInputDate2;
    protected String accountStatementDeliveryMethod;
    protected String freeInputField2;
    protected String freeInputField3;
    protected SimpleCurrency lastAccountCurrency;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar currencyChangeDate;
    protected Boolean jointAccount;
    protected String responsibleCustomer;
    protected String jointAccountTitle;
    protected GetAccountFreeAttributesDetailResponse freeAttributes;
    protected GetStoppageListResponse stoppages;
    protected GetOverdraftAuthNDetailResponse overdraftAuthorization;
    protected BigDecimal availableBalance;

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
     * Obtient la valeur de la propriété currency.
     * 
     * @return
     *     possible object is
     *     {@link SimpleCurrency }
     *     
     */
    public SimpleCurrency getCurrency() {
        return currency;
    }

    /**
     * Définit la valeur de la propriété currency.
     * 
     * @param value
     *     allowed object is
     *     {@link SimpleCurrency }
     *     
     */
    public void setCurrency(SimpleCurrency value) {
        this.currency = value;
    }

    /**
     * Obtient la valeur de la propriété accountNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Définit la valeur de la propriété accountNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
    }

    /**
     * Obtient la valeur de la propriété accountSuffix.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountSuffix() {
        return accountSuffix;
    }

    /**
     * Définit la valeur de la propriété accountSuffix.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountSuffix(String value) {
        this.accountSuffix = value;
    }

    /**
     * Obtient la valeur de la propriété accountClass.
     * 
     * @return
     *     possible object is
     *     {@link AccountClass }
     *     
     */
    public AccountClass getAccountClass() {
        return accountClass;
    }

    /**
     * Définit la valeur de la propriété accountClass.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountClass }
     *     
     */
    public void setAccountClass(AccountClass value) {
        this.accountClass = value;
    }

    /**
     * Obtient la valeur de la propriété accountKey.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountKey() {
        return accountKey;
    }

    /**
     * Définit la valeur de la propriété accountKey.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountKey(String value) {
        this.accountKey = value;
    }

    /**
     * Obtient la valeur de la propriété customer.
     * 
     * @return
     *     possible object is
     *     {@link RestrictedCustomer }
     *     
     */
    public RestrictedCustomer getCustomer() {
        return customer;
    }

    /**
     * Définit la valeur de la propriété customer.
     * 
     * @param value
     *     allowed object is
     *     {@link RestrictedCustomer }
     *     
     */
    public void setCustomer(RestrictedCustomer value) {
        this.customer = value;
    }

    /**
     * Obtient la valeur de la propriété accountDesignation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountDesignation() {
        return accountDesignation;
    }

    /**
     * Définit la valeur de la propriété accountDesignation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountDesignation(String value) {
        this.accountDesignation = value;
    }

    /**
     * Obtient la valeur de la propriété service.
     * 
     * @return
     *     possible object is
     *     {@link Service }
     *     
     */
    public Service getService() {
        return service;
    }

    /**
     * Définit la valeur de la propriété service.
     * 
     * @param value
     *     allowed object is
     *     {@link Service }
     *     
     */
    public void setService(Service value) {
        this.service = value;
    }

    /**
     * Obtient la valeur de la propriété accountSide.
     * 
     * @return
     *     possible object is
     *     {@link AccountSide }
     *     
     */
    public AccountSide getAccountSide() {
        return accountSide;
    }

    /**
     * Définit la valeur de la propriété accountSide.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountSide }
     *     
     */
    public void setAccountSide(AccountSide value) {
        this.accountSide = value;
    }

    /**
     * Obtient la valeur de la propriété matchingCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatchingCode() {
        return matchingCode;
    }

    /**
     * Définit la valeur de la propriété matchingCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatchingCode(String value) {
        this.matchingCode = value;
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
     * Obtient la valeur de la propriété accountSubjectToInterestCalculation.
     * 
     * @return
     *     possible object is
     *     {@link AccountSubjectToInterestCalculation }
     *     
     */
    public AccountSubjectToInterestCalculation getAccountSubjectToInterestCalculation() {
        return accountSubjectToInterestCalculation;
    }

    /**
     * Définit la valeur de la propriété accountSubjectToInterestCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountSubjectToInterestCalculation }
     *     
     */
    public void setAccountSubjectToInterestCalculation(AccountSubjectToInterestCalculation value) {
        this.accountSubjectToInterestCalculation = value;
    }

    /**
     * Obtient la valeur de la propriété codeForInterestLadderPrinting.
     * 
     * @return
     *     possible object is
     *     {@link CodeForInterestLadderPrinting }
     *     
     */
    public CodeForInterestLadderPrinting getCodeForInterestLadderPrinting() {
        return codeForInterestLadderPrinting;
    }

    /**
     * Définit la valeur de la propriété codeForInterestLadderPrinting.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeForInterestLadderPrinting }
     *     
     */
    public void setCodeForInterestLadderPrinting(CodeForInterestLadderPrinting value) {
        this.codeForInterestLadderPrinting = value;
    }

    /**
     * Obtient la valeur de la propriété accountStatementCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountStatementCode() {
        return accountStatementCode;
    }

    /**
     * Définit la valeur de la propriété accountStatementCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountStatementCode(String value) {
        this.accountStatementCode = value;
    }

    /**
     * Obtient la valeur de la propriété taxableAccount.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTaxableAccount() {
        return taxableAccount;
    }

    /**
     * Définit la valeur de la propriété taxableAccount.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTaxableAccount(Boolean value) {
        this.taxableAccount = value;
    }

    /**
     * Obtient la valeur de la propriété accountNotToBePurged.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAccountNotToBePurged() {
        return accountNotToBePurged;
    }

    /**
     * Définit la valeur de la propriété accountNotToBePurged.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAccountNotToBePurged(Boolean value) {
        this.accountNotToBePurged = value;
    }

    /**
     * Obtient la valeur de la propriété pendingClosure.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPendingClosure() {
        return pendingClosure;
    }

    /**
     * Définit la valeur de la propriété pendingClosure.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPendingClosure(Boolean value) {
        this.pendingClosure = value;
    }

    /**
     * Obtient la valeur de la propriété directCreditCeiling.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDirectCreditCeiling() {
        return directCreditCeiling;
    }

    /**
     * Définit la valeur de la propriété directCreditCeiling.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDirectCreditCeiling(BigDecimal value) {
        this.directCreditCeiling = value;
    }

    /**
     * Obtient la valeur de la propriété thresholdForReorderingCheques.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getThresholdForReorderingCheques() {
        return thresholdForReorderingCheques;
    }

    /**
     * Définit la valeur de la propriété thresholdForReorderingCheques.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setThresholdForReorderingCheques(BigDecimal value) {
        this.thresholdForReorderingCheques = value;
    }

    /**
     * Obtient la valeur de la propriété closedAccount.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isClosedAccount() {
        return closedAccount;
    }

    /**
     * Définit la valeur de la propriété closedAccount.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setClosedAccount(Boolean value) {
        this.closedAccount = value;
    }

    /**
     * Obtient la valeur de la propriété openingDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOpeningDate() {
        return openingDate;
    }

    /**
     * Définit la valeur de la propriété openingDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOpeningDate(XMLGregorianCalendar value) {
        this.openingDate = value;
    }

    /**
     * Obtient la valeur de la propriété lastModificationDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastModificationDate() {
        return lastModificationDate;
    }

    /**
     * Définit la valeur de la propriété lastModificationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastModificationDate(XMLGregorianCalendar value) {
        this.lastModificationDate = value;
    }

    /**
     * Obtient la valeur de la propriété pendingClosureStatusDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPendingClosureStatusDate() {
        return pendingClosureStatusDate;
    }

    /**
     * Définit la valeur de la propriété pendingClosureStatusDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPendingClosureStatusDate(XMLGregorianCalendar value) {
        this.pendingClosureStatusDate = value;
    }

    /**
     * Obtient la valeur de la propriété closureDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getClosureDate() {
        return closureDate;
    }

    /**
     * Définit la valeur de la propriété closureDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setClosureDate(XMLGregorianCalendar value) {
        this.closureDate = value;
    }

    /**
     * Obtient la valeur de la propriété modificationSheetNumber.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getModificationSheetNumber() {
        return modificationSheetNumber;
    }

    /**
     * Définit la valeur de la propriété modificationSheetNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setModificationSheetNumber(BigDecimal value) {
        this.modificationSheetNumber = value;
    }

    /**
     * Obtient la valeur de la propriété accountSubjectToDeductionAtSource.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAccountSubjectToDeductionAtSource() {
        return accountSubjectToDeductionAtSource;
    }

    /**
     * Définit la valeur de la propriété accountSubjectToDeductionAtSource.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAccountSubjectToDeductionAtSource(BigDecimal value) {
        this.accountSubjectToDeductionAtSource = value;
    }

    /**
     * Obtient la valeur de la propriété accountingBalance.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAccountingBalance() {
        return accountingBalance;
    }

    /**
     * Définit la valeur de la propriété accountingBalance.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAccountingBalance(BigDecimal value) {
        this.accountingBalance = value;
    }

    /**
     * Obtient la valeur de la propriété valueDateBalance.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValueDateBalance() {
        return valueDateBalance;
    }

    /**
     * Définit la valeur de la propriété valueDateBalance.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValueDateBalance(BigDecimal value) {
        this.valueDateBalance = value;
    }

    /**
     * Obtient la valeur de la propriété valueDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValueDate() {
        return valueDate;
    }

    /**
     * Définit la valeur de la propriété valueDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValueDate(XMLGregorianCalendar value) {
        this.valueDate = value;
    }

    /**
     * Obtient la valeur de la propriété historyBalance.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getHistoryBalance() {
        return historyBalance;
    }

    /**
     * Définit la valeur de la propriété historyBalance.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setHistoryBalance(BigDecimal value) {
        this.historyBalance = value;
    }

    /**
     * Obtient la valeur de la propriété historyDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getHistoryDate() {
        return historyDate;
    }

    /**
     * Définit la valeur de la propriété historyDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setHistoryDate(XMLGregorianCalendar value) {
        this.historyDate = value;
    }

    /**
     * Obtient la valeur de la propriété interestCalculationBalance.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInterestCalculationBalance() {
        return interestCalculationBalance;
    }

    /**
     * Définit la valeur de la propriété interestCalculationBalance.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInterestCalculationBalance(BigDecimal value) {
        this.interestCalculationBalance = value;
    }

    /**
     * Obtient la valeur de la propriété interestCalculationDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInterestCalculationDate() {
        return interestCalculationDate;
    }

    /**
     * Définit la valeur de la propriété interestCalculationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInterestCalculationDate(XMLGregorianCalendar value) {
        this.interestCalculationDate = value;
    }

    /**
     * Obtient la valeur de la propriété indicativeBalance.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIndicativeBalance() {
        return indicativeBalance;
    }

    /**
     * Définit la valeur de la propriété indicativeBalance.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIndicativeBalance(BigDecimal value) {
        this.indicativeBalance = value;
    }

    /**
     * Obtient la valeur de la propriété unavailableFundsWithoutDirectCredit.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUnavailableFundsWithoutDirectCredit() {
        return unavailableFundsWithoutDirectCredit;
    }

    /**
     * Définit la valeur de la propriété unavailableFundsWithoutDirectCredit.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUnavailableFundsWithoutDirectCredit(BigDecimal value) {
        this.unavailableFundsWithoutDirectCredit = value;
    }

    /**
     * Obtient la valeur de la propriété unavailableDirectCreditFunds.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUnavailableDirectCreditFunds() {
        return unavailableDirectCreditFunds;
    }

    /**
     * Définit la valeur de la propriété unavailableDirectCreditFunds.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUnavailableDirectCreditFunds(BigDecimal value) {
        this.unavailableDirectCreditFunds = value;
    }

    /**
     * Obtient la valeur de la propriété dailyUnavailableFundsWithoutDirectCredit.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDailyUnavailableFundsWithoutDirectCredit() {
        return dailyUnavailableFundsWithoutDirectCredit;
    }

    /**
     * Définit la valeur de la propriété dailyUnavailableFundsWithoutDirectCredit.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDailyUnavailableFundsWithoutDirectCredit(BigDecimal value) {
        this.dailyUnavailableFundsWithoutDirectCredit = value;
    }

    /**
     * Obtient la valeur de la propriété dailyUnavailableDirectCreditFunds.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDailyUnavailableDirectCreditFunds() {
        return dailyUnavailableDirectCreditFunds;
    }

    /**
     * Définit la valeur de la propriété dailyUnavailableDirectCreditFunds.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDailyUnavailableDirectCreditFunds(BigDecimal value) {
        this.dailyUnavailableDirectCreditFunds = value;
    }

    /**
     * Obtient la valeur de la propriété debitTurnovers.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDebitTurnovers() {
        return debitTurnovers;
    }

    /**
     * Définit la valeur de la propriété debitTurnovers.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDebitTurnovers(BigDecimal value) {
        this.debitTurnovers = value;
    }

    /**
     * Obtient la valeur de la propriété creditTurnovers.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCreditTurnovers() {
        return creditTurnovers;
    }

    /**
     * Définit la valeur de la propriété creditTurnovers.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCreditTurnovers(BigDecimal value) {
        this.creditTurnovers = value;
    }

    /**
     * Obtient la valeur de la propriété lastMovementDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastMovementDate() {
        return lastMovementDate;
    }

    /**
     * Définit la valeur de la propriété lastMovementDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastMovementDate(XMLGregorianCalendar value) {
        this.lastMovementDate = value;
    }

    /**
     * Obtient la valeur de la propriété lastCreditDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastCreditDate() {
        return lastCreditDate;
    }

    /**
     * Définit la valeur de la propriété lastCreditDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastCreditDate(XMLGregorianCalendar value) {
        this.lastCreditDate = value;
    }

    /**
     * Obtient la valeur de la propriété lastDebitDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastDebitDate() {
        return lastDebitDate;
    }

    /**
     * Définit la valeur de la propriété lastDebitDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastDebitDate(XMLGregorianCalendar value) {
        this.lastDebitDate = value;
    }

    /**
     * Obtient la valeur de la propriété userWhoInitiated.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUserWhoInitiated() {
        return userWhoInitiated;
    }

    /**
     * Définit la valeur de la propriété userWhoInitiated.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUserWhoInitiated(User value) {
        this.userWhoInitiated = value;
    }

    /**
     * Obtient la valeur de la propriété frequencyOfDebitInterestCalculation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrequencyOfDebitInterestCalculation() {
        return frequencyOfDebitInterestCalculation;
    }

    /**
     * Définit la valeur de la propriété frequencyOfDebitInterestCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrequencyOfDebitInterestCalculation(String value) {
        this.frequencyOfDebitInterestCalculation = value;
    }

    /**
     * Obtient la valeur de la propriété transferToDebtRecoveryProcedure.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransferToDebtRecoveryProcedure() {
        return transferToDebtRecoveryProcedure;
    }

    /**
     * Définit la valeur de la propriété transferToDebtRecoveryProcedure.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransferToDebtRecoveryProcedure(String value) {
        this.transferToDebtRecoveryProcedure = value;
    }

    /**
     * Obtient la valeur de la propriété mergeBranch.
     * 
     * @return
     *     possible object is
     *     {@link Branch }
     *     
     */
    public Branch getMergeBranch() {
        return mergeBranch;
    }

    /**
     * Définit la valeur de la propriété mergeBranch.
     * 
     * @param value
     *     allowed object is
     *     {@link Branch }
     *     
     */
    public void setMergeBranch(Branch value) {
        this.mergeBranch = value;
    }

    /**
     * Obtient la valeur de la propriété mergeCurrency.
     * 
     * @return
     *     possible object is
     *     {@link SimpleCurrency }
     *     
     */
    public SimpleCurrency getMergeCurrency() {
        return mergeCurrency;
    }

    /**
     * Définit la valeur de la propriété mergeCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link SimpleCurrency }
     *     
     */
    public void setMergeCurrency(SimpleCurrency value) {
        this.mergeCurrency = value;
    }

    /**
     * Obtient la valeur de la propriété mergeClass.
     * 
     * @return
     *     possible object is
     *     {@link AccountClass }
     *     
     */
    public AccountClass getMergeClass() {
        return mergeClass;
    }

    /**
     * Définit la valeur de la propriété mergeClass.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountClass }
     *     
     */
    public void setMergeClass(AccountClass value) {
        this.mergeClass = value;
    }

    /**
     * Obtient la valeur de la propriété mergeAccountNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMergeAccountNumber() {
        return mergeAccountNumber;
    }

    /**
     * Définit la valeur de la propriété mergeAccountNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMergeAccountNumber(String value) {
        this.mergeAccountNumber = value;
    }

    /**
     * Obtient la valeur de la propriété mergeAccountSuffix.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMergeAccountSuffix() {
        return mergeAccountSuffix;
    }

    /**
     * Définit la valeur de la propriété mergeAccountSuffix.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMergeAccountSuffix(String value) {
        this.mergeAccountSuffix = value;
    }

    /**
     * Obtient la valeur de la propriété frequencyOfCreditInterestCalculation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrequencyOfCreditInterestCalculation() {
        return frequencyOfCreditInterestCalculation;
    }

    /**
     * Définit la valeur de la propriété frequencyOfCreditInterestCalculation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrequencyOfCreditInterestCalculation(String value) {
        this.frequencyOfCreditInterestCalculation = value;
    }

    /**
     * Obtient la valeur de la propriété lastMatchingPairAllocated.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastMatchingPairAllocated() {
        return lastMatchingPairAllocated;
    }

    /**
     * Définit la valeur de la propriété lastMatchingPairAllocated.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastMatchingPairAllocated(String value) {
        this.lastMatchingPairAllocated = value;
    }

    /**
     * Obtient la valeur de la propriété lastMatchingDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastMatchingDate() {
        return lastMatchingDate;
    }

    /**
     * Définit la valeur de la propriété lastMatchingDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastMatchingDate(XMLGregorianCalendar value) {
        this.lastMatchingDate = value;
    }

    /**
     * Obtient la valeur de la propriété overdraftLimit1.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOverdraftLimit1() {
        return overdraftLimit1;
    }

    /**
     * Définit la valeur de la propriété overdraftLimit1.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOverdraftLimit1(BigDecimal value) {
        this.overdraftLimit1 = value;
    }

    /**
     * Obtient la valeur de la propriété expiryDate1.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpiryDate1() {
        return expiryDate1;
    }

    /**
     * Définit la valeur de la propriété expiryDate1.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpiryDate1(XMLGregorianCalendar value) {
        this.expiryDate1 = value;
    }

    /**
     * Obtient la valeur de la propriété lastDebitMovementDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastDebitMovementDate() {
        return lastDebitMovementDate;
    }

    /**
     * Définit la valeur de la propriété lastDebitMovementDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastDebitMovementDate(XMLGregorianCalendar value) {
        this.lastDebitMovementDate = value;
    }

    /**
     * Obtient la valeur de la propriété lastOverdraftLimitDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastOverdraftLimitDate() {
        return lastOverdraftLimitDate;
    }

    /**
     * Définit la valeur de la propriété lastOverdraftLimitDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastOverdraftLimitDate(XMLGregorianCalendar value) {
        this.lastOverdraftLimitDate = value;
    }

    /**
     * Obtient la valeur de la propriété beginningOfOccasionalInstalment.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBeginningOfOccasionalInstalment() {
        return beginningOfOccasionalInstalment;
    }

    /**
     * Définit la valeur de la propriété beginningOfOccasionalInstalment.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBeginningOfOccasionalInstalment(XMLGregorianCalendar value) {
        this.beginningOfOccasionalInstalment = value;
    }

    /**
     * Obtient la valeur de la propriété realTimeTransferCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRealTimeTransferCode() {
        return realTimeTransferCode;
    }

    /**
     * Définit la valeur de la propriété realTimeTransferCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRealTimeTransferCode(String value) {
        this.realTimeTransferCode = value;
    }

    /**
     * Obtient la valeur de la propriété originalDateWhenAccountShowedDebitBalance.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOriginalDateWhenAccountShowedDebitBalance() {
        return originalDateWhenAccountShowedDebitBalance;
    }

    /**
     * Définit la valeur de la propriété originalDateWhenAccountShowedDebitBalance.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOriginalDateWhenAccountShowedDebitBalance(XMLGregorianCalendar value) {
        this.originalDateWhenAccountShowedDebitBalance = value;
    }

    /**
     * Obtient la valeur de la propriété originalDateWhenOverdraftLimitExceeded.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOriginalDateWhenOverdraftLimitExceeded() {
        return originalDateWhenOverdraftLimitExceeded;
    }

    /**
     * Définit la valeur de la propriété originalDateWhenOverdraftLimitExceeded.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOriginalDateWhenOverdraftLimitExceeded(XMLGregorianCalendar value) {
        this.originalDateWhenOverdraftLimitExceeded = value;
    }

    /**
     * Obtient la valeur de la propriété checkDigitDeclared.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckDigitDeclared() {
        return checkDigitDeclared;
    }

    /**
     * Définit la valeur de la propriété checkDigitDeclared.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckDigitDeclared(String value) {
        this.checkDigitDeclared = value;
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
     * Obtient la valeur de la propriété maturityDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMaturityDate() {
        return maturityDate;
    }

    /**
     * Définit la valeur de la propriété maturityDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMaturityDate(XMLGregorianCalendar value) {
        this.maturityDate = value;
    }

    /**
     * Obtient la valeur de la propriété accountPledging.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountPledging() {
        return accountPledging;
    }

    /**
     * Définit la valeur de la propriété accountPledging.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountPledging(String value) {
        this.accountPledging = value;
    }

    /**
     * Obtient la valeur de la propriété chequeDeliveryMethod.
     * 
     * @return
     *     possible object is
     *     {@link ChequeBookDeliveryMethod }
     *     
     */
    public ChequeBookDeliveryMethod getChequeDeliveryMethod() {
        return chequeDeliveryMethod;
    }

    /**
     * Définit la valeur de la propriété chequeDeliveryMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link ChequeBookDeliveryMethod }
     *     
     */
    public void setChequeDeliveryMethod(ChequeBookDeliveryMethod value) {
        this.chequeDeliveryMethod = value;
    }

    /**
     * Obtient la valeur de la propriété defaultChequeBookType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultChequeBookType() {
        return defaultChequeBookType;
    }

    /**
     * Définit la valeur de la propriété defaultChequeBookType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultChequeBookType(String value) {
        this.defaultChequeBookType = value;
    }

    /**
     * Obtient la valeur de la propriété branchThatDeliveredChequeBook.
     * 
     * @return
     *     possible object is
     *     {@link Branch }
     *     
     */
    public Branch getBranchThatDeliveredChequeBook() {
        return branchThatDeliveredChequeBook;
    }

    /**
     * Définit la valeur de la propriété branchThatDeliveredChequeBook.
     * 
     * @param value
     *     allowed object is
     *     {@link Branch }
     *     
     */
    public void setBranchThatDeliveredChequeBook(Branch value) {
        this.branchThatDeliveredChequeBook = value;
    }

    /**
     * Obtient la valeur de la propriété reasonForAccountClosure.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReasonForAccountClosure() {
        return reasonForAccountClosure;
    }

    /**
     * Définit la valeur de la propriété reasonForAccountClosure.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReasonForAccountClosure(String value) {
        this.reasonForAccountClosure = value;
    }

    /**
     * Obtient la valeur de la propriété userWhoCreated.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUserWhoCreated() {
        return userWhoCreated;
    }

    /**
     * Définit la valeur de la propriété userWhoCreated.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUserWhoCreated(User value) {
        this.userWhoCreated = value;
    }

    /**
     * Obtient la valeur de la propriété userWhoSetThePendingClosureStatus.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUserWhoSetThePendingClosureStatus() {
        return userWhoSetThePendingClosureStatus;
    }

    /**
     * Définit la valeur de la propriété userWhoSetThePendingClosureStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUserWhoSetThePendingClosureStatus(User value) {
        this.userWhoSetThePendingClosureStatus = value;
    }

    /**
     * Obtient la valeur de la propriété userWhoClosed.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUserWhoClosed() {
        return userWhoClosed;
    }

    /**
     * Définit la valeur de la propriété userWhoClosed.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUserWhoClosed(User value) {
        this.userWhoClosed = value;
    }

    /**
     * Obtient la valeur de la propriété branchWhereTheAccountWasCreated.
     * 
     * @return
     *     possible object is
     *     {@link Branch }
     *     
     */
    public Branch getBranchWhereTheAccountWasCreated() {
        return branchWhereTheAccountWasCreated;
    }

    /**
     * Définit la valeur de la propriété branchWhereTheAccountWasCreated.
     * 
     * @param value
     *     allowed object is
     *     {@link Branch }
     *     
     */
    public void setBranchWhereTheAccountWasCreated(Branch value) {
        this.branchWhereTheAccountWasCreated = value;
    }

    /**
     * Obtient la valeur de la propriété branchFromAccountInformationForm.
     * 
     * @return
     *     possible object is
     *     {@link Branch }
     *     
     */
    public Branch getBranchFromAccountInformationForm() {
        return branchFromAccountInformationForm;
    }

    /**
     * Définit la valeur de la propriété branchFromAccountInformationForm.
     * 
     * @param value
     *     allowed object is
     *     {@link Branch }
     *     
     */
    public void setBranchFromAccountInformationForm(Branch value) {
        this.branchFromAccountInformationForm = value;
    }

    /**
     * Obtient la valeur de la propriété lastBranchThatHeldTheAccount.
     * 
     * @return
     *     possible object is
     *     {@link Branch }
     *     
     */
    public Branch getLastBranchThatHeldTheAccount() {
        return lastBranchThatHeldTheAccount;
    }

    /**
     * Définit la valeur de la propriété lastBranchThatHeldTheAccount.
     * 
     * @param value
     *     allowed object is
     *     {@link Branch }
     *     
     */
    public void setLastBranchThatHeldTheAccount(Branch value) {
        this.lastBranchThatHeldTheAccount = value;
    }

    /**
     * Obtient la valeur de la propriété chequeAddressType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChequeAddressType() {
        return chequeAddressType;
    }

    /**
     * Définit la valeur de la propriété chequeAddressType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChequeAddressType(String value) {
        this.chequeAddressType = value;
    }

    /**
     * Obtient la valeur de la propriété chequeAddressCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChequeAddressCode() {
        return chequeAddressCode;
    }

    /**
     * Définit la valeur de la propriété chequeAddressCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChequeAddressCode(String value) {
        this.chequeAddressCode = value;
    }

    /**
     * Obtient la valeur de la propriété ibanAccountKey.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIBANAccountKey() {
        return ibanAccountKey;
    }

    /**
     * Définit la valeur de la propriété ibanAccountKey.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIBANAccountKey(String value) {
        this.ibanAccountKey = value;
    }

    /**
     * Obtient la valeur de la propriété temporaryOpening.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemporaryOpening() {
        return temporaryOpening;
    }

    /**
     * Définit la valeur de la propriété temporaryOpening.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemporaryOpening(String value) {
        this.temporaryOpening = value;
    }

    /**
     * Obtient la valeur de la propriété package.
     * 
     * @return
     *     possible object is
     *     {@link Package }
     *     
     */
    public Package getPackage() {
        return _package;
    }

    /**
     * Définit la valeur de la propriété package.
     * 
     * @param value
     *     allowed object is
     *     {@link Package }
     *     
     */
    public void setPackage(Package value) {
        this._package = value;
    }

    /**
     * Obtient la valeur de la propriété amountOfReservedFunds.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmountOfReservedFunds() {
        return amountOfReservedFunds;
    }

    /**
     * Définit la valeur de la propriété amountOfReservedFunds.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmountOfReservedFunds(BigDecimal value) {
        this.amountOfReservedFunds = value;
    }

    /**
     * Obtient la valeur de la propriété indicativeBalanceUpdatedByFundReservation.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIndicativeBalanceUpdatedByFundReservation() {
        return indicativeBalanceUpdatedByFundReservation;
    }

    /**
     * Définit la valeur de la propriété indicativeBalanceUpdatedByFundReservation.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIndicativeBalanceUpdatedByFundReservation(Boolean value) {
        this.indicativeBalanceUpdatedByFundReservation = value;
    }

    /**
     * Obtient la valeur de la propriété freeInputAmount2.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFreeInputAmount2() {
        return freeInputAmount2;
    }

    /**
     * Définit la valeur de la propriété freeInputAmount2.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFreeInputAmount2(BigDecimal value) {
        this.freeInputAmount2 = value;
    }

    /**
     * Obtient la valeur de la propriété freeInputDate1.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFreeInputDate1() {
        return freeInputDate1;
    }

    /**
     * Définit la valeur de la propriété freeInputDate1.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFreeInputDate1(XMLGregorianCalendar value) {
        this.freeInputDate1 = value;
    }

    /**
     * Obtient la valeur de la propriété freeInputDate2.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFreeInputDate2() {
        return freeInputDate2;
    }

    /**
     * Définit la valeur de la propriété freeInputDate2.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFreeInputDate2(XMLGregorianCalendar value) {
        this.freeInputDate2 = value;
    }

    /**
     * Obtient la valeur de la propriété accountStatementDeliveryMethod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountStatementDeliveryMethod() {
        return accountStatementDeliveryMethod;
    }

    /**
     * Définit la valeur de la propriété accountStatementDeliveryMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountStatementDeliveryMethod(String value) {
        this.accountStatementDeliveryMethod = value;
    }

    /**
     * Obtient la valeur de la propriété freeInputField2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFreeInputField2() {
        return freeInputField2;
    }

    /**
     * Définit la valeur de la propriété freeInputField2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFreeInputField2(String value) {
        this.freeInputField2 = value;
    }

    /**
     * Obtient la valeur de la propriété freeInputField3.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFreeInputField3() {
        return freeInputField3;
    }

    /**
     * Définit la valeur de la propriété freeInputField3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFreeInputField3(String value) {
        this.freeInputField3 = value;
    }

    /**
     * Obtient la valeur de la propriété lastAccountCurrency.
     * 
     * @return
     *     possible object is
     *     {@link SimpleCurrency }
     *     
     */
    public SimpleCurrency getLastAccountCurrency() {
        return lastAccountCurrency;
    }

    /**
     * Définit la valeur de la propriété lastAccountCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link SimpleCurrency }
     *     
     */
    public void setLastAccountCurrency(SimpleCurrency value) {
        this.lastAccountCurrency = value;
    }

    /**
     * Obtient la valeur de la propriété currencyChangeDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCurrencyChangeDate() {
        return currencyChangeDate;
    }

    /**
     * Définit la valeur de la propriété currencyChangeDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCurrencyChangeDate(XMLGregorianCalendar value) {
        this.currencyChangeDate = value;
    }

    /**
     * Obtient la valeur de la propriété jointAccount.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isJointAccount() {
        return jointAccount;
    }

    /**
     * Définit la valeur de la propriété jointAccount.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setJointAccount(Boolean value) {
        this.jointAccount = value;
    }

    /**
     * Obtient la valeur de la propriété responsibleCustomer.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsibleCustomer() {
        return responsibleCustomer;
    }

    /**
     * Définit la valeur de la propriété responsibleCustomer.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsibleCustomer(String value) {
        this.responsibleCustomer = value;
    }

    /**
     * Obtient la valeur de la propriété jointAccountTitle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJointAccountTitle() {
        return jointAccountTitle;
    }

    /**
     * Définit la valeur de la propriété jointAccountTitle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJointAccountTitle(String value) {
        this.jointAccountTitle = value;
    }

    /**
     * Obtient la valeur de la propriété freeAttributes.
     * 
     * @return
     *     possible object is
     *     {@link GetAccountFreeAttributesDetailResponse }
     *     
     */
    public GetAccountFreeAttributesDetailResponse getFreeAttributes() {
        return freeAttributes;
    }

    /**
     * Définit la valeur de la propriété freeAttributes.
     * 
     * @param value
     *     allowed object is
     *     {@link GetAccountFreeAttributesDetailResponse }
     *     
     */
    public void setFreeAttributes(GetAccountFreeAttributesDetailResponse value) {
        this.freeAttributes = value;
    }

    /**
     * Obtient la valeur de la propriété stoppages.
     * 
     * @return
     *     possible object is
     *     {@link GetStoppageListResponse }
     *     
     */
    public GetStoppageListResponse getStoppages() {
        return stoppages;
    }

    /**
     * Définit la valeur de la propriété stoppages.
     * 
     * @param value
     *     allowed object is
     *     {@link GetStoppageListResponse }
     *     
     */
    public void setStoppages(GetStoppageListResponse value) {
        this.stoppages = value;
    }

    /**
     * Obtient la valeur de la propriété overdraftAuthorization.
     * 
     * @return
     *     possible object is
     *     {@link GetOverdraftAuthNDetailResponse }
     *     
     */
    public GetOverdraftAuthNDetailResponse getOverdraftAuthorization() {
        return overdraftAuthorization;
    }

    /**
     * Définit la valeur de la propriété overdraftAuthorization.
     * 
     * @param value
     *     allowed object is
     *     {@link GetOverdraftAuthNDetailResponse }
     *     
     */
    public void setOverdraftAuthorization(GetOverdraftAuthNDetailResponse value) {
        this.overdraftAuthorization = value;
    }

    /**
     * Obtient la valeur de la propriété availableBalance.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    /**
     * Définit la valeur de la propriété availableBalance.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAvailableBalance(BigDecimal value) {
        this.availableBalance = value;
    }

}
