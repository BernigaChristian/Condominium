package me.berniga;
import java.time.LocalDate;
import java.util.Random;
public class YearSimulation {
    public static Owner ownersList(){
        String[] tempNames={"George","John","Michael","Josh","Jett","Hunter","Robert","Will"};
        String[] tempSurnames={"Lawrence","Smith","Johnshon","Brown","Anderson","Moore","Jackson"};
        return new Owner(tempNames[new Random().nextInt(tempNames.length)],tempSurnames[new Random().nextInt(tempSurnames.length)]);
    }
    public static Condominium condominiumList(){
        Condominium c1=new Condominium(ownersList().getId());
        for(int i=0;i<10;i++)
            c1.addFlat(ownersList().getId(),new Random().nextInt(8)+1,new Random().nextInt(40)+40,new Random().nextInt(4)+1);
        for(int i=0;i<c1.getFlatsNumber();i++)
            c1.getFlat(i).setThousands(c1.surface());
        return c1;
    }
    public static void simulation(Condominium c){
        LocalDate date=LocalDate.of(2022,1,1);
        for(int j=0;j<12;j++){
            for(int i=0;i<date.lengthOfMonth();i++){
                for(int k=0;k<c.getFlatsNumber();k++){
                    Flat temp=c.getFlat(k);
                    temp.shower(new Random().nextInt(c.getFlat(k).getPeople())+1);
                    if(j<4||j>9)   temp.heating((new Random().nextInt(5)+6)+((new Random().nextInt(4)+1)));
                }
                date=date.plusDays(1);
            }
        }
        costs(c);
    }
    public static void costs(Condominium c){
        for(int i=0;i<c.getFlatsNumber();i++)
            //c.getFlat(i).costs();
            System.out.println(i+": "+c.getFlat(i).costs());
    }
    public static void main(String[] args) {
        Condominium c1=condominiumList();
        simulation(c1);
    }
}
