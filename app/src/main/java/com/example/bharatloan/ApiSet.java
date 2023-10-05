package com.example.bharatloan;


import com.example.bharatloan.model.ModelFetchAllDetails;
import com.example.bharatloan.model.ModelFetchRepaymentDetails;
import com.example.bharatloan.model.ModelFetchStaticLink;
import com.example.bharatloan.model.ModelInsertPersonalDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiSet {
    //-----------------------------------------------------------GET--------------------------------------------------------------------------
    @GET("fetch_static_payment.php")
    Call<List<ModelFetchStaticLink>> fetchStaticAmount();


    //
//    //----------------------------------------------------GET POST-----------------------------------------------------------------------------
//    @FormUrlEncoded
//    @POST("fetch_ids_on_home_fragment.php")
//    Call<List<ModelHomeFragment>> getMyids(
//            @Field("user_credentials") String user_credentials
//
//    //-------------------------------------------------------POST-----------------------------------------------------------------------------------
//
    @FormUrlEncoded
    @POST("insert_personal_details.php")
    Call<ModelInsertPersonalDetails> sendCreateIdRequestDetails(
            @Field("mobile_no") String mobile_no,
            @Field("name") String name,
            @Field("email") String email

    );

    @FormUrlEncoded
    @POST("insert_documents.php")
    Call<ModelInsertPersonalDetails> sendDocuments(
            @Field("mobile_no") String mobile_no,
            @Field("pan_card") String pan_card,
            @Field("aadhar_card") String aadhar,
            @Field("dob") String dob,
            @Field("gender") String gender

    );

    @FormUrlEncoded
    @POST("insert_loan_amount.php")
    Call<ModelInsertPersonalDetails> sendLoanAmount(
            @Field("mobile_no") String mobile_no,
            @Field("amount_for_loan") String amount_for_loan,
            @Field("tenure") String tenure,
            @Field("account_no") String acc_no,
            @Field("ifsc_code") String ifsc_code,
            @Field("bank_name") String bank_name);
    @FormUrlEncoded
    @POST("insert_pay.php")
    Call<ModelInsertPersonalDetails> sendPay(
            @Field("mob_num") String mob_num,
            @Field("loan_amount") String loan_amount,
            @Field("pay_again") String pay_again,
            @Field("payment_link") String payment_link,
            @Field("quotes") String quotes
    );

    @FormUrlEncoded
    @POST("fetch_repayment_details.php")
    Call<List<ModelFetchRepaymentDetails>> fetchRepaymentDetail(
            @Field("mob_num") String mob_num
    );

    @FormUrlEncoded
    @POST("fetch_all_details.php")
    Call<ModelFetchAllDetails>fetchAllDetails(
            @Field("mob_num") String mob_num
    );

}