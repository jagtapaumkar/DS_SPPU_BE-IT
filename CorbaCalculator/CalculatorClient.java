import CalculatorApp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

public class CalculatorClient {
    public static void main(String args[]) {
        try {
            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            Calculator calcRef = CalculatorHelper.narrow(ncRef.resolve_str("Calculator"));

            System.out.println("Result of 10 + 5: " + calcRef.add(10, 5));
            System.out.println("Result of 10 - 5: " + calcRef.subtract(10, 5));
            System.out.println("Result of 10 * 5: " + calcRef.multiply(10, 5));
            System.out.println("Result of 10 / 5: " + calcRef.divide(10, 5));

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }
}
