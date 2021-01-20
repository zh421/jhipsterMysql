package aiot.java.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZoneId;

@AllArgsConstructor
public enum ZoneType {

    GMT(ZoneId.of("GMT"), "國際標準時區"),

    TAIPEI(ZoneId.of("Asia/Taipei"), "台灣台北");

    @Getter private ZoneId zoneId;
    @Getter private String name;

// TODO:這邊要驗證這樣用對不對，原本沒有以下程式
    ZoneType(ZoneId gmt, String name) {
    }

    public ZoneId getZoneId() {
        return zoneId;
    }
}
