


public class SalesTaxCalculator {

    public static void main(String[] args) {
        // Validate input
        if (args.length != 2) {
            System.out.println("Usage: java SalesTaxCalculator <state> <amount>");
            return;
        }

        // Parse input arguments
        String stateName = args[0];
        double saleAmount;

        try {
            saleAmount = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Amount must be a valid number.");
            return;
        }

        // Determine the state dynamically
        State state = null;
        if ("Indiana".equalsIgnoreCase(stateName)) {
            state = new Indiana();
            state.setTaxBehavior(new SevenPercent());
        } else if ("Alaska".equalsIgnoreCase(stateName)) {
            state = new Alaska();
            state.setTaxBehavior(new NoTax());
        } else if ("Hawaii".equalsIgnoreCase(stateName)) {
            state = new Hawaii();
            state.setTaxBehavior(new FourPointFivePercent());
        } else {
            System.out.println("Error: Invalid state. Only 'Indiana', 'Alaska', and 'Hawaii' are supported.");
            return;
        }

        // Show the tax information
        state.showTax(saleAmount);
    }
}

// SalesTaxBehavior.java
interface SalesTaxBehavior {
    double compute(double value);
}

// NoTax.java
class NoTax implements SalesTaxBehavior {
    @Override
    public double compute(double value) {
        return 0.0;
    }
}

// SevenPercent.java
class SevenPercent implements SalesTaxBehavior {
    @Override
    public double compute(double value) {
        return value * 0.07;
    }
}

// FourPointFivePercent.java
class FourPointFivePercent implements SalesTaxBehavior {
    @Override
    public double compute(double value) {
        return value * 0.045;
    }
}

// State.java
abstract class State {
    protected String name;
    protected SalesTaxBehavior taxBehavior;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTaxBehavior(SalesTaxBehavior taxBehavior) {
        this.taxBehavior = taxBehavior;
    }

    public abstract void showTax(double value);
}

// Alaska.java
class Alaska extends State {
    public Alaska() {
        this.name = "Alaska";
    }

    @Override
    public void showTax(double value) {
        double tax = taxBehavior.compute(value);
        System.out.printf("The sales tax on $%.2f in %s is $%.2f.%n", value, name, tax);
    }
}

// Indiana.java
class Indiana extends State {
    public Indiana() {
        this.name = "Indiana";
    }

    @Override
    public void showTax(double value) {
        double tax = taxBehavior.compute(value);
        System.out.printf("The sales tax on $%.2f in %s is $%.2f.%n", value, name, tax);
    }
}

// Hawaii.java
class Hawaii extends State {
    public Hawaii() {
        this.name = "Hawaii";
    }

    @Override
    public void showTax(double value) {
        double tax = taxBehavior.compute(value);
        System.out.printf("The sales tax on $%.2f in %s is $%.2f.%n", value, name, tax);
    }
}
