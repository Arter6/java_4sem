package com.example.java_3110;

public class Rogue extends Player
{
    int defence=30;
    int attack=20;

    @Override
    void battleTo(Player player2)
    {
        super.battleTo(player2);
        player2.attack-=attack;
    }
}
