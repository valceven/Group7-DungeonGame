package entity;

interface PlayerClass {

    void attack();

    String getName();

    class Knight implements PlayerClass {

        @Override
        public void attack() {
            System.out.println("Knight attacks with a sword!");
        }

        @Override
        public String getName() {
            return "player";
        }
    }

    class Archer implements PlayerClass {

        @Override
        public void attack() {
            System.out.println("Archer attacks with a bow!");
        }

        @Override
        public String getName() {
            return "Archer";
        }
    }

    class Mage implements PlayerClass {

        @Override
        public void attack() {
            System.out.println("Mage casts a spell!");
        }

        @Override
        public String getName() {
            return "Mage";
        }
    }
}
