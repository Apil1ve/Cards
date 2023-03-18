import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static ArrayList<Player> players;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Доступные команды: \n1.start N C\n2.get-card C \n3.exit");
            String input = sc.nextLine();
            String[] splitedInput = input.split(" "); // start 10 3 | get-card 2
            if (splitedInput[0].equals("start")) {
                start(Integer.parseInt(splitedInput[1]), Integer.parseInt(splitedInput[2]));
            } else if (splitedInput[0].equals("get-card"))  {
                getCard(Integer.parseInt(splitedInput[1]));
            } else if (splitedInput[0].equals("exit")) {
                System.exit(1);
            } else {
                System.out.println("Неизвестная команда");
            }
        }
    }

    // start 10 3 | n = 10, c = 3
    // n - это количество карт для каждого игрока
    // с - это количество игроков
    static void start(int n, int c) {
        players = new ArrayList<Player>();
        Random ran = new Random();
        for (int i = 0; i < c; i++) {
            // список карт для каждого игрока
            ArrayList<Card> cards = new ArrayList<Card>();
            for (int j = 0; j < n; j++) {
                int value = ran.nextInt(10) + 1; // [0, ..., 11)
                int colorIndex = ran.nextInt(4); // [0, ..., 4)
                Color color = Color.values()[colorIndex]; // []Color{} = Color.values()
                cards.add(new Card(color, value));
            }
            players.add(new Player(i+1, cards));
        }
    }

    // c - номер игрока
    // get-card 2 | c = 2
    static void getCard(int c) {
        if (players == null) {
            System.out.println("Нет игроков");
        } else if (c > players.size()) {
            System.out.println("Нет игрока с таким номером");
        } else {
            Player player = players.get(c-1);
            for (int i = 0; i < player.cards.size(); i++) {
                Card card = player.cards.get(i);
                System.out.println(card.color + " " + card.value);
            }
        }
    }
}

class Card {
    Color color;
    int value;

    public Card(Color _color, int _value) {
        color = _color;
        value = _value;
    }
}

class Player {
    int id;
    ArrayList<Card> cards;

    public Player(int _id, ArrayList<Card> _cards) {
        id = _id;
        cards = _cards;
    }
}

enum Color {
    R, // 0
    G, // 1
    B, // 2
    W // 3
}
