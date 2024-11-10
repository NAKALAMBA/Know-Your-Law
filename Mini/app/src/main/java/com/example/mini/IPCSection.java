package com.example.mini;

public class IPCSection {
    private String description;
    private String offense;
    private String punishment;

    public IPCSection() { }

    public IPCSection(String description, String offense, String punishment) {
        this.description = description;
        this.offense = offense;
        this.punishment = punishment;
    }

    public String getDescription() {
        return description;
    }

    public String getOffense() {
        return offense;
    }

    public String getPunishment() {
        return punishment;
    }
}
