package entity;

interface PlayerClass {

    String attack();

    String getName();

    class Knight implements PlayerClass {

        @Override
        public String attack() {
            return ("knight");
        }

        @Override
        public String getName() {
            return "player";
        }
    }

    class Archer implements PlayerClass {

        @Override
        public String attack() {
            return ("Archer shoots an arrow!");
        }

        @Override
        public String getName() {
            return "Archer";
        }
    }

    class Mage implements PlayerClass {

        @Override
        public String attack() {
            return ("Mage casts a spell!");
        }

        @Override
        public String getName() {
            return "Mage";
        }
    }
}
