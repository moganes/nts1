package com.spectrum.notesapi.model.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by "Mohammad Shah Alam" on 20 May, 2021
 */

@Data
@Builder
@Document(indexName = "properties-macdomain")
@AllArgsConstructor
@NoArgsConstructor
public class MacDomainDB {
    private String name;
    private String recordID;
}
