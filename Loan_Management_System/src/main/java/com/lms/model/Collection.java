package com.lms.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@NamedNativeQuery(name = "Collection.getAllCollections", query = "CALL GetAllCollections()", resultClass = Collection.class)
@Table(name = "collection_master")
public class Collection {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int loanId;
    private LocalDate collDate;
    private double collAmmount;
    private int collType;
    private int collBy;
    @Column(name = "entry_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private LocalDate entryDate;
    private int collStatus;
    private int collBranchId;
    private int collUpadateRepayment;
    @Column(name = "pending_amount", columnDefinition = "DEFAULT 0.0")
    private double pendingAmount;


    public Collection() {
        super();
        // TODO Auto-generated constructor stub
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getLoanId() {
        return loanId;
    }


    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }


    public LocalDate getCollDate() {
        return collDate;
    }


    public void setCollDate(LocalDate collDate) {
        this.collDate = collDate;
    }


    public double getCollAmmount() {
        return collAmmount;
    }


    public void setCollAmmount(double collAmmount) {
        this.collAmmount = collAmmount;
    }


    public int getCollType() {
        return collType;
    }


    public void setCollType(int collType) {
        this.collType = collType;
    }


    public int getCollBy() {
        return collBy;
    }


    public void setCollBy(int collBy) {
        this.collBy = collBy;
    }


    public LocalDate getEntryDate() {
        return entryDate;
    }


    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }


    public int getCollStatus() {
        return collStatus;
    }


    public void setCollStatus(int collStatus) {
        this.collStatus = collStatus;
    }


    public int getCollBranchId() {
        return collBranchId;
    }


    public void setCollBranchId(int collBranchId) {
        this.collBranchId = collBranchId;
    }


    public int getCollUpadateRepayment() {
        return collUpadateRepayment;
    }


    public void setCollUpadateRepayment(int collUpadateRepayment) {
        this.collUpadateRepayment = collUpadateRepayment;
    }


    public double getPendingAmount() {
        return pendingAmount;
    }


    public void setPendingAmount(double pendingAmount) {
        this.pendingAmount = pendingAmount;
    }


    public Collection(int id, int loanId, LocalDate collDate, double collAmmount, int collType, int collBy, LocalDate entryDate, int collStatus, int collBranchId, int collUpadateRepayment, double pendingAmount) {
        super();
        this.id = id;
        this.loanId = loanId;
        this.collDate = collDate;
        this.collAmmount = collAmmount;
        this.collType = collType;
        this.collBy = collBy;
        this.entryDate = entryDate;
        this.collStatus = collStatus;
        this.collBranchId = collBranchId;
        this.collUpadateRepayment = collUpadateRepayment;
        this.pendingAmount = pendingAmount;
    }


    @Override
    public String toString() {
        return "Collection [id=" + id + ", loanId=" + loanId + ", collDate=" + collDate + ", collAmmount=" + collAmmount + ", collType=" + collType + ", collBy=" + collBy + ", entryDate=" + entryDate + ", collStatus=" + collStatus + ", collBranchId=" + collBranchId + ", collUpadateRepayment=" + collUpadateRepayment + ", pendingAmount=" + pendingAmount + "]";
    }


}
