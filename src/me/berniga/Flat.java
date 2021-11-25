package me.berniga;

public class Flat {
    private final int WATERFEE = 1;
    private final int HEATINGFEE = 4;
    private static int flats = 0;
    private final int id;
    private int ownerId;
    private int compartments;
    private int surface;
    private double thousands;
    private int people;
    private double waterConsumption;
    private double heatingTime;

    //constructors
    public Flat(int ownerId, int compartments, int surface, int people) {
        id = ++flats;
        this.ownerId = ownerId;
        this.compartments = compartments;
        this.surface = surface;
        this.people = people;
        this.waterConsumption = 0;
        this.heatingTime = 0;
    }

    //getters & setters
    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getCompartments() {
        return compartments;
    }

    public void setCompartments(int compartments) {
        this.compartments = compartments;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public double getThousands() {
        return thousands;
    }

    public void setThousands(double totSurface) {
        this.thousands = (1000 * surface) / totSurface;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public double getWaterConsumption() {
        return waterConsumption;
    }

    public void setWaterConsumption(double waterConsumption) {
        this.waterConsumption = waterConsumption;
    }

    public double getHeatingTime() {
        return heatingTime;
    }

    public void setHeatingConsumption(double heatingTime) {
        this.heatingTime = heatingTime;
    }

    //methods
    public void shower(int people) {
        this.waterConsumption += (50 * people);
    }

    public void heating(int time) {
        this.heatingTime += time;
    }

    public double costs() {return ((this.heatingTime * HEATINGFEE) + thousands) + ((this.waterConsumption / 100)*WATERFEE);}

}
