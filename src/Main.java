import java.util.Random;
import java.util.Scanner;

public class Main {

    static int bossHealth = 0;
    static int bossDamage = 50;
    static String bossDefenceType = "";

    static int[] heroesHealth = {260, 280, 270, 290};
    static int[] heroesDamage = {20, 25, 15};
    static String[] heroesSuperAbilities = {"Physical", "Magical", "Kinetic"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter boss health: ");
        bossHealth = scanner.nextInt();
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void changeBossDefence() {

        Random r = new Random();
        int randomNumber = r.nextInt(heroesSuperAbilities.length); // 0, 1, 2
        bossDefenceType = heroesSuperAbilities[randomNumber];
    }

    public static void round() {
        changeBossDefence();
        Random r = new Random();
        int chance = r.nextInt(2); //0,1
        if (chance == 1) {
            System.out.println("Boss hits first");
            bossHit();
            heroesHit();
        } else {
            System.out.println("Heroes hit first");
            heroesHit();
            bossHit();
        }
        setMedicAid();
        printStatistics();
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes win!!!");
            return true;
        }

        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 && heroesHealth[3] <= 0) {
            System.out.println("Boss win!!!");
            return true;
        }
        return false;
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesSuperAbilities[i] == bossDefenceType) {
                    Random r = new Random();
                    int coeff = r.nextInt(7) + 2; //0,1,2,3,4
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println(heroesSuperAbilities[i] + " hit with critical damage " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void setMedicAid() {
        for (int i = 0; i < heroesSuperAbilities.length; i++) {
            if (heroesHealth[3] != 0) {
                if (heroesHealth[i] < 100) {
                    heroesHealth[i] = heroesHealth[i] + 30;
                    System.out.println("Медик вылечил на " + 30 + " "
                            + heroesSuperAbilities[i]);
                }
            }

        }
    }


    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] - bossDamage < 0) {
                heroesHealth[i] = 0;
            } else {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
        }
    }


    public static void printStatistics() {
        System.out.println("__________________");
        System.out.println("Boss health: " + bossHealth);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magic health: " + heroesHealth[1]);
        System.out.println("Kinetic health: " + heroesHealth[2]);
        System.out.println("Medic health: " + heroesHealth[3]);
        System.out.println("__________________");
    }
}