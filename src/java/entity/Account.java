/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author huynh
 */
public class Account {
    private int accID;
    private String accName;
    private String phoneNum;
    private String accPassword;
    private int isAdmin;
    private int isSeller;

    public Account() {
    }

    public Account(int accID, String accName, String phoneNum, String accPassword, int isAdmin, int isSeller) {
        this.accID = accID;
        this.accName = accName;
        this.phoneNum = phoneNum;
        this.accPassword = accPassword;
        this.isAdmin = isAdmin;
        this.isSeller = isSeller;
    }

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAccPassword() {
        return accPassword;
    }

    public void setAccPassword(String accPassword) {
        this.accPassword = accPassword;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(int isSeller) {
        this.isSeller = isSeller;
    }

    @Override
    public String toString() {
        return "Account{" + "accID=" + accID + ", accName=" + accName + ", phoneNum=" + phoneNum + ", accPassword=" + accPassword + ", isAdmin=" + isAdmin + ", isSeller=" + isSeller + '}';
    }




    
    
}
