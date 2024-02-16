package com.example.ispw.enums;

public enum SeriesStatus {
    CURRENTLY_WATCHING("Currently watching"),
    NOT_YET_STARTED("Not yet started"),
    COMING_SOON("Coming soon"),
    FINISHED_WATCHING("Finished watching");

    private final String id;

    SeriesStatus(String id) {
        this.id = id;
    }

    public static SeriesStatus fromString(String id) {
        for (SeriesStatus type : values()) {
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
