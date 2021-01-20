package com.marcos_narvaez.examen_practico_android_marcos_narvaez;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Random;

@Entity(tableName = "persona_table")
public class Persona {

    @Ignore
    private final boolean IS_MAN = true;

    @Ignore
    private final int BELOW_IDEAL_WEIGHT = -1;

    @Ignore
    private final int ABOVE_IDEAL_WEIGHT = 1;

    @Ignore
    private final int AT_IDEAL_WEIGHT = 0;

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name = "";
    private int age = 0;

    public int getId() {
        return id;
    }

    public void setNSS(String NSS) {
        this.NSS = NSS;
    }

    private String NSS;
    private boolean gender = IS_MAN;
    private double weight = 0;
    private double height = 0;

    public Persona(String name, int age, boolean gender, double weight, double height) {
        this.name = name;
        this.age = age;
        this.NSS = generateNSS(8);
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    public boolean isOlderEnough(){
        return this.age >= 18;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + name + '\'' +
                ", edad=" + age +
                ", NSS='" + NSS + '\'' +
                ", genero=" + gender +
                ", peso=" + weight +
                ", estatura=" + height +
                ", IMC=" + calculateIMC() +
                ", es mayor de edad=" + isOlderEnough() +
                '}';
    }

    public int calculateIMC(){
        double imc = (this.weight/((height/100)*(height/100)));

        if(gender) {
            return manImc(imc);
        } else {
            return womanImc(imc);
        }
    }

    private int manImc(double imc){

        if(imc < 20){
            return BELOW_IDEAL_WEIGHT;
        } else if (imc > 25) {
            return ABOVE_IDEAL_WEIGHT;
        } else {
            return AT_IDEAL_WEIGHT;
        }
    }

    private int womanImc(double imc){

        if(imc < 19){
            return BELOW_IDEAL_WEIGHT;
        } else if (imc > 24) {
            return ABOVE_IDEAL_WEIGHT;
        } else {
            return AT_IDEAL_WEIGHT;
        }
    }

    private String generateNSS(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append("0123456789qwertyuiopasdfghjklzxcvbnm".charAt(random.nextInt("0123456789qwertyuiopasdfghjklzxcvbnm".length())));
        return sb.toString();
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNSS() {
        return NSS;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
