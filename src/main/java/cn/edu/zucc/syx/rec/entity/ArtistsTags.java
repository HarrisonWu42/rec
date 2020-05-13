package cn.edu.zucc.syx.rec.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistsTags {
    // 歌手标签
    @Field(type = FieldType.Keyword)
    private String tag;

    // 标签频率
    @Field(type = FieldType.Float)
    private Float freq;

    // 标签权重
    @Field(type = FieldType.Float)
    private Float weight;
}
