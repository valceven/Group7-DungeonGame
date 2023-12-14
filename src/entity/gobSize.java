package entity;

interface gobSize {
    String getSizeText();
    int rangeMultiplier();
    class Normal implements gobSize {
        @Override
        public String getSizeText() {
            return "Normal";
        }

        @Override
        public int rangeMultiplier() {
            return 2;
        }
    }
    class Mini implements gobSize {
        @Override
        public String getSizeText() {
            return "Mini";
        }

        @Override
        public int rangeMultiplier() {
            return 1;
        }
    }
}
