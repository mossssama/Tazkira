package com.example.alarm.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Surah implements Serializable {
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("englishName")
    @Expose
    private String englishName;
    @SerializedName("englishNameTranslation")
    @Expose
    private String englishNameTranslation;
    @SerializedName("numberOfAyahs")
    @Expose
    private Integer numberOfAyahs;
    @SerializedName("revelationType")
    @Expose
    private String revelationType;
    private final static long serialVersionUID = -6781927473565222084L;

    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }
    public String getEnglishNameTranslation() {
        return englishNameTranslation;
    }

    public void setEnglishNameTranslation(String englishNameTranslation) {
        this.englishNameTranslation = englishNameTranslation;
    }

    public Integer getNumberOfAyahs() {
        return numberOfAyahs;
    }
    public void setNumberOfAyahs(Integer numberOfAyahs) {
        this.numberOfAyahs = numberOfAyahs;
    }

    public String getRevelationType() {
        return revelationType;
    }
    public void setRevelationType(String revelationType) {
        this.revelationType = revelationType;
    }

}
