package com.fr.decision.authority.data.pack.node;

import java.util.Scanner;

/**
 * Created by windy on 2019/7/17.
 */
public class Fab {

    private static int MAXN;
    private final int mod = 10000;

    class Matrix {
        private int[][] a;

        public Matrix() {
            this.a = new int[2][2];
            a[0][0] = a[0][1] = a[1][1] = 1;
            a[1][0] = 0;
        }

    }

    //(a*b)%mod  矩阵乘法
    private Matrix mul(Matrix a, Matrix b) {
        Matrix ans = new Matrix();
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++)
            {
                ans.a[i][j] = 0;
                for(int k = 0; k < 2; k++){
                    ans.a[i][j] += a.a[i][k] * b.a[k][j];
                }
                ans.a[i][j] %= mod;
            }
        return ans;
    }

    //(a+b)%mod  //矩阵加法
    private Matrix add(Matrix a,Matrix b) {
        int i,j,k;
        Matrix ans = new Matrix();
        for(i = 0; i < 2; i++){
            for(j = 0; j < 2; j++) {
                ans.a[i][j] = a.a[i][j]+b.a[i][j];
                ans.a[i][j] %= mod;
            }
        }
        return ans;
    }

    //(a^n)%mod  //矩阵快速幂
    private Matrix pow(Matrix a,int n) {
        Matrix ans = new Matrix();
        while(n > 0) {
            if (n % 2 != 0){ // n&1
                ans = mul(ans,a);
            }
            n /= 2;
            a = mul(a, a);
        }
        return ans;
    }

    //(a+a^2+a^3....+a^n)%mod// 矩阵的幂和
    private Matrix sum(Matrix a,int n) {
        int m;
        Matrix ans, pre;
        if(n == 1){
            return a;
        }
        m = n / 2;
        pre = sum(a, m);                      //[1,n/2]
        ans = add(pre, mul(pre,pow(a,m)));   //ans=[1,n/2]+a^(n/2)*[1,n/2]
        if((n & 1) != 0){
            ans = add(ans, pow(a, n));//ans=ans+a^n
        }
        return ans;
    }

    private int doFab() {
        Matrix m = new Matrix();
        Matrix mm = pow(m, MAXN - 2);
        return mm
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MAXN = sc.nextInt();
    }
}