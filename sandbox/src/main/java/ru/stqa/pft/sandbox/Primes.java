package ru.stqa.pft.sandbox;

/**
 * Created by Ксюшенька on 11.03.2016.
 */
public class Primes {

    public static boolean isPrime(int n){
        for (int i = 2; i < n; i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimeWhile(int n){
        int i = 2;
        while (i < 2){
            if (n % i == 0 && n % 1 != 0){
                return false;
            }
            i ++;
        }
        return i == n;
    }

    public static boolean isPrime(long n){
        for (long i = 2; i < n; i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }
    public static boolean isPrimeFast(int n){
        int m = (int) Math.sqrt(n);
        for (int i = 2; i < m / 2; i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }
}
