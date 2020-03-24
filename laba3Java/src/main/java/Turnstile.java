 class Turnstile {
    private CardCatalog catalog;
    private int passPrise;
    public Turnstile( CardCatalog Catalog){
        catalog = Catalog;
        passPrise = 8;
    }
    void LetGo(PassCard card){
        System.out.print(ANSI_GREEN + " Пропустити." + ANSI_RESET);
        if(card.GetIsDiscount()){ System.out.print(ANSI_PURPLE + "     Attention! Пільговий квиток." + ANSI_RESET);}
         System.out.println("");
        card.AddAccept();
    }
    void DontLetGo(PassCard card){
        card.AddDeclines();
    }
  void  PassTheCard(PassCard card) {
      if (card != null && catalog.IsInCatalog(card)) {
          System.out.print(" ID: "+card.GetId());
        if(card.GetIsActive()) {
            if (card instanceof Card_OnMoneyAmount) {
                if (((Card_OnMoneyAmount) card).GetMoneyLeft() >= passPrise) {
                    ((Card_OnMoneyAmount) card).MinusMoney(passPrise);
                    System.out.print(" Залишок на картці:" + ((Card_OnMoneyAmount) card).GetMoneyLeft() + "$.");
                    LetGo(card);
                } else {
                    System.out.println(ANSI_PURPLE + " Warning! Недостатньо гршей на картці, прохід заборонено." + ANSI_RESET);
                    DontLetGo(card);
                }
            }
            if (card instanceof Card_OnNumerOfPasses) {
                if (((Card_OnNumerOfPasses) card).GetPassesLeft() > 0) {
                    ((Card_OnNumerOfPasses) card).Minus1Pass();
                    System.out.print(" Залишок проходів: " + ((Card_OnNumerOfPasses) card).GetPassesLeft() + ".");
                    LetGo(card);
                } else {
                    System.out.println(ANSI_PURPLE + " Warning! Кількість проходів вичерпалась, прохід заборонено." + ANSI_RESET);
                    DontLetGo(card);
                }
            }
            if (card instanceof Card_OnTimeDuration) {

                if (((Card_OnTimeDuration) card).GetDaysLeft() > 0) {
                    System.out.print(" Залишок днів" + ((Card_OnTimeDuration) card).GetDaysLeft());
                    LetGo(card);
                } else {
                    System.out.println(ANSI_PURPLE + " Warning! Термін дії вичерпано, прохід заборонено." + ANSI_RESET);
                    DontLetGo(card);
                }
            }
        }
        else{
            System.out.println(ANSI_PURPLE +" Warning! Картка заблокована" + ANSI_RESET);
            DontLetGo(card);
        }
      }
      else{
          System.out.println(ANSI_PURPLE +" Warning! Картки намає в системі, прохід заборонено." + ANSI_RESET);

      }
  }

    public void SetPassPrise(int passPrise) {
        this.passPrise = passPrise;
    }
     public static final String ANSI_RESET = "\u001B[0m";
     public static final String ANSI_PURPLE = "\u001B[35m";
     public static final String ANSI_GREEN = "\u001B[32m";
}
