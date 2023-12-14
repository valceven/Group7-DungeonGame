package entity;

interface gobClass {
    String getClassText();
    int attackSpeed();
    int speedMult();

    char getAxis();

    class Archer implements gobClass {
        char axis;
        public Archer(char x) {
            axis = x;
        }

        @Override
        public String getClassText() {
            return "Archer";
        }

        @Override
        public int attackSpeed() {
            return 40;
        }

        @Override
        public int speedMult() {
            return 1;
        }

        public char getAxis(){
            return axis;
        }
    }
    class Fighter implements gobClass {
        @Override
        public String getClassText() {
            return "Fighter";
        }

        @Override
        public int attackSpeed() {
            return 30;
        }

        @Override
        public int speedMult() {
            return 2;
        }

        @Override
        public char getAxis() {
            return 0;
        }
    }
}