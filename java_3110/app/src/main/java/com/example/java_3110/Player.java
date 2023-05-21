package com.example.java_3110;

public class Player {
    int health = 100;
    int defence = 40;
    int attack = 15;

    void battleTo(Player player2)
    {
        if (player2.getClass()==Knight.class)
        {
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
        if (player2.defence>0)
        {
            defence-=attack/2;
            health-=attack/2;
        }
        else
        {
            health-=attack;
        }
    }
}
