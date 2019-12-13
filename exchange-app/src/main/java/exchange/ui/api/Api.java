package exchange.ui.api;

public class Api {
    private static final String BASE = "/api";


    public static class GiftCard{
        public static final String BASE_GIFT_CARD = BASE + "/gift-cards";
        public static final String ISSUE_GIFT_CARD = "/issue";
        public static final String REDEEM_GIFT_CARD = "/{id}/redeem/{amount}";
    }

    public static class Load{
        public static final String BASE_LOADS = BASE + "/loads";

    }
}
