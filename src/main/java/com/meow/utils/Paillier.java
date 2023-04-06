package com.meow.utils;
import java.io.IOException;

import java.math.*;

import java.util.*;

public class Paillier{
    //选取两个较大的质数p与q，lambda是p-1与q-1的最小公倍数
    private BigInteger p, q, lambda;

    //n是p与q的乘积
    public BigInteger n;

    //n_square = n*n
    public BigInteger n_square;
    private BigInteger g;
    private int bitLength;

    //加密对象
    public Paillier(BigInteger n) {
        this.n = n;
        this.n_square =n.multiply(n);
        g=new BigInteger("2");
        bitLength =32;

    }
    //解密对象，前段由javascript代码实现
    public Paillier(BigInteger n,BigInteger lambda) {
        this.n = n;
        this.n_square =n.multiply(n);
        g=new BigInteger("2");
        bitLength =32;
        this.lambda =lambda;

    }

    public Paillier(int bitLengthVal, int certainty) {
        Key(bitLengthVal, certainty);
    }
    //默认生成公私钥对象
    public Paillier() {
        Key(32, 64);
    }

    public BigInteger getLambda() {
        return lambda;
    }

    public void setLambda(BigInteger lambda) {
        this.lambda = lambda;
    }

    public void Key(int bitLengthVal, int certainty) {
        bitLength = bitLengthVal;
        //随机构造两个大素数，详情参见API，BigInteger的构造方法
        p = new BigInteger(bitLength / 2, certainty, new Random());
        q = new BigInteger(bitLength / 2, certainty, new Random());

        //n=p*q;
        n = p.multiply(q);

        //nsquare=n*n;
        n_square = n.multiply(n);
        g=new BigInteger("2");

        //求p-1与q-1的乘积除于p-1于q-1的最大公约数
        lambda = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE))
                .divide(p.subtract(BigInteger.ONE).gcd(q.subtract(BigInteger.ONE)));

        //检测g是某满足要求
        if (g.modPow(lambda, n_square).subtract(BigInteger.ONE).divide(n).gcd(n).intValue() != 1) {
            System.out.println("g的选取不合适!");
            System.exit(1);
        }
    }


    //随机生成r的加密 由前段js实现
    public BigInteger En(BigInteger m) {
        BigInteger r = new BigInteger(bitLength, new Random());
        System.out.println("r:"+r);
        return g.modPow(m, n_square).multiply(r.modPow(n, n_square)).mod(n_square);
    }

    //解密
    public BigInteger De(BigInteger c) {
        BigInteger u = g.modPow(lambda, n_square).subtract(BigInteger.ONE).divide(n).modInverse(n);
        return c.modPow(lambda, n_square).subtract(BigInteger.ONE).divide(n).multiply(u).mod(n);
    }

//    public static void main(String[] args) throws IOException {
//      //  System.out.println(DigestUtils.md5DigestAsHex("123123".getBytes(StandardCharsets.UTF_8)));
//        System.out.println("生成PK，SK  :默认最大长度为32位，g为2");
//        Paillier paillier = new Paillier(); //默认最大长度为32位，g为2；
//        System.out.println(paillier.n);
//        System.out.println(paillier.getLambda());
//
//
//        System.out.println("创建用于加密的对象");
//        Paillier enpaillier = new Paillier(new BigInteger("1529340503"));
//
//
//        BigInteger en = enpaillier.En(new BigInteger("10"));
//        System.out.println(en);
//        System.out.println("创建用于解密的对象");
//        Paillier depaillier = new Paillier(new BigInteger("1529340503"),new BigInteger("764631084"));
//        BigInteger em1 =new BigInteger("512820633044098394");
//        System.out.println(depaillier.De(em1));
//
//
//    }
}
