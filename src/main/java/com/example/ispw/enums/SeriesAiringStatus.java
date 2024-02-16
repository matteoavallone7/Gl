package com.example.ispw.enums;

public enum SeriesAiringStatus {
    ON_AIR("On air"),
    RENEWED("Renewed"),
    COMPLETED("Completed");

    private final String id;

    SeriesAiringStatus(String id) {
        this.id = id;
    }

    public static SeriesAiringStatus fromString(String id) {
        for (SeriesAiringStatus type : values()) {
            if (type.getId().equals(id)) {
                return type;
            }
        }
        return null;
    }

    public String getId() {
        return id;
    }

}
