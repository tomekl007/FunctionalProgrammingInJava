package beinglazy;

import java.util.function.Supplier;

public class Evaluation {
    public static boolean evaluate(final int value) {
        System.out.println("evaluating ..." + value);
        simulateTimeConsumingOp(2000);
        return value > 100;
    }

    private static void simulateTimeConsumingOp(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void eagerEvaluator(
            final boolean input1, final boolean input2) {
        System.out.println("eagerEvaluator called...");
        System.out.println("accept?: " + (input1 && input2));
    }


    public static void main(String[] args) {
        Evaluation.eagerEvaluator(evaluate(1), evaluate(2));
        Evaluation.lazyEvaluator(() -> evaluate(1), () -> evaluate(2)); //will not evaluate second, because first invoke
        //will not satisfy and condition
    }


    public static void lazyEvaluator(
            final Supplier<Boolean> input1, final Supplier<Boolean> input2) {
        System.out.println("lazyEvaluator called...");
        System.out.println("accept?: " + (input1.get() && input2.get()));
    }
//...
}