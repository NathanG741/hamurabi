//package MyHammurabi;
import java.util.Random;
import java.util.Scanner;

public class MyHammurabi {
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        new MyHammurabi().playGame();
    }

    void playGame() {
        int totalDeaths = 0;
        int percentDied = 0;
        int year = 0;
        int population = 95;
        int stores = 2800;
        int immigrants = 5;
        int deaths;
        int harvest = 3000;
        int yield = 3;
        int acres = harvest / yield;
        int eaten = harvest - stores;
        int landPrice = 0;
        int fullPeople;
        int temp;
        boolean plague = false;

        System.out.println("\t\t\t\tHAMURABI\n\t       CREATIVE COMPUTING MORRISTOWN, NEW JERSEY\n\n" +
                "TRY YOUR HAND AT GOVERNING ANCIENT SUMERIA\nSUCCESSFULLY FOR A TEN-YEAR TERM OF OFFICE.");
        while (year < 10) {
            year += 1;
            population += immigrants;
            landPrice = newCostOfLand();
            System.out.println("REPORT");
            plague = isPlague();

            int acresToBuys = askHowManyAcresToBuy(landPrice, stores);
            acres += acresToBuys;
            stores -= acresToBuys * landPrice;

            int acresToSell = askHowManyAcresToSell(acres);
        }
//        a.finished();

    }

    private int askHowManyAcresToBuy(int landPrice, int stores) {
        while (true) {
            System.out.print("HOW MANY ACRES DO YOU WISH TO BUY?  ");
            int acresToBuy = scanner.nextInt();
            acresToBuy = checkHowManyAcresToBuy(landPrice, stores, acresToBuy);
            if (acresToBuy != -9999) {
                return acresToBuy;
            }
        }
    }

    private int askHowManyAcresToSell(int acresOwned) {
        while (true) {
            System.out.print("HOW MANY ACRES DO YOU WISH TO SELL?  ");
            int acresToSell = scanner.nextInt();
            acresToSell = checkHowManyAcresToSell(acresOwned, acresToSell);
            if (acresToSell != -9999) {
                return acresToSell;
            }
        }
    }

    final String FINK = "DUE TO THIS EXTREME MISMANAGEMENT YOU HAVE NOT ONLY\n" +
            "BEEN IMPEACHED AND THROWN OUT OF OFFICE BUT YOU HAVE\n" +
            "ALSO BEEN DECLARED PERSONA NON GRATA!!\n";
    Scanner input = new Scanner(System.in);

    public int checkHowManyAcresToBuy(int price, int bushels, int acresToBuy) {
        if (acresToBuy < 0) {
            epicFail(0, 0);
        } else if (acresToBuy * price > bushels) {
            System.out.println("HAMURABI:  THINK AGAIN. YOU HAVE ONLY\n" +
                    bushels + " BUSHELS OF GRAIN. NOW THEN,");
            acresToBuy = -9999;
        }
        return acresToBuy;
    }

    public int checkHowManyAcresToSell(int acresOwned, int acresToSell) {
        if (acresToSell < 0) {
             epicFail(0, 0);
        } else if (acresToSell > acresOwned) {
            System.out.println("HAMURABI:  THINK AGAIN. YOU OWN ONLY " + acresOwned + " ACRES. NOW THEN,");
            acresToSell = -9999;
        }
        return acresToSell;
    }

    public int checkHowMuchGrainToFeedPeople(int bushels, int feed) {
        if (feed < 0){
            // epicFail(0);
        }else if (feed > bushels){
            System.out.println("HAMURABI:  THINK AGAIN. YOU HAVE ONLY\n" +
                    bushels + " BUSHELS OF GRAIN. NOW THEN,");}
        return feed;
    }
    public int checkHowManyAcresToPlant(int acresOwned, int population, int bushels, int acresToPlant){
        if (acresToPlant < 0) {
            //epicFail(0);
        } else if (acresToPlant > acresOwned) {
            System.out.println("HAMURABI:  THINK AGAIN. YOU OWN ONLY " + acresOwned + " ACRES. NOW THEN,");
        } else if (acresToPlant / 2 > bushels) {
            System.out.println("HAMURABI:  THINK AGAIN. YOU HAVE ONLY\n" +
                    bushels + " BUSHELS OF GRAIN. NOW THEN,");
        }else if (acresToPlant > population * 10) {
            System.out.println("BUT YOU HAVE ONLY" + population + "PEOPLE TO TEND THE FIELDS. NOW THEN,");
        }
        return acresToPlant;
    }


    public int grainEatenByRats(int bushels) {
        Random rand = new Random();
        int temp = (rand.nextInt(10) + 1);
        if (temp <= 4) {
            double percentEaten = 0.01 *(rand.nextInt(21)+10);
            return (int) Math.round(bushels * percentEaten);
        }
        else {
            return 0;
        }
    }

    public int harvest(int acres) {
        int yeild = (int) (Math.random() * 6 + 1);
        return acres * yeild;
    }

    public int immigrants(int population, int acresOwned, int grainInStorage) {
        return (20 * acresOwned + grainInStorage) / population / 100 + 1;
    }

    public boolean uprising(int population, int howManyPeopleStarved) {
        return howManyPeopleStarved > .45 * population;
    }

    public int starvationDeaths(int population, int bushelsFedToPeople) {
        int fullPeople = bushelsFedToPeople / 20;
        if (population > fullPeople) {
            return population - fullPeople;
        }
        return 0;
    }

    public int plagueDeaths(int population, boolean isPlague) {
        if (isPlague) {
            population = population / 2;
            return population;
        }
        return 0;
    }

    public boolean isPlague() {
       return (20 * Math.random() >= 17);
    }
    public int newCostOfLand() {
        Random rand = new Random();
        return (rand.nextInt(7) + 17);
    }
    public void epicFail(int x, int deaths) {
        String reason = "";
        switch (x) {
            case 0: reason = "HAMURABI:  I CANNOT DO WHAT YOU WISH.\n" +
                    "GET YOURSELF ANOTHER STEWARD!!!!!"; break;
            case 1: reason = "YOU STARVED " + deaths + " PEOPLE IN ONE YEAR!!!\n" +
                    FINK; break;
        }
        System.out.println(reason);
        System.exit(0);
    }
}
