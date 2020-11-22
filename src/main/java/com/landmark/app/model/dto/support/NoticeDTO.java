package com.landmark.app.model.dto.support;

import com.landmark.app.model.domain.support.Notice;
import com.landmark.app.utils.MapperUtils;
import com.landmark.app.utils.helper.StaticHelper;
import lombok.Data;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

@Data
public class NoticeDTO {

    private int id;
    private String title;
    private String content;
    private Date createdTime;
    private Date modifiedTime;

    public String getCreatedTime() {
        return StaticHelper.dateToString(createdTime, "yyyy-MM-dd HH:mm");
    }

    public String getModifiedTime() {
        return StaticHelper.dateToString(modifiedTime, "yyyy-MM-dd HH:mm");
    }

    public static NoticeDTO of(Notice notice) { return MapperUtils.convert(notice, NoticeDTO.class); }

    public static List<NoticeDTO> of(List<Notice> notice) {
        return MapperUtils.convert(notice, new TypeToken<List<NoticeDTO>>(){}.getType());
    }

    public static Page<NoticeDTO> of(Page<Notice> notice) {
        return MapperUtils.convert(notice, NoticeDTO.class);
    }
}
