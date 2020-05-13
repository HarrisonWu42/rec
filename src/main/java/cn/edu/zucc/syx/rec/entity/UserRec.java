package cn.edu.zucc.syx.rec.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRec {
    @Field(type = FieldType.Nested)
    private List<KeySong> songs;

    @Field(type = FieldType.Nested)
    private List<KeyArtists> artists;
}
