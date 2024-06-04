public class primes {
    static int [] primes = new int[110];
    static int cnt;
    static boolean [] st = new boolean[110];
    static void getprimes(int n) {
        for (int i = 2; i < n; i ++ ) {
            if (!st[i]) primes[cnt ++] = i;
            for (int j = 0; primes[j] <= n / i; j ++ ) {
                st[primes[j] * i] = true;
                if (i % primes[j] == 0) break;
            }
        }
    }
    public static void main(String[] args) {
        getprimes(100);
        for (int i = 0; i < cnt; i ++ ) System.out.printf("%d ", primes[i]);
    }
}
