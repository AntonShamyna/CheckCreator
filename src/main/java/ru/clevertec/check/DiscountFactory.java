package main.java.ru.clevertec.check;

public class DiscountFactory {

        public Discount CreateDiscount (DiscountType type) {
            Discount discount = null;

            switch (type) {
                case DISCOUNT_ANY:
                    discount = new DiscountAny();
                    break;
                case DISCOUNT_SPEC:
                    discount = new DiscountSpec();
                    break;
            }

            return discount;
        }
}
