package com.spectrum.notesapi.model.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "properties-account")
public class Account {

    //@TODO: copied from wifi - check the usage and delete it
    private String region_id;
    private String address_zip;
    private String address_city;
    private String address_apt;
    private String address_state;
    private String address_street;
    private String coordinates_latitude;
    private String coordinates_longitude;
    private String insertDateTime;
}


