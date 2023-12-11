package entity;

interface gobClass {
    String getClassText();
    int attackSpeed();
    int speedMult();
    class Archer implements gobClass {
        @Override
        public String getClassText() {
            return "Archer";
        }

        @Override
        public int attackSpeed() {
            return 5;
        }

        @Override
        public int speedMult() {
            return 2;
        }
    }
    class Fighter implements gobClass {
        @Override
        public String getClassText() {
            return "Fighter";
        }

        @Override
        public int attackSpeed() {
            return 0;
        }

        @Override
        public int speedMult() {
            return 1;
        }
    }
}