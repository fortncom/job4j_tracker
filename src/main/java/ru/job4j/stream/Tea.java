package ru.job4j.stream;

public class Tea {

    private String  name;
    private short   size;
    private boolean sugar;
    private boolean lemon;
    private boolean mint;
    private boolean milk;

    static class Builder {
        private String name;
        private short size;
        private boolean sugar;
        private boolean lemon;
        private boolean mint;
        private boolean milk;

        public Builder buildName(String name) {
            this.name = name;
            return this;
        }

        public Builder buildSize(short size) {
            this.size = size;
            return this;
        }

        public Builder buildSugar(boolean sugar) {
            this.sugar = sugar;
            return this;
        }

        public Builder buildLemon(boolean lemon) {
            this.lemon = lemon;
            return this;
        }

        public Builder buildMint(boolean mint) {
            this.mint = mint;
            return this;
        }

        public Builder buildMilk(boolean milk) {
            this.milk = milk;
            return this;
        }

        Tea build() {
            Tea tea = new Tea();
            tea.name = name;
            tea.size = size;
            tea.sugar = sugar;
            tea.lemon = lemon;
            tea.mint = mint;
            tea.milk = milk;
            return tea;
        }
    }

    public static void main(String[] args) {
        Tea tea = new Tea.Builder().buildName("black")
                .buildSize((short) 250)
                .buildSugar(true)
                .buildLemon(true)
                .buildMint(true)
                .build();
        System.out.println(tea);
    }

    @Override
    public String toString() {
        return "Tea{"
                + "name='" + name + '\''
                + ", size=" + size
                + ", sugar=" + sugar
                + ", lemon=" + lemon
                + ", mint=" + mint
                + ", milk=" + milk
                + '}';
    }
}
