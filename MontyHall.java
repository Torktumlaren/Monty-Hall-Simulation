import java.util.Random;

public class MontyHall {

    public static float probabilityPercent(float a, float b) {
        return (a / b) * 100.f;
    }

    public static void main(String[] args) {

        int ITERATIONS = 10_000_000;

        Random rnd = new Random();

        int successesStay = 0;
        int successesSwitch = 0;
        
        long startTime = System.nanoTime();

        for (int i = 0; i < ITERATIONS; i++) {
            boolean[] doors = {false, false, false};

            int doorIndex = rnd.nextInt(3);
            doors[doorIndex] = true;

            // Pick a door
            int choiceContenstant = rnd.nextInt(3);
        
            // Eliminate one (dealers choice)
            int choiceHost = -1;

            if (doorIndex == choiceContenstant) {
                int[] possibleChoices = {0, 0};
                int index = 0;
                for (int j = 0; j <= 2; j++) {
                    if (j != doorIndex) {
                        possibleChoices[index++] = j;
                    }

                    choiceHost = possibleChoices[rnd.nextInt(2)];
                }
            } else {
                choiceHost = 3 - (choiceContenstant + doorIndex);
            }

            // Stay
            if (doors[choiceContenstant])
                successesStay++;

            // Switch
            if (doors[3 - (choiceContenstant + choiceHost)])
                successesSwitch++;
        }

        long stopTime = System.nanoTime();
        
        float probabilityStay = probabilityPercent(successesStay, ITERATIONS);
        float probabilitySwitch = probabilityPercent(successesSwitch, ITERATIONS);

        System.out.println("Probability of success when staying: " + probabilityStay + "%");
        System.out.println("Probability of success when switching: " + probabilitySwitch + "%\n");

        System.out.println(ITERATIONS + " Iterations completed in " + (float)(stopTime - startTime)/1_000_000 + " milliseconds.\n");
    }
}
