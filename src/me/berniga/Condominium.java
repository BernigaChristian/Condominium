package me.berniga;

import java.util.ArrayList;

public class Condominium {
    private static int condominiums = 0;
    private final int id;
    private ArrayList<Flat> flats = new ArrayList<Flat>();

    //constructors
    public Condominium() {
        id = ++condominiums;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public ArrayList<Flat> getFlats() {return flats;}

    public int getFlatsNumber() {
        return flats.size();
    }

    public Flat getFlat(int i) {
        return flats.get(i);
    }

    //methods
    public void addFlat(int ownerId, int compartements, int surface, int people) {
        flats.add(new Flat(ownerId, compartements, surface, people));
    }

    public int surface() {
        int totSurface = 0;
        for (Flat flat : flats) totSurface += flat.getSurface();
        return totSurface;
    }

}
