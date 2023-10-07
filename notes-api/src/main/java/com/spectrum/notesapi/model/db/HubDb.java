package com.spectrum.notesapi.model.db;

/**
 * Created by "Mohammad Shah Alam" on 04 Aug, 2020
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Builder
@Document(indexName = "properties-hub")
@AllArgsConstructor
@NoArgsConstructor
public class HubDb {
    private String hubName;
    private String recordID;
}
