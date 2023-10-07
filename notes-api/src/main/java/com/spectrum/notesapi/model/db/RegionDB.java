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
@Document(indexName = "properties-region")
public class RegionDB {

    private String comBasicVideoSub;
    private String comHSDSub;
    private String comVoiceSub;
    private String resBasicVideoSub;
    private String resHSDSub;
    private String resVoiceSub;
    private String coordinates_latitude;
    private String coordinates_longitude;
    private String enabled;
    private String name;
    private String remedy_name;
    private String recordID;
    private String insertDateTime;
}