import java.io.*;
import java.util.*;

public class MathAssessmentSimple {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();

        System.out.print("What is your name: ");
        String name = scan.nextLine().trim();
        String filename = "/home/ahristov26/" + name + "-answers.txt";

        try {
            FileWriter writer = new FileWriter(filename);

            for (int i = 1; i <= 5; i++) {
                int num1 = random.nextInt(100);
                int num2 = random.nextInt(1, 100);
                int correctAnswer = 0;
                char operator = '+';
                int option = random.nextInt(4);

                switch (option) {
                    case 0:
                        operator = '+';
                        correctAnswer = num1 + num2;
                        break;
                    case 1:
                        operator = '-';
                        correctAnswer = num1 - num2;
                        break;
                    case 2:
                        operator = '*';
                        correctAnswer = num1 * num2;
                        break;
                    case 3:
                        operator = '/';
                        num1 = num2 * random.nextInt(1, 11);
                        correctAnswer = num1 / num2;
                        break;
                }

                System.out.println("Q" + i + ": what is " + num1 + " " + operator + " " + num2 + " ?");
                int userAnswer = scan.nextInt();
                boolean isCorrect = userAnswer == correctAnswer;

                writer.write("Q" + i + ": " + num1 + " " + operator + " " + num2 + " = " + correctAnswer);
                writer.write(" | answer: " + userAnswer);
                writer.write(" | " + (isCorrect ? "correct" : "incorrect") + "\n");
            }
            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
