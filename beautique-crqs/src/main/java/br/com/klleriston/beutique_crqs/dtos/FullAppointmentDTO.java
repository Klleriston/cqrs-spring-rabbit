package br.com.klleriston.beutique_crqs.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullAppointmentDTO {
    private Long id;
    private LocalDateTime dateTime;
    private Boolean appointmentOpen;

    private CustomerDTO customer;
    private BeautyProcedureDTO beautyProcedure;
}
