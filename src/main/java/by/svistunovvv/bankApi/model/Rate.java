package by.svistunovvv.bankApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = @Index(name = "idx_rate_currency_date", columnList = "currency, date"))
public class Rate {

    @Id
    @GeneratedValue
    private Long id;
    private String currency;
    private String date;
    private String officialRate;
    private String name;
    private Integer scale;
}
