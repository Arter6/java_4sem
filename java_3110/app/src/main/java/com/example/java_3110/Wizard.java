package com.example.java_3110;

public class Wizard extends Player
{
    int defence=50;
    int attack=40;

    @Override
    void battleTo(Player player2) {
        if (player2.defence>0)
        {
            defence-=attack/3;
            health-=2*attack/3;
        }
        else
        {
            health-=attack;
        }
    }
}
