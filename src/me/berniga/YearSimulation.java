package me.berniga;

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class YearSimulation {
    //INSTANCING OF THE OWNER ARRAY (RETURNS THE ARRAY)
    public static Owner[] ownersList(){
        String[] tempNames={"George","John","Michael","Josh","Jett","Hunter","Robert"};
        String[] tempSurnames={"Lawrence","Smith","Johnshon","Brown","Anderson","Moore","Jackson"};
        Owner[] owners=new Owner[tempNames.length];
        for(int i=0;i<tempNames.length;i++)
            owners[i]=new Owner(tempNames[i],tempSurnames[i]);
        return owners;
    }

    //INSTANCING OF THE CONDOMINIUM ARRAY (RETURNS THE ARRAY)
    public static Condominium[] condominiumList(Owner[] owners){
        Condominium[] condominiums=new Condominium[3];
        for(int j=0;j<condominiums.length;j++){
            condominiums[j]=new Condominium();
            for(int i=0;i<10;i++)
                condominiums[j].addFlat(owners[new Random().nextInt(owners.length)].getId(),new Random().nextInt(15)+1,new Random().nextInt(80)+30,new Random().nextInt(4)+1);
            condominiums[j].surface();
            int totSurface=condominiums[j].getSurface();
            for(int i=0;i<condominiums[j].getFlatsNumber();i++)
                condominiums[j].getFlat(i).setThousands(totSurface);
        }
        return condominiums;
    }

    /*FROM NAME TO ID
   -----------------------------
   - it returns the ID by taking a name String and searching in the owners list;
    */
    public static int id(String name,Owner[] owners)    throws InvalidNameorIdException {
        for(Owner o:owners)
            if(o.getName().equalsIgnoreCase(name))  return o.getId();
        throw new InvalidNameorIdException();
    }

    /*SHOW FLATS
   -----------------------------
   - the first one show all the flats of an owner or all the flats' costs (it depends on the 'action' parameter value);
   - the second one shows the cost of a single flat in a coiched condominium;
   - the third one shows the costs off all the flats in a coiched condominium;
    */
    public static void showFlats(int ownerId,Condominium[] condominiums,int action){
        for(Condominium c:condominiums){
            int totHeating=c.getHeatingTime();
            for(Flat f:c.getFlats())
                if(f.getOwnerId()==ownerId){
                    if(action==0)   System.out.println(f.toString());
                    else    System.out.println("Flat "+f.getId()+" costs="+f.costs(totHeating));
                }
        }
    }

    public static void showFlats(int flatId,Condominium condominium){
        int totHeating=condominium.getHeatingTime();
        for(Flat f:condominium.getFlats())
                    if(flatId==f.getId())   {
                        System.out.println("Flat "+f.getId()+" costs="+f.costs(totHeating));
                        break;
                    }
    }

    public static void showFlats(Condominium condominium){
        int totHeating=condominium.getHeatingTime();
        for(Flat f:condominium.getFlats())
                System.out.println("Flat "+f.getId()+" costs="+f.costs(totHeating));
    }

    /*MANAGES INPUTS
    -----------------------------
    - the first switch is used to choice between the Owner area or the Condominium one;
    - the others switches are used to choice between all the subOptions of the previous choices;
    - inside the OWNER area you have to type the name of the owner to be able to log in his private area;
    - inside this private area of the owner you can see all his flats info or all flats' costs;
    - inside the CONDOMINIUM area you have to type the number (=id) of the condominium;
    - inside this private area of the condominium you can see all flats' costs or a single one;
     */
    public static void manageChoice(Condominium[] condominiums,Owner[] owners,String option){
        Scanner scan=new Scanner(System.in);
        switch(option){
            case "OWNER":
                System.out.printf("\tPLEASE TYPE THE NAME TO LOG IN: ");
                String ownerName=scan.nextLine();
                try{
                    int ownerId=id(ownerName,owners);
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("\tYOU LOGGED IN THE OWNERS AREA OF "+ownerName);
                    System.out.println("\tHere are the possible choices:");
                    System.out.println("\t-Type ALL to see all his flats ");
                    System.out.println("\t-Type COST to see the costs of all his flats ");
                    System.out.printf("\tType your choice: ");
                    //subOption=scan.nextLine();
                    switch(scan.nextLine()){
                        case "ALL":
                            System.out.println("\n\nThese are all the flats of "+ownerName);
                            showFlats(ownerId,condominiums,0);
                            System.out.println("------------------------------------------------------------------");
                            break;
                        case "COST":
                            System.out.println("\n\nThese are all the costs of flats of "+ownerName);
                            showFlats(ownerId,condominiums,1);
                            System.out.println("------------------------------------------------------------------");
                            break;
                        default: System.out.println("\tINVALID COMMAND");
                    }
                }
                catch(InvalidNameorIdException e){
                    System.out.println("THE CHOICED OWNER DOESN'T EXIST");
                }

                break;
            case "CONDOMINIUM":
                System.out.printf("\tPLEASE TYPE THE CIVIC NUMBER (=ID) OF THE CONDOMINIUM TO LOG IN :");
                int condominium=scan.nextInt();
                //find id of the condominium
                System.out.println("------------------------------------------------------------------");
                System.out.println("\tYOU LOGGED IN THE CONDOMINIUM AREA OF "+condominium);
                System.out.println("\tHere are the possible choices:");
                System.out.println("\t-Type ALL to see the costs of all flats ");
                System.out.println("\t-Type SINGLE to see the costs of a specified flat");
                System.out.println("\tType your choice: ");
                //subOption=scan.nextLine();
                switch(scan.nextLine()){
                    case "ALL":
                        showFlats(condominiums[condominium]);
                        break;
                    case "SINGLE":
                        System.out.printf("\t-Type the number of the flat: ");
                        showFlats(scan.nextInt(),condominiums[condominium]);
                        break;
                    default: System.out.println("\tINVALID COMMAND");
                }
                break;
            default: System.out.println("INVALID COMMAND");

        }
    }

    /*1 YEAR SIMULATION
    -----------------------------
    - it creates a starting date (1st January 2022);
    - there are 3 for (1st- the iteration of the months,2nd- the iteration of the days of that month,3rd- the iteration of the flats of a condominium);
    - for each flat the number of people that uses the shower is randomized
    - the heating and opening window methods are called only if the current month is smaller than 4 or bigger than 9 (WINTER AND SPRING)
     */
    public static void simulation(Condominium c){
        LocalDate date=LocalDate.of(2022,1,1);
        for(int j=0;j<12;j++){
            for(int i=0;i<date.lengthOfMonth();i++){
                for(int k=0;k<c.getFlatsNumber();k++){
                    Flat temp=c.getFlat(k);
                    temp.shower(new Random().nextInt(temp.getPeople())+1);
                    if(j<4||j>9)   temp.heating(new Random().nextInt(9)+7);
                }
                date=date.plusDays(1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        Owner[] owners=ownersList();
        Condominium[] condominiums=condominiumList(owners);
        for(int i=0;i<condominiums.length;i++)  simulation(condominiums[i]);
        System.out.println("ALLOWED ACTIONS");
        System.out.println("- Type OWNER to be able to choice want you wanna know about him/her");
        System.out.println("- Type CONDOMINIUM to be able to see the costs of a choiched condominium");
        System.out.printf("Type your choice: ");
        String option=scan.nextLine();
        while(!option.equalsIgnoreCase("exit")){
            manageChoice(condominiums,owners,option);
            System.out.printf("Type your choice: ");
            option= scan.nextLine();
        }
    }
}
