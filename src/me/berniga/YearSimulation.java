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
                condominiums[j].addFlat(owners[new Random().nextInt(owners.length)].getId(),new Random().nextInt(8)+1,new Random().nextInt(40)+40,new Random().nextInt(4)+1);
            int totSurface=condominiums[j].surface();
            for(int i=0;i<condominiums[j].getFlatsNumber();i++)
                condominiums[j].getFlat(i).setThousands(totSurface);
        }
        return condominiums;
    }
    //RETURNS THE OWNER ID OF THE GIVEN NAME
    public static int id(String name,Owner[] owners)    throws InvalidNameorId{
        for(Owner o:owners)
            if(o.getName().equalsIgnoreCase(name))  return o.getId();
        throw new InvalidNameorId();
    }
    //SHOWS THE COSTS OF FLATS OF A OWNER OR SIMPLY THE FLATS (IT DEPENDS ON THE VARIABLE ACTION)
    public static void showFlats(int ownerId,Condominium[] condominiums,int action){
        for(Condominium c:condominiums)
            for(Flat f:c.getFlats())
                if(f.getOwnerId()==ownerId){
                    if(action==0)   System.out.println(f.toString());
                    else    System.out.println(f.costsToString());
                }
    }
    //SHOWS THE COSTS OF A FLAT OF A CONDOMINIUM
    public static void showFlats(int flatId,Condominium condominium){
            for(Flat f:condominium.getFlats())
                    if(flatId==f.getId())   {
                        System.out.println(f.costsToString());
                        break;
                    }
    }
    //SHOWS THE COSTS OF FLATS OF A CONDOMINIUM IN GENERAL
    public static void showFlats(Condominium condominium){
        for(Flat f:condominium.getFlats())
                System.out.println(f.costsToString());
    }
    //MANAGES THE INPUT COMMANDS
    public static void manageChoice(Condominium[] condominiums,Owner[] owners,String option){
        Scanner scan=new Scanner(System.in);
        String subOption;
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
                    System.out.printf("\t-Type your choice: ");
                    subOption=scan.nextLine();
                    switch(subOption){
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
                        default:
                            System.out.println("\tINVALID COMMAND");
                    }
                }
                catch(InvalidNameorId e){
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
                System.out.println("\t-Type your choice: ");
                subOption=scan.nextLine();
                switch(subOption){
                    case "ALL":
                        showFlats(condominiums[condominium]);
                        break;
                    case "SINGLE":
                        System.out.printf("\t-Type the number of the flat: ");
                        showFlats(scan.nextInt(),condominiums[condominium]);
                        break;
                    default:
                        System.out.println("\tINVALID COMMAND");
                }
                break;
            default:
                System.out.println("INVALID COMMAND");

        }
    }
    //SIMULATE A PERIOD OF 1 YEAR
    public static void simulation(Condominium c){
        LocalDate date=LocalDate.of(2022,1,1);
        for(int j=0;j<12;j++){
            for(int i=0;i<date.lengthOfMonth();i++){
                for(int k=0;k<c.getFlatsNumber();k++){
                    Flat temp=c.getFlat(k);
                    temp.shower(new Random().nextInt(temp.getPeople())+1);
                    if(j<4||j>9)   temp.heating((new Random().nextInt(5)+6)+((new Random().nextInt(5)+1)));
                }
                date=date.plusDays(1);
            }
        }
    }
    //PRINTS THE COSTS FOR ALL THE FLATS OF A
    public static void costs(Condominium c){
        for(Flat flat:c.getFlats())
            System.out.println("flat "+flat.getId()+": "+flat.costs());
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
