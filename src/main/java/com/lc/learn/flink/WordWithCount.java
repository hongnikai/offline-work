package com.lc.learn.flink;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lc 2020-09-23 15:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordWithCount {

    public String word;
    public long count;

}
