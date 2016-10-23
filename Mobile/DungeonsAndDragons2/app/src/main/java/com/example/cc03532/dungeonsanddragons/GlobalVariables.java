package com.example.cc03532.dungeonsanddragons;

import android.app.Application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cc03532 on 9/20/2016.
 */
public class GlobalVariables extends Application implements Serializable {

    private String USERNAME;

    private String CHARACTER_NAME;

    private String RACE_VALUE;
    private String SUBRACE_VALUE;

    private int STRENGTH_RACE_MODIFIER = 0;
    private int DEXTERITY_RACE_MODIFIER = 0;
    private int CONSTITUTION_RACE_MODIFIER = 0;
    private int INTELLIGENCE_RACE_MODIFIER = 0;
    private int WISDOM_RACE_MODIFIER = 0;
    private int CHARISMA_RACE_MODIFIER = 0;

    private String CLASS_VALUE;
    private String HIT_DIE;

    private int STRENGTH_VALUE = 0;
    private int DEXTERITY_VALUE = 0;
    private int CONSTITUTION_VALUE = 0;
    private int INTELLIGENCE_VALUE = 0;
    private int WISDOM_VALUE = 0;
    private int CHARISMA_VALUE = 0;

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getCHARACTER_NAME() {
        return CHARACTER_NAME;
    }

    public void setCHARACTER_NAME(String characterName) {
        this.CHARACTER_NAME = characterName;
    }

    public String getRACE_VALUE() {
        return RACE_VALUE;
    }
    public void setRACE_VALUE(String RACE_VALUE) {
        this.RACE_VALUE = RACE_VALUE;
    }

    public void setSUBRACE_VALUE(String newVal) { SUBRACE_VALUE = newVal; }
    public String getSUBRACE_VALUE() { return SUBRACE_VALUE; }

    public void setSTRENGTH_RACE_MODIFIER(int newVal){
        STRENGTH_RACE_MODIFIER = newVal;
    }
    public int getSTRENGTH_RACE_MODIFIER(){
        return STRENGTH_RACE_MODIFIER;
    }

    public void setDEXTERITY_RACE_MODIFIER(int newVal) { DEXTERITY_RACE_MODIFIER = newVal; }
    public int getDEXTERITY_RACE_MODIFIER(){
        return DEXTERITY_RACE_MODIFIER;
    }

    public void setCONSTITUTION_RACE_MODIFIER(int newVal) { CONSTITUTION_RACE_MODIFIER = newVal; }
    public int getCONSTITUTION_RACE_MODIFIER(){
        return CONSTITUTION_RACE_MODIFIER;
    }

    public void setINTELLIGENCE_RACE_MODIFIER(int newVal) { INTELLIGENCE_RACE_MODIFIER = newVal; }
    public int getINTELLIGENCE_RACE_MODIFIER(){
        return INTELLIGENCE_RACE_MODIFIER;
    }

    public void setWISDOM_RACE_MODIFIER(int newVal){
       WISDOM_RACE_MODIFIER = newVal;
    }
    public int getWISDOM_RACE_MODIFIER(){
        return WISDOM_RACE_MODIFIER;
    }

    public void setCHARISMA_RACE_MODIFIER(int newVal){
        CHARISMA_RACE_MODIFIER = newVal;
    }
    public int getCHARISMA_RACE_MODIFIER() { return CHARISMA_RACE_MODIFIER; }

    public void setCLASS_VALUE(String newVal) { CLASS_VALUE = newVal; }
    public String getCLASS_VALUE() { return CLASS_VALUE; }

    public void setHIT_DIE(String newVal) { HIT_DIE = newVal; }
    public String getHIT_DIE() { return HIT_DIE; }

    public void setSTRENGTH_VALUE(int newVal) {
        STRENGTH_VALUE = newVal;
    }
    public int getSTRENGTH_VALUE() {
        return STRENGTH_VALUE;
    }

    public void setDEXTERITY_VALUE(int newVal) {
        DEXTERITY_VALUE = newVal;
    }
    public int getDEXTERITY_VALUE() {
        return DEXTERITY_VALUE;
    }

    public void setCONSTITUTION_VALUE(int newVal) {
        CONSTITUTION_VALUE = newVal;
    }
    public int getCONSTITUTION_VALUE() {
        return CONSTITUTION_VALUE;
    }

    public void setINTELLIGENCE_VALUE(int newVal) {
        INTELLIGENCE_VALUE = newVal;
    }
    public int getINTELLIGENCE_VALUE() {
        return INTELLIGENCE_VALUE;
    }

    public void setWISDOM_VALUE(int newVal) {
        WISDOM_VALUE = newVal;
    }
    public int getWISDOM_VALUE() {
        return WISDOM_VALUE;
    }

    public void setCHARISMA_VALUE(int newVal) {
        CHARISMA_VALUE = newVal;
    }
    public int getCHARISMA_VALUE() { return CHARISMA_VALUE; }
}
