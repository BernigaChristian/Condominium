package me.berniga;

import java.util.ArrayList;

public class Condominium {
    private static int condominiums = 0;
    private final int id;
    private int ownerId;
    private ArrayList<Flat> flats;

    //constructors
    public Condominium(int ownerId) {
        id = ++condominiums;
        this.ownerId = ownerId;
    }

}
