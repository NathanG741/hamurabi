//package MyHammurabi;
import java.util.Random;
import java.util.Scanner;

public class MyHammurabi {
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
    final String FINK = "DUE TO THIS EXTREME MISMANAGEMENT YOU HAVE NOT ONLY\n" +
            "BEEN IMPEACHED AND THROWN OUT OF OFFICE BUT YOU HAVE\n" +
            "ALSO BEEN DECLARED PERSONA NON GRATA!!\n";
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
        int deaths = 0;
        int harvest = 3000;
        int yield = 3;
        int acres = harvest / yield;
        int eaten = harvest - stores;
        int landPrice;
        int fullPeople;
        int temp;
        boolean plague = false;
        int totalImmigrants = 5;
        int totalPopulation = 95 + totalImmigrants;

        System.out.println("\t\t\t\tHAMURABI\n\t       CREATIVE COMPUTING WILMINGTON, DELAWARE\n\n" +
                "TRY YOUR HAND AT GOVERNING ANCIENT SUMERIA\nSUCCESSFULLY FOR A TEN-YEAR TERM OF OFFICE.");

        while (year < 10) {
            year += 1;
            population += immigrants;
            landPrice = newCostOfLand();
            System.out.println(printSummary(year, deaths, immigrants, plague, population, acres, yield, eaten, stores, landPrice));
            if (year > 1) {
                plague = isPlague();
            }

            int acresToBuys = askHowManyAcresToBuy(landPrice, stores);
            acres += acresToBuys;
            stores -= acresToBuys * landPrice;

            int acresToSell = askHowManyAcresToSell(acres);
            stores += acresToSell * landPrice;
            acres -= acresToSell;

            int feedPeople = askHowMuchGrainToFeedPeople(stores);
            stores -= feedPeople;

            int acresToPlant = askHowManyAcresToPlant(acres, population, stores);
            stores -= acresToPlant * 2;
            yield = (int) (Math.random() * 6 + 1);
            harvest = harvest(acresToPlant, yield);
            eaten = grainEatenByRats(stores);
            stores += (harvest - eaten);

            int plagueDeaths = plagueDeaths(population, plague);
            population -= plagueDeaths;

            int starvationDeaths = starvationDeaths(population, feedPeople);
            if (uprising(population, starvationDeaths)) {
                epicFail(1, starvationDeaths);
            } else if (starvationDeaths > 0) {
                population -= starvationDeaths;
                immigrants = 0;
            } else {
                immigrants = immigrants(population, acres, stores);
                totalImmigrants += immigrants;
            }

            deaths += starvationDeaths;
        }

        percentDied = deaths / totalPopulation / year;
        finalSummary(percentDied, deaths, acres, population);

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

    private int askHowMuchGrainToFeedPeople(int bushels) {
        while (true) {
            System.out.print("HOW MANY BUSHELS DO YOU WISH TO FEED YOUR PEOPLE?  ");
            int feedPeople = scanner.nextInt();
            feedPeople = checkHowMuchGrainToFeedPeople(bushels, feedPeople);
            if (feedPeople != -9999) {
                return feedPeople;
            }
        }
    }

    private int askHowManyAcresToPlant(int acresOwned, int population, int bushels) {
        while (true) {
            System.out.print("HOW MANY ACRES DO YOU WISH TO PLANT WITH SEED?  ");
            int acresToPlant = scanner.nextInt();
            acresToPlant = checkHowManyAcresToPlant(acresOwned, population, bushels, acresToPlant);
            if (acresToPlant != -9999) {
                return acresToPlant;
            }
        }
    }

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
             epicFail(0, 0);
        } else if (feed > bushels){
            System.out.println("HAMURABI:  THINK AGAIN. YOU HAVE ONLY\n" +
                    bushels + " BUSHELS OF GRAIN. NOW THEN,");
            return -9999;
        }
        return feed;
    }
    public int checkHowManyAcresToPlant(int acresOwned, int population, int bushels, int acresToPlant){
        if (acresToPlant < 0) {
            epicFail(0, 0);
        } else if (acresToPlant > acresOwned) {
            System.out.println("HAMURABI:  THINK AGAIN. YOU OWN ONLY " + acresOwned + " ACRES. NOW THEN,");
            return -9999;
        } else if (acresToPlant * 2 > bushels) {
            System.out.println("HAMURABI:  THINK AGAIN. YOU HAVE ONLY\n" +
                    bushels + " BUSHELS OF GRAIN. NOW THEN,");
            return -9999;
        }else if (acresToPlant > population * 10) {
            System.out.println("BUT YOU HAVE ONLY " + population + " PEOPLE TO TEND THE FIELDS. NOW THEN,");
            return -9999;
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

    public int harvest(int acres, int yield) {
        return acres * yield;
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

    private static String printSummary(int year, int deaths, int immigrants, boolean plague, int population,
                                       int acres, int yield, int eaten, int stores, int landPrice) {
        String answer = "\nHAMURABI:  I BEG TO REPORT TO YOU,\n" +
                "IN YEAR " + year + ", " + deaths + " PEOPLE STARVED, " + immigrants + " CAME TO THE CITY.\n";
        if (plague) {
            answer += "A HORRIBLE PLAGUE STRUCK!  HALF THE PEOPLE DIED.\n";
        }
        answer += "POPULATION IS NOW " + population + ".\n" +
                "THE CITY NOW OWNS " + acres + " ACRES.\n" +
                "YOU HARVESTED " + yield + " BUSHELS PER ACRE.\n" +
                "RATS ATE " + eaten + " BUSHELS.\n" +
                "YOU NOW HAVE " + stores + " BUSHELS IN STORE\n\n" +
                "LAND IS TRADING AT " + landPrice + " BUSHELS PER ACRE.";
        return answer;
    }

    private void finalSummary(int percentDied, int totalDeaths, int acres, int population) {
        String answer = "\nIN YOUR 10-YEAR TERM OF OFFICE, " + percentDied + " PERCENT OF THE\n" +
                "POPULATION STARVED PER YEAR ON AVERAGE, I.E., A TOTAL OF\n" +
                totalDeaths + " PEOPLE DIED!!\n" +
                "YOU STARTED WITH 10 ACRES PER PERSON AND ENDED WITH\n" +
                acres / population + " ACRES PER PERSON\n\n";
        if (percentDied > 33 || acres / population < 7)
            answer += FINK;
        else if (percentDied > 10 || acres / population < 9)
            answer += "YOUR HEAVY-HANDED PERFORMANCE SMACKS OF NERO AND IVAN IV.\n" +
                    "THE PEOPLE (REMAINING) FIND YOU AN UNPLEASANT RULER, AND,\n" +
                    "FRANKLY, HATE YOUR GUTS!";
        else if (percentDied > 3 || acres / population < 10)
            answer += "YOUR PERFORMANCE COULD HAVE BEEN SOMEWHAT BETTER, BUT\n" +
                    "REALLY WASN'T TOO BAD AT ALL.\n" +
                    Math.random() * population * .8 + " PEOPLE WOULD" +
                    "DEARLY LIKE TO SEE YOU ASSASSINATED BUT WE ALL HAVE OUR" +
                    "TRIVIAL PROBLEMS";
        else
            answer += "A FANTASTIC PERFORMANCE!!!  CHARLEMANGE, DISRAELI, AND\n" +
                    "JEFFERSON COMBINED COULD NOT HAVE DONE BETTER!";
        answer += "\n\n\n\n\n\n\n\n\n\nSo long for now.";
        System.out.println(answer);
    }
}
