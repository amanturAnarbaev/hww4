package com.company;


import java.util.Random;

public class Main {
    public static  int bossHealth = 700;
    public static int bossDamage = 50;

    public static int[] heroesHealth = {250,270, 280, 260};
    public static int[] heroesDamage = {0,20,15,25};


    public static String[] heroesAttackType = {"MEDIC ","Physical", "Magical", "Kinetic"};
    public static String bossDefenceType = "";

    public static int roundNumber = 0;


    public static void main(String[] args) {

        printStatistics();
        while (!isGameFinished()){
            round();
        }

    }

    public static Boolean isGameFinished(){
        if (bossHealth<=0){
            System.out.println("Heroes won!");
            return true;
        }

        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!");
            return  true;
        }
        return false;
    }

    public static void printStatistics(){
        System.out.println(roundNumber + " ROUND");
        System.out.println("Boss Health: " + bossHealth + "["+bossDamage+"]");

        for (int i = 0; i < heroesDamage.length; i++) {

            System.out.println(heroesAttackType[i] + " health " + heroesHealth[i] +"["+heroesDamage[i]+"]");
        }
    }

    public static void bossHits(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if(heroesHealth[i] < bossDamage){
                heroesHealth[i] = 0;
            }else {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
            for (int j = 1; j < heroesHealth.length; j++) {
                if (heroesHealth[i]<100 && heroesHealth[i]>0){
                    heroesHealth[i]=heroesHealth[i]+40;
                    break;
                }
                continue;}
        }
    }


    public static void medic(){
        for (int i = 1; i < heroesHealth.length; i++) {
            if (heroesHealth[i]<100 && heroesHealth[i]>0){
                heroesHealth[i]=heroesHealth[i]+40;
                break;
            }
            continue;}
    }

    public static  void heroesHits(){

        for (int i = 0; i < heroesDamage.length; i++) {
            if(heroesHealth[i]>0 && bossHealth>0) {
                if(bossDefenceType ==heroesAttackType[i]){
                    Random r = new Random();
                    int coef = r.nextInt(8)+2;

                    if(bossHealth < heroesDamage[i]*coef){
                        bossHealth  = 0;
                    }else {
                        bossHealth = bossHealth - heroesDamage[i]*coef;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i]*coef);
                }else{
                    if(bossHealth<heroesDamage[i]){
                        bossHealth = 0;
                    }else{
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }

        }
    }

    public static void chooseBossDefenceType(){
        Random random = new Random();
        int randomIndex = random.nextInt(3)+1;
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choice: " + bossDefenceType);
    }



    public static void round(){
        roundNumber++;
        chooseBossDefenceType();
        bossHits();

        heroesHits();
        printStatistics();
    }

}

