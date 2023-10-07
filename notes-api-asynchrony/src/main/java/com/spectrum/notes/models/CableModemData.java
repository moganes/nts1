package com.spectrum.notes.models;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CableModemData {
    private CableModemDaily cableModemDaily;
    private CableModem5Min cableModem5Min;
}
