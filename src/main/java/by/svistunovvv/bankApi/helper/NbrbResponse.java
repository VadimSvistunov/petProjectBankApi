package by.svistunovvv.bankApi.helper;

import lombok.Data;

@Data
public class NbrbResponse {
    private int cur_id;
    private String date;
    private String cur_abbreviation;
    private int cur_scale;
    private String cur_name;
    private String cur_officialrate;
}
