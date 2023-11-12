import java.util.Random;
import java.util.Scanner;

public class MyHammurabi {
    int totalDeaths = 0;
    int percentDied = 0;
    int year = 0;
    int population = 95;
    int stores = 2800;
    int immigrants = 5;
    int deaths;
    int harvest = 3000;
    int yeild = 3;
    int acres = harvest / yeild;
    int eaten = harvest - stores;
    int landPrice;
    int fullPeople;
    int temp;
    boolean plague = false;
    final String FINK = "DUE TO THIS EXTREME MISMANAGEMENT YOU HAVE NOT ONLY\n" +
            "BEEN IMPEACHED AND THROWN OUT OF OFFICE BUT YOU HAVE\n" +
            "ALSO BEEN DECLARED PERSONA NON GRATA!!\n";
    Scanner input = new Scanner(System.in);

    public int checkHowManyAcresToBuy(int price, int bushels, int acresToBuy) {
        if (acresToBuy < 0) {
            // epicFail(0);
        } else if (acresToBuy * price > bushels) {
            System.out.println("HAMURABI:  THINK AGAIN. YOU HAVE ONLY\n" +
                    bushels + " BUSHELS OF GRAIN. NOW THEN,");
        }
        return acresToBuy;
    }

    public int checkHowManyAcresToSell(int acresOwned, int acresToSell) {
        if (acresToSell < 0) {
            // epicFail(0);
        } else if (acresToSell > acresOwned) {
            System.out.println("HAMURABI:  THINK AGAIN. YOU OWN ONLY " + acresOwned + " ACRES. NOW THEN,");
        }
        return acresToSell;
    }

    public int grainEatenByRats(int bushels) {
        Random rand = new Random();
        temp = (rand.nextInt(10) + 1);
        if (temp <= 4) {
            double percentEaten = 0.01 *(rand.nextInt(21)+10);
            return (int) Math.round(bushels * percentEaten);
        }
        else {
            return 0;
        }
    }

    public int harvest(int acres) {
        yeild = (int) (Math.random() * 6 + 1);
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

    public int plagueDeaths(int population) {
        if (20 * Math.random() >= 17) {
            plague = true;
            population = population / 2;
            return population;
        }
        return 0;
    }

    public int newCostOfLand() {
        Random rand = new Random();
        landPrice = (rand.nextInt(7) + 17);
        return landPrice;
    }
}
