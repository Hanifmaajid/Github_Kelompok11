package com.example.github;

public class Repository {
    private String name;
    private String language;
    private String owner;

    private final String nameStart = "Name: ";
    private final String languageStart = "Language: ";
    private final String ownerStart = "Owner: ";

    public Repository(String name, String language, String owner, String stars) {
        this.name = name;
        this.language = language;
        this.owner = owner;
    }

    public String getName() {
        return this.nameStart + name;
    }

    public String getLanguage() {
        return this.language + language;
    }

    public String getOwner() {
        return this.ownerStart + owner;
    }
}
