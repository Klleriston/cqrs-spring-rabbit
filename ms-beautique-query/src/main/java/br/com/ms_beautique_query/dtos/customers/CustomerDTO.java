package br.com.ms_beautique_query.dtos.customers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(collection = "customers")
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
