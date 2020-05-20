import java.util.Scanner;

public class MainApp {

    static Scanner scn = new Scanner(System.in);
    static Scanner scnNumb = new Scanner(System.in);
    static Repository repository = new Repository();


    public static void main(String[] args) {
        System.out.println("Для перевода средств с одной карты на другую нажмите 1");
        System.out.println("Для поплнения счета в определенной валюте нажмите 2");
        System.out.println("Для конвертации счета в определенной валюте нажмите 3");
        System.out.println("Для просмотра баланса вашего счета в гривнах нажмите 4");
        int choise = scnNumb.nextInt();
        switch (choise) {
            case 1:
                moneyTransaction();
                break;
            case 2:
                refill();
                break;
            case 3:
                currencyСonversion();
                break;
            case 4:
                getAllMoneyInUan();
                break;

            default:
                System.out.println("Введите число от 1 до 4");
        }
    }


    private static void moneyTransaction() {
        System.out.println("Введите номер счета с которого делаете перевод");
        String acFrom = scn.nextLine();
        System.out.println("Введите валюту в которой делаете перевод");
        String currency = scn.nextLine();
        System.out.println("Введите суму которую хотите перевести");
        long sum = scnNumb.nextLong();
        System.out.println("Введите счет на который делаете перевод");
        String acTo = scn.nextLine();
        repository.transfer(acFrom, currency, sum, acTo);
    }

    private static void refill() {
        System.out.println("Введите номер счета для пополнения");
        String ac = scn.nextLine();
        System.out.println("Введите валюту");
        String currency = scn.nextLine();
        System.out.println("Введите суму");
        long sum = scnNumb.nextLong();
        repository.moneyTransfer(ac, currency, sum);
    }

    private static void currencyСonversion() {
        System.out.println("Введите ваш счет");
        String ac = scn.nextLine();
        System.out.println("Введите валюту в которую хотите переконвертировать счет");
        String currency = scn.nextLine();

        repository.conversion(ac, currency);
    }

    private static void getAllMoneyInUan() {
        System.out.println("Введите ваш счет");
        String ac = scn.nextLine();
        repository.getMoneyUan(ac);
    }
}


