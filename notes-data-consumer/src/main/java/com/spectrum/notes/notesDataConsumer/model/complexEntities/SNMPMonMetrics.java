package com.spectrum.notes.notesDataConsumer.model.complexEntities;

import com.spectrum.notes.notesDataConsumer.model.SNMPMonMetricsFlat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author alam
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SNMPMonMetrics {

    private String metric;
    private Tags tags;
    private Integer timestamp;
    private Float value;

    public SNMPMonMetricsFlat toFlat() {
        return SNMPMonMetricsFlat.builder()
                .cmtsID(tags.getCmtSid())
                .id(tags.getId())
                .timestamp(timestamp)
                .metric(metric)
                .value(value)
                .build();
    }
}