class Calculator {
    String brand;

    Calculator(String brand) {
        this.brand = brand;
    }

    void operate() {
        System.out.println(brand + " calculator is performing a basic operation.");
    }

    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}

class ScientificCalculator extends Calculator {
    ScientificCalculator(String brand) {
        super(brand);
    }

    @Override
    void operate() {
        System.out.println(brand + " scientific calculator is performing advanced math.");
    }
}

public class OOP {
    public static void main(String[] args) {
        Calculator calc = new Calculator("Generic");
        System.out.println("Sum of 2: " + calc.add(5, 10));
        System.out.println("Sum of 3: " + calc.add(5, 10, 15));
        ScientificCalculator sciCalc = new ScientificCalculator("Brand");
        sciCalc.operate();
    }
}