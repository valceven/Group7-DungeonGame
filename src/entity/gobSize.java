package entity;

interface gobSize {
    String getSizeText();
    int rangeMultiplier();
    class Normal implements gobSize {
        public String getSizeText() {
            return "normal";
        }

        @Override
        public int rangeMultiplier() {
            return 2;
        }
    }
    class Mini implements gobSize {
        public String getSizeText() {
            return "mini";
        }

        @Override
        public int rangeMultiplier() {
            return 1;
        }
    }
}
