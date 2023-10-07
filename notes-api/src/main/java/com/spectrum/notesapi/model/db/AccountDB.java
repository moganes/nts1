package com.spectrum.notesapi.model.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "properties-account")
public class AccountDB {


    @Field("account")
    private String account;

    @Field("ecpBillingDivision")
    private String billingID;

    private String accountHolderName;

    private String customerType;

    @Field("address_zip")
    private String addressZip;

    @Field("address_city")
    private String addressCity;

    @Field("address_apt")
    private String addressApt;

    @Field("address_state")
    private String addressState;

    @Field("address_street")
    private String addressStreet;

    @Field("coordinates_latitude")
    private String coordinatesLatitude;

    @Field("coordinates_longitude")
    private String coordinatesLongitude;

    private String recordID;

    private String insertDateTime;

    private String region_id;
    private String soaDivisionID;
    private String spcDivisionID;
}