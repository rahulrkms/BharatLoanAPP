package com.example.bharatloan.model;
import java.util.List;
public class ModelFetchAllDetails {
    private List<Document> documents;
    private List<LoanAmount> loan_amount;
    private List<PersonalDetails> personal_details;

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<LoanAmount> getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(List<LoanAmount> loan_amount) {
        this.loan_amount = loan_amount;
    }

    public List<PersonalDetails> getPersonal_details() {
        return personal_details;
    }

    public void setPersonal_details(List<PersonalDetails> personal_details) {
        this.personal_details = personal_details;
    }

    public class Document {
        private String id;
        private String mobile_no;
        private String pan_card;
        private String aadhar_card;
        private String dob;
        private String gender;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMobile_no() {
            return mobile_no;
        }

        public void setMobile_no(String mobile_no) {
            this.mobile_no = mobile_no;
        }

        public String getPan_card() {
            return pan_card;
        }

        public void setPan_card(String pan_card) {
            this.pan_card = pan_card;
        }

        public String getAadhar_card() {
            return aadhar_card;
        }

        public void setAadhar_card(String aadhar_card) {
            this.aadhar_card = aadhar_card;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }

    public class LoanAmount {
        private String id;
        private String mobile_no;
        private String amount_for_loan;
        private String tenure;
        private String account_no;
        private String ifsc_code;
        private String bank_name;

        // Add getters and setters

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMobile_no() {
            return mobile_no;
        }

        public void setMobile_no(String mobile_no) {
            this.mobile_no = mobile_no;
        }

        public String getAmount_for_loan() {
            return amount_for_loan;
        }

        public void setAmount_for_loan(String amount_for_loan) {
            this.amount_for_loan = amount_for_loan;
        }

        public String getTenure() {
            return tenure;
        }

        public void setTenure(String tenure) {
            this.tenure = tenure;
        }

        public String getAccount_no() {
            return account_no;
        }

        public void setAccount_no(String account_no) {
            this.account_no = account_no;
        }

        public String getIfsc_code() {
            return ifsc_code;
        }

        public void setIfsc_code(String ifsc_code) {
            this.ifsc_code = ifsc_code;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }
    }

    public class PersonalDetails {
        private String name;
        private String email;

        // Add getters and setters

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

}
