import java.util.Scanner;

public class Main {

    // 1. Цикл со сложением — O(n)
    public static int multiplyByLoop(int a, int b) {
        if (a == 0 || b == 0) return 0;
        int sign = ((a < 0) ^ (b < 0)) ? -1 : 1;
        long x = Math.abs((long) a);
        long y = Math.abs((long) b);
        if (x > y) {
            long temp = x;
            x = y;
            y = temp;
        }

        long result = 0;
        for (long i = 0; i < y; i++) {
            result += x;
        }
        return (int) (result * sign);
    }

    // 2. Рекурсия  — O(log n)
    public static int multiplyByRecursion(int a, int b) {
        if (b == 0) return 0;
        if (b == 1) return a;
        if (b < 0) return -multiplyByRecursion(a, -b);

        int half = multiplyByRecursion(a, b >> 1);
        return (b % 2 == 0) ? half + half : half + half + a;
    }

    // 3. Инкремент в цикле (while) — O(n)
    public static int multiplyByIncrement(int a, int b) {
        if (a == 0 || b == 0) return 0;
        int sign = ((a < 0) ^ (b < 0)) ? -1 : 1;
        a = Math.abs(a);
        b = Math.abs(b);

        int result = 0;
        int count = 0;
        while (count < b) {
            result += a;
            count++;
        }
        return result * sign;
    }

    // 4. Логарифм
    public static int multiplyByLog(int a, int b) {
        if (a == 0 || b == 0) return 0;
        int sign = ((a < 0) ^ (b < 0)) ? -1 : 1;
        double x = Math.abs(a);
        double y = Math.abs(b);
        double logSum = Math.log(x) + Math.log(y);
        double result = Math.pow(Math.E, logSum);
        return (int) Math.round(sign * result);
    }

    //  логарифм через основание 10
    public static int multiplyByLog10(int a, int b) {
        if (a == 0 || b == 0) return 0;
        int sign = ((a < 0) ^ (b < 0)) ? -1 : 1;
        double x = Math.abs(a);
        double y = Math.abs(b);
        double logSum = Math.log10(x) + Math.log10(y);
        double result = Math.pow(10, logSum);
        return (int) Math.round(sign * result);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("══════════════════════════════════════════════");
        System.out.println("    УМНОЖЕНИЕ БЕЗ ОПЕРАТОРА *");
        System.out.println("══════════════════════════════════════════════");
        System.out.println("Доступные методы:");
        System.out.println("  1. Цикл со сложением");
        System.out.println("  2. Рекурсия (половинное деление)");
        System.out.println("  3. Инкремент (while)");
        System.out.println("  4. Логарифм (без exp)");
        System.out.println("  5. Логарифм по основанию 10 (без exp)");
        System.out.println("══════════════════════════════════════════════");

        // Выбор метода
        System.out.print("\nВыберите метод (1-5): ");
        int method = scanner.nextInt();

        // Ввод чисел
        System.out.print("Введите первое число (a): ");
        int a = scanner.nextInt();
        System.out.print("Введите второе число (b): ");
        int b = scanner.nextInt();

        // Проверка на отрицательные для логарифмов
        if ((method == 4 || method == 5) && (a == 0 || b == 0)) {
            System.out.println("⚠️  Результат: 0");
            scanner.close();
            return;
        }

        System.out.println("\n────────────────────────────────────────────");
        System.out.printf("Вычисляем: %d * %d\n\n", a, b);


        long startTime = System.nanoTime();
        int result = 0;
        String methodName = "";

        switch (method) {
            case 1:
                methodName = "Цикл со сложением";
                result = multiplyByLoop(a, b);
                break;
            case 2:
                methodName = "Рекурсия ";
                result = multiplyByRecursion(a, b);
                break;
            case 3:
                methodName = "Инкремент";
                result = multiplyByIncrement(a, b);
                break;
            case 4:
                methodName = "Логарифм  ";
                result = multiplyByLog(a, b);
                break;
            case 5:
                methodName = "Логарифм (log10) ";
                result = multiplyByLog10(a, b);
                break;
            default:
                System.out.println("❌ Неверный метод!");
                scanner.close();
                return;
        }

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000.0;

        System.out.printf("✅ Метод: %s\n", methodName);
        System.out.printf("📊  Результат: %d\n", result);
        System.out.println("────────────────────────────────────────────");

        scanner.close();
    }
}