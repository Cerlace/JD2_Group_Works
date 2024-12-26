package itacademy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CarDTO {
    private Integer id;
    private String vin;
    private String name;
    private Timestamp changeTime;
    private Timestamp creationTime;
}
