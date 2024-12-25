package itacademy.entity;

import itacademy.utils.CarConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "cars")
public class CarEntity extends CarConverter implements Serializable {
    /**
     *Поля CarEntity заменены геттерами, чтобы использовались поля класса CarConverter и можно было навесить над ними аннотации
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Override
    public Integer getId(){
        return super.getId();
    }

    @Column(name = "vin")
    @Override
    public String getVin() {
        return super.getVin();
    }

    @Column(name = "name")
    @Override
    public String getName() {
        return super.getName();
    }

}
