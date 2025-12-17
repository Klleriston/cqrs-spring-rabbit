package br.com.klleriston.beutique_crqs.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDTO {
    private Long id;
    private LocalDateTime dateTime;
    private Boolean appointmentOpen;

    private Long customer;
    private Long beautyProcedure;
}
