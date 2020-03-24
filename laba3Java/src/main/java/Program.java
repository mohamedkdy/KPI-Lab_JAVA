import java.time.LocalDateTime;

public class Program {
    public static void main(String[] args)
    {
        //екземпляри класів, необхідні для роботи
        //instances of classes needed for the job
        CardCatalog cardCatalog = new CardCatalog(); //актуальний каталог
        Turnstile turnstile = new Turnstile(cardCatalog); //турнікет
        //картки
        PassCard cardNummber = new Card_OnNumerOfPasses(1234, true, 1);
        PassCard cardMoney = new Card_OnMoneyAmount(2432,10);
        PassCard cardDaysToday = new Card_OnTimeDuration(134, true, 12);
        PassCard cardDaysPrev = new Card_OnTimeDuration(344, false, 20, LocalDateTime.parse("2020-03-01T10:45:30"));
        //додавання в каталог
        cardCatalog.AddInCatalog(cardNummber);
        cardCatalog.AddInCatalog(cardMoney);
        cardCatalog.AddInCatalog(cardDaysToday);
        cardCatalog.AddInCatalog(cardDaysPrev);


        //картка без каталогу
        PassCard cardMoneyOutOfCatalog = new Card_OnMoneyAmount(2432,17);

         //PROGRAM TEST
      System.out.println(ANSI_BLUE +"Перше проходження" + ANSI_RESET);
        for (PassCard card: cardCatalog.GetList()) {
            turnstile.PassTheCard(card);
        }
        turnstile.PassTheCard(cardNummber);
        turnstile.PassTheCard(cardMoney);
        turnstile.PassTheCard(cardMoneyOutOfCatalog);

        System.out.println(ANSI_BLUE + "Друге проходження" + ANSI_RESET);
        for (PassCard card: cardCatalog.GetList()) {
            turnstile.PassTheCard(card);
        }
        turnstile.PassTheCard(cardMoneyOutOfCatalog);

        cardCatalog.BanTheCard(1234); //card lock by id
        cardCatalog.ShowCardInfo(1234); //card info
        cardCatalog.ShowAcceptsAndDeclines(1234);


    }
     // COLORS
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
}
